package com.game.service.winningstrategy;

import com.game.models.Board;
import com.game.models.Move;
import com.game.models.Player;

public interface WinningStrategy {

	Player checkWinner(Board board, Move lastMove);
}
