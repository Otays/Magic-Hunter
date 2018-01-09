package MagicHunter;

import java.util.Scanner;

public class Driver {
	static Scanner userInput = new Scanner(System.in);

	public static void main(String[] args){
		LatinMatrix MatrixL;
		LatinMatrix MatrixM;

		System.out.println("----------                                                         ----------");
		System.out.println("----------    [ Magic Hunter ]                                     ----------");
		System.out.println("----------    An algorithm for finding orthogonal latin squares    ----------");
		System.out.println("----------                                                         ----------\n");

		String inputName1 = "L";
		String inputName2 = "M";
		byte inputSize = 0;

		System.out.println("Enter a name for the first matrix.");
		System.out.print(" > ");
		if(userInput.hasNextLine()){
			inputName1 = userInput.nextLine();
			if (inputName1.isEmpty()) inputName1 = "L";
		}

		System.out.println("Enter a name for the second matrix.");
		System.out.print(" > ");
		if(userInput.hasNextLine()){
			inputName2 = userInput.nextLine();
			if (inputName2.isEmpty()) inputName2 = "M";
		}
		
		System.out.println("Enter size (n) for the two nxn matrices.");
		System.out.print(" > ");
		if(userInput.hasNextByte()){
			inputSize = userInput.nextByte();
		}
		userInput.close();
		
		MatrixL = new LatinMatrix(inputSize, inputName1);
		MatrixM = new LatinMatrix(inputSize, inputName2);
		
		MatrixL.LatinFiller();

        Transversal transversals = new Transversal((byte)(inputSize));

        MatrixM.setMatrix(hunt(MatrixL, transversals));
        
        System.out.print("\n");
		MatrixL.PrintMatrix(MatrixM);	// Prints L and M side by side

        if (MatrixL.validityCheck() && MatrixM.validityCheck()){
        	System.out.println("Matrix " + MatrixL.getName() + " is valid.");
        	System.out.println("Matrix " + MatrixM.getName() + " is valid.");
        	if (MatrixL.orthohonalCheck(MatrixM)) {
        		System.out.println("Matrix " + MatrixL.getName() + " and " + MatrixM.getName() + " are orthogonal.");
        		return;
        	}
        }
        System.out.println("Matrix " + MatrixL.getName() + " has no orthogonal mates.");
	}
	
	public static LatinMatrix hunt(LatinMatrix MatrixA, Transversal transversals) {
		LatinMatrix MatrixB = new LatinMatrix(MatrixA.getDim(), "B");

		int subCardinality = 1;
		for (int i = 2; i < MatrixB.getDim(); i++) subCardinality *= i;
		
		int[] TraversalIndexes = new int[MatrixB.getDim()];
		
		while (!TraversalCheck(TraversalIndexes, subCardinality)) {

			int k = 0;
			boolean breakOut = false;
			while (k < MatrixB.getDim() && !breakOut) {
				TraversalIndexes[k]++;
				if (TraversalIndexes[k] >= subCardinality) {
					TraversalIndexes[k] = 0;
					k++;
				} else {
					breakOut = true;
				}
			}
			
			for (byte i = 0; i < MatrixB.getDim(); i++) { 
				MatrixB.setRow(i, transversals.getTransversal(TraversalIndexes[i] + i*subCardinality));
			}
			
			if (MatrixB.validityCheck()) {
				if (MatrixA.orthohonalCheck(MatrixB)) return MatrixB;
			}
			
		}
		
		return MatrixB;
		
	}
	
	public static boolean TraversalCheck(int[] indexList, int subCardinality) {
		
		for (int i = 0; i < indexList.length; i++) if (indexList[i] != subCardinality-1) return false;
		
		return true;
	}
	
}














