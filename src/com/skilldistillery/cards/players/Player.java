package com.skilldistillery.cards.players;

import java.util.Comparator;
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
			if (count % 4 == 0) {
				System.out.println();
			}

		}
	}

	public Chip getBet(int bet) {
		if (chips.size() > 0 && bet > 0) {
			int count = 0;
			Comparator<Chip> comp = new ChipComparator();
			chips.sort(comp);
			Chip previous = chips.get(0);
			if (chips.size() == 1) {
				return chips.remove(0);
			}
			if (chips.size() > 1) {
				previous = chips.get(1);
			} else if (chips.size() == 0) {
				return null;
			}
			for (Chip chip : chips) {
				if (chip.getValue() == bet) {
					return chips.remove(chips.indexOf(chip));
				}
				count++;
			}
			if (count >= chips.size()) {
				for (Chip chip : chips) {
					if (chip.getValue() > bet) {
						int value = chip.getValue() - bet;
						Chip remainder = new Chip(value, "HOUSE YELLOW");
						this.chips.add(remainder);
						return chips.remove(chips.indexOf(chip));
					} else if (chip.getValue() + previous.getValue() >= bet) {
						int value1 = previous.getValue();
						int value2 = chip.getValue();
						chips.remove(chip);
						chips.remove(previous);
						chip.setValue(bet);
						chip.setColor("HOUSE RED");
						Chip remainder = new Chip(Math.abs(value1 + value2 - bet), "HOUSE YELLOW");
						if (remainder.getValue() > 0 && remainder.getValue() > (bet - chip.getValue())) {
							this.chips.add(remainder);
						}
						return chip;
					}
					previous = chip;
				}
			}
		}else if(bet == 0) {
			System.out.println("Nice try");
			return null;
		}
		System.out.println(this.name + " No more chips of that size");
		return null;
	}

	class ChipComparator implements Comparator<Chip> {

		@Override
		public int compare(Chip chip1, Chip chip2) {
			if (chip1.getValue() > chip2.getValue()) {
				return 1;
			} else if (chip1.getValue() < chip2.getValue()) {
				return -1;
			} else {
				return 0;
			}
		}

	}

	public void addChips(List<Chip> winnings) {
		this.chips.addAll(winnings);
	}

}
