package com.example.monopoly;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import javafx.scene.text.Font;
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
	Integer freePrisonExit;
	Boolean prison;
	Color color;

	PlayerInfo(Integer id, Integer imgId, GridPane gridPane, VBox playerVBox, Color color){
		this.id = id;
		this.imgId = imgId;
		this.money = 1500;
		this.cardOwn = new ArrayList<>();
		this.position = 0;
		this.pawn = new Circle(11, 11, 20, color);
		this.pawn.setId(this.id.toString());
		this.gridPane = gridPane;
		this.playerVBox = playerVBox;
		this.opponent = null;
		this.moneyText = new Text();
		this.moneyText.setText("1500$");
		this.freePrisonExit = 0;
		this.prison = false;
		this.color = color;
	}

	public void setOpponent(PlayerInfo opponent)
	{
		this.opponent = opponent;
	}

	public void displayPlayer()
	{
		AnchorPane aP = new AnchorPane();

		Text player = new Text();
		Font font = new Font(20);
		moneyText.setFont(font);
		Text money = moneyText;
		
		Integer pid = id;
		if(pid<2)
			pid+=1;
		player.setText("Gracz " + pid);
		font.font(25);
		player.setFont(font);
		if(id == 0)
			player.setFill(Color.RED);
		else
			player.setFill(Color.GREEN);
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

	public void sellBuildings(Text text, AnchorPane aP, Integer pay, int anchorIndex) {
		text.setText("Nie wystarczające środki, sprzedaj część majątku, potrzebujesz: " + pay + "$");
		aP.getChildren().add(text);
		aP.getChildren().get(anchorIndex).setLayoutY(120);
		aP.getChildren().get(anchorIndex).setLayoutY(25);
		if (this.cardOwn.isEmpty()) {
			text.setText("Przegrałeś, nie masz środków ani majątu");
		} else {
			int x = 2;
			VBox vbox = new VBox();
			
			for (CardInfo ownCard : this.cardOwn) {
				Button sellButton = new Button("Sprzedaj: " + ownCard.name + " za: " + (ownCard.cost / 2 + ownCard.rentCost / 2));
				sellButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent actionEvent) {
						money += (ownCard.cost / 2 + ownCard.rentCost / 2);
						cardOwn.remove(ownCard);
						moneyText.setText(money.toString() + "$");
						
						AnchorPane result = null;
					    ObservableList<Node> childrens = gridPane.getChildren();

					    for (Node node : childrens) {
					        if(node instanceof AnchorPane && GridPane.getRowIndex(node) == ownCard.positionY && GridPane.getColumnIndex(node) == ownCard.positionX) {
					            result = (AnchorPane)node;
					            result.getChildren().remove(result.getChildren().size() - 1);
					            break;
					        }
					        
					    }

						
						if (money >= pay) {
							money -= pay;
							moneyText.setText(money.toString() + "$");
							opponent.money += pay;
							opponent.moneyText.setText(opponent.money.toString() + "$");
							Text done = new Text();
							done.setText("Udało się zapłacić");
							aP.getChildren().add(done);
							aP.getChildren().get(aP.getChildren().size() - 1).setLayoutY(120);
							aP.getChildren().remove(anchorIndex);
						}
						sellButton.setDisable(true);
						if(cardOwn.size()==0)
						{
							text.setText("Przegrałeś, nie masz środków ani majątu");
						}
					}

				});
				sellButton.setWrapText(true);
				sellButton.setMaxWidth(180);
				sellButton.setPrefWidth(180);
				sellButton.setMaxHeight(60);
				vbox.getChildren().add(sellButton);
				
				//x++;
				//aP.getChildren().get(x).setLayoutY(160 + (x - 2) * 60);
			}
			aP.getChildren().add(vbox);
			aP.getChildren().get(aP.getChildren().size() - 1).setLayoutY(180);
		}
	}

	public void recognizeCard(CardInfo card, ArrayList<OpportunityCards> ChanceCards, ArrayList<OpportunityCards> CommCards, ArrayList<CardInfo> cards) {
		if(playerVBox.getChildren().size() > 1) {
			playerVBox.getChildren().remove(1);
			playerVBox.getChildren().remove(0);
			displayPlayer();
			//playerVBox.getChildren().remove(2);
		}

		if(card.id == 30) {
			AnchorPane aP = new AnchorPane();
			Text cName = new Text();
			cName.setText(card.name);
			cName.setWrappingWidth(180);
			cName.setTextAlignment(TextAlignment.CENTER);
			aP.getChildren().add(cName);
			aP.getChildren().get(0).setLayoutY(50);
			this.prison = true;
			this.money -= 200;
			Button goButton = new Button("Idź");
			goButton.setOnAction(new EventHandler<>() {
				@Override
				public void handle(ActionEvent event) {
					changePosition(20, cards, ChanceCards, CommCards);
				}
			});
			aP.getChildren().add(goButton);
			aP.getChildren().get(1).setLayoutY(180);
			aP.getChildren().get(1).setLayoutX(25);
			playerVBox.getChildren().add(aP);
			return;
		}

		if(opponent.playerVBox.getChildren().size() > 1) {
			AnchorPane anchorPane = (AnchorPane) opponent.playerVBox.getChildren().get(1);
			 ObservableList<Node> childrens = anchorPane.getChildren();
			 Button butt = new Button();
			 for (Node node : childrens) {
				 if(node instanceof Button) {
					 butt = (Button)node;
				 }
			 }
			 butt.setDisable(true);
		}


		//Button buyButton = new Button("Buy");
		AnchorPane aP = new AnchorPane();
		Rectangle rec = new Rectangle(0, 0, 180, 180);
		rec.setStyle("-fx-fill:"+card.fill+";");
		int famId = card.familyId;
		Text cName = new Text();
		Text cost = new Text();
		Text rentCost = new Text();
		cName.setText(card.name);

		cName.setWrappingWidth(180);
		cName.setTextAlignment(TextAlignment.CENTER);
		if(famId >= 0 && famId <= 7) {
			cName.setFill(Color.WHITE);
		}
		aP.getChildren().add(rec);
		aP.getChildren().add(cName);

		aP.getChildren().get(1).setLayoutY(20);
		for (CardInfo i: opponent.cardOwn) {
			if(i.equals(card))
			{
				
				ObservableList<Node> childrens = gridPane.getChildren();
				 Button butt = new Button();
				 for (Node node : childrens) {
					 if(node instanceof Button) {
						 butt = (Button)node;
					 }
				 }	
				Text text = new Text();
				text.setWrappingWidth(180);
				text.setTextAlignment(TextAlignment.CENTER);
				if(this.money < i.rentCost) {
					
					butt.setDisable(true);
					sellBuildings(text, aP, i.rentCost,aP.getChildren().size());
				}
				else {
					text.setText("Zapłacono: " + i.rentCost);
					opponent.money+=i.rentCost;
					this.money-=i.rentCost;
					this.moneyText.setText(money+"$");
					opponent.moneyText.setText(opponent.money + "$");
					aP.getChildren().add(text);
					aP.getChildren().get(2).setLayoutY(120);
					butt.setDisable(false);
				}
				playerVBox.getChildren().add(aP);
				return;
			}
		}

		for (CardInfo i: cardOwn) {
			if(i.equals(card))
			{
				if(card.familyId == 11) {
					Text text = new Text();
					text.setText("Już posiadasz tą kartę");
					text.setWrappingWidth(180);
					text.setTextAlignment(TextAlignment.CENTER);
					aP.getChildren().add(text);
					aP.getChildren().get(2).setLayoutY(60);
				}
				else if(card.houses < 4 && !card.name.equals("Indeks") && !card.name.equals("Żabka")) {
					Button buyButton = new Button("Kup akademik");
					buyButton.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent actionEvent) {
							if(card.houses==4)
								return;
							if(card.familyId <= 1) {
								if(money >= 50) {
									card.houses += 1;
									money -= 50;
									card.rentCost += card.houses * 10;
								}
								else {
									Text text = new Text();
									text.setText("Nie wystarczające środki");
									text.setWrappingWidth(180);
									text.setTextAlignment(TextAlignment.CENTER);
									aP.getChildren().add(text);
									aP.getChildren().get(4).setLayoutY(130);
								}

							}
							else if (card.familyId <= 3) {
								if(money >= 100) {
									card.houses += 1;
									money -= 100;
									card.rentCost += card.houses * 25;
								}
								else {
									Text text = new Text();
									text.setText("Nie wystarczające środki");
									text.setWrappingWidth(180);
									text.setTextAlignment(TextAlignment.CENTER);
									aP.getChildren().add(text);
									aP.getChildren().get(4).setLayoutY(130);
								}

							}
							else if (card.familyId <= 5) {
								if(money >= 150) {
									card.houses += 1;
									money -= 150;
									card.rentCost += card.houses * 50;
								}
								else {
									Text text = new Text();
									text.setText("Nie wystarczające środki");
									text.setWrappingWidth(180);
									text.setTextAlignment(TextAlignment.CENTER);
									aP.getChildren().add(text);
									aP.getChildren().get(4).setLayoutY(130);
								}

							}
							else if (card.familyId <= 7) {
								if(money >= 200) {
									card.houses += 1;
									money -= 200;
									card.rentCost += card.houses * 100;
								}
								else {
									Text text = new Text();
									text.setText("Nie wystarczające środki");
									text.setWrappingWidth(180);
									text.setTextAlignment(TextAlignment.CENTER);
									aP.getChildren().add(text);
									aP.getChildren().get(4).setLayoutY(130);
								}
							}

							moneyText.setText(money+"$");

							new blockMove(buyButton, 500).start();
						}

					});
					Text housePrice = new Text();


					if(card.familyId <= 1) {
						housePrice.setText("Cena akademika: 50$");

					}
					else if (card.familyId <= 3) {
						housePrice.setText("Cena akademika: 100$");
					}
					else if (card.familyId <= 5) {
						housePrice.setText("Cena akademika: 150$");
					}
					else if (card.familyId <= 7) {
						housePrice.setText("Cena akademika: 200$");
					}


					aP.getChildren().add(buyButton);
					aP.getChildren().get(2).setLayoutY(180);
					aP.getChildren().get(2).setLayoutX(50);
					aP.getChildren().add(housePrice);
					aP.getChildren().get(3).setLayoutY(150);
				}
				else {
					if(card.hotel < 2  && !card.name.equals("Indeks") && !card.name.equals("Żabka")) {
						Button buyButton = new Button("Kup prywatny akademik");
						buyButton.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent actionEvent) {

								if(card.familyId <= 1) {
									if(money >= 100) {
										card.hotel += 1;
										money -= 100;
										card.rentCost += card.hotel * 50;
									}
									else {
										Text text = new Text();
										text.setText("Nie wystarczające środki");
										text.setWrappingWidth(180);
										text.setTextAlignment(TextAlignment.CENTER);
										aP.getChildren().add(text);
										aP.getChildren().get(4).setLayoutY(130);
									}

								}
								else if (card.familyId <= 3) {
									if(money >= 200) {
										card.hotel += 1;
										money -= 200;
										card.rentCost += card.hotel * 100;
									}
									else {
										Text text = new Text();
										text.setText("Nie wystarczające środki");
										text.setWrappingWidth(180);
										text.setTextAlignment(TextAlignment.CENTER);
										aP.getChildren().add(text);
										aP.getChildren().get(4).setLayoutY(130);
									}

								}
								else if (card.familyId <= 5) {
									if(money >= 300) {
										card.hotel += 1;
										money -= 300;
										card.rentCost += card.hotel * 150;
									}
									else {
										Text text = new Text();
										text.setText("Nie wystarczające środki");
										text.setWrappingWidth(180);
										text.setTextAlignment(TextAlignment.CENTER);
										aP.getChildren().add(text);
										aP.getChildren().get(4).setLayoutY(130);
									}

								}
								else if (card.familyId <= 7) {
									if(money >= 400) {
										card.hotel += 1;
										money -= 400;
										card.rentCost += card.hotel * 200;
									}
									else {
										Text text = new Text();
										text.setText("Nie wystarczające środki");
										text.setWrappingWidth(180);
										text.setTextAlignment(TextAlignment.CENTER);
										aP.getChildren().add(text);
										aP.getChildren().get(4).setLayoutY(130);
									}
								}

								moneyText.setText(money+"$");

								new blockMove(buyButton, 500).start();
							}

						});
						Text housePrice = new Text();


						if(card.familyId <= 1) {
							housePrice.setText("Cena prywatnego akademika: 100$");

						}
						else if (card.familyId <= 3) {
							housePrice.setText("Cena prywatnego akademika: 200$");
						}
						else if (card.familyId <= 5) {
							housePrice.setText("Cena prywatnego akademika: 300$");
						}
						else if (card.familyId <= 7) {
							housePrice.setText("Cena prywatnego akademika: 400$");
						}


						aP.getChildren().add(buyButton);
						aP.getChildren().get(2).setLayoutY(180);
						aP.getChildren().get(2).setLayoutX(50);
						aP.getChildren().add(housePrice);
						aP.getChildren().get(3).setLayoutY(150);
					}
					else {
						Text nothinToBuy = new Text();
						nothinToBuy.setText("Nie ma już miejsc na budowe");
						aP.getChildren().add(nothinToBuy);
						aP.getChildren().get(2).setLayoutY(150);
					}
				}
				playerVBox.getChildren().add(aP);
				return;
			}

		}

		if(card.id==4 || card.id==39)
		{
			Text text = new Text();
			text.setWrappingWidth(100);
			text.setTextAlignment(TextAlignment.CENTER);

			if(money<card.cost)
			{
				sellBuildings(text, aP, card.cost,aP.getChildren().size());;
			}
			else
			{
				money-=card.cost;
				this.moneyText.setText(money+"$");

				text.setText("Zapłacono "+card.cost.toString()+"$");
				aP.getChildren().add(text);
				aP.getChildren().get(2).setLayoutY(120);
				aP.getChildren().get(2).setLayoutX(25);
			}

			playerVBox.getChildren().add(aP);
			return;
		}

		else if(card.cost > 0) {

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
			if(money <= card.cost) {
				Text noMoney = new Text();
				noMoney.setText("Nie wystarczające środki");
				aP.getChildren().add(noMoney);
				aP.getChildren().get(4).setLayoutY(120);
				aP.getChildren().get(4).setLayoutX(25);
			}
			else {
				Button buyButton = new Button("Kup");
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
						buyButton.setDisable(true);
						
						
						AnchorPane result = null;
					    ObservableList<Node> childrens = gridPane.getChildren();

					    for (Node node : childrens) {
					        if(node instanceof AnchorPane && GridPane.getRowIndex(node) == card.positionY && GridPane.getColumnIndex(node) == card.positionX) {
					            result = (AnchorPane)node;
					            double width = result.getWidth();
								double height = result.getHeight();
								Rectangle rec = new Rectangle(width, height);
								rec.setFill(color.deriveColor(1, 1, 1, 0.15));
					            result.getChildren().add(rec);
					            break;
					        }
					        
					    }
						
					}

				});

				aP.getChildren().add(buyButton);
				aP.getChildren().get(4).setLayoutY(180);
				aP.getChildren().get(4).setLayoutX(25);
			}
		}

		else if (card.familyId == 9 || card.familyId == 12) {
			Button buyButton = new Button();
			OpportunityCards oppCard = getChanceCard(ChanceCards);
			cost.setText(oppCard.name);
			cost.setWrappingWidth(130);
			cost.setTextAlignment(TextAlignment.CENTER);
			aP.getChildren().add(cost);
			aP.getChildren().get(2).setLayoutY(40);
			aP.getChildren().get(2).setLayoutX(25);

			if(oppCard.get == 0 && oppCard.pay == 0) {

				if(oppCard.prisonExit) {
					freePrisonExit += 1;
				}
				else {
					playerVBox.getChildren().add(aP);

					ObservableList<Node> childrens = gridPane.getChildren();
					Button butt = new Button();
					for (Node node : childrens) {
						if(node instanceof Button) {
							butt = (Button)node;
						}
					}
							if(oppCard.whereToGo > position) {
								Button goButton = new Button("Idź");
								goButton.setOnAction(new EventHandler<>() {
									@Override
									public void handle(ActionEvent event) {
										changePosition((oppCard.whereToGo - position), cards, ChanceCards, CommCards);
									}
								});
								aP.getChildren().add(goButton);
								aP.getChildren().get(3).setLayoutY(180);
								aP.getChildren().get(3).setLayoutX(25);
								butt.setDisable(true);

							}
							else {
								Button goButton = new Button("Idź");
								goButton.setOnAction(new EventHandler<>() {
									@Override
									public void handle(ActionEvent event) {
										int x = 40 - position;
										changePosition((oppCard.whereToGo + x), cards, ChanceCards, CommCards);
									}
								});
								aP.getChildren().add(goButton);
								aP.getChildren().get(3).setLayoutY(180);
								aP.getChildren().get(3).setLayoutX(25);

							}
							return;
				}

			}
	//TUTAJ
			else if(oppCard.get == 0) {
				Text text = new Text();
				text.setWrappingWidth(100);
				text.setTextAlignment(TextAlignment.CENTER);
				if(money<oppCard.pay)
				{
					sellBuildings(text, aP, oppCard.pay,aP.getChildren().size());
				}
				else
				{
					text.setText("Zapłacono "+oppCard.pay.toString()+"$");
					aP.getChildren().add(text);
					aP.getChildren().get(3).setLayoutY(150);
					aP.getChildren().get(3).setLayoutX(25);
					this.money-=oppCard.pay;
					this.moneyText.setText(money+"$");
				}

			}
			else if(oppCard.pay == 0) {
				buyButton.setText("Odbierz");
				buyButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent actionEvent) {

						money+=oppCard.get;
						moneyText.setText(money+"$");
						buyButton.setDisable(true);
					}

				});
				aP.getChildren().add(buyButton);
				aP.getChildren().get(3).setLayoutY(180);
				aP.getChildren().get(3).setLayoutX(25);
			}


		}

		playerVBox.getChildren().add(aP);
		return;
	}


	public void changePosition(int newPos, ArrayList<CardInfo> cards, ArrayList<OpportunityCards> ChanceCards, ArrayList<OpportunityCards> CommCards) {
		int x, y, count;
		count = this.position + newPos;
		if(count >= 40) {
			count -= 40;
			money+=200;
			moneyText.setText(money+"$");
		}
		for(int i = 0; i < 40; i++) {
			CardInfo card = cards.get(i);

			if(count == card.id) {
				x = card.positionX;
				y = card.positionY;
				GridPane.setColumnIndex(this.pawn, x);
				GridPane.setRowIndex(this.pawn, y);
				this.position = card.id;
				this.recognizeCard(card, ChanceCards, CommCards, cards);
				return;
			}
		}
	}

	public OpportunityCards getChanceCard(ArrayList<OpportunityCards> ChanceCards) {
		Random rand = new Random();
		int x = rand.nextInt(50);

		return ChanceCards.get(x);
	}

	public OpportunityCards getCommCard(ArrayList<OpportunityCards> CommCards) {
		Random rand = new Random();
		int x = rand.nextInt(50);

		return CommCards.get(x);
	}



}
