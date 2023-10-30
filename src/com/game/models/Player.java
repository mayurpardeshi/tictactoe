package com.game.models;

import java.util.Scanner;

import com.game.service.exception.GameOverException;

public class Player {
	private int id;
	private String name;
	private char symbol;
	private PlayerType playerType;
	
	
	
	public Player(int id, String name, char symbol, PlayerType playerType) {
		super();
		this.id = id;
		this.name = name;
		this.symbol = symbol;
		this.playerType = playerType;
	}
	public Player() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getSymbol() {
		return symbol;
	}
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	public PlayerType getPlayerType() {
		return playerType;
	}
	public void setPlayerType(PlayerType playerType) {
		this.playerType = playerType;
	} 
	
	public Move makeMove(Board board) throws GameOverException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the row for your move");
		int row = sc.nextInt();
		System.out.println("Enter the col for your move");
		int col = sc.nextInt();
		//TODO validation for the move check row and col and cell status
		
		board.getMatrix().get(row).get(col).setCellState(CellState.FILLED);
		board.getMatrix().get(row).get(col).setPlayer(this);
		return new Move(row, col, this);
	}
}
