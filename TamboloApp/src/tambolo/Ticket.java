package tambolo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class Ticket{
	//Values which can be given by the user to custamize the experience
	static int rows = 3;
	static int cols = 9;
	static int numPerRow = 5; 
	static int lowerLimit = 1;
	static int upperLimit = 90;
	static int rangeOfCol = 10;
	static String[] names = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
	
	Integer ticketContents[][];//the numbers in the ticket along with position
	Boolean numStatus[][]; //true=num, false = cut, null=empty
	HashMap<Integer, List<Integer>> universalSet = new HashMap<>();
	
	void initSet() { //initializing the domain of numbers for the ticket
		int num = lowerLimit;
		for(int i = 0; i<cols; i++) { //since column numbers for reference should start from 1
			universalSet.put(i, new ArrayList<>(rangeOfCol));
			for(int j = 0; j<rangeOfCol; j++) {
				universalSet.get(i).add(num++);
				universalSet.put(i, universalSet.get(i));
			}
		}
	}
	
	//shuffling the true set to determine positions
	Boolean[] shuffle() {
		Boolean numPresent[] = new Boolean[cols]; //array to denote if a number is present at said position
		for(int i = 0; i<cols; i++) {
			if(i<numPerRow)
				numPresent[i] = true;
			else
				numPresent[i] = null;
		}
		//shuffling the so formed array
		List<Boolean> placementList = Arrays.asList(numPresent);
		Collections.shuffle(placementList);
		placementList.toArray(numPresent);
		return numPresent;
	}
	
	Integer[][] generator() {
		initSet(); //so that any ticket will remove elements from its own local universal set
		
		ticketContents = new Integer[rows][cols];
		for(int i = 0; i<rows; i++) {
			for(int j = 0; j<cols; j++) {
				ticketContents[i][j] = null; //to avoid null
			}
		}
		//assign the places which will hold a number
		numStatus = new Boolean[rows][cols];
		for(int i = 0; i<rows; i++) {
			numStatus[i] = shuffle();
		}
		
		//assign numbers to the places they should go.
		for(int i = 0; i< rows; i++) {
			for(int j = 0; j<cols; j++) {
				//since at this point only values are true and null, only checking for null
				if(numStatus[i][j] != null ) {
					Collections.shuffle(universalSet.get(j));
					ticketContents[i][j] = universalSet.get(j).get(0);
					universalSet.get(j).remove(0);
				}
			}
		}
		
		//Sort the columns in ascending order
		int indexOfLowest = 0;
		for(int i = 0; i<cols; i++) { //since each column is to be sorted
			for(int j = 0; j<rows; j++) { //the rows are to be sorted
				indexOfLowest = j;
				for(int k = j+1; k<rows; k++) {
					//if(ticketContents[k][i] != null && ticketContents[indexOfLowest][i] != null) { //avoiding null pointer exception (alternatively try catch)
					try {	
						if(ticketContents[k][i] < ticketContents[indexOfLowest][i]) {
							indexOfLowest = k;
						}
					} catch(NullPointerException e) {
						
					}
				}
				/*
				 * Here, standard selection sort is applied.
				 * indexOfLowest stores the index of the lowest number in the column
				 * it's value is then stored in a temp variable
				 * Since, this value has to come to the current interation of the "J" loop
				 	we override it with the current value in the Jth row of the Ith column
				 * Now, we give the current location (the Jth row), the value that we stored in temp
				 	vis-a-vis the lowest obtained number for this iteration of the loop
				 */
				Integer temp = ticketContents[indexOfLowest][i];
				ticketContents[indexOfLowest][i] = ticketContents[j][i];
				ticketContents[j][i] = temp;
			}
		}
		
		//testing purposes
//		for(int i = 0; i<rows; i++) {
//			for( int j = 0; j<cols; j++) {
//				System.out.print(ticketContents[i][j] + "      ");
//			}
//			System.out.println();
//		} 
		
		return ticketContents;
	}
	
//	public static void main(String[] args) {
//		Ticket ob = new Ticket();
//		ob.generator();
//	}
}
