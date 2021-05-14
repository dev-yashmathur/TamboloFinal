package tambolo;

import java.util.ArrayList;
import java.util.List;

public class StaticItems {
	static int rows = 3;
	static int cols = 9;
	static int numPerRow = 5; 
	static int lowerLimit = 1;
	static int upperLimit = 90;
	static int rangeOfCol = 10;
	static String[] player_names;
	static String[] col_names = new String[cols];
	static ArrayList<Integer> left = new ArrayList<Integer>(StaticItems.upperLimit-StaticItems.lowerLimit+1);
	static List<Integer> done = new ArrayList<>();
}
