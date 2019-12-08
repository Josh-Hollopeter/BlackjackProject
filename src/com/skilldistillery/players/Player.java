package com.skilldistillery.players;

import java.util.List;

import com.skilldistillery.cards.common.Chip;
import com.skilldistillery.cards.common.Hand;

public abstract class Player {
	private String name;
	private Hand hand;
	private List<Chip> chips;
	
	

	public List<Chip> getChips() {
		return chips;
	}
	public void setChips(List<Chip> chips) {
		this.chips = chips;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Hand getHand() {
		return hand;
	}
	public void setHand(Hand hand) {
		this.hand = hand;
	}
	public void printChips() {
		int count = 0;
		for (Chip chip : chips) {
			System.out.print(chip);
			count++;
		if(count %4 == 0) {
			System.out.println();
		}
			
		}
	}
	public Chip getBet(int bet) {
		if (chips.size() > 0) {
			for (Chip chip : chips) {
				if (chip.getValue() == bet) {
					return chips.remove(chips.indexOf(chip));
				}

			}
		}
		System.out.println("No more chips of that size");
		return null;
	}
	

}
