package com.example.modernartui1;

import android.graphics.Color;

public class ColorComponent {

	private String[] colors;

	public ColorComponent(String[] colors){
		this.colors = colors;
	}

	public int getColor(int sliderValue) {
		return Color.parseColor(colors[sliderValue]);
	}
}