package com.collabera.model;

/**
 * @author rutpatel
 *
 */
public class Stats {

	int cardsFound;
	int totalDonations;

	public Stats() {
	}

	public Stats(int cardsFound, int totalDonations) {
		this.cardsFound = cardsFound;
		this.totalDonations = totalDonations;
	}

	public int getCardsFound() {
		return cardsFound;
	}

	public void setCardsFound(int cardsFound) {
		this.cardsFound = cardsFound;
	}

	public int getTotalDonations() {
		return totalDonations;
	}

	public void setTotalDonations(int totalDonations) {
		this.totalDonations = totalDonations;
	}

	@Override
	public String toString() {
		return "Stats [cardsFound=" + cardsFound + ", totalDonations=" + totalDonations + "]";
	}

}
