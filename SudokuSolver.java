import java.awt.Point;

public class SudokuSolver {

	private int[][] tmp;

	private int[][] sudokuMatrix;
	
	
	SudokuSolver(int[][] matrix){
		sudokuMatrix = matrix;
		tmp = new int[Sudoku.SIZE][Sudoku.SIZE];
	}
	
	public void setMatrix(int[][] matrix){
		this.sudokuMatrix = matrix;
		tmp = new int[Sudoku.SIZE][Sudoku.SIZE];
	}
	
	public int[][] getMatrix() {
		return this.getMatrix();
	}
	
	/**
	 * Debugging purpose only
	 *
	 */
	public void print_matrix(int[][] matrix) {
		
		if (matrix == null) {
			return;
		}
		
		for(int i = 0; i < Sudoku.SIZE; i++) {
			for(int j = 0; j < Sudoku.SIZE; j++) {
				if(matrix[i][j] == 0)
					System.out.print(" "+matrix[i][j]+" ");
				else
					System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public void setToZero() {
		tmp = new int[Sudoku.SIZE][Sudoku.SIZE];
		
		for(int i = 0; i < Sudoku.SIZE; i++) {
			for(int j = 0; j < Sudoku.SIZE; j++) {
				tmp[i][j] = 0;
			}
		}
	}
	
	public void solve() {
		int a;
		Point p = new Point(-1,-1);
		boolean test = true;
		
		while(test) {// we repeat until there is no modification
			test = false;
			setToZero();
			for(int k = 1; k <= 9; k++) {
				markedTheMatrix(k);
				
				for(int l = 0; l < 9; l++) {
					if((a = possibleGameLine(l)) > 0) {
						if(sudokuMatrix[l][a] == 0) {
							test = true;
							sudokuMatrix[l][a] = k;
						}
					}
				}
				for(int l = 0; l < 9; l++) {
					if((a = possibleGameColumn(l)) > 0) {
						if(sudokuMatrix[a][l] == 0) {
							test = true;
							sudokuMatrix[a][l] = k;
						}
					}
				}
				
				if((p = possibleGameMiniMatrixOne()) != null) {
					if(sudokuMatrix[p.x][p.y] == 0) {
						test = true;
						sudokuMatrix[p.x][p.y] = k;
					}
				}
				
				if((p = possibleGameMiniMatrixTwo()) != null) {
					if(sudokuMatrix[p.x][p.y] == 0) {
						test = true;
						sudokuMatrix[p.x][p.y] = k;
					}
				}
				
				if((p = possibleGameMiniMatrixThree()) != null) {
					if(sudokuMatrix[p.x][p.y] == 0) {
						test = true;
						sudokuMatrix[p.x][p.y] = k;
					}
				}
				
				if((p = possibleGameMiniMatrixFour()) != null) {
					if(sudokuMatrix[p.x][p.y] == 0) {
						test = true;
						sudokuMatrix[p.x][p.y] = k;
					}
				}
				
				if((p = possibleGameMiniMatrixFive()) != null) {
					if(sudokuMatrix[p.x][p.y] == 0) {
						test = true;
						sudokuMatrix[p.x][p.y] = k;
					}
				}
				
				if((p = possibleGameMiniMatrixSix()) != null) {
					if(sudokuMatrix[p.x][p.y] == 0) {
						test = true;
						sudokuMatrix[p.x][p.y] = k;
					}
				}
				
				if((p = possibleGameMiniMatrixSeven()) != null) {
					if(sudokuMatrix[p.x][p.y] == 0) {
						test = true;
						sudokuMatrix[p.x][p.y] = k;
					}
				}
				
				if((p = possibleGameMiniMatrixEight()) != null) {
					if(sudokuMatrix[p.x][p.y] == 0) {
						test = true;
						sudokuMatrix[p.x][p.y] = k;
					}
				}
				
				if((p = possibleGameMiniMatrixNine()) != null) {
					if(sudokuMatrix[p.x][p.y] == 0) {
						test = true;
						sudokuMatrix[p.x][p.y] = k;
					}
				}
			}
		}
	}
	
	/**
	 * this function put -1 everywhere we cannot put the 
	 * value val in the matrix
	 * @param val
	 * @param matrix
	 */
	public void markedTheMatrix(int val) {
		if(val < 1 || val > 9)
			return;
		
		setToZero();
		
		for(int i = 0; i < Sudoku.SIZE; i++) {
			for(int j = 0; j < Sudoku.SIZE; j++) {	
				markLine(val,i,j);
				markColumn(val,i,j);
				markMiniMatrix(val,i,j);
			}
		}
		
	}
	
	/**
	 * use in marked_the_matrix
	 * pos = -1 if we don't find 
	 * 
	 * pos = i if we find one
	 * 
	 * @param val
	 * @param col
	 * @return
	 */
	public void markLine(int val, int pi, int pj) {
		
		if(tmp[pi][pj] == -1)
			return;
		
		if(sudokuMatrix[pi][pj] > 0 && sudokuMatrix[pi][pj] != val) {
			tmp[pi][pj] = -1;
			return;
		}
		
		
		for(int j = 0; j < Sudoku.SIZE; j++ ) {
			if(j != pj) {
				if( sudokuMatrix[pi][j] == val) {// it is not possble to put val in the line pi
					for(int k = 0; k < Sudoku.SIZE; k++)
						tmp[pi][k] = -1;
					return;
				}
			}	
		}
		
		return;
	}
	
	public void markColumn(int val, int pi, int pj) {
		
		if(tmp[pi][pj] == -1)
			return;
		
		if(sudokuMatrix[pi][pj] > 0 && sudokuMatrix[pi][pj] != val) {
			tmp[pi][pj] = -1;
			return;
		}
		
		for(int i = 0; i < Sudoku.SIZE; i++ ) {
			if(i != pi) {
				if( sudokuMatrix[i][pj] == val) {// it is not possble to put val in the line pi
					for(int k = 0; k < Sudoku.SIZE; k++)
						tmp[k][pj] = -1;
					return;
				}
			}	
		}
		return;
	}
	
 	public void putMinusOneInMiniMatrix(int[][] mat, int line, int col, int s) {
 		int sizeI = line+s;
 		int sizeJ = col+s;
 		
 		for(int i = line; i < sizeI; i++) {
 			for(int j = col; j < sizeJ; j++) {
 				mat[i][j] = -1;
 			}
 		}
 	}
	
 	public void markMiniMatrix(int[][] mat, int line, int col, int s) {
 		int sizeI = line+s;
 		int sizeJ = col+s;
 		
		for(int i = line; i < sizeI; i++) {
			for(int j = col; j < sizeJ; j++) {
				if((sudokuMatrix[i][j] != 0) &&( mat[i][j] == 0)) {
					mat[i][j] =-1;
					return;
				}
			}
		}
 	}
 	
	/**
	 * miniMatrix1 miniMatrix2 miniMatrix3
	 * miniMatrix4 miniMatrix5 miniMatrix6
	 * miniMatrix7 miniMatrix8 miniMatrix9
	 * 
	 * @param val
	 * @param pi
	 * @param pj
	 * @param tmp
	 */
	public void markMiniMatrix(int val, int pi, int pj) {
		
		if( (pi >= 0) && (pi <= 2) && (pj>=0) && (pj <= 2)) { //mini matrix 1 done
			
			if(sudokuMatrix[pi][pj] == val) {
				putMinusOneInMiniMatrix(tmp, 0, 0, 3);
				return;
			}
			
			markMiniMatrix(tmp, 0, 0, 3);
			return;
		}
		
		if( (pi >= 0) && (pi <= 2) && (pj>=3) && (pj <= 5) ) { //mini matrix 2
			
			if(sudokuMatrix[pi][pj] == val) {
				putMinusOneInMiniMatrix(tmp, 0, 3, 3);
				return;
			}
			
			markMiniMatrix(tmp, 0, 3, 3);
			return;
		}
		
		if( (pi >= 0) && (pi <= 2) && (pj>=6) && (pj <= 8) ) { //mini matrix 3
			
			if(sudokuMatrix[pi][pj] == val) {
				putMinusOneInMiniMatrix(tmp, 0, 6, 3);
				return;
			}
			
			markMiniMatrix(tmp, 0, 6, 3);
			return;
		}
		
		if( (pi >= 3) && (pi <= 5) && (pj>=0) && (pj <= 2) ) { //mini matrix  4
			
			if(sudokuMatrix[pi][pj] == val) {
				putMinusOneInMiniMatrix(tmp, 3, 0, 3);
				return;
			}
			
			markMiniMatrix(tmp, 3, 0, 3);
			return;
		}
		
		if( (pi >= 3) && (pi <= 5) && (pj>=3) && (pj <= 5) ) { //mini matrix  5
			
			if(sudokuMatrix[pi][pj] == val) {
				putMinusOneInMiniMatrix(tmp, 3, 3, 3);

				return;
			}
			
			
			markMiniMatrix(tmp, 3, 3, 3);
			return;
		}
		
		if( (pi >= 3) && (pi <= 5) && (pj>=6) && (pj <= 8) ) { //mini matrix  6
			
			if(sudokuMatrix[pi][pj] == val) {
				putMinusOneInMiniMatrix(tmp, 3, 6, 3);
				return;
			}
			
			markMiniMatrix(tmp, 3, 6, 3);
			return;
		}
		
		if( (pi >= 6) && (pi <= 8) && (pj>=0) && (pj <= 2) ) { //mini matrix  7
			
			if(sudokuMatrix[pi][pj] == val) {
				putMinusOneInMiniMatrix(tmp, 6, 0, 3);
				return;
			}
			markMiniMatrix(tmp, 6, 0, 3);
			return;
		}
		
		if( (pi >= 6) && (pi <= 8) && (pj>=3) && (pj <= 5) ) { //mini matrix  8
			
			if(sudokuMatrix[pi][pj] == val) {
				putMinusOneInMiniMatrix(tmp, 6, 3, 3);
				return;
			}
			
			markMiniMatrix(tmp, 6, 3, 3);
			return;
		}
		
		if( (pi >= 6) && (pi <= 8) && (pj>=6) && (pj <= 8) ) { //mini matrix  9
			
			if(sudokuMatrix[pi][pj] == val) {
				putMinusOneInMiniMatrix(tmp, 6, 6, 3);
				return;
			}
			
			markMiniMatrix(tmp, 6, 6, 3);
		}
		
		return;
	}
	
	/**
	 * this function will tell if you can actually write
	 * a valid number in an empty case. in a line.
	 * it will return the column intersection in a given line
	 * @param pi
	 * @return 
	 */
	public int possibleGameLine(int pi) {
		int position = 0;
		int total = 0;
		
		for(int j = 0; j< Sudoku.SIZE; j++) {
				total += tmp[pi][j];
				if(tmp[pi][j] == 0)
					position = j;
		}
		
		if(total == -8) {
			return position;
		}
		
		return -1;
	}

	/**
	 * 
	 * @param pj
	 * @return
	 */
	public int possibleGameColumn(int pj) {
		int position = 0;
		int total = 0;
		
		for(int i = 0; i< Sudoku.SIZE; i++) {
				total += tmp[i][pj];
				if(tmp[i][pj] == 0)
					position = i;
		}
		
		if(total == -8) {
			return position;
		}
		
		return -1;
	}


	public Point possibleGameMiniMatrixOne() {
		Point p = new Point(0,0);
		int total = 0;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				total += tmp[i][j];
				if(tmp[i][j] == 0)
					p.setLocation(i, j);
			}
		}
		
		if(total == -8) {
			return p;
		}
		
		return null;
	}

	public Point possibleGameMiniMatrixTwo() {
		Point p = new Point(0,0);
		int total = 0;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 3; j < 6; j++) {
				total += tmp[i][j];
				if(tmp[i][j] == 0)
					p.setLocation(i, j);
			}
		}
		
