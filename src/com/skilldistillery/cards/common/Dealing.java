package com.skilldistillery.cards.common;

import java.util.Scanner;

import com.skilldistillery.cards.common.Card;

public class Dealing {
	private static int turn = 0;
	private static Card faceDown;

	public static void main(String[] args) {
		Deck deck = new Deck();
		deck.shuffle();
		Scanner input = new Scanner(System.in);

		int count = 0;
		while (true) {
			if (turn == 0) {
				Card card = deck.dealCard();
				count += card.getValue();
				System.out.println("Player " + card);
				card = deck.dealCard();
				count += card.getValue();
				System.out.println("Player " + card);
				System.out.println("Player score " + count);
				System.out.println("Computer Turn");
				computerTurn(count, deck);
				turn ++;
			}
			System.out.println("Hit or Stay");
			String hit = input.nextLine();
			if (hit.equalsIgnoreCase("Hit")) {
				Card card = deck.dealCard();
				System.out.println("Player " + card);
				count += card.getValue();

			}
			if (count > 21) {
				System.out.println("Computer Wins");
				break;
			}
		}
		System.out.println(count);
		input.close();
	}

	public static void computerTurn(int playerScore, Deck deck) {
		int computerScore = 0;
		while (computerScore < playerScore) {
			if (turn < 2) {
				Card card = deck.dealCard();
				System.out.println("Computer " + card);
				computerScore += card.getValue();
				faceDown = deck.dealCard();
				System.out.println("Computer score " + computerScore);
				break;

			}
			Card card = deck.dealCard();
			System.out.println("Computer " +faceDown);
			computerScore += faceDown.getValue();
			System.out.println("Computer score " + computerScore);
			break;

		}

	}
}
