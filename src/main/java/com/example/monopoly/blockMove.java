package com.example.monopoly;

import javafx.scene.control.Button;

public class blockMove extends Thread{
	Button button;
	Integer time;
	public blockMove(Button button, Integer time) {
		this.button = button;
		this.time = time;
	}
	
	public void run()
    {
		button.setDisable(true);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		button.setDisable(false);
       
    }
}
