package com.example.monopoly;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
	PlayerInfo opponent;

	PlayerInfo(Integer id, Integer imgId, GridPane gridPane, VBox playerVBox){
		this.id = id;
		this.imgId = imgId;
		this.money = 1500;
		this.cardOwn = new ArrayList<>();
		this.position = 0;
		this.pawn = new Circle(11, 11, 20, Color.PINK);
		this.pawn.setId(this.id.toString());
		this.gridPane = gridPane;
		this.playerVBox = playerVBox;
		this.opponent = null;
		this.moneyText = new Text();
		this.moneyText.setText("1500$");
	}
	
	public void setOpponent(PlayerInfo opponent)
	{
		this.opponent = opponent;
	}

	public void displayPlayer()
	{
		AnchorPane aP = new AnchorPane();

		Text player = new Text();
		Text money = moneyText;
		Integer pid = id;
		if(pid<2)
			pid+=1;
		player.setText("Player " + pid);
		player.setWrappingWidth(100);
		player.setTextAlignment(TextAlignment.CENTER);
		aP.getChildren().add(player);
		money.setWrappingWidth(100);
		money.setTextAlignment(TextAlignment.CENTER);
		aP.getChildren().add(moneyText);

		aP.getChildren().get(0).setLayoutY(20);
		aP.getChildren().get(1).setLayoutY(40);
		aP.getChildren().get(0).setLayoutX(25);
		aP.getChildren().get(1).setLayoutX(25);

		playerVBox.getChildren().add(aP);


		return;
	}

	public void recognizeCard(CardInfo card, ArrayList<OpportunityCards> ChanceCards, ArrayList<OpportunityCards> CommCards) {
		if(playerVBox.getChildren().size() > 1) {
			playerVBox.getChildren().remove(1);
			playerVBox.getChildren().remove(0);
			displayPlayer();
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
				this.moneyText.setText(money+"$");
				return;
			}
		}

		//Button buyButton = new Button("Buy");
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
		cName.setText(card.name);
		cName.setWrappingWidth(100);
		cName.setTextAlignment(TextAlignment.CENTER);
		aP.getChildren().add(rec);
		aP.getChildren().add(cName);
		aP.getChildren().get(1).setLayoutY(60);
		aP.getChildren().get(1).setLayoutX(25);


		if(card.id==4 || card.id==39)
		{
			money-=card.cost;
			this.moneyText.setText(money+"$");
			Text text = new Text();
			text.setText("Zapłacono "+card.cost.toString()+"$");
			text.setWrappingWidth(100);
			text.setTextAlignment(TextAlignment.CENTER);
			aP.getChildren().add(text);
			aP.getChildren().get(2).setLayoutY(120);
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

			rentCost.setText("Czynsz: " + card.rentCost.toString() +"$");
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
					moneyText.setText(money+"$");
					cardOwn.add(card);
					
				}
				
			});
			
			aP.getChildren().add(buyButton);
			buyButton.toFront();
			aP.getChildren().get(4).setLayoutY(180);
			aP.getChildren().get(4).setLayoutX(25);
		}
		
		else if (card.familyId == 9 || card.familyId == 12) {
			Button buyButton = new Button();
			OpportunityCards oppCard = getChanceCard(ChanceCards);
			cost.setText(oppCard.name);
			cost.setWrappingWidth(100);
			cost.setTextAlignment(TextAlignment.CENTER);
			aP.getChildren().add(cost);
			aP.getChildren().get(1).setLayoutY(10);
			aP.getChildren().get(2).setLayoutY(60);
			aP.getChildren().get(2).setLayoutX(25);
			
			if(oppCard.get == 0 && oppCard.pay == 0) {
				buyButton.setText("OK");
			}
			else if(oppCard.get == 0) {
				buyButton.setText("Pay");
			}
			else if(oppCard.pay == 0) {
				buyButton.setText("Get");
			}
			
			aP.getChildren().add(buyButton);
			aP.getChildren().get(3).setLayoutY(140);
			aP.getChildren().get(3).setLayoutX(25);
		}
		



		
		playerVBox.getChildren().add(aP);
		return;
	}
	
	public void changePosition(int newPos, ArrayList<CardInfo> cards, ArrayList<OpportunityCards> ChanceCards, ArrayList<OpportunityCards> CommCards) {
		int x, y, count;
		for(int i = 0; i < 40; i++) {
			CardInfo card = cards.get(i);
			count = this.position + newPos;
			if(count >= 40) {
				count -= 40;
				money+=200;
				moneyText.setText(money+"$");
			}
			if(count == card.id) {
				x = card.positionX;
				y = card.positionY;
				GridPane.setColumnIndex(this.pawn, x);
				GridPane.setRowIndex(this.pawn, y);
				this.position = card.id;
				this.recognizeCard(card, ChanceCards, CommCards);
				return;
			}
		}
	}
	
	public OpportunityCards getChanceCard(ArrayList<OpportunityCards> ChanceCards) {
		Random rand = new Random();
		int x = rand.nextInt(50);
		x += 1;
		return ChanceCards.get(x);
	}
	
	public OpportunityCards getCommCard(ArrayList<OpportunityCards> CommCards) {
		Random rand = new Random();
		int x = rand.nextInt(50);
		x += 1;
		return CommCards.get(x);
	}
	
	

}
