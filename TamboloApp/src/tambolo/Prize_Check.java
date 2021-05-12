package tambolo;

public class Prize_Check {
	ticketRender current;
	Boolean[][] numStatus_local;
	Prize_Check(ticketRender ob) {
		current = ob;
		numStatus_local = current.table.numStatus_table;
	}
	
	boolean LINE(int line_no) {
		boolean status = true;
		for(int col = 0; col<Ticket.cols; col++) {
			if(numStatus_local[line_no][col] == null || numStatus_local[line_no][col] == false ) {
				continue;
			}
			else {
				status = false;
				break;
			}
		}
		return status;
	}
}
