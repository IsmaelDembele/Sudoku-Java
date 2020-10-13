import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;



public class Sudoku extends JFrame implements FocusListener, ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6301134498742903982L;

	public static final int SIZE = 9; // the size of the Sudoku
	
	/**
	 * Sudoku grid variables
	 */
	private JTextField[][] text;// Sudoku grid text field
	private JPanel panel_sg; // Sudoku grid panel
	private GridLayout layout_sg;// the layout for Sudoku grid
	
	private GridBagLayout gblayout;
	private GridBagConstraints gbc;
	private Container c;
	
	/**
	 * the list of button that are going to be added to the Frame
	 */
	private JPanel buttons_panel;
	private GridLayout layoutButton;
	private JButton _new;
	private JButton reset;
	private JButton check;
	private JButton solve;
	
	
	private MatrixList mList = new MatrixList();
	private int[][] matrixCopy = new int[SIZE][SIZE];
	private int[][] sudokuMatrix = new int[SIZE][SIZE];

	
	Border border = null;
	private Font myFont = new Font("Cambria",Font.BOLD,20);
	
	public Sudoku() {
		copyMatrix(sudokuMatrix, mList.getCurrent());
		copyMatrix(matrixCopy,sudokuMatrix);
		
		//Grid initialization;
		panel_sg = new JPanel();
		text = new JTextField[SIZE][SIZE];
		layout_sg = new GridLayout(9,9);
		panel_sg.setLayout(layout_sg);
		
		//buttons panel creation
		buttons_panel = new JPanel();
		_new = new JButton("New");
		reset = new JButton("Reset");
		check = new JButton("Check");
		solve = new JButton("Solve");
		layoutButton = new GridLayout();
		
		
		_new.addActionListener(this);
		reset.addActionListener(this);
		check.addActionListener(this);
		solve.addActionListener(this);
		
		buttons_panel.setLayout(layoutButton);
		buttons_panel.add(_new);
		buttons_panel.add(reset);
		buttons_panel.add(check);
		buttons_panel.add(solve);
		
		create_textfields();//create the text fields and the border
		
		c = this.getContentPane();
		gblayout = new GridBagLayout();
		gbc= new GridBagConstraints();
		
		c.setLayout(gblayout);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 9;
		gbc.gridheight = 9;
		gbc.fill = GridBagConstraints.BOTH;
		
		c.add(panel_sg,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.gridwidth = 9;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		
		c.add(buttons_panel,gbc);
		
		this.setResizable(false);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.pack();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	

	@Override
	public void focusGained(FocusEvent e) {	
	}

	@Override
	public void focusLost(FocusEvent e) {
		JTextField jt = (JTextField) e.getSource();
		String myString = jt.getText();
		char c;
		
		
		myString = myString.replaceAll("\\s", ""); // remove all the white space
		jt.setText(myString);
		
		if(myString.equals("") || myString.length() == 0)
			return;
		
		
		c = myString.charAt(0);
		
		if( (myString.length() > 1) ||  (c < '1') || (c > '9' )) { // if the text is not null
			jt.setText("");
		}
	}
	
	
	public void create_textfields() {
		
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				
				text[i][j] = new JTextField("",2);
				text[i][j].setHorizontalAlignment(JTextField.CENTER);
				text[i][j].addFocusListener(this);			
				text[i][j].setFont(myFont);	 
				
				panel_sg.add(text[i][j]);
				
				border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black);
				text[i][j].setBorder(border);
				 
				if((i == 2) || (i == 5)) {
					if ((j == 2) || (j == 5)){
						border = BorderFactory.createMatteBorder(1, 1, 3, 3, Color.black);
						text[i][j].setBorder(border);
					}else {
					border = BorderFactory.createMatteBorder(1, 1, 3, 1, Color.black);
					text[i][j].setBorder(border);
					}
					
				}if ((j == 2) || (j == 5)){
					if((i == 2) || (i == 5)) {
					border = BorderFactory.createMatteBorder(1, 1, 3, 3, Color.black);
					text[i][j].setBorder(border);
					}else {
						border = BorderFactory.createMatteBorder(1, 1, 1, 3, Color.black);
						text[i][j].setBorder(border);
					}
				}
			}
		}
		print_matrix(sudokuMatrix);
	}

	/**
	 * copy the matrix to the text field
	 * 0 represent empty text field
	 */
	public void print_matrix(int[][] matrix) {
		if (matrix == null)
			return;
		
		for(int i = 0; i < SIZE; i++ ) {
			for(int j = 0; j < SIZE; j++) {
				if(matrix[i][j] != 0) {
					text[i][j].setText(matrix[i][j]+"");
					text[i][j].setBackground(Color.GRAY);
					text[i][j].setEditable(false);	
				}
			}
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		
		if(b == null)
			return;
		
		
		if(b.equals(_new)) {
			clearTextField();
			copyMatrix(sudokuMatrix,mList.getNext());
			copyMatrix(matrixCopy,sudokuMatrix);
			
			print_matrix(sudokuMatrix);
			
			repaint();
			
		}
		
		if(b.equals(reset)) {// reset
			clearTextField();
			copyMatrix(sudokuMatrix, matrixCopy);
			print_matrix(matrixCopy);
			repaint();
		}
		
		if(b.equals(check)) { // check
			int[][] a = new int [SIZE][SIZE];
			SudokuSolver solve; 
			
			copyMatrix(a, matrixCopy);
			
			solve = new SudokuSolver(a);
			solve.solve();
			
			checkGame(a);
			
			print_matrix(sudokuMatrix);
			repaint();
		}
		if(b.equals(solve)) {
			SudokuSolver solver = new SudokuSolver(sudokuMatrix);
			solver.solve();
			print_matrix(sudokuMatrix);
		}
		
	}
	
	public void copyMatrix(int[][] a,int[][] b) {
		for(int i = 0; i < SIZE;i++) {
			for(int j = 0; j < SIZE; j++){
				a[i][j] = b [i][j];
			}
		}
	}
	
	public void checkGame(int[][] matrix) {
		String sTemp;
		int valTemp;
		boolean b = true;
		
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j< SIZE; j++) {
				
				if(!text[i][j].getText().equals("")) {
					sTemp = text[i][j].getText();
					valTemp = Integer.parseInt(sTemp);
					if(matrix[i][j] != valTemp) {
						text[i][j].setBackground(Color.RED);
						b=false;
					}
				}else {
					b = false;
				}
				
				if((text[i][j].getBackground()).equals(Color.RED) && text[i][j].getText().equals(""))
					text[i][j].setBackground(Color.WHITE);
				
			}
		}
		
		if(b) {
			changeMatrixGridColor(Color.GREEN);
		}
		
	}
	
	public void changeMatrixGridColor(Color c) {
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j< SIZE; j++) {
				text[i][j].setBackground(c);
			}
		}
	}
	
	public void clearTextField() {
		for(int i = 0; i < Sudoku.SIZE; i++) {
			for(int j = 0; j < Sudoku.SIZE; j++) {
				text[i][j].setText("");
				text[i][j].setBackground(Color.WHITE);
				text[i][j].setEditable(true);		
			}
		}
	}
}






















