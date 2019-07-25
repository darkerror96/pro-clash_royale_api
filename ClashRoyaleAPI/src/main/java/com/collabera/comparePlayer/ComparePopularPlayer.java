package com.collabera.comparePlayer;

import java.util.Formatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.http.ResponseEntity;

import com.collabera.model.Player;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static java.util.stream.Collectors.*;

import java.util.Collections;

/**
 * @author rutpatel
 *
 */
public class ComparePopularPlayer {

	static Map<String, Player> comparePopularPlayers = new HashMap<>();
	static Map<String, Integer> popularPlayersRanking = new TreeMap<>();

	public static void comparePopularPlayerDetails(ResponseEntity<String> response) {

		JsonArray jsonArr = (JsonArray) new JsonParser().parse(response.getBody());
		jsonArr.forEach(jsonTemp -> {

			JsonObject jsonObj = jsonTemp.getAsJsonObject();

			JsonObject popularity = (JsonObject) jsonObj.get("popularity");

			String tag = jsonObj.get("tag").toString().replaceAll("\"", "");
			String name = jsonObj.get("name").toString().replaceAll("\"", "");

			float hits = Float.parseFloat(popularity.get("hitsPerDayAvg").toString().replaceAll("\"", ""));

			Player newPlayer = new Player(tag, name, hits);

			if (comparePopularPlayers.containsKey(tag)) {
				popularPlayersRanking.put(tag, (int) newPlayer.getHits());
			} else {
				comparePopularPlayers.put(tag, newPlayer);
			}

		});

	}

	public static void printPlayerRank() {

		Map<String, Integer> sorted = popularPlayersRanking.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));

		int i = 1;
		for (Entry<String, Integer> temp : sorted.entrySet()) {
			Player tempPlayer = comparePopularPlayers.get(temp.getKey());

			Formatter fmt = new Formatter();

			fmt.format("%-10s | %-20s %s", "#" + tempPlayer.getTag(), tempPlayer.getName(),
					"= " + i++ + " (" + tempPlayer.getHits() + ")");

			System.out.println(fmt);
		}
	}

}
