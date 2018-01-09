package MagicHunter;

public class Transversal {
	// Transversals are portential components of a nxn latin matrix
	//public byte rowindex; // [0,n-1]
	//public byte[] colvalues; // values in range [1,n] with n-1 values total

	private byte[][] transversalSet;
	private byte dim;
	private int cardinality;
	private int currentLine;
	
	// Constructor
	public Transversal(byte initDim) {
		dim = initDim;
		cardinality = 1;
		currentLine = 0;
		
		// calculate Factorial(dim)
		for (int i = 2; i <= dim; i++) cardinality *= i;
		
		transversalSet = new byte[cardinality][dim];
		
		byte[] initSet = new byte[dim];
		for (byte k = 0; k < dim; k++) initSet[k] = (byte) (k + 1);
		
		permute(initSet);
		gapfiller();
	}
	
	// Simple Accessors
	public byte[] getTransversal(int index) {
		return transversalSet[index];
	}
	
	// Private Methods
	private void permute(byte[] set) {
		
		if (set.length == 1) {
			if (currentLine < cardinality) transversalSet[currentLine][dim - 1] = set[0];
			return;
		}
		
		for (byte k = 0; k < set.length; k++){
			byte[] subset = new byte[set.length-1];

			if (k > 0) ++currentLine;
			transversalSet[currentLine][dim - set.length] = set[k];
			subset = removekth(k, set);
			permute(subset);
		}
	}
	
	private byte[] removekth(byte index, byte[] set) {
		
		byte[] rtrnSet = new byte[set.length - 1];
		
		byte k = 0;
		for (byte i = 0; i < set.length; i++){
			if (i != index && k < rtrnSet.length) { 
				rtrnSet[k] = set[i];
				k++;
			}
		}
			
		return rtrnSet;
	}

	private void gapfiller() {
		for (int i = 1; i < cardinality; i++) {
			for (int j = 0; j < dim; j++) {
				if (transversalSet[i][j] == 0)
					transversalSet[i][j] = transversalSet[i-1][j];
			}
		}
	}

	// Public Methods
	public void printTransversals() {
		
		for (int i = 0; i < cardinality; i++) {
			for (int j = 0; j < dim; j++) {
				System.out.print(transversalSet[i][j]);
			}
			System.out.print("\n");
		}
	}
	
}




















