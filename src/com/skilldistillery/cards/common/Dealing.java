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

		while (true) {
			System.out.println("Dealer Turn");
			if(computerHand.isBust()) {
				System.out.println("Bust, You win!!");
			}
			if(computerHand.isBlackjack() && !playerHand.isBlackjack()) {
				System.out.println("Dealer wins :(");
				
			}
			if(computerHand.isBlackjack() && playerHand.isBlackjack()) {
				System.out.println("Tie Game :(");
				
			}
			if (computerHand.handSize() == 1) {
				System.out.println("Dealer " + faceDown);
				computerHand.addCard(faceDown);
				System.out.println("Dealer " + computerHand.toString());
				System.out.println("Dealer score " + computerHand.getHandValue());
				break;
			}
			if (computerHand.handSize() == 0) {
				computerHand.addCard(deck.dealCard());
				System.out.println("Dealer " + computerHand.toString());
				System.out.println("Dealer score " + computerHand.getHandValue());
				faceDown = deck.dealCard();
				playerTurn(deck);

			}
			if(computerHand.getHandValue() < 17){
				computerHand.addCard(deck.dealCard());
				System.out.println("Dealer score " + computerHand.getHandValue());
				System.out.println("Dealer " + computerHand.toString());
				continue;
				
			}


			break;
		}
		
	}

	public void playerTurn(Deck deck) {

		int count = 0;
		while (true) {
			if(playerHand.isBust()) {
				System.out.println("Bust, You win!!");
			}
			if(playerHand.isBlackjack() && !computerHand.isBlackjack()) {
				System.out.println("Dealer wins :(");
				
			}
			if(computerHand.isBlackjack() && playerHand.isBlackjack() && computerHand.handSize() > 1) {
				System.out.println("Tie Game :(");
				
			}
			if (playerHand.handSize() == 0) {
				playerHand.addCard(deck.dealCard());
				System.out.println(playerHand.getHandValue());
				System.out.println("Player " + playerHand.toString());
				playerHand.addCard(deck.dealCard());
				

				System.out.println(playerHand.getHandValue());
				System.out.println("Player " + playerHand.toString());
				System.out.println("Player score " + playerHand.getHandValue());
				computerTurn(playerHand.getHandValue(),deck);
				
				break;
			}
			System.out.println("Hit or Stay");
			String hit = input.nextLine();
			if (hit.equalsIgnoreCase("Hit")) {
				playerHand.addCard(deck.dealCard());
				System.out.println("Player " + playerHand.toString());
				System.out.println("Player score " + playerHand.getHandValue());
				continue;

			}
			if (hit.equalsIgnoreCase("Stay")) {
				computerTurn(playerHand.getHandValue(),deck);
				break;
			}

			if (count > 21) {
				System.out.println("Computer Wins");
				break;
			}
			computerTurn(playerHand.getHandValue(),deck);
			break;
		}
		
		input.close();

	}

}
