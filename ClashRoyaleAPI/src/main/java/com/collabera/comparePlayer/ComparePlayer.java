package com.collabera.comparePlayer;

import java.util.Formatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.http.ResponseEntity;

import com.collabera.model.Arena;
import com.collabera.model.Clan;
import com.collabera.model.Games;
import com.collabera.model.Player;
import com.collabera.model.Stats;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author rutpatel
 *
 */
public class ComparePlayer {

	static Map<String, Player> comparePlayers = new HashMap<>();
	static Map<String, Integer> playersRanking = new TreeMap<>();

	public Player comparePlayerDetails(ResponseEntity<String> response) {

		JsonObject json = (JsonObject) new JsonParser().parse(response.getBody());
		JsonObject arena = (JsonObject) json.get("arena");
		JsonObject clan = (JsonObject) json.get("clan");
		JsonObject stats = (JsonObject) json.get("stats");
		JsonObject games = (JsonObject) json.get("games");

		String tag = json.get("tag").toString().replaceAll("\"", "");
		String name = json.get("name").toString().replaceAll("\"", "");
		int trophies = Integer.parseInt(json.get("trophies").toString().replaceAll("\"", ""));
		String arenaName = arena.get("name").toString().replaceAll("\"", "");
		String clanName = clan.get("name").toString().replaceAll("\"", "");

		int totalGames = Integer.parseInt(games.get("total").toString().replaceAll("\"", ""));
		int totalDonations = Integer.parseInt(stats.get("totalDonations").toString().replaceAll("\"", ""));
		int cardsFound = Integer.parseInt(stats.get("cardsFound").toString().replaceAll("\"", ""));

		int wins = Integer.parseInt(games.get("wins").toString().replaceAll("\"", ""));
		float winsPercent = Float.parseFloat(games.get("winsPercent").toString().replaceAll("\"", ""));
		int draws = Integer.parseInt(games.get("draws").toString().replaceAll("\"", ""));
		float drawsPercent = Float.parseFloat(games.get("drawsPercent").toString().replaceAll("\"", ""));
		int losses = Integer.parseInt(games.get("losses").toString().replaceAll("\"", ""));
		float lossesPercent = Float.parseFloat(games.get("lossesPercent").toString().replaceAll("\"", ""));

		Arena a = new Arena(arenaName);
		Clan c = new Clan(clanName);
		Stats s = new Stats(cardsFound, totalDonations);
		Games g = new Games(totalGames, wins, winsPercent, draws, drawsPercent, losses, lossesPercent);
		Player newPlayer = new Player(tag, name, trophies, a, c, s, g);

		if (comparePlayers.containsKey(tag)) {

			Player existingPlayer = comparePlayers.get(tag);

			int oldDiff = existingPlayer.getGames().getWins() + existingPlayer.getGames().getDraws()
					- existingPlayer.getGames().getLosses();
			int newDiff = newPlayer.getGames().getWins() + newPlayer.getGames().getDraws()
					- newPlayer.getGames().getLosses();

			int diff = newDiff - oldDiff;
			playersRanking.put(tag, diff);

		} else {
			comparePlayers.put(tag, newPlayer);
		}

		return newPlayer;
	}

	public List<Player> comparePlayersDetails(ResponseEntity<String> response) {

		List<Player> playersList = new ArrayList<>();

		JsonArray jsonArr = (JsonArray) new JsonParser().parse(response.getBody());
		jsonArr.forEach(temp -> {

			JsonObject json = temp.getAsJsonObject();

			JsonObject arena = (JsonObject) json.get("arena");
			JsonObject clan = (JsonObject) json.get("clan");
			JsonObject stats = (JsonObject) json.get("stats");
			JsonObject games = (JsonObject) json.get("games");

			String tag = json.get("tag").toString().replaceAll("\"", "");
			String name = json.get("name").toString().replaceAll("\"", "");
			int trophies = Integer.parseInt(json.get("trophies").toString().replaceAll("\"", ""));
			String arenaName = arena.get("name").toString().replaceAll("\"", "");
			String clanName = clan.get("name").toString().replaceAll("\"", "");

			int totalGames = Integer.parseInt(games.get("total").toString().replaceAll("\"", ""));
			int totalDonations = Integer.parseInt(stats.get("totalDonations").toString().replaceAll("\"", ""));
			int cardsFound = Integer.parseInt(stats.get("cardsFound").toString().replaceAll("\"", ""));

			int wins = Integer.parseInt(games.get("wins").toString().replaceAll("\"", ""));
			float winsPercent = Float.parseFloat(games.get("winsPercent").toString().replaceAll("\"", ""));
			int draws = Integer.parseInt(games.get("draws").toString().replaceAll("\"", ""));
			float drawsPercent = Float.parseFloat(games.get("drawsPercent").toString().replaceAll("\"", ""));
			int losses = Integer.parseInt(games.get("losses").toString().replaceAll("\"", ""));
			float lossesPercent = Float.parseFloat(games.get("lossesPercent").toString().replaceAll("\"", ""));

			Arena a = new Arena(arenaName);
			Clan c = new Clan(clanName);
			Stats s = new Stats(cardsFound, totalDonations);
			Games g = new Games(totalGames, wins, winsPercent, draws, drawsPercent, losses, lossesPercent);
			Player newPlayer = new Player(tag, name, trophies, a, c, s, g);

			if (comparePlayers.containsKey(tag)) {

				Player existingPlayer = comparePlayers.get(tag);

				int oldDiff = existingPlayer.getGames().getWins() + existingPlayer.getGames().getDraws()
						- existingPlayer.getGames().getLosses();
				int newDiff = newPlayer.getGames().getWins() + newPlayer.getGames().getDraws()
						- newPlayer.getGames().getLosses();

				int diff = newDiff - oldDiff;
				playersRanking.put(tag, diff);

			} else {
				comparePlayers.put(tag, newPlayer);
			}

			playersList.add(newPlayer);
		});

		return playersList;
	}

	public void printPlayerRank() {

		Map<String, Integer> sorted = playersRanking.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));

		int i = 1;
		for (Entry<String, Integer> temp : sorted.entrySet()) {
			Player tempPlayer = comparePlayers.get(temp.getKey());

			Formatter fmt = new Formatter();

			fmt.format("%-10s | %-10s %s", "#" + tempPlayer.getTag(), "   " + tempPlayer.getName(),
					" =    " + i++ + "     :  " + temp.getValue());

			System.out.println(fmt);
		}
	}

}
