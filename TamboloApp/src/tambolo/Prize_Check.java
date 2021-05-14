package tambolo;

public class Prize_Check {


	static boolean LINE(ticketRender ob, int line_no) {
		ticketRender current = ob;
		Boolean[][] numStatus_local = current.table.numStatus_table;
		boolean status = true;
		for(int col = 0; col<StaticItems.cols; col++) {
			if(numStatus_local[line_no][col] == null || numStatus_local[line_no][col] == false ) {
				continue;
			}
			else {
				status = false;
				break;
			}
		}
		//System.out.println(status);
		return status;
	}
}
