package com.skilldistillery.cards.common;

public enum PokerChips {
	
BLUE(10), GREY(15), 
ORANGE(20), BLACK(25);
	
	 private int value;
	 private String color;

	private PokerChips(int value) {
		this.value = value;
	}

	private PokerChips(int value, String color) {
		this.value = value;
		this.color = color;
	}
	public String getColor() {
		return color;
	}
	
	public int getValue() {
		return this.value;
	}
	
}
