package com.example.monopoly;

public class CardInfo {
	String name;
	Integer cost;
	Integer id;
	Integer familyId;
	Integer positionX;
	Integer posiotionY;
	
	CardInfo(String name, Integer cost, Integer id, Integer familyId, Integer positionX, Integer positionY){
		this.name = name;
		this.cost = cost;
		this.id = id;
		this.familyId = familyId;
		this.positionX = positionX;
		this.posiotionY = positionY;
	}
	
	
}
