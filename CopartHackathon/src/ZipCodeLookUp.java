
//http://www.zipcodeapi.com/rest/<api_key>/info.<format>/<zip_code>/<units>
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

import javax.swing.*;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ZipCodeLookUp{

	private String apiKey = "SH8Be5Im18StbtqknXgm9a9aoJZsukum2CXhHGDJOvd0ZDknBAJKLSV9CVC6g6si";
	private String units = "/degrees";
	private String apiUrl = "http://www.zipcodeapi.com/rest/" + apiKey + "/info.json/";

	public static void main(String[] args) {    
        JFrame frame = new JFrame("Zip to Location");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();    
        frame.add(panel);
        ZipCodeLookUp z = new ZipCodeLookUp();
        z.placeComponents(panel, z);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel, ZipCodeLookUp z) {

        panel.setLayout(null);

        JLabel zipLabel = new JLabel("ZIP Code");
        zipLabel.setBounds(10,20,80,25);
        panel.add(zipLabel);

        JTextField zipText = new JTextField(20);
        zipText.setBounds(100,20,165,25);
        panel.add(zipText);
        
        JTextField res = new JTextField(20);
        res.setEditable(false);
        res.setBounds(100,120,165,25);
        panel.add(res);

        JButton button = new JButton("Get Location");
        button.setBounds(10, 80, 150, 25);
        panel.add(button);
        
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String cityState = z.getLocation(zipText.getText());
				res.setText(cityState);
			}
		});
    }

	public String getLocation(String zip) {
		try {

			URL url = new URL(apiUrl + zip + units);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// conn.setRequestMethod("GET");
			// conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			String str = null;
			while ((output = br.readLine()) != null) {
				str = output;
			}
			conn.disconnect();

			JSONParser parser = new JSONParser();
			org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) parser.parse(str);
			String city = jsonObject.get("city").toString();
			String state = jsonObject.get("state").toString();

			return city + ", " + state;
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


}