		if(total == -8) {
			return p;
		}
		
		return null;
	}
	
	public Point possibleGameMiniMatrixThree() {
		Point p = new Point(0,0);
		int total = 0;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 6; j < 9; j++) {
				total += tmp[i][j];
				if(tmp[i][j] == 0)
					p.setLocation(i, j);
			}
		}
		
		if(total == -8) {
			return p;
		}
		
		return null;
	}
	
	public Point possibleGameMiniMatrixFour() {
		Point p = new Point(0,0);
		int total = 0;
		
		for(int i = 3; i < 6; i++) {
			for(int j = 0; j < 3; j++) {
				total += tmp[i][j];
				if(tmp[i][j] == 0)
					p.setLocation(i, j);
			}
		}
		
		if(total == -8) {
			return p;
		}
		
		return null;
	}
	
	public Point possibleGameMiniMatrixFive() {
		Point p = new Point(0,0);
		int total = 0;
		
		for(int i = 3; i < 6; i++) {
			for(int j = 3; j < 6; j++) {
				total += tmp[i][j];
				if(tmp[i][j] == 0)
					p.setLocation(i, j);
			}
		}

		if(total == -8) {
			return p;
		}
		
		return null;
	}
	
	public Point possibleGameMiniMatrixSix() {
		Point p = new Point(0,0);
		int total = 0;
		
		for(int i = 3; i < 6; i++) {
			for(int j = 6; j < 9; j++) {
				total += tmp[i][j];
				if(tmp[i][j] == 0)
					p.setLocation(i, j);
			}
		}
		
		if(total == -8) {
			return p;
		}
		
		return null;
	}
	
	public Point possibleGameMiniMatrixSeven() {
		Point p = new Point(0,0);
		int total = 0;
		
		for(int i = 6; i < 9; i++) {
			for(int j = 0; j < 3; j++) {
				total += tmp[i][j];
				if(tmp[i][j] == 0)
					p.setLocation(i, j);
			}
		}
		
		if(total == -8) {
			return p;
		}
		
		return null;
	}
	
	public Point possibleGameMiniMatrixEight() {
		Point p = new Point(0,0);
		int total = 0;
		
		for(int i = 6; i < 9; i++) {
			for(int j = 3; j < 6; j++) {
				total += tmp[i][j];
				if(tmp[i][j] == 0) {
					p.setLocation(i, j);
				}
			}
		}
		
		if(total == -8) {
			return p;
		}
		
		return null;
	}
	
	public Point possibleGameMiniMatrixNine() {
		Point p = new Point(0,0);
		int total = 0;
		
		for(int i = 6; i < 9; i++) {
			for(int j = 6; j < 9; j++) {
				total += tmp[i][j];
				if(tmp[i][j] == 0)
					p.setLocation(i, j);
			}
		}
		
		if(total == -8) {
			return p;
		}
		
		return null;
	}
}