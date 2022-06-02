package com.example.monopoly;

import java.util.Random;

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
	Integer houses;
	Integer hotel;
	Integer owner;
	
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
		this.houses = 0;
		this.hotel = 0;
		this.owner = null;
	}
	
	
	
	
	
}
