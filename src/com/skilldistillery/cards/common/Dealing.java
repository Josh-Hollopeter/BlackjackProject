package com.skilldistillery.cards.common;

import java.util.Scanner;

import com.skilldistillery.cards.blackjack.BlackJackHand;
import com.skilldistillery.cards.common.Card;

public class Dealing {
	private int turn = 0;
	private Card faceDown;
	BlackJackHand computerHand = new BlackJackHand();
	BlackJackHand playerHand = new BlackJackHand();
	Scanner input = new Scanner(System.in);
	private boolean winCondition = true;

	public static void main(String[] args) {
		Dealing app = new Dealing();
		app.run();
	}

	public void run() {

		Deck deck = new Deck();
		deck.shuffle();
		playerTurn(deck);

	}

	public void computerTurn(int playerScore, Deck deck) {
		
		while (winCondition) {
			if (computerHand.handSize() == 1) {
				System.out.println("Dealer Turn");
				System.out.println("Dealer " + faceDown);
				computerHand.addCard(faceDown);
				System.out.println("Dealer " + computerHand.toString());
				System.out.println("Dealer score " + computerHand.getHandValue());
				
			} else if (computerHand.handSize() == 0) {
				System.out.println("Dealer Turn");
				computerHand.addCard(deck.dealCard());
				System.out.println("Dealer " + computerHand.toString());
				System.out.println("Dealer score " + computerHand.getHandValue());
				faceDown = deck.dealCard();
				playerTurn(deck);
				break;

			}

			if (computerHand.getHandValue() < 17) {
				computerHand.addCard(deck.dealCard());
				System.out.println("Dealer " + computerHand.toString());
				System.out.println("Dealer score " + computerHand.getHandValue());
				continue;
			}
//			if(!winLogic()) {
//				break;
//			}
//			break;
			this.winCondition = winLogic();
		}
	}

	public void playerTurn(Deck deck) {

		while (winCondition) {
			if (playerHand.isBust()) {
				System.out.println("You bust, Dealer wins :(");
			} else if (playerHand.handSize() == 0) {
				playerHand.addCard(deck.dealCard());
				System.out.println(playerHand.getHandValue());
				System.out.println("Player " + playerHand.toString());
				playerHand.addCard(deck.dealCard());

				System.out.println(playerHand.getHandValue());
				System.out.println("Player " + playerHand.toString());
				System.out.println("Player score " + playerHand.getHandValue());
				computerTurn(playerHand.getHandValue(), deck);

				break;
			}

			System.out.println("Hit or Stay");
			String hit = input.nextLine();
			if (hit.equalsIgnoreCase("Hit")) {
				playerHand.addCard(deck.dealCard());
				System.out.println("Player " + playerHand.toString());
				System.out.println("Player score " + playerHand.getHandValue());
				continue;

			} else if (hit.equalsIgnoreCase("Stay")) {
				computerTurn(playerHand.getHandValue(), deck);
				break;
			}

//			computerTurn(playerHand.getHandValue(), deck);
//			break;

		}
		input.close();

	}

	public boolean winLogic() {

		if (computerHand.isBust()) {
			System.out.println("Dealer bust, You win!!");
			return false;
		} else if (computerHand.isBlackjack() && !playerHand.isBlackjack()) {
			System.out.println("Dealer wins :(");
			return false;

		} else if (computerHand.getHandValue() == playerHand.getHandValue() && computerHand.getHandValue() >= 17) {
			System.out.println("Tie Game ");
			return false;

		} else if (playerHand.isBlackjack() && !computerHand.isBlackjack() && computerHand.getHandValue() >= 17) {
			System.out.println("Blackjack you win!!");
			return false;
		} else if (computerHand.getHandValue() >= 17 && computerHand.getHandValue() > playerHand.getHandValue()) {
			System.out.println("Dealer wins");
			return false;
		} else if (computerHand.getHandValue() >= 17 && !(computerHand.getHandValue() > playerHand.getHandValue())) {
			System.out.println("You win!!!");
			return false;
		}

		return true;

	}

}
