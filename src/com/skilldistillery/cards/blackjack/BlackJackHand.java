package com.skilldistillery.cards.blackjack;

import com.skilldistillery.cards.common.Card;

public class BlackJackHand extends Hand {

	public BlackJackHand() {

	}
	public BlackJackHand(Card card) {
		super.userHand.add(card);
	}

	public int getHandValue() {
		int cardCount = 0;
		for (Card card : userHand) {
			cardCount += card.getValue();

		}
		return cardCount;
	}

	public boolean isBust() {
		boolean isBust = getHandValue() > 21;
		return isBust;
	}

	public boolean isBlackjack() {
		boolean blackjack = getHandValue() == 21;
		return blackjack;
	}
	public int handSize() {
		return super.userHand.size();
	}
}