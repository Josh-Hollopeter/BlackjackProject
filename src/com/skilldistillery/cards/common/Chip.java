package com.skilldistillery.cards.common;

public class Chip {

	private int value;
	private String color;

	public Chip(int i, String string) {
		this.value = i;
		this.color = string;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chip other = (Chip) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Chip [value=" + value + ", color=" + color + "]";
	}

}