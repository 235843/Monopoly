package com.example.monopoly;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

public class CardInfo {
	String name;
	Integer rentCost;
	Integer cost;
	Integer id;
	Integer familyId;
	Integer positionX;
	Integer positionY;
	AnchorPane anchorPane;
	String fill;
	Integer houses;
	Integer hotel;
	Integer owner;
	
	CardInfo(String name, Integer cost, Integer rentCost, Integer id, Integer familyId, Integer positionX, Integer positionY, AnchorPane anchorPane, String string){
		this.name = name;
		this.cost = cost;
		this.rentCost = rentCost;
		this.id = id;
		this.familyId = familyId;
		this.positionX = positionX;
		this.positionY = positionY;
		this.anchorPane = anchorPane;
		this.fill = string;
		this.houses = 0;
		this.hotel = 0;
		this.owner = null;
	}
	
	
	
	
	
}
