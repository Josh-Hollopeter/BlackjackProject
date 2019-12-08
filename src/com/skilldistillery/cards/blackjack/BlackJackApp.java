package com.skilldistillery.cards.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.cards.blackjack.BlackJackHand;
import com.skilldistillery.cards.common.Card;
import com.skilldistillery.cards.common.Chip;
import com.skilldistillery.cards.common.ChipPool;
import com.skilldistillery.cards.common.Deck;
import com.skilldistillery.players.ComputerDealer;
import com.skilldistillery.players.HumanPlayer;

public class BlackJackApp {
	private Card faceDown;
	private HumanPlayer player;
	private ComputerDealer dealer;
	private BlackJackHand computerHand = new BlackJackHand();
	private BlackJackHand playerHand = new BlackJackHand();
	private ChipPool chips;
	Scanner input = new Scanner(System.in);
	private boolean winCondition = true;
	private Deck deck = new Deck();
	private List<Chip> winnings;
	

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


	public void computerTurn() {

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
		int bet = 0;
		while (winCondition) {
			if (playerHand.isBust()) {
				System.out.println("You bust, Dealer wins :(");
			}

			else if (playerHand.handSize() == 0) {
				System.out.println("Place bet");
				bet = input.nextInt();
				placeBet(bet);
				playerHand.addCard(deck.dealCard());
				System.out.println("Player " + playerHand.toString());
				System.out.println("Player score " + playerHand.getHandValue());

				playerHand.addCard(deck.dealCard());

				System.out.println("Player " + playerHand.toString());
				System.out.println("Player score " + playerHand.getHandValue());
				computerTurn();
				break;
			}
			System.out.println("Hit or Stay");
			String hit = input.nextLine();
			if (hit.equalsIgnoreCase("Hit")) {
				System.out.println("Place bet");
				bet = input.nextInt();
				placeBet(bet);
				playerHand.addCard(deck.dealCard());
				System.out.println("Player " + playerHand.toString());
				System.out.println("Player score " + playerHand.getHandValue());
				this.winCondition = winLogic();
				continue;
			} else if (hit.equalsIgnoreCase("Stay")) {
				computerTurn();
				break;
			}
		}

	}

	public boolean winLogic() {

		if (computerHand.isBust()) {
			System.out.println("**********************");
			System.out.println("Dealer bust, You win!!");
			return false;
		} else if (playerHand.isBust()) {
			System.out.println("_________________________");
			System.out.println("You bust, Dealer wins :(");
			return false;
		} else if (computerHand.isBlackjack() && !playerHand.isBlackjack()) {
			System.out.println("________________");
			System.out.println("Dealer wins :(");
			return false;
		} else if (computerHand.getHandValue() == playerHand.getHandValue() && computerHand.getHandValue() >= 17) {
			System.out.println("\u25C8\u25C8\u25C8\u25C8\u25C8\u25C8\u25C8\u25C8\u25C8");
			System.out.println("Tie Game ");
			return false;
		} else if (playerHand.isBlackjack() && !computerHand.isBlackjack() && computerHand.getHandValue() >= 17) {
			System.out.println("*******************");
			System.out.println("Blackjack you win!!");
			return false;
		} else if (computerHand.getHandValue() >= 17 && computerHand.getHandValue() > playerHand.getHandValue()) {
			System.out.println("____________");
			System.out.println("Dealer wins");
			return false;
		} else if (computerHand.getHandValue() >= 17 && !(computerHand.getHandValue() > playerHand.getHandValue())) {
			System.out.println("************");
			System.out.println("You win!!!");
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
		winnings = new ArrayList<Chip>();
		chips = new ChipPool();
		player = new HumanPlayer("Billy");
		dealer = new ComputerDealer("Dealer");
		player.setChips(chips.getChips());
		dealer.setChips(chips.getChips());
		while (playAgain.equalsIgnoreCase("yes")) {
			System.out.println("Your Chips");
			player.printChips();
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

	public void addCardPrintScore(BlackJackHand hand) {
		hand.addCard(deck.dealCard());
		System.out.println(hand.toString());
		System.out.println("Player score " + hand.getHandValue());

	}
	public void placeBet(int bet) {
		winnings.add(player.getBet(bet));
		winnings.add(dealer.getBet(bet));
		System.out.println("Computer matches your bet");
		System.out.println("Prize pool" + winnings);
		input.nextLine();
		
	}

}
