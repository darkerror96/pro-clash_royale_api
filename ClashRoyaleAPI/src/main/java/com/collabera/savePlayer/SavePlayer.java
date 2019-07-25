package com.collabera.savePlayer;

import java.util.Formatter;

import org.bson.Document;
import org.springframework.http.ResponseEntity;

import com.collabera.model.Arena;
import com.collabera.model.Clan;
import com.collabera.model.Games;
import com.collabera.model.Player;
import com.collabera.model.Stats;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * @author rutpatel
 *
 */
public class SavePlayer {

	public static void printPlayerDetails(ResponseEntity<String> response) {

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

		Formatter fmt = new Formatter();

		fmt.format("%n %45s #%s %n%n", name, tag);

		fmt.format("%-30s | %-30s | %10s %n", "Current Trophies - " + trophies, "Arena - " + arenaName,
				"Clan Name - " + clanName);

		fmt.format("%-30s | %-30s | %10s %n", "Total Games Played - " + totalGames,
				"Total Donations - " + totalDonations, "Total Cards Found - " + cardsFound);

		fmt.format("%-30s | %-30s | %10s %n", "Total Wins - " + wins, "Total Draws - " + draws,
				"Total Losses - " + losses);

		fmt.format("%-30s | %-30s | %10s %n", "Win % - " + winsPercent, "Draw % - " + drawsPercent,
				"Lose % - " + lossesPercent);

		System.out.println(fmt);

		MongoClient client = new MongoClient("localhost", 27017);
		MongoDatabase mongoDb = client.getDatabase("ClashRoyale");
		MongoCollection<Document> players = mongoDb.getCollection("Player");

		Arena a = new Arena(arenaName);
		Clan c = new Clan(clanName);
		Stats s = new Stats(cardsFound, totalDonations);
		Games g = new Games(totalGames, wins, winsPercent, draws, drawsPercent, losses, lossesPercent);
		Player p = new Player(tag, name, trophies, a, c, s, g);

		players.insertOne(convToMongoDoc(p));

		client.close();

	}

	private static Document convToMongoDoc(Player p) {
		Arena a = p.getArena();
		Clan c = p.getClan();
		Stats s = p.getStats();
		Games g = p.getGames();

		Document aDoc = new Document();
		aDoc.put("name", a.getName());

		Document cDoc = new Document();
		cDoc.put("name", c.getName());

		Document sDoc = new Document();
		sDoc.put("cardsFound", s.getCardsFound());
		sDoc.put("totalDonations", s.getTotalDonations());

		Document gDoc = new Document();
		gDoc.put("total", g.getTotal());
		gDoc.put("wins", g.getWins());
		gDoc.put("winsPercent", g.getWinsPercent());
		gDoc.put("draws", g.getDraws());
		gDoc.put("drawsPercent", g.getDrawsPercent());
		gDoc.put("losses", g.getLosses());
		gDoc.put("lossesPercent", g.getLossesPercent());

		Document pDoc = new Document();
		pDoc.put("tag", p.getTag());
		pDoc.put("name", p.getName());
		pDoc.put("trophies", p.getTrophies());
		pDoc.put("arena", aDoc);
		pDoc.put("clan", cDoc);
		pDoc.put("stats", sDoc);
		pDoc.put("games", gDoc);

		return pDoc;
	}

}
