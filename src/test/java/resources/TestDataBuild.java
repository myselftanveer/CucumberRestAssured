package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlacePojo;
import pojo.Location;

public class TestDataBuild {

	public AddPlacePojo addPlaceApi(String name, String language, String address) {

		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);

		AddPlacePojo add = new AddPlacePojo();
		add.setLocation(loc);
		add.setAccuracy(55);
		add.setName(name);
		add.setPhone_number(1234567890);
		add.setAddress(address);

		List<String> list = new ArrayList<String>();
		list.add("shoe park");
		list.add("shop");
		add.setTypes(list);

		add.setWebsite("http://google.com");
		add.setLanguage(language);
		return add;
	}

	public String deletePlacePayload(String placeID) {
		return "{\r\n" + "     \"place_id\": \""+placeID+"\"\r\n" + " }";
	}

}
