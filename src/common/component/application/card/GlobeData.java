package common.component.application.card;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import common.component.application.card.entity.Card;

public class GlobeData {
	private static Map<String, List<Card>> CARD = new Hashtable<String, List<Card>>();

	public static List<Card> getCard(String key) {
		return CARD.get(key);
	}

	public static void setCard(String key, List<Card> value) {
		CARD.put(key, value);
	}
	
	
}
