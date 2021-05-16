package tambolo;

public class Prize_Check {

	static boolean CORNERS(ticketRender ob) {
		for(int i = 0; i<StaticItems.rows; i++) {
			if(ob.onlyNumsInOrder[i][0].present==false && ob.onlyNumsInOrder[i][StaticItems.numPerRow-1].present == false) {
				continue;
			}
			else
				return false;
		}
		return true;
	}
	
	static boolean LINES(ticketRender ob, int line_no) {
		for(int i = 0; i<StaticItems.numPerRow; i++) {
			if(ob.onlyNumsInOrder[line_no][i].present == false)
				continue;
			else
				return false;
		}
		return true;
	}
	
	static boolean HOUSIE(ticketRender ob) {
		if(ob.countOfDone == (StaticItems.rows * StaticItems.numPerRow)) {
			return true;
		}
		else
			return false;
	}
	
	static boolean FIRST_N(ticketRender ob) {
		if(ob.countOfDone == Prizes.First_To) {
			return true;
		}
		else
			return false;
	}
}
