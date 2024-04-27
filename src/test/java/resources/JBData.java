package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.JsonBody;
import pojo.Location;

public class JBData {
	public JsonBody bodyData(String name, String language, String address) {
		JsonBody jb=new JsonBody();
		jb.setAccuracy(50);
		jb.setName(name);
		jb.setPhone_number("03335207665");
		jb.setAddress(address);
		jb.setWebsite("http://google.com");
		jb.setLanguage(language);
		List<String> addval=new ArrayList<String>();
		addval.add("Shoe Park");
		addval.add("shop");
		Location l=new Location();
		l.setLat(38.383494);
		l.setLng(33.427362);
		jb.setLocation(l);
		return jb;	
	}
	public String deletePlaceload(String placeid) {
		return "{\r\n    \"place_id\":\""+placeid+"\"\r\n}";	
	}
}
