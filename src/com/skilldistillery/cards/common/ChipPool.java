package com.skilldistillery.cards.common;

import java.util.ArrayList;
import java.util.List;

public class ChipPool {
	List<Chip> chips;

	public ChipPool() {
		this.chips = new ArrayList<>(20);
		for (int i = 0; i < 2; i++) {
			for (PokerChips v : PokerChips.values()) {
				chips.add(new Chip(v.getValue(), v.name()));
				{
				}
			}
		}
	}

	public List<Chip> getChips() {
		return chips;
	}

	public void setChips(List<Chip> chips) {
		this.chips = chips;
	}



	@Override
	public String toString() {
		return "ChipPool [chips=" + chips + "]";
	}
	public void printChips() {
		int count = 0;
		for (Chip chip : chips) {
			System.out.print(chip);
			count++;
		if(count %4 ==0) {
			System.out.println();
		}
			
		}
	}
	
}
