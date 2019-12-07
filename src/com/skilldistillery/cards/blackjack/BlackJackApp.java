package com.skilldistillery.cards.blackjack;

import java.util.Scanner;

import com.skilldistillery.cards.blackjack.BlackJackHand;
import com.skilldistillery.cards.common.Card;
import com.skilldistillery.cards.common.Deck;

public class BlackJackApp {
	private Card faceDown;
	BlackJackHand computerHand = new BlackJackHand();
	BlackJackHand playerHand = new BlackJackHand();
	Scanner input = new Scanner(System.in);
	private boolean winCondition = true;
	private Deck deck = new Deck();

	public static void main(String[] args) {
		BlackJackApp app = new BlackJackApp();
		app.run();
	}

	public void run() {

		deck.shuffle();
		newGame();
		System.out.println("Goodbye");
		input.close();
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
				playerTurn();
				break;
			}
			if (computerHand.getHandValue() < 17) {
				computerHand.addCard(deck.dealCard());
				System.out.println("Dealer " + computerHand.toString());
				System.out.println("Dealer score " + computerHand.getHandValue());
				continue;
			}
			this.winCondition = winLogic();
		}
	}

	public void playerTurn() {

		while (winCondition) {
			if (playerHand.isBust()) {
				System.out.println("You bust, Dealer wins :(");
			}

			else if (playerHand.handSize() == 0) {
				playerHand.addCard(deck.dealCard());
				System.out.println("Player " + playerHand.toString());
				System.out.println("Player score " + playerHand.getHandValue());

				playerHand.addCard(deck.dealCard());

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
				this.winCondition = winLogic();
				continue;
			} else if (hit.equalsIgnoreCase("Stay")) {
				computerTurn(playerHand.getHandValue(), deck);
				break;
			}
		}

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
		} else if (playerHand.isBust()) {
			System.out.println("You bust, Dealer wins :(");
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

	public void newGame() {
		String playAgain = "yes";
		while (playAgain.equalsIgnoreCase("yes")) {
			winCondition = true;
			if (deck.deckSize() <= 25) {
				deck = new Deck();
				deck.shuffle();
			}
			playerTurn();
			score();
			computerHand.clear();
			playerHand.clear();
			System.out.println("Another round?");
			playAgain = input.nextLine();

		}

	}
	public void addCardPrintScore(BlackJackHand hand){
		
	}

}
