package inlämning;
import java.awt.Color;

import javax.swing.JTextField;

public class Sudoku { 
	private int [][] board;
	public Sudoku() {  //Creates the sudoku board
		board = new int[9][9];
	}
	/*
	 * Solves the sudoku
	 * @return true if solution was found.
	 */
	public boolean solve() {  
		
		return isSolved(findNextEmptySpot()[0], findNextEmptySpot()[1]); // Finds the first empty spot
	}

	private boolean isSolved (int row, int col) {  
	for (int n = 1; n <= 9; n++){
		if (findNextEmptySpot()[0] == 10) { // If nextrow and nextcol doesn't find a new empty spot.
			return true;
		}
		
		if (check(row, col, n) == true) {
			
				
				board[row][col] = n;
				int nextRow = findNextEmptySpot()[0];
				int nextCol = findNextEmptySpot()[1] ;
				if(isSolved(nextRow, nextCol)) { // will return true if a solution is found.
					return true;
				}
				board[row][col] = 0;
		}
	}
		return false;
	}
	
	private int[] findNextEmptySpot() {  //Finds the next empty spot in the board
		int[] a= new int[2];
		for (int chRow = 0; chRow < 9; chRow++) { // Finds the next empty spot until they no longer exist
			for (int chCol= 0; chCol < 9; chCol++) {
				if (board[chRow][chCol] == 0) {
					
					a[0] = chRow;
					a[1] = chCol;
					return a;
				}
			}
		}
		a[0] = 10; // Sets a random value for the row and col to be used as the ending value.
		a[1] = 10;
		return a;
	}
	/*
	 * Inserts the value num into the soduko board on row row & colum col
	 * @param row is the row to insert, col is the column to insert and num is the number to insert
	 */
	public void set(int row, int col, int num) {   
		board[row][col]= num;
	}
	
	/* Gets the value of the board on the  row & col
	 * @param row is the row to insert, col is the column to insert.
	 * @return the value of the row row & column col
	 */
	
	public int get(int row, int col){ 
		return board[row][col];
	}
	
	/* Checks if a number num from 1 to 9 can be
	 * Inserted on index row and col
	 * @param row is the row to insert, col is the column to insert and num is the number to insert
	 *  @returns true if an insert is possible.
	 */
	
	public boolean check(int row, int col, int num) {  
		for (int checkCol = 0; checkCol < 9; checkCol++) {
			if (checkCol == col ) continue; // Needed for a special case when pressing the solve button.
			if (board[row][checkCol] == num) {
				return false;
			}
		}
		for (int checkRow = 0; checkRow < 9; checkRow++) {
			if (checkRow == row) continue;
			if (board[checkRow][col] == num) {
				return false;
			}
		}
		 int row2 = (row / 3) * 3 ; // Tänk på att row / 3 avrundar till heltal
	      int col2 = (col / 3) * 3 ;

	      for( int r = 0; r < 3; r++ ) {
	         for( int c = 0; c < 3; c++ ) {
	        	 if (row2+r == row || col2+c == col) continue; 
	         if( board[row2+r][col2+c] == num ) return false;
	         }
	      }
		return true;
	}
	/*  Prints the graphical board.
	 * @param a is the graphical board to be inserted. 
	 */
	
	public void print(JTextField[][] a) { 
		for (int row = 0; row < 9; row ++) {
			for (int col = 0; col < 9; col ++) {
				
				a[row][col].setText("" + get(row, col));
				
			
		}
			
	}
	}
}
