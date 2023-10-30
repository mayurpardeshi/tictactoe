package com.game.models;

import com.game.service.botplayingstrategy.BotPlayingStrategyFactory;
import com.game.service.exception.GameOverException;

public class Bot extends Player {
	
	public Bot(int id, String name, char symbol, PlayerType playerType,BotDificultyLevel botDificultyLevel) {
		super(id, name, symbol, playerType);
		this.botDificultyLevel = botDificultyLevel;
	}

	private BotDificultyLevel botDificultyLevel;
	
	public Move makeMove(Board board) throws GameOverException {
		com.game.service.botplayingstrategy.BotPlayingStrategy botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy();
		return botPlayingStrategy.makeMove(board);
	}
}
