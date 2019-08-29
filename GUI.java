package inlämning;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class GUI {

private Sudoku soduko;
private JTextField[][] a;
 
	


/*
 * Loads the graphical interface
 */
public  GUI() {  
		
		soduko = new Sudoku();
		a = new JTextField[9][9];
		
		// Code for the interface
		
		JFrame frame = new JFrame("Soduko");
		frame.setSize(300, 300);
		JPanel boardPanel = new JPanel(new GridLayout(9,9));
		frame.add(boardPanel, BorderLayout.CENTER);
		
		for (int i = 0; i < 9; i++) { // Creates the squares for the board
			for (int k = 0; k < 9; k++) {
				a[i][k]= new JTextField("");
				boardPanel.add(a[i][k]);
			}
		
		}
		JPanel buttonPanel = new JPanel(new GridLayout(1,2));
		frame.add(buttonPanel, BorderLayout.SOUTH);
		JButton clearButton = new JButton("Clear");
		JButton solveButton = new JButton("Solve");
		buttonPanel.add(clearButton);
		buttonPanel.add(solveButton);
		
		// Code for the interface END.
		
		clear(); // Clears the board from numbers.
		 loadColors(a); // Loads the colors
		 frame.setVisible(true); // Makes everything visible.
		 
		 // Code for Button Press

		 clearButton.addActionListener(new ActionListener() {

		
		public void actionPerformed(ActionEvent arg0) { 
				clear();
				loadColors(a);
				popupWindow("Cleared");
		}
	   
	});
	
	solveButton.addActionListener(new ActionListener() { // adds a listener. This happens on button press.

		 
		public void actionPerformed(ActionEvent arg0) {
			
			if (insertSoduko(a) == false) {
				popupWindow("invalid input");
				return; // Completes the button press.
			}
			for (int i = 0; i < 9; i++) { // If 
				for (int k = 0; k < 9; k++) {
					if (a[i][k].getText().isEmpty()) continue;
				
					if (soduko.check(i, k, Integer.parseInt(a[i][k].getText())) == false) { // a quicker way to check if the user for example inserted two 5s in the first row
						popupWindow("Can't be solved");
					return;
				}
				}
			}
			
			
			if (soduko.solve() == true) {
				popupWindow("solved");
				loadColors(a); // If a square is red it will return to its normal color
				soduko.print(a);
			}
			else {
				popupWindow("Can't be solved");
			}
		}
	   
	});
	
	// Code for button press
	} 


/*
 * Clears the graphical board
 */
	
	public void clear() {			
		for (int i = 0; i < 9; i++) {
			for(int k = 0; k < 9; k++) {
				a[i][k].setText("");
			}
		}
		insertSoduko(a);
	}
	
	/* Loads the colors of the graphical board
	 * @param a is the graphical board to load colors on
	 */
	
	public void loadColors(JTextField[][] a) { 
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
		if(y==0 && x==3 || y==0 && x==4 || y==0 && x==5 || y==1 && x==3 || y==1 && x==4 || y==1 && x==5 ||
				y==2 && x==3 || y==2 && x==4 || y==2 && x==5) {
				a[x][y].setBackground(Color.LIGHT_GRAY);
				} else if(y==3 && x==0 || y==3 && x==1 || y==3 && x==2 || y==4 && x==0 || y==4 && x==1 || y==4 && x==2 ||
				y==5 && x==0 || y==5 && x==1 || y==5 && x==2) {
				a[x][y].setBackground(Color.LIGHT_GRAY);
				}else if(y==3 && x==6 || y==3 && x==7 || y==3 && x==8 || y==4 && x==6 || y==4 && x==7 || y==4 && x==8 ||
				y==5 && x==6 || y==5 && x==7 || y==5 && x==8) {
					a[x][y].setBackground(Color.LIGHT_GRAY);
				}else if(y==6 && x==3 || y==6 && x==4 || y==6 && x==5 || y==7 && x==3 || y==7 && x==4 || y==7 && x==5 ||
				y==8 && x==3 || y==8 && x==4 || y==8 && x==5) {
					a[x][y].setBackground(Color.LIGHT_GRAY);
				}
				else {
					a[x][y].setBackground(Color.WHITE);
				}
		}
		}
	
	}
	/* Creates a popup window
	 * @param text is the text to be displayed in the window.
	 */
	public void popupWindow(String text) {
		JFrame frame = new JFrame("Soduko");
		JOptionPane.showMessageDialog(frame, text);
		
	}
	

	public static void main(String[] args) { 
			new GUI();
	}
	
	/* inserts values placed in the board
	 *  @return false if the square contains an out of range number or a symbol.
	 */
	public boolean insertSoduko(JTextField[][] a) {
		boolean InsertSuccess = true;
		for (int row = 0; row < 9; row ++) {
			for (int col = 0; col < 9; col ++) {
				if (a[row][col].getText().isEmpty()) {
					
					soduko.set(row,col,0);
					continue;
				}
		
				int value = 0;
				   try {  // Tries to parseInt. parseInt returns exception if the arguement isn't a number.
					   value = Integer.parseInt(a[row][col].getText());
					   soduko.set(row,col,value);
					   
				    } catch(NumberFormatException e) { // If there is a letter or symbol in the soduko.
				    	InsertSuccess = false;
				        a[row][col].setBackground(Color.RED); // Mark red
				    }
				   
					
					
					if (value < 1 || value > 9) { // Value can only be from 1 to 9
						InsertSuccess = false;
				        a[row][col].setBackground(Color.RED);
					}
		
			
		}
			
	}
		return InsertSuccess;
		}


}

