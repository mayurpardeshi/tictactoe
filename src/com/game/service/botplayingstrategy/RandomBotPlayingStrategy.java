package com.game.service.botplayingstrategy;

import java.util.List;

import com.game.models.Board;
import com.game.models.Cell;
import com.game.models.CellState;
import com.game.models.Move;
import com.game.service.exception.GameOverException;

public class RandomBotPlayingStrategy implements BotPlayingStrategy {

	@Override
	public Move makeMove(Board board) throws GameOverException {
		 List<List<Cell>> matrix = board.getMatrix();
		 
		 for(int i=0;i<matrix.size();i++) {
			 for(int j=0;j<matrix.size();j++) {
				 if(matrix.get(i).get(j).getCellState().equals(CellState.EMPTY)) {
					 return new Move(i,j,matrix.get(i).get(j).getPlayer());
				 }
			 }
		 }
		 throw new GameOverException("No new cell present to play with Game Over!");
	}

}
