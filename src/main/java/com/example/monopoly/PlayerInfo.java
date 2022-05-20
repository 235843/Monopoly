package com.example.monopoly;

import java.util.ArrayList;

public class PlayerInfo {
	Integer id;
	Integer imgId;
	Integer money;
	ArrayList<CardInfo> cardOwn;
	Integer position;
	
	PlayerInfo(Integer id, Integer imgId){
		this.id = id;
		this.imgId = imgId;
		this.money = 1500;
		this.cardOwn = null;
		this.position = CardInfo.id;
	}
	

}
