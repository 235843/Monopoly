package com.example.monopoly;

import java.util.ArrayList;

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
		this.position = CardInfo.id;
		this.pawn = new Circle(11, 11, 20, Color.PINK);
	}
	

}
