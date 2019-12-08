package com.skilldistillery.cards.common;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand {
	
	private List<Card> userHand = new ArrayList<Card>();

	public Hand() {
	}
	
	public void addCard(Card card){
		this.getUserHand().add(card);
	}
	
	public void clear() {
		getUserHand().removeAll(getUserHand());
	}
	public abstract int getHandValue();
	

	@Override
	public String toString() {
		return "Hand=" + getUserHand();
	}

	public List<Card> getUserHand() {
		return userHand;
	}

	public void setUserHand(List<Card> userHand) {
		this.userHand = userHand;
	}

}
