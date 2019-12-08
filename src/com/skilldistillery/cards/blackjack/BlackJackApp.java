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
	boolean outOfChips = true;
	private Card faceDown;
	private HumanPlayer player;
	private ComputerDealer dealer;
	private BlackJackHand computerHand = new BlackJackHand();
	private BlackJackHand playerHand = new BlackJackHand();
	private ChipPool chips;
	private Scanner input = new Scanner(System.in);
	private boolean winCondition = true;
	private Deck deck = new Deck();
	private List<Chip> winnings;
	private Chip chip;

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
		System.out.println("Your Chips");
		player.printChips();
		while (winCondition) {
			if (playerHand.isBust()) {
				System.out.println("You bust, Dealer wins :(");
			}

			else if (playerHand.handSize() == 0) {
				System.out.println("Place bet");
				bet = input.nextInt();
				outOfChips = placeBet(bet);
				playerHand.addCard(deck.dealCard());
				System.out.println("Player " + playerHand.toString());
				System.out.println("Player score " + playerHand.getHandValue());

				playerHand.addCard(deck.dealCard());

				System.out.println("Player " + playerHand.toString());
				System.out.println("Player score " + playerHand.getHandValue());
				computerTurn();
				break;
			}
			System.out.println("\nHit or Stay");
			String hit = input.nextLine();
			if (hit.equalsIgnoreCase("Hit")) {
				System.out.println("Place bet");
				bet = input.nextInt();
				outOfChips = placeBet(bet);
				playerHand.addCard(deck.dealCard());
				System.out.println("Player " + playerHand.toString());
				System.out.println("Player score " + playerHand.getHandValue());
				this.winCondition = winLogic();
				continue;
			} else if (hit.equalsIgnoreCase("Stay")) {
				System.out.println("Place bet");
				bet = input.nextInt();
				outOfChips = placeBet(bet);
				computerTurn();
				break;
			}
		}

	}

	public boolean winLogic() {

		if (computerHand.isBust()) {
			System.out.println("**********************");
			System.out.println("Dealer bust, You win!!");
			System.out.println(player.getName() + " You receive " + winnings);
			player.addChips(winnings);
			winnings.removeAll(winnings);
			return false;
		} else if (playerHand.isBust()) {
			System.out.println("_________________________");
			System.out.println("You bust, Dealer wins :(");
			dealer.addChips(winnings);
			winnings.removeAll(winnings);
			return false;
		} else if (!outOfChips && player.getChips().size() < dealer.getChips().size()) {
			System.out.println("Out of chips "+ player.getName() + " is thrown out of the casino");
			return false;
		} else if (!outOfChips && player.getChips().size() >= dealer.getChips().size()) {
			System.out.println("\u25C8\u25C8\u25C8\u25C8\u25C8\u25C8\u25C8\u25C8\u25C8");
			System.out.println("Congratulations you've cleaned out the house!!!");
			System.out.println("You're a world class poker player!!!");
			System.out.println("\u25C8\u25C8\u25C8\u25C8\u25C8\u25C8\u25C8\u25C8\u25C8");
			return false;
		} else if (computerHand.isBlackjack() && !playerHand.isBlackjack()) {
			System.out.println("________________");
			System.out.println("Dealer wins :(");
			dealer.addChips(winnings);
			winnings.removeAll(winnings);
			return false;
		} else if (computerHand.getHandValue() == playerHand.getHandValue() && computerHand.getHandValue() >= 17) {
			System.out.println("\u25C8\u25C8\u25C8\u25C8\u25C8\u25C8\u25C8\u25C8\u25C8");
			System.out.println("Tie Game Chips stay in prize pool");
			return false;
		} else if (playerHand.isBlackjack() && !computerHand.isBlackjack() && computerHand.getHandValue() >= 17) {
			System.out.println("*******************");
			System.out.println(player.getName() + " Blackjack you win!!");
			System.out.println("You receive " + winnings);
			player.addChips(winnings);
			winnings.removeAll(winnings);
			return false;
		} else if (computerHand.getHandValue() >= 17 && computerHand.getHandValue() > playerHand.getHandValue()) {
			System.out.println("____________");
			System.out.println("Dealer wins");
			dealer.addChips(winnings);
			winnings.removeAll(winnings);
			return false;
		} else if (computerHand.getHandValue() >= 17 && !(computerHand.getHandValue() > playerHand.getHandValue())) {
			System.out.println("************");
			System.out.println(player.getName() + " You win!!!");
			System.out.println("You receive " + winnings);
			player.addChips(winnings);
			winnings.removeAll(winnings);
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
		System.out.println("Enter your name");
		String name = input.nextLine();
		winnings = new ArrayList<Chip>();
		chips = new ChipPool();
		player = new HumanPlayer(name);
		dealer = new ComputerDealer("Dealer");
		player.setChips(chips.getChips());
		chips = new ChipPool();
		dealer.setChips(chips.getChips());
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

	public void addCardPrintScore(BlackJackHand hand) {
		hand.addCard(deck.dealCard());
		System.out.println(hand.toString());
		System.out.println("Player score " + hand.getHandValue());

	}

	public boolean placeBet(int bet) {
		chip = player.getBet(bet);
		player.printChips();
		if (chip != null) {
			winnings.add(chip);
		} else {
			return false;
		}
		chip = dealer.getBet(bet);
		if (chip != null) {
			winnings.add(chip);
		} else {
			return false;
		}
		System.out.println("dealer");
		System.out.println("Dealer matches your bet");
		System.out.println("Prize pool" + winnings);
		input.nextLine();
		return true;
	}

}
