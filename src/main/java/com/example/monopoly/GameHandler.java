package com.example.monopoly;

import javafx.event.ActionEvent;

public class GameHandler {
	public void initializeCardInfo() {
		int id = 0;
		for(int i = 11; i > 0; i--) {
			for(int j = 11; j > 0; j--) {
				new CardInfo("x", 10, id, id, j, i);
				id++;
			}
		}
	}
	
	
	
	

	public int randDice() {
		System.out.println("Xxxx");
		
		return 1;
	}
}
