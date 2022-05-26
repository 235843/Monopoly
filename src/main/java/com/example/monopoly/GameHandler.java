package com.example.monopoly;

import java.util.ArrayList;
import java.util.Random;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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
			cards.add(new CardInfo("x", 10, id, id, i, y, getNodeByRowColumnIndex(i, y, id)));
			id++;
		}
		x = 1;
		y--;
		for(int i = y; i > 0; i--) {
			cards.add(new CardInfo("x", 10, id, id, x, i, getNodeByRowColumnIndex(x, i, id)));
			id++;
		}
		y = 1;
		x = 2;
		for(int i = x; i < 12; i++) {
			cards.add(new CardInfo("x", 10, id, id, i, y, getNodeByRowColumnIndex(i, y, id)));
			id++;
		}
		x = 11;
		y = 2;
		for(int i = y; i < 11; i++) {
			cards.add(new CardInfo("x", 10, id, id, x, i, getNodeByRowColumnIndex(x, i, id)));
			id++;
		}
		players.add(new PlayerInfo(0, 0));
		gridpane.add(players.get(0).pawn, 11, 11);
		players.get(0).pawn.setCenterX(5);
		
		for(int i = 0; i < id; i++) {
			System.out.println(cards.get(i).anchorPane.getChildren());
		}
		
	}
	
	public AnchorPane getNodeByRowColumnIndex (final int row, final int column, final Integer id) {
		AnchorPane result = null;
	    ObservableList<Node> childrens = gridpane.getChildren();

	    for (Node node : childrens) {
	        if(node instanceof AnchorPane && GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
	            result = (AnchorPane)node;
	            result.setId(id.toString());
	            break;
	        }
	        
	    }

	    return result;
	}
	
	
	

	public void randDice() {
		Random rand = new Random();
		int x = rand.nextInt(6);
		x += 1;
		players.get(0).changePosition(x, cards);
	}
	
	
}
