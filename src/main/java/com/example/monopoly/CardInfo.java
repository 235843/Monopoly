package com.example.monopoly;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

public class CardInfo {
	String name;
	Integer rentCost;
	Integer cost;
	Integer id;
	Integer familyId;
	Integer positionX;
	Integer posiotionY;
	AnchorPane anchorPane;
	Paint fill;
	
	CardInfo(String name, Integer cost, Integer rentCost, Integer id, Integer familyId, Integer positionX, Integer positionY, AnchorPane anchorPane, Paint fill){
		this.name = name;
		this.cost = cost;
		this.rentCost = rentCost;
		this.id = id;
		this.familyId = familyId;
		this.positionX = positionX;
		this.posiotionY = positionY;
		this.anchorPane = anchorPane;
		this.fill = fill;
	}
	
	
}
