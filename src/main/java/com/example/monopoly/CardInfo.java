package com.example.monopoly;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class CardInfo {
	String name;
	Integer cost;
	Integer id;
	Integer familyId;
	Integer positionX;
	Integer posiotionY;
	AnchorPane anchorPane;
	
	CardInfo(String name, Integer cost, Integer id, Integer familyId, Integer positionX, Integer positionY, AnchorPane anchorPane){
		this.name = name;
		this.cost = cost;
		this.id = id;
		this.familyId = familyId;
		this.positionX = positionX;
		this.posiotionY = positionY;
		this.anchorPane = anchorPane;
	}
	
	
}
