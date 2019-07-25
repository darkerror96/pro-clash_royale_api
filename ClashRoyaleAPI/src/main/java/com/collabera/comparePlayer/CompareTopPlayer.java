/**
 * 
 */
package com.collabera.comparePlayer;

import java.util.Formatter;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.springframework.http.ResponseEntity;

import com.collabera.model.Player;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author rutpatel
 *
 */
public class CompareTopPlayer {

	static Map<Integer, Player> topPlayersRanking = new TreeMap<>();

	public static void compareTopPlayerDetails(ResponseEntity<String> response) {

		JsonArray jsonArr = (JsonArray) new JsonParser().parse(response.getBody());
		for (int i = 0; i < 10; i++) {

			JsonObject jsonObj = jsonArr.get(i).getAsJsonObject();

			String tag = jsonObj.get("tag").toString().replaceAll("\"", "");
			String name = jsonObj.get("name").toString().replaceAll("\"", "");
			int rank = Integer.parseInt(jsonObj.get("rank").toString().replaceAll("\"", ""));
			int trophies = Integer.parseInt(jsonObj.get("trophies").toString().replaceAll("\"", ""));

			Player p = new Player(tag, name, trophies);

			topPlayersRanking.put(rank, p);

		}

	}

	public static void printPlayerRank() {

		for (Entry<Integer, Player> temp : topPlayersRanking.entrySet()) {
			Player tempPlayer = topPlayersRanking.get(temp.getKey());

			Formatter fmt = new Formatter();

			fmt.format("%-10s | %-20s %s", "#" + tempPlayer.getTag(), tempPlayer.getName(),
					"= " + temp.getKey() + " (" + tempPlayer.getTrophies() + ")");

			System.out.println(fmt);
		}
	}

}
