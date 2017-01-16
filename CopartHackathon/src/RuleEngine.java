import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RuleEngine {

	public Dest applyRule(JSONObject json) {
		String origin = json.get("origin_addresses").toString();
		JSONArray jarr = (JSONArray) json.get("destination_addresses");
		Iterator iter = jarr.iterator();
		Dest[] destList = new Dest[jarr.size()];
		int i = 0;
		while(iter.hasNext()) {
			destList[i] = new Dest(String.valueOf(iter.next()));
			i++;
		}
		JSONObject jobjD = (JSONObject) ((JSONArray) json.get("rows")).get(0);
		JSONArray jarrD = (JSONArray) jobjD.get("elements");
		Iterator iterele = jarrD.iterator();
		i = 0;
		while(iterele.hasNext()) { 
			Dest d = destList[i];
			JSONObject o = (JSONObject) iterele.next();
			d.setDist(Integer.parseInt(((JSONObject)o.get("distance")).get("value").toString()));
			d.setDuration(Integer.parseInt(((JSONObject)o.get("duration")).get("value").toString()));
			d.setDurTraffic(Integer.parseInt(((JSONObject)o.get("duration_in_traffic")).get("value").toString()));
			i++;
		}
		Dest minDistDest = destList[0];
		int minDist = destList[0].getDist();
		for(Dest d : destList) {
			if(d.getDist()<minDist) {
				minDist = d.getDist();
				minDistDest = d;
			}
		}
		return minDistDest;
	}

}
