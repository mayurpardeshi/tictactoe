package com.game.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
	private int size;
	private List<List<Cell>> board;
	
	public Board(Board board) {
		this.size = board.size;
		this.board = board.board;
	}
	
	public Board(int size) {
		this.size = size;
		board = new ArrayList<>();
		
		for(int i=0;i<size;i++) {
			this.board.add(new ArrayList<Cell>());
			for(int j=0;j<size;j++) {
				this.board.get(i).add(new Cell(i,j));
			}
		}
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<List<Cell>> getMatrix() {
		return board;
	}
	public void setMatrix(List<List<Cell>> matrix) {
		this.board = matrix;
	}
	public void printBoard() {
		for(int i=0;i<size;i++) {
			List<Cell> cells = board.get(i);
			for(Cell cell:cells) {
				cell.display();
			}
			System.out.println();
		}
	}
}
