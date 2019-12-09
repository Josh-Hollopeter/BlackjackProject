package com.skilldistillery.cards.blackjack;

import java.util.Scanner;

import com.skilldistillery.cards.blackjack.BlackJackHand;
import com.skilldistillery.cards.common.Card;
import com.skilldistillery.cards.common.Deck;

public class BlackJackAppMVP {
	private Card faceDown;
	BlackJackHand computerHand = new BlackJackHand();
	BlackJackHand playerHand = new BlackJackHand();
	Scanner input = new Scanner(System.in);
	private boolean winCondition = true;
	private int autoWin = 0;
	private int messageCount = 0;

	public static void main(String[] args) {
		BlackJackAppMVP app = new BlackJackAppMVP();
		app.run();
	}

	public void run() {
		
		Deck deck = new Deck();
//		deck.printShuffledDeck(); this will print shuffled deck
		deck.shuffle();
		playerTurn(deck);
		score();
		System.out.println("Goodbye");
		input.close();
	}

	public void computerTurn(int playerScore, Deck deck) {

		while (winCondition) {
			if (computerHand.handSize() == 1 && winCondition) {
				System.out.println("Dealer Turn");
				computerHand.addCard(faceDown);
				System.out.println("Dealer " + computerHand.toString());
				System.out.println("Dealer score " + computerHand.getHandValue());
			}
			if (computerHand.handSize() == 0 && winCondition) {
				System.out.println("Dealer Turn one facedown one faceup he checks to see if they equal 21");
				faceDown = deck.dealCard();
				computerHand.addCard(faceDown);
				autoWin += faceDown.getValue();
				faceDown = deck.dealCard();
				autoWin += faceDown.getValue();
				this.winCondition = winLogic();
				System.out.println("Dealer " + computerHand.toString());
				System.out.println("Dealer score " + computerHand.getHandValue());
				break;
			}
			if (computerHand.getHandValue() < 17 && computerHand.handSize() > 1) {
				computerHand.addCard(deck.dealCard());
				System.out.println("Dealer " + computerHand.toString());
				System.out.println("Dealer score " + computerHand.getHandValue());
				this.winCondition = winLogic();
				continue;
			}

			this.winCondition = winLogic();

		}
	}

	public void playerTurn(Deck deck) {
		this.winCondition = winLogic();
		String hit = "";
		while (winCondition) {

			if (playerHand.handSize() == 0) {
				playerHand.addCard(deck.dealCard());
				System.out.println("Player " + playerHand.toString());
				System.out.println("Player score " + playerHand.getHandValue());
				computerTurn(playerHand.getHandValue(), deck);
				if (this.winCondition) {
					playerHand.addCard(deck.dealCard());
					System.out.println("Player " + playerHand.toString());
					System.out.println("Player score " + playerHand.getHandValue());
					continue;
				}
			}
			this.winCondition = winLogic();
			if (this.winCondition) {
				System.out.println("Hit or Stay");
				hit = input.nextLine();
			}
			if (hit.equalsIgnoreCase("Hit")) {
				playerHand.addCard(deck.dealCard());
				System.out.println("Player " + playerHand.toString());
				System.out.println("Player score " + playerHand.getHandValue());
				this.winCondition = winLogic();
				continue;
			} else if (hit.equalsIgnoreCase("Stay")) {
				computerTurn(playerHand.getHandValue(), deck);
				break;
			}
			this.winCondition = winLogic();

		}

	}

	public boolean winLogic() {
		if (messageCount >= 1) {
			return false;
		}

		if (autoWin == 21) {
			computerHand.addCard(faceDown);
			System.out.println("Dealer wins :(");
			messageCount++;
			return false;
		}
		if (computerHand.isBust()) {
			System.out.println("Dealer bust, You win!!");
			messageCount++;
			return false;
		} else if (playerHand.isBust()) {
			System.out.println("You bust, Dealer wins :(");
			messageCount++;
			return false;
		} else if (computerHand.isBlackjack()) {
			System.out.println("Dealer wins :(");
			messageCount++;
			return false;
		} else if (playerHand.isBlackjack()) {
			System.out.println("Blackjack you win!!");
			messageCount++;
			return false;
		} else if (computerHand.getHandValue() == playerHand.getHandValue() && computerHand.getHandValue() >= 17) {
			System.out.println("Tie Game ");
			messageCount++;
			return false;

		} else if (computerHand.getHandValue() >= 17 && computerHand.getHandValue() > playerHand.getHandValue()) {
			System.out.println("Dealer wins");
			messageCount++;
			return false;
		} else if (computerHand.getHandValue() >= 17 && !(computerHand.getHandValue() > playerHand.getHandValue())) {
			System.out.println("You win!!!");
			messageCount++;
			return false;
		}
		return true;
	}

	public void score() {
		System.out.println("Your final " + playerHand.toString());
		System.out.println("Dealer final " + computerHand.toString());
		System.out.println(
				"Final [Dealer:" + computerHand.getHandValue() + "] [Player: " + playerHand.getHandValue() + "]");
	}

}