import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MostAppropriateFacility {
	
//	private String origin;
//	private String[] destinationList;
	
	public static void main(String[] args) {
		MostAppropriateFacility m = new MostAppropriateFacility();
		SqlConn conn = new SqlConn();
		String customerCityandState;
		customerCityandState = conn.searchForCustomerState("002");
		int indexDelimiter = customerCityandState.indexOf('_');
		String customerState = customerCityandState.substring(indexDelimiter + 1);
		StringBuffer appendedCities = new StringBuffer();
		for (String string : conn.searchForCities(customerState)) {
			appendedCities.append(string+"+");
		}
		System.out.println(customerState);
		appendedCities.substring(0, appendedCities.length() - 1);
		customerCityandState = customerCityandState.replace('_', '+');
		customerCityandState = customerCityandState.replace(' ', '+');
		String finalAppend = appendedCities.toString().replace(' ', '+');
		m.find(customerCityandState, finalAppend);
	}

	public void find(String customerCityandState, String appendedCities) {
		// AIzaSyB0qybrurG3RtvAhZE46Rp32PJWOTByYTQ
		try {
			URL url = new URL(
					"https://maps.googleapis.com/maps/api/distancematrix/json?origins="+customerCityandState+"&destinations="+appendedCities+"&departure_time=1541202457&traffic_model=best_guess&key=AIzaSyC09H6k_QN5Sb_MsvWfp7rNlnxDJ8cUXcQ");
			System.out.println(url.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			String str = "";
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				str = str + output;
			}
			conn.disconnect();
			JSONParser parser = new JSONParser();
			org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) parser.parse(str);
			RuleEngine re = new RuleEngine();
			Dest nearestCopart = re.applyRule(jsonObject);
			System.out.println(nearestCopart.getAddress());

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
