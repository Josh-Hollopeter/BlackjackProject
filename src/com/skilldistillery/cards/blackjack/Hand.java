package com.skilldistillery.cards.blackjack;

import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.cards.common.Card;

public abstract class Hand {
	
	List<Card> userHand = new ArrayList<Card>();

	public Hand() {
	}
	
	public void addCard(Card card){
		this.userHand.add(card);
	}
	
	public void clear() {
		userHand.removeAll(userHand);
	}
	public abstract int getHandValue();
	

	@Override
	public String toString() {
		return "Hand=" + userHand;
	}

}
