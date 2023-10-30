package com.game.controller;

import java.util.List;

import com.game.models.Board;
import com.game.models.Game;
import com.game.models.GameStatus;
import com.game.models.Move;
import com.game.models.Player;
import com.game.models.PlayerType;
import com.game.service.exception.GameOverException;
import com.game.service.winningstrategy.WinningStrategies;
import com.game.service.winningstrategy.WinningStrategyFactory;

public class GameController {

	public Game createGame(int dimention, List<Player> players,WinningStrategies winningStrategies) throws Exception {
		try {
			return Game.builder().dimantion(dimention)
					.winningStrategy(WinningStrategyFactory.getWinningStrategy(WinningStrategies.ORDERONE_WINNINGSTRATEGY, dimention))
					.players(players)
					.build();
		} catch (Exception e) {
			System.out.println("Could not start a game"+e.getMessage());
		}
		return null;
	}
	
	public void displayBoard(Game game) {
		game.getCurrentBoard().printBoard();
	}
	
	public GameStatus getGameStatus(Game game) {
		return game.getGameStatus();
	}
	
	public Player getGameWinner(Game game) {
		return game.getWinner();
	}
	
	public Move executeMove(Game game, Player player) throws GameOverException {
		Move move = player.makeMove(game.getCurrentBoard());
		game.setNumberOfSymbols(game.getNumberOfSymbols()+1);
		updateGameStatus(game);
		updateGameMoves(game, move);
		updateBoardState(game);
		return move;
	}
	
	private void updateGameStatus(Game game) {
		int numberOfSymbols = game.getNumberOfSymbols();
		int dimension = game.getCurrentBoard().getSize();
		if(numberOfSymbols == (dimension * dimension)) {
			game.setGameStatus(GameStatus.DRAW);
		}
	}

	public void updateGameMoves(Game game, Move move) {
		game.getMoves().add(move);
	}
	
	public void updateBoardState(Game game) {
		game.getBoardStates().add(new Board(game.getCurrentBoard()));
	}
	
	public Player checkWinner(Game game, Move lastPayedMove) {
		Player player = game.getWinningStrategy().checkWinner(game.getCurrentBoard(), lastPayedMove);
		if(player != null) {
			game.setWinner(player);
			game.setGameStatus(GameStatus.COMPLETED);
			return player;
		}
		return null;
	}
		
	public void replayGame(Game game) {
		//TODO
	}

	public void undoMove(Game game, Move movePlayed) {
		// TODO Auto-generated method stub
		
	}
}
