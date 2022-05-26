package com.example.monopoly;

import java.util.ArrayList;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PlayerInfo {
	Integer id;
	Integer imgId;
	Integer money;
	ArrayList<CardInfo> cardOwn;
	Integer position;
	Circle pawn;
	
	PlayerInfo(Integer id, Integer imgId){
		this.id = id;
		this.imgId = imgId;
		this.money = 1500;
		this.cardOwn = null;
		this.position = 0;
		this.pawn = new Circle(11, 11, 20, Color.PINK);
		this.pawn.setId(this.id.toString());
	}
	
//ADA 
	public void changePosition(int newPos, ArrayList<CardInfo> cards) {
		int x, y, count;
		for(int i = 0; i < 40; i++) {
			CardInfo card = cards.get(i);
			count = this.position + newPos;
			if(count >= 40) {
				count -= 40;
			}
			if(count == card.id) {
				x = card.positionX;
				y = card.posiotionY;
				GridPane.setColumnIndex(this.pawn, x);
				GridPane.setRowIndex(this.pawn, y);
				this.position = card.id;
				break;
			}
		}
		
		System.out.println(GridPane.getColumnIndex(this.pawn));
	}
}
