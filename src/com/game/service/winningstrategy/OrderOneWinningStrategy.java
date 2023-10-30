package com.game.service.winningstrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.print.attribute.standard.Sides;

import com.game.models.Board;
import com.game.models.Move;
import com.game.models.Player;

public class OrderOneWinningStrategy implements WinningStrategy {
	private int dimention;
	private List<HashMap<Character, Integer>> rowHashMap;
	private List<HashMap<Character, Integer>> colHashMap;
	private HashMap<Character, Integer> topLeftHashMap;
	private HashMap<Character, Integer> topRightHashMap;
	private HashMap<Character, Integer> cornerHashMap;
	
	public OrderOneWinningStrategy(int dimention) {
		this.dimention  = dimention;
		this.rowHashMap = new ArrayList<>();
		this.colHashMap = new ArrayList<>();
		this.topLeftHashMap = new HashMap<>();
		this.topRightHashMap = new HashMap<>();
		this.cornerHashMap = new HashMap<>();
		
		for(int i=0;i<dimention;i++) {
			rowHashMap.add(new HashMap<Character, Integer>());
			colHashMap.add(new HashMap<Character, Integer>());
		}
	}
	boolean isTopLeftDiagonalCell(int row, int col) {
		return row == col;
	}
	boolean isTopRightDiagonalCell(int row, int col) {
		return row+col == dimention;
	}
	boolean iscornerCell(int row, int col) {
		if(row == 0 || row == dimention-1)
			return col == 0 || col == dimention-1;
		else
			return false;
	}
	@Override
	public Player checkWinner(Board board, Move lastMove) {
		Player player = lastMove.getPlayer();
		char symbol = player.getSymbol();
		int row = lastMove.getCell().getRow();
		int col = lastMove.getCell().getCol();
		
		if(checkRowWin(row, col, symbol)) {
			return player;
		}
		else if(checkColWin(row, col, symbol)) {
			return player;
		}
		else if(isTopLeftDiagonalCell(row, col) && checkTopLeftDiagonalWin(symbol)) {
			return player;
		}
		else if(isTopRightDiagonalCell(row, col) && checkTopRightDiagonalWin(symbol)) {
			return player;
		}
		else if(checkCornerWin(row, col, symbol)) {
			return player;
		}
		return null;
	}
	
	public boolean checkRowWin(int row, int col, char symbol) {
		//if symbol is not existing, insert into the map
		rowHashMap.get(row).putIfAbsent(symbol, 0);
		/*if(!rowHashMap.get(row).containsKey(symbol)) {
			rowHashMap.get(row).put(symbol, 0);
		}*/
		//for every insertion of symbol update the count
		rowHashMap.get(row).put(symbol, rowHashMap.get(row).get(symbol)+1);
		
		return rowHashMap.get(row).get(symbol) == dimention;
	}
	
	public boolean checkColWin(int row, int col, char symbol) {
		colHashMap.get(col).putIfAbsent(symbol, 0);
		
		colHashMap.get(col).put(symbol, colHashMap.get(col).get(symbol)+1);
		
		return colHashMap.get(col).get(symbol) == dimention;
	}

	public boolean checkTopLeftDiagonalWin(char symbol) {
		topLeftHashMap.putIfAbsent(symbol, 0);
		
		topLeftHashMap.put(symbol, topLeftHashMap.get(symbol)+1);
		
		return topLeftHashMap.get(symbol) == dimention;
	}
	
	public boolean checkTopRightDiagonalWin(char symbol) {
		topRightHashMap.putIfAbsent(symbol, 0);
		
		topRightHashMap.put(symbol, topRightHashMap.get(symbol)+1);
		
		return topRightHashMap.get(symbol) == dimention;
	}
	
	public boolean checkCornerWin(int row, int col, char symbol) {
		cornerHashMap.putIfAbsent(symbol, 0);
		
		cornerHashMap.put(symbol, cornerHashMap.get(symbol)+1);
		
		return cornerHashMap.get(symbol) == dimention;
	}

}
