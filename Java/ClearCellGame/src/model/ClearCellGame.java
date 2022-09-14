package model;

import java.util.Random;

/* This class must extend Game */
/**
 * @author karendepenyou
 * */
public class ClearCellGame extends Game {
	private int score;
	Random random;

	public ClearCellGame(int maxRows, int maxCols, Random random, int strategy) {
		super(maxRows, maxCols);
		this.random = random;
		score = 0;
		strategy = 1;

	}

	/**
	 * method checks to see if any cell at the bottom is colored AKA not empty
	 * if so the game is over.
	 * @return boolean
	 *  */
	@Override
	public boolean isGameOver() {
		boolean gameOver = false;
		for (int i = 0; i < board[0].length; i++) {
			if (board[board.length - 1][i] != BoardCell.EMPTY) {
				gameOver = true;
			}

		}
		// TODO Auto-generated method stub
		return gameOver;
	}

	@Override
	public int getScore() {

		return score;
	}
	/**
	 * method moves the cells down as more cells are generated and assigned random colors
	 * brings the bottom cells up rather then bringing upper cells down*/
	@Override
	public void nextAnimationStep() {
		if (!isGameOver()) {
			for (int i = board.length - 1; i > 0; i--) {
				for (int j = board[i].length - 1; j >= 0; j--) {
					board[i][j] = board[i - 1][j];
				}
			}

			for (int j = 0; j < board[0].length; j++) {
				board[0][j] = BoardCell.getNonEmptyRandomBoardCell(random);
			}

		}

	}

	@Override
	public void processCell(int rowIndex, int colIndex) {
		if (!isGameOver()) {
			if (board[rowIndex][colIndex] != BoardCell.EMPTY) {
				BoardCell cell = getBoardCell(rowIndex, colIndex);
				int row1 = rowIndex;
				int col1 = colIndex;
				setBoardCell(rowIndex, colIndex, BoardCell.EMPTY);

				// left
				while (col1 != 0 && (getBoardCell(row1, col1 - 1) == cell)) {

					board[row1][col1 - 1] = BoardCell.EMPTY;
					col1--;
					score++;
				}

				// right
				int row2 = rowIndex;
				int col2 = colIndex;
				while (col2 != board[row2].length - 1 && 
						(getBoardCell(row2, col2 + 1) == cell)) {

					board[row2][col2 + 1] = BoardCell.EMPTY;
					col2++;
					score++;
				}
				
				// up
				int row3 = rowIndex;
				int col3 = colIndex;
				while (row3 != 0 && (getBoardCell(row3 - 1, col3) == cell)) {

					board[row3 - 1][col3] = BoardCell.EMPTY;
					row3--;
					score++;
				}
				
				// down
				int row4 = rowIndex;
				int col4 = colIndex;
				while (row4 != board.length - 1 && 
						(getBoardCell(row4 + 1, col4) == cell)) {

					board[row4 + 1][col4] = BoardCell.EMPTY;
					row4++;
					score++;
				}
				
				// top left
				int row5 = rowIndex;
				int col5 = colIndex;
				while ((row5 != 0 && col5 != 0) &&
						(getBoardCell(row5 - 1, col5 - 1) == cell)) {
					
					board[row5 - 1][col5 - 1] = BoardCell.EMPTY;
					row5--;
					col5--;
					score++;
				}
				
				// bottom left
				int row6 = rowIndex;
				int col6 = colIndex;
				while ((row6 != board.length-1 && col6 != 0) && 
						(getBoardCell(row6 + 1, col6 - 1) == cell)) {

					board[row6 + 1][col6 - 1] = BoardCell.EMPTY;
					row6++;
					col6--;
					score++;
				}

				 //top right
				int row7 = rowIndex;
				int col7 = colIndex;
				while ((row7 != 0 && col7 != board[row7].length - 1)
						&& (getBoardCell(row7 - 1, col7 + 1) == cell)) {

					board[row7 - 1][col7 + 1] = BoardCell.EMPTY;
					row7--;
					col7++;
					score++;
				}
				
				//Bottom Right
				int row8 = rowIndex;
				int col8 = colIndex;
				while ((row8 != board.length-1 && col8 != board[row8].length - 1) && (getBoardCell(row8 + 1, col8 + 1) == cell)) {

					board[row8 + 1][col8 + 1] = BoardCell.EMPTY;
					row8++;
					col8++;
					score++;
				}
				/**
				 * collapse cell if last row is empty. Check this
				 * by calling the isEmpty method 
				 * moves the non empty cells 1 row above
				 * and empties the row they were in before*/
				for (int row = getMaxRows() - 2; row > 0; row--) {
					if (isEmpty(row) == true) {
						for (int i = row; i < getMaxRows() - 1; i++) {
							for (int col = 0; col < getMaxCols(); col++) {
								board[i][col] = board[i + 1][col];
								board[i + 1][col] = BoardCell.EMPTY;
							}
						}
					}
				}
			}
		}
	}

	/**
	 * helper method to check is the given row is empty or not will help with the
	 * colapsing
	 */
	private boolean isEmpty(int row) {
		boolean rowEmpty = false;
		int count = 0;
		for (int col = 0; col < getMaxCols(); col++) {
		
			if (getBoardCell(row, col) == BoardCell.EMPTY) {
				count++;
			}
		}
		
		if (count == getMaxCols()) {
			rowEmpty = true;
		}
		
		return rowEmpty;
	}

}
