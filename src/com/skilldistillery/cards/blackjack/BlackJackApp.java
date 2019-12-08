package com.skilldistillery.cards.blackjack;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
	private int noChips = 0;
	boolean hasChips = true;
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
	private int bet = 0;

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

		while (winCondition && hasChips) {
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

		while (winCondition && hasChips) {
			if (playerHand.handSize() == 0) {
				bet = bet();
				hasChips = placeBet(bet);
				addCardPrintScore(playerHand);
				addCardPrintScore(playerHand);
				computerTurn();
				break;
			}

			System.out.println("\nHit or Stay");
			String hit = input.nextLine();
			if (hit.equalsIgnoreCase("Hit")) {
				bet = bet();
				hasChips = placeBet(bet);
				addCardPrintScore(playerHand);
				this.winCondition = winLogic();
				continue;
			} else if (hit.equalsIgnoreCase("Stay")) {
				bet = bet();
				hasChips = placeBet(bet);
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
		} else if (!hasChips && player.getChips().size() < dealer.getChips().size() || player.getChips().size() == 0) {
			System.out.println("Out of chips " + player.getName() + " is thrown out of the casino");
			noChips++;
			return false;
		} else if (!hasChips && player.getChips().size() >= dealer.getChips().size() || dealer.getChips().size() == 0) {
			System.out.println("\u25C8\u25C8\u25C8\u25C8\u25C8\u25C8\u25C8\u25C8\u25C8");
			System.out.println("Congratulations you've cleaned out the house!!!");
			System.out.println("You're a world class poker player!!!");
			System.out.println("\u25C8\u25C8\u25C8\u25C8\u25C8\u25C8\u25C8\u25C8\u25C8");
			player.addChips(winnings);
			System.out.println(player.getChips());
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
		chips = new ChipPool(2);
		player = new HumanPlayer(name);
		dealer = new ComputerDealer("Dealer");
		player.setChips(chips.getChips());
		chips = new ChipPool(5);
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
			if (!hasChips && noChips == 0) {
				winLogic();
				break;
			}
			System.out.println("Another round? Yes or no");
			playAgain = input.nextLine();
			continue;

		}

	}

	public void addCardPrintScore(BlackJackHand hand) {
		hand.addCard(deck.dealCard());
		System.out.println("Player " + hand.toString());
		System.out.println("Player score " + hand.getHandValue());

	}

	public int bet() {
		boolean badBet = true;
		while (badBet) {
			System.out.println("\nPlace bet MAX is 25");
			try {
				bet = Integer.parseInt(input.nextLine());
				if (bet <= 25) {
					badBet = false;
				} else {
					continue;
				}
			} catch (NumberFormatException e) {
				System.out.println("Enter a number");
			}
		}
		return bet;
	}

	public boolean placeBet(int bet) {

		chip = player.getBet(bet);
		if (chip != null) {
			System.out.println("Your Chips");
			winnings.add(chip);
			player.printChips();
		} else {
			return false;
		}
		chip = dealer.getBet(bet);
		if (chip != null) {
			winnings.add(chip);
			System.out.println("Dealer matches your bet");
		} else {
			return false;
		}
		System.out.println("Your Chips");

		return true;
	}

}
