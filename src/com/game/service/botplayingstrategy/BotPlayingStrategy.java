package com.game.service.botplayingstrategy;

import com.game.models.Board;
import com.game.models.Move;
import com.game.service.exception.GameOverException;

public interface BotPlayingStrategy {
	Move makeMove(Board board) throws GameOverException;
}
