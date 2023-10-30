package com.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.game.controller.GameController;
import com.game.models.Board;
import com.game.models.Bot;
import com.game.models.BotDificultyLevel;
import com.game.models.Game;
import com.game.models.GameStatus;
import com.game.models.Move;
import com.game.models.Player;
import com.game.models.PlayerType;
import com.game.service.winningstrategy.WinningStrategies;
import com.game.service.winningstrategy.WinningStrategy;

public class TicTacToeGame {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		GameController gameController = new GameController();
		System.out.println("Enter dimention of a game");
		int dimention = sc.nextInt();
		
		System.out.println("will there be any bot in the game Y/N");
		String isBotPresent = sc.next();
		
		List<Player> players = new ArrayList<Player>();
		int iteratorNumber = dimention-1;
		if(isBotPresent.equalsIgnoreCase("Y")) {
			iteratorNumber -= 1;
		}
		
		for(int i=0;i<iteratorNumber;i++) {
			System.out.println("what is the name of player : "+i+1);
			String playerName = sc.next();
			
			System.out.println("what is the symbol for player "+i+1+" : ");
			String symbol = sc.next();
			
			players.add(new Player(i,playerName, symbol.charAt(0), PlayerType.HUMAN));
		}
		
		if(isBotPresent.equalsIgnoreCase("y")) {
			System.out.println("what is the name of bot");
			String name = sc.next();
			System.out.println("what is the symbol of bot");
			String symbol = sc.next();
			//TODO take input for bot difficulty level and set strategy accordingly
			
			BotDificultyLevel botDificultyLevel = BotDificultyLevel.EASY;
			Bot bot =  new Bot(dimention, name, symbol.charAt(0), PlayerType.BOT, botDificultyLevel);
			players.add(bot);
		}
		Collections.shuffle(players);
		Game game = gameController.createGame(dimention, players, WinningStrategies.ORDERONE_WINNINGSTRATEGY);
		int playerIndex = 0;
		while(gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)) {
			System.out.println("Current Board status");
			gameController.displayBoard(game);
			playerIndex++;
			playerIndex = playerIndex % players.size();
			
			Move movePlayed = gameController.executeMove(game, players.get(playerIndex));
			System.out.println("Do you want to undo your move? Y/N");
			String isUndoRequired = sc.next();
			if(isUndoRequired.equalsIgnoreCase("Y")) {
				//TODO write a logic to undo the move
				gameController.undoMove(game,movePlayed);
			}
			
			Player winner  = gameController.checkWinner(game, movePlayed);
			if(winner != null) {
				System.out.println("Winner is : "+winner.getName());
				break;
			}
			System.out.println("final board status");
			gameController.displayBoard(game);
		}
		
	}	
}
