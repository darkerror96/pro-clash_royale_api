package com.collabera.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author rutpatel
 *
 */
@Document(value = "Player")
public class Player {

	@Id
	String id;
	String tag;
	String name;
	int trophies;
	float hits;
	Arena arena;
	Clan clan;
	Stats stats;
	Games games;

	public Player() {

	}

	public Player(String tag, String name, int trophies, Arena arena, Clan clan, Stats stats, Games games) {
		this.tag = tag;
		this.name = name;
		this.trophies = trophies;
		this.arena = arena;
		this.clan = clan;
		this.stats = stats;
		this.games = games;
	}

	public Player(String tag, String name, float hits) {
		this.tag = tag;
		this.name = name;
		this.hits = hits;
	}

	public Player(String tag, String name, int trophies) {
		this.tag = tag;
		this.name = name;
		this.trophies = trophies;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTrophies() {
		return trophies;
	}

	public void setTrophies(int trophies) {
		this.trophies = trophies;
	}

	public float getHits() {
		return hits;
	}

	public void setHits(float hits) {
		this.hits = hits;
	}

	public Arena getArena() {
		return arena;
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}

	public Clan getClan() {
		return clan;
	}

	public void setClan(Clan clan) {
		this.clan = clan;
	}

	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

	public Games getGames() {
		return games;
	}

	public void setGames(Games games) {
		this.games = games;
	}

	@Override
	public String toString() {
		return "Player [tag=" + tag + ", name=" + name + ", trophies=" + trophies + ", hits=" + hits + ", arena="
				+ arena + ", clan=" + clan + ", stats=" + stats + ", games=" + games + "]";
	}

}
