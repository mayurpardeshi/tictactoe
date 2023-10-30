package com.game.service.winningstrategy;

public class WinningStrategyFactory {
	public static WinningStrategy getWinningStrategy(WinningStrategies winningStrategies, int dimention) {
		switch(winningStrategies) {
			case ORDERONE_WINNINGSTRATEGY : {
				return new OrderOneWinningStrategy(dimention);
			}
		}
		return null;
	}
}
