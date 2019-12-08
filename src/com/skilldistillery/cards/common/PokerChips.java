package com.skilldistillery.cards.common;

public enum PokerChips {
	
BLUE(10), GREY(20), 
ORANGE(50), BLACK(100);
	
	private PokerChips(int value, String color) {
	this.value = value;
	this.color = color;
}
	private int value;
	private String color;

	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	private PokerChips (int value) {
		this.value = value;
	}
	public int getValue() {
		return this.value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
