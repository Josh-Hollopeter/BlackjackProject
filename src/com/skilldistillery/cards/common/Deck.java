package com.skilldistillery.cards.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.skilldistillery.cards.common.Card;



public class Deck {
	
List<Card> cardDeck;

 public Deck() {
	 this.cardDeck = new ArrayList<>(52);
	 for(Suit s : Suit.values()) {
	      for(Rank r : Rank.values()) {
	        cardDeck.add(new Card(r, s));
	      }
	    }
	  }
 public void shuffle() {
	 Collections.shuffle(cardDeck);
 }
 public Card dealCard() {
	return cardDeck.remove(0);
 }
 public int deckSize() {
	 return cardDeck.size();
 }
 public void printShuffledDeck() {
		int count = 0;
		this.shuffle();
		for (Card card : this.cardDeck) {
			System.out.print(card);
			count++;
			if (count % 8 == 0) {
				System.out.println();
			}

		}
	}
 public void removeAll() {
	 cardDeck.removeAll(cardDeck);
 }
	 
 }


