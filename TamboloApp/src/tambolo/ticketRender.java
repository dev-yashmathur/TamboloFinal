package tambolo;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

@SuppressWarnings("serial")
class TickTable extends JTable {
	
	TickTable(Object[][] contents, Object[] colNames, int dim) {
		super(contents, colNames);
		setSize(450,150);
		TableColumnModel colModel = this.getColumnModel();
		for (int column = 0; column < this.getColumnCount(); column++) {
	        colModel.getColumn(column).setPreferredWidth(dim);
	    }
		this.setRowHeight(dim);
		
		//this.setDefaultEditor(Integer.class, null);
		this.setDefaultRenderer(Integer.class, new NumStatusRender());
	}
	@Override
	public Class<?> getColumnClass(int column) {
		return Integer.class;
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
//	public static void main(String[] args) {
//		Ticket ob = new Ticket();
//		JFrame frame = new JFrame();
//		frame.setLayout(new FlowLayout());
//		frame.add(new TickTable(ob.generator(), Ticket.names, 40) );
//		
//		frame.pack();
//		frame.setVisible(true);
//	}
}

@SuppressWarnings("serial")
public class ticketRender extends JPanel{
	Ticket ob = new Ticket();
	TickTable table = new TickTable(ob.generator(), Ticket.names, 40);
	JLabel ticketHeader;
	//JPanel ticketPanel;
	JScrollPane jsp;
	ticketRender () {
		super();
		ticketHeader = new JLabel("DEMO");
		//jsp = new JScrollPane(table);
		//jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(ticketHeader);
		this.add(table);
		this.setSize(400, 120);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		ticketRender test = new ticketRender();
		frame.setLayout(new FlowLayout());
		frame.add(test);
		frame.pack();
		frame.setVisible(true);
	}
}
