package com.game.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.game.exception.DuplicateSymbolException;
import com.game.exception.InvalidBoardSizeException;
import com.game.exception.InvalidNumberOfPlayersException;
import com.game.service.winningstrategy.WinningStrategy;

public class Game {
	private Board currentBoard;
	private List<Player> players;
	private Player currentPlayer;
	private GameStatus gameStatus;
	private Player winner;
	private List<Move> moves;
	private List<Board> boardStates;
	private WinningStrategy winningStrategy;
	private int numberOfSymbols;
	
	public Board getCurrentBoard() {
		return currentBoard;
	}

	public void setCurrentBoard(Board currentBoard) {
		this.currentBoard = currentBoard;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public List<Move> getMoves() {
		return moves;
	}

	public void setMoves(List<Move> moves) {
		this.moves = moves;
	}

	public List<Board> getBoardStates() {
		return boardStates;
	}

	public void setBoardStates(List<Board> boardStates) {
		this.boardStates = boardStates;
	}

	public WinningStrategy getWinningStrategy() {
		return winningStrategy;
	}

	public void setWinningStrategy(WinningStrategy winningStrategy) {
		this.winningStrategy = winningStrategy;
	}

	public void setNumberOfSymbols(int numberOfSymbols) {
		this.numberOfSymbols = numberOfSymbols;
	}
	public int getNumberOfSymbols() {
		return numberOfSymbols;
	}

	public static Builder builder() {
		return new Builder();
	}
	
	private Game(Board currentBoard, List<Player> players,
			WinningStrategy winningStrategy) {
		super();
		this.currentBoard = currentBoard;
		this.players = players;
		this.gameStatus = GameStatus.IN_PROGRESS;
		this.moves = new ArrayList<Move>();
		this.boardStates = new ArrayList<Board>();
		this.winningStrategy = winningStrategy;
		this.numberOfSymbols = 0;
	}

	public static class Builder{
		private int dimantion;
		private List<Player> players;
		private WinningStrategy winningStrategy;
		
		public Builder dimantion(int dimantion) {
			this.dimantion = dimantion;
			return this;
		}
		public Builder players(List<Player> players) {
			this.players = players;
			return this;
		}
		public Builder winningStrategy(WinningStrategy winningStrategy) {
			this.winningStrategy = winningStrategy;
			return this;
		}
		public void validateBotCount() throws InvalidBotCountException {
			int botCnt = 0;
			for(Player player :players) {
				if(player.getPlayerType().equals(PlayerType.BOT)) {
					botCnt++;
				}
			}
			if(botCnt > 1) {
				throw new InvalidBotCountException("Bot count should not be more than 1 current count : "+botCnt);
			}
		}
		public void validateBoardSize() throws InvalidBoardSizeException {
			if(dimantion<3 || dimantion>10) {
				throw new InvalidBoardSizeException("Board size should be >= 3 and <= 10 current size : "+dimantion);
			}
		}
		public void validatePlayerNumber() throws InvalidNumberOfPlayersException {
			if(players.size() != (dimantion-1)) {
				throw new InvalidNumberOfPlayersException("Number of players invalid count : "+players.size());
			}
		}
		
		public void validateDuplicateSymbol() throws DuplicateSymbolException {
			HashSet<Character> symbolSet = new HashSet<Character>();
			for(Player player : players) {
				symbolSet.add(player.getSymbol());
			}
			if(symbolSet.size() != players.size()) {
				throw new DuplicateSymbolException("All playsers should have unique symbols");
			}
		}
		
		public void validate() throws Exception{
			validateBoardSize();
			validateBotCount();
			validateDuplicateSymbol();
			validatePlayerNumber();
		}
		
		public Game build() throws Exception {
			validate();
			return new Game(new Board(dimantion), players, winningStrategy);
		}
	}
}
