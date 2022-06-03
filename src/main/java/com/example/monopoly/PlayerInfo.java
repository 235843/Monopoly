package com.example.monopoly;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
	PlayerInfo opponent;

	PlayerInfo(Integer id, Integer imgId, GridPane gridPane, Text moneyText, VBox playerVBox){
		this.id = id;
		this.imgId = imgId;
		this.money = 1500;
		this.cardOwn = new ArrayList<>();
		this.position = 0;
		this.pawn = new Circle(11, 11, 20, Color.PINK);
		this.pawn.setId(this.id.toString());
		this.gridPane = gridPane;
		this.moneyText = moneyText;
		this.playerVBox = playerVBox;
		this.opponent = null;
	}

	public void setOpponent(PlayerInfo opponent)
	{
		this.opponent = opponent;
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
				y = card.positionY;
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

		for (CardInfo i: opponent.cardOwn) {
			if(i.equals(card))
			{
				if(i.hotel==1)
				{
					opponent.money+=i.rentCost*7;
					this.money-=i.rentCost*7;
					return;
				}
				opponent.money+=i.rentCost*(i.houses+1);
				this.money-=i.rentCost*(i.houses+1);
				return;
			}
		}



		AnchorPane aP = new AnchorPane();
		Rectangle rec = new Rectangle(0, 0, 180, 180);
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
		Text cName = new Text();
		Text cost = new Text();
		Text rentCost = new Text();
		Text playersMoney = new Text();
		cName.setText(card.name);
		cName.setWrappingWidth(100);
		cName.setTextAlignment(TextAlignment.CENTER);
		aP.getChildren().add(rec);
		aP.getChildren().add(cName);

		if(card.id==4 || card.id==39)
		{
			money-=card.cost;
			Text text = new Text();
			text.setText("Zapłacono "+card.cost.toString()+"$");
			text.setWrappingWidth(100);
			text.setTextAlignment(TextAlignment.CENTER);
			aP.getChildren().add(text);
			aP.getChildren().get(2).setLayoutY(140);
			aP.getChildren().get(2).setLayoutX(25);

		}


		else if(card.cost>0) {
			Button buyButton = new Button("Buy");
			cost.setText("Cena: "+card.cost.toString()+"$");
			cost.setWrappingWidth(100);
			cost.setTextAlignment(TextAlignment.CENTER);
			aP.getChildren().add(cost);

			aP.getChildren().get(2).setLayoutY(160);
			aP.getChildren().get(2).setLayoutX(25);

			rentCost.setText("Czynsz :" + card.rentCost.toString() +"$");
			rentCost.setWrappingWidth(100);
			rentCost.setTextAlignment(TextAlignment.CENTER);
			aP.getChildren().add(rentCost);
			aP.getChildren().get(3).setLayoutY(140);
			aP.getChildren().get(3).setLayoutX(25);

			buyButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent actionEvent) {

					for (CardInfo i: cardOwn) {
						if(i.equals(card))
							return;
					}
					money-=card.cost;
					cardOwn.add(card);
					aP.getChildren().remove(5);
					playersMoney.setText(money.toString());
					playersMoney.setWrappingWidth(100);
					playersMoney.setTextAlignment(TextAlignment.CENTER);
					aP.getChildren().add(playersMoney);
					aP.getChildren().get(5).setLayoutX(25);
					aP.getChildren().get(5).setLayoutY(150);
				}
			});

			aP.getChildren().add(buyButton);
			aP.getChildren().get(4).setLayoutY(150);
			aP.getChildren().get(4).setLayoutX(25);
			playersMoney.setText(this.money.toString());
			playersMoney.setWrappingWidth(100);
			playersMoney.setTextAlignment(TextAlignment.CENTER);
			aP.getChildren().add(playersMoney);
			aP.getChildren().get(5).setLayoutX(25);
			aP.getChildren().get(5).setLayoutY(150);


		}



		aP.getChildren().get(1).setLayoutY(60);
		aP.getChildren().get(1).setLayoutX(25);
		playerVBox.getChildren().add(aP);
		return;
	}

	public void payTaxes(CardInfo card, AnchorPane aP)
	{
		money-=card.cost;
		Text text = new Text();
		text.setText("Zapłacono "+card.cost.toString()+"$");
		text.setWrappingWidth(100);
		text.setTextAlignment(TextAlignment.CENTER);
		aP.getChildren().add(text);
		aP.getChildren().get(2).setLayoutY(140);
		aP.getChildren().get(2).setLayoutX(25);
	}

}
