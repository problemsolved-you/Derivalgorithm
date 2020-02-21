package application;
	
import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.shape.*;
import javafx.scene.paint.*;

import java.util.EmptyStackException;
import java.util.Stack;

import pieces.RubiksCube;

import algorithms.InvalidAlgorithmException;
import algorithms.Algs;


public class Derivalgorithm extends Application {
	RubiksCube cube = new RubiksCube();
	Stack<String> executedAlgs = new Stack<String>();
	
	GridPane cubeDisplay = updateCube(cube);
	
	TextField algInput = new TextField();
	Button executeAlg = new Button("Execute Alg");
	Button undo = new Button("Undo");
	Button scramble = new Button("Scramble");
	HBox buttons = new HBox();
	
	@Override
	public void start(Stage primaryStage) {
		Image favicon = new Image(getClass().getResourceAsStream("/favicon.png"));
		primaryStage.getIcons().addAll(favicon);
		primaryStage.setTitle("Derivalgorithm v0.2");
		GridPane.setConstraints(cubeDisplay, 0, 0);
		algInput.setPromptText("Type in your alg here");
		GridPane.setConstraints(algInput, 0, 1);
		buttons.getChildren().addAll(executeAlg, undo, scramble);
		GridPane.setConstraints(buttons, 0, 2);
		executeAlg.setOnAction(e -> {
			try{
				Algs.executeAlg(algInput.getText(), cube);
				executedAlgs.push(algInput.getText());
				updateDisplay(primaryStage);
			} catch (InvalidAlgorithmException x) {
				AlertBox.display("Error!", x.getMessage());
			}
		});
		undo.setOnAction(e -> {
			try {
				Algs.executeAlg(Algs.inverse(executedAlgs.pop()), cube);
				updateDisplay(primaryStage);
			} catch(EmptyStackException x) {
				AlertBox.display("Error!", "Nothing previously executed!");
			}
		});
		scramble.setOnAction(e -> {
			Algs.scramble(cube);
			updateDisplay(primaryStage);
		});
		updateDisplay(primaryStage);
	}
	
	private void updateDisplay(Stage stage) {
		cubeDisplay = updateCube(cube);
		GridPane shell = new GridPane();
		shell.getChildren().addAll(cubeDisplay, algInput, buttons);	
		Scene primaryScene = new Scene(shell);
		stage.setScene(primaryScene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private GridPane updateCube(RubiksCube cube) {
		GridPane complete = new GridPane();
		char[][] state = cube.readState();
		for(int i = 0; i < state.length; i++) {
			for(int j = 0; j < state[i].length; j++) {
				Rectangle sticker = new Rectangle(0, 0, 20, 20);
				if(state[i][j] != ' ') {
					sticker.setStroke(Color.BLACK);
				}
				switch(state[i][j]) {
					case 'W':
						sticker.setFill(Color.WHITE);
						break;
					case 'Y':
						sticker.setFill(Color.YELLOW);
						break;
					case 'G':
						sticker.setFill(Color.GREEN);
						break;
					case 'B':
						sticker.setFill(Color.BLUE);
						break;
					case 'R':
						sticker.setFill(Color.RED);
						break;
					case 'O':
						sticker.setFill(Color.ORANGE);
						break;
					default:
						sticker.setFill(Color.WHITE);
						break;
				}
				GridPane.setConstraints(sticker, j, i);
				complete.getChildren().add(sticker);
			}
		}
		return complete;
	}
}
