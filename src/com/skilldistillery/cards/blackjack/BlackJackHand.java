package com.skilldistillery.cards.blackjack;

import com.skilldistillery.cards.common.Card;
import com.skilldistillery.cards.common.Hand;

public class BlackJackHand extends Hand {

	public BlackJackHand() {

	}

	public BlackJackHand(Card card) {
		super.getUserHand().add(card);
	}

	public int getHandValue() {
		int cardCount = 0;
		for (Card card : getUserHand()) {
			cardCount += card.getValue();
		}
		if (cardCount > 21) {
			for (Card card : getUserHand()) {
				if (card.getValue() == 11 && cardCount > 21) {
					cardCount -= 10;
				}
			}
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
		return super.getUserHand().size();
	}

}
