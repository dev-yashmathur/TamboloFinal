package tambolo;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;

@SuppressWarnings("serial")
public class TicketTable extends AbstractTableModel {
//	Ticket ob = new Ticket();
	Integer[][] contents;
	JPanel ticketPanel;
		
	TicketTable(Integer[][] nums) {
		contents = nums; 
	}
	
	@Override 
	public int getRowCount() {
		return StaticItems.rows;
	}
	@Override 
	public int getColumnCount() {
		return StaticItems.cols;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return contents[rowIndex][columnIndex];
	}
	
	@Override
	public Class<?> getColumnClass(int column) {
		return Integer.class;
	}
	

//	public static void main(String[] args) {
//		Ticket ob = new Ticket();
//		JFrame frame = new JFrame();
//		frame.setLayout(new FlowLayout());
//		JTable myTable = new JTable(new TicketTable(ob.generator()));
//		frame.add(myTable);
//		
//		frame.pack();
//		frame.setVisible(true);
//	}
}
