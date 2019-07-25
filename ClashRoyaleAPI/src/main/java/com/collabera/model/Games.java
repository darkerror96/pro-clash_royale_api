package com.collabera.model;

/**
 * @author rutpatel
 *
 */
public class Games {

	int total;
	int wins;
	float winsPercent;
	int draws;
	float drawsPercent;
	int losses;
	float lossesPercent;

	public Games() {
	}

	public Games(int total, int wins, float winsPercent, int draws, float drawsPercent, int losses,
			float lossesPercent) {
		this.total = total;
		this.wins = wins;
		this.winsPercent = winsPercent;
		this.draws = draws;
		this.drawsPercent = drawsPercent;
		this.losses = losses;
		this.lossesPercent = lossesPercent;
	}

	public Games(int total, int wins, int draws, int losses) {
		this.total = total;
		this.wins = wins;
		this.draws = draws;
		this.losses = losses;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public float getWinsPercent() {
		return winsPercent;
	}

	public void setWinsPercent(float winsPercent) {
		this.winsPercent = winsPercent;
	}

	public int getDraws() {
		return draws;
	}

	public void setDraws(int draws) {
		this.draws = draws;
	}

	public float getDrawsPercent() {
		return drawsPercent;
	}

	public void setDrawsPercent(float drawsPercent) {
		this.drawsPercent = drawsPercent;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public float getLossesPercent() {
		return lossesPercent;
	}

	public void setLossesPercent(float lossesPercent) {
		this.lossesPercent = lossesPercent;
	}

	@Override
	public String toString() {
		return "Games [total=" + total + ", wins=" + wins + ", winsPercent=" + winsPercent + ", draws=" + draws
				+ ", drawsPercent=" + drawsPercent + ", losses=" + losses + ", lossesPercent=" + lossesPercent + "]";
	}

}
