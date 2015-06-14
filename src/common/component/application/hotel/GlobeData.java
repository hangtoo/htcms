package common.component.application.hotel;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import common.component.application.hotel.entity.Hotel;

public class GlobeData {
	private static Map<String, List<Hotel>> HOTEL = new Hashtable<String, List<Hotel>>();

	public static List<Hotel> getHotel(String key) {
		return HOTEL.get(key);
	}

	public static void setHotel(String key, List<Hotel> value) {
		HOTEL.put(key, value);
	}
	
	
}
