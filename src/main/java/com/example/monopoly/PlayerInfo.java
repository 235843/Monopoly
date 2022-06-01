package com.example.monopoly;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class PlayerInfo {
	Integer id;
	Integer imgId;
	Integer money;
	ArrayList<CardInfo> cardOwn;
	Integer position;
	Circle pawn;
	GridPane gridPane;
	Text moneyText;
	VBox playerVBox;
	
	PlayerInfo(Integer id, Integer imgId, GridPane gridPane, Text moneyText, VBox playerVBox){
		this.id = id;
		this.imgId = imgId;
		this.money = 1500;
		this.cardOwn = null;
		this.position = 0;
		this.pawn = new Circle(11, 11, 20, Color.PINK);
		this.pawn.setId(this.id.toString());
		this.gridPane = gridPane;
		this.moneyText = moneyText;
		this.playerVBox = playerVBox;
	}
	
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
				this.recognizeCard(card);
				break;
			}
		}	
	}
	
	public void recognizeCard(CardInfo card) {
		if(playerVBox.getChildren().size() > 2) {
			playerVBox.getChildren().remove(2);
			//playerVBox.getChildren().remove(2);
		}
		AnchorPane aP = new AnchorPane();
		Rectangle rec = new Rectangle(0, 0, 150, 150);
		rec.setFill(card.fill);
		int famId = card.familyId;
		if (famId == 2 || famId == 3) {
			rec.setRotate(-90);
		}
		else if (famId == 4 || famId == 5) {
			rec.setRotate(-180);
		}
		else if (famId == 6 || famId == 7) {
			rec.setRotate(90);
		}
		Text text = new Text();
		text.setText(card.name);
		text.setWrappingWidth(100);	
		text.setTextAlignment(TextAlignment.CENTER);
		aP.getChildren().add(rec);
		aP.getChildren().add(text);
		aP.getChildren().get(1).setLayoutY(60);
		aP.getChildren().get(1).setLayoutX(25);
		playerVBox.getChildren().add(aP);
		return;
	}
}
