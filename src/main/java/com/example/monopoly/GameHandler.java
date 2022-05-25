package com.example.monopoly;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class GameHandler {
	ArrayList<CardInfo> cards = new ArrayList<CardInfo>();
	ArrayList<PlayerInfo> players = new ArrayList<PlayerInfo>();
	@FXML
    private GridPane gridpane;
	@FXML
	public void initialize() {
		int id = 0;
		int x = 11;
		int y = 11;
		for(int i = x; i > 0; i--) {
			cards.add(new CardInfo("x", 10, id, id, i, y));
			id++;
		}
		x = 1;
		y--;
		for(int i = y; i > 0; i--) {
			cards.add(new CardInfo("x", 10, id, id, x, i));
			id++;
		}
		y = 1;
		x = 2;
		for(int i = x; i < 12; i++) {
			cards.add(new CardInfo("x", 10, id, id, i, y));
			id++;
		}
		x = 11;
		y = 2;
		for(int i = y; i < 12; i++) {
			cards.add(new CardInfo("x", 10, id, id, x, i));
			id++;
		}
		//int players_num = 1;
		players.add(new PlayerInfo(0, 0));
		gridpane.add(players.get(0).pawn, 11, 11);
		players.get(0).pawn.setCenterX(5);
		
		
	}
	
	
	
	

	public int randDice() {
		System.out.println("Xxxx");
		
		return 1;
	}
}
