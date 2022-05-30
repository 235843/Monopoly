package com.example.monopoly;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class GameHandler {
	Integer round = 1;
	ArrayList<CardInfo> cards = new ArrayList<CardInfo>();
	ArrayList<PlayerInfo> players = new ArrayList<PlayerInfo>();
	@FXML
    private GridPane gridpane;
	@FXML
	public void initialize() {
		String[] names = {
				"Start",
				"Katedra Inżynierii Bioprocesowej",
				"Pomoc starszego roku",
				"Katedra Inżynierii Środowiska",
				"Zaległe zajęcia WF",
				"Centrym Sprotu",
				"Katedra Zarządzania",
				"Mail z dziekanatu",
				"Instytut Nauk Społecznych i Zarządzania Technologiami",
				"Katedra Zarządzania Produkcją i Logistyki",
				"Poprawka",
				"Żabka",
				"Katedra Mechaniki Materiałów",
				"Instytut Architektury i Urbanistyki",
				"Instytut Inżynierii Środowiska i Instalacji Budowlanych",
				"Centrym Matematyki i Fizyki",
				"Instytut Technologii i Analizy Żywności",
				"Pomoc starszego roku",
				"Katedra Biotechnologii Środowiskowej",
				"Instytut Surowców Naturalnych i Kosmetyków",
				"Biblioteka",
				"Instytut Architektury Tekstyliów",
				"Mail z dziekanatu",
				"Instytut Materiałoznawstwa Tekstyliów i Kompozytów Polimerowych",
				"Katedra Technologii Dziewiarskich i Maszyn Włókienniczych",
				"Centrum Językowe",
				"Instytut Inżynierii Materiałowej",
				"Instytut Maszyn Przepływowych",
				"Indeks",
				"Katedra Automatyki, Biomechaniki i Mechatroniki",
				"Idż na porawkę",
				"Instytut Automatyki",
				"Instytut Elektroniki",
				"Pomoc starszego roku",
				"Instytut Informatyki Stosowanej",
				"Centrum Technologi Informatycznych",
				"Mail z dziekanatu",
				"Instytut Fizyki",
				"Instytut Matematyki",
				"Opłata za legitymację"
		};
		
		Integer[] costs = {
			0, 60, 0, 60, 200, 200, 100, 0, 100, 120, 0, 150, 140, 140, 160, 200, 180, 0, 180, 200, 0, 220, 0, 220, 240,
			200, 260, 260, 150, 280, 0, 300, 300, 0, 320, 200, 0, 350, 400, 100
		};
		
		Integer[] famId = {
				8, 0, 9, 0, 10, 11, 1, 12, 1, 1, 13, 10, 2, 2, 2, 11, 3, 9, 3, 3, 13, 4, 12, 4, 4, 11, 5, 5, 10, 5, 13, 
				6, 6, 12, 6, 11, 12, 7, 7, 10
		};
		
		int id = 0;
		int x = 11;
		int y = 11;
		for(int i = x; i > 0; i--) {
			cards.add(new CardInfo(names[id], costs[id], id, famId[id], i, y, getNodeByRowColumnIndex(i, y, id)));
			id++;
		}
		x = 1;
		y--;
		for(int i = y; i > 0; i--) {
			cards.add(new CardInfo(names[id], costs[id], id, famId[id], x, i, getNodeByRowColumnIndex(x, i, id)));
			id++;
		}
		y = 1;
		x = 2;
		for(int i = x; i < 12; i++) {
			cards.add(new CardInfo(names[id], costs[id], id, famId[id], i, y, getNodeByRowColumnIndex(i, y, id)));
			id++;
		}
		x = 11;
		y = 2;
		for(int i = y; i < 11; i++) {
			cards.add(new CardInfo(names[id], costs[id], id, famId[id], x, i, getNodeByRowColumnIndex(x, i, id)));
			id++;
		}
		players.add(new PlayerInfo(0, 0));
		players.add(new PlayerInfo(1, 1));
		gridpane.add(players.get(0).pawn, 11, 11);
		gridpane.add(players.get(1).pawn, 11, 11);
		players.get(0).pawn.setCenterX(5);
		players.get(1).pawn.setCenterX(5);
	
		
	}
	
	public AnchorPane getNodeByRowColumnIndex (final int row, final int column, final Integer id) {
		AnchorPane result = null;
	    ObservableList<Node> childrens = gridpane.getChildren();

	    for (Node node : childrens) {
	        if(node instanceof AnchorPane && GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
	            result = (AnchorPane)node;
	            result.setLeftAnchor(gridpane, 0.0);
	            result.setRightAnchor(gridpane, 0.0);
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
		if(round % 2 == 0) {
			players.get(1).changePosition(x, cards);
		}
		else {
			players.get(0).changePosition(x, cards);
		}
		round++;
	}
	
	public void recognizeCard() {
		
	}
	
	
}
