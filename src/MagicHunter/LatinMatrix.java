package MagicHunter;

public class LatinMatrix {

	private byte [][] Matrix;	// Latin square stored as matrix
	private byte dim;			// Size of matrix
	private String name;		// Name of matrix
	
	public LatinMatrix(byte size, String matrixName) {
		Matrix = new byte [size][size];
		dim = size;
		name = matrixName;
	}
	
	// Simple Accessors
	public byte getValue(byte x, byte y) { return Matrix[x][y];	}
	public byte getDim() { return dim; }
	public String getName() { return name; }
	public byte[][] getMatrix() { return Matrix; }
	
	// Simple Mutators
	public void setValue(byte x, byte y, byte value) { Matrix[x][y] = value; }
	public void setRow(byte index, byte[] row) { Matrix[index] = row; }
	public void setMatrix(LatinMatrix B) { Matrix = B.getMatrix(); }
	
	public void LatinFiller() {
		for (byte i = 0; i < dim; i++) {
			for (byte j = 0; j < dim; j++) {
				setValue(i,j, (byte)((i+j) % dim + 1));
			}
		}
	}

	public void PrintMatrix() {
		for (byte i = 0; i < dim; i++) {
			
			// Precede lines with name or space buffer
			if (i == dim/2) {
				System.out.print(" " + name + " = ");
			} else {
				for (byte k = 0; k < name.length()+4; k++) {
					System.out.print(" ");
				}
			}
			
			System.out.print("[ ");
			for (byte j = 0; j < dim-1; j++) {
				
				if (getValue(i, j) == 0) 
					System.out.print("*, ");
				else 
					System.out.print(getValue(i, j) + ", ");
			}
			if (getValue(i, (byte)(dim-1)) == 0) 
				System.out.println("* ] ");
			else
				System.out.println(getValue(i, (byte)(dim-1)) + " ] ");
		}
		System.out.print("\n");
	}

	public void PrintMatrix(LatinMatrix rightSide) {
		
		if (dim != rightSide.dim) {
			this.PrintMatrix();
			rightSide.PrintMatrix();
			return;
		}
		
		for (byte i = 0; i < dim; i++) {
			
			// Precede lines with name or space buffer
			if (i == dim/2) {
				System.out.print(" " + name + " = ");
			} else {
				for (byte k = 0; k < name.length()+4; k++) {
					System.out.print(" ");
				}
			}
			
			System.out.print("[ ");
			for (byte j = 0; j < dim-1; j++) {
				
				if (getValue(i, j) == 0) 
					System.out.print("*, ");
				else 
					System.out.print(getValue(i, j) + ", ");
			}
			if (getValue(i, (byte)(dim-1)) == 0) 
				System.out.print("* ] ");
			else
				System.out.print(getValue(i, (byte)(dim-1)) + " ] ");
			
			// Other Matrix
			System.out.print("  ");
			if (i == rightSide.dim/2) {
				System.out.print(" " + rightSide.name + " = ");
			} else {
				for (byte k = 0; k < rightSide.name.length()+4; k++) {
					System.out.print(" ");
				}
			}
			
			System.out.print("[ ");
			for (byte j = 0; j < rightSide.dim-1; j++) {
				
				if (rightSide.getValue(i, j) == 0) 
					System.out.print("*, ");
				else 
					System.out.print(rightSide.getValue(i, j) + ", ");
			}
			if (rightSide.getValue(i, (byte)(rightSide.dim-1)) == 0) 
				System.out.println("* ] ");
			else
				System.out.println(rightSide.getValue(i, (byte)(dim-1)) + " ] ");
			
		}
		System.out.print("\n");
	}

	public boolean validityCheck() {
		// Scan through columns
		for (byte j = 0; j < dim; j++) {
			// Scan through rows
			for (byte i = 0; i < dim; i++) {
				// Check columns and rows for matches
				for (byte k = 0; k < dim; k++) {
					if ((getValue(i,j) == getValue(i,k) && j != k) 
					 || (getValue(i,j) == getValue(k,j) && i != k)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean orthohonalCheck(LatinMatrix other) {
		boolean [][] collection = new boolean [dim][dim];	// Stores unique compositions of the two matrices
		
		// Check for incompatible dimensions
		if (dim != other.dim) return false;
		
		// Scan through columns
		for (byte j = 0; j < dim; j++) {
			// Scan through rows
			for (byte i = 0; i < dim; i++) {
				// Check the uniqueness of the combination against our collection
				if (collection[getValue(i, j)-1][other.getValue(i, j)-1] == true) {
					//System.out.println("Matrix " + name + " and " + other.name + " are not orthogonal.");
					return false;
				}
				collection[getValue(i, j)-1][other.getValue(i, j)-1] = true;
			}
		}
		
		// All checks pass
		//System.out.println("Matrix " + name + " and " + other.name + " are orthogonal.");
		return true;
	}

	public void rowSwap(byte y1, byte y2) {
		byte [] temp = new byte [dim];
		
		// Iterate through a row
		for (byte i = 0; i < dim; i++) {
			temp[i] = getValue(i,y1);
			setValue(i,y1, getValue(i,y2));
			setValue(i,y2, temp[i]);
		}
	}

	public void cLatinSquareswap(byte x1, byte x2) {
		byte [] temp = new byte [dim];
		
		// Iterate through a column
		for (byte i = 0; i < dim; i++) {
			temp[i] = getValue(x1,i);
			setValue(x1,i, getValue(x2,i));
			setValue(x2,i, temp[i]);
		}
	}
	
}




















