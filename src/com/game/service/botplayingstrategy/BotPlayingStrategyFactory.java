package com.game.service.botplayingstrategy;

public class BotPlayingStrategyFactory {
	//TODO create botplayingstragey and based on enum return the strategy 
	public static BotPlayingStrategy getBotPlayingStrategy() {
		return new RandomBotPlayingStrategy();
	}
}
