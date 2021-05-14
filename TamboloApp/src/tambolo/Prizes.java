package tambolo;

import java.util.ArrayList;
import java.util.List;

//The variables required for the entire game play
public class Prizes {
	static ArrayList<Integer> left = new ArrayList<Integer>(Ticket.upperLimit-Ticket.lowerLimit+1);
	static List<Integer> done = new ArrayList<>();
	//set them in the actionListeners of swing interfaces.
	//NOTE: null = not present, true = left, false = finished
	static Boolean LINE_PRIZES[];
	static Boolean FULL_HOUSE[];
	static Boolean FIRST_N;
	static Boolean DAY;
	static Boolean NIGHT;
	static Boolean RAILWAYS;
	
}
