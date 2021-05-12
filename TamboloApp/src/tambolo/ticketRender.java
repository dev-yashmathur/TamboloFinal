package tambolo;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
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
	        colModel.getColumn(column).setMinWidth(25);
	    }
		this.setRowHeight(dim);
		
		//this.setDefaultEditor(Integer.class, null);
		this.setDefaultRenderer(Integer.class, new NumStatusRender());
		this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
	}
	@Override
	public Class<?> getColumnClass(int column) {
		return Integer.class;
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}

@SuppressWarnings("serial")
public class ticketRender extends JPanel{
	ticketRender (String name) {
		super();
		Ticket ob = new Ticket();
		TickTable table = new TickTable(ob.generator(), Ticket.names, 50);
		JPanel tableLayer = new JPanel(); //layer to store the table, so as to give it a header
		
		tableLayer.add(table);
		Font name_font = new Font(Font.MONOSPACED, Font.BOLD, 21);
		TitledBorder ticket_name = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2, true), 
				name, TitledBorder.CENTER, TitledBorder.BELOW_TOP);
		ticket_name.setTitleFont(name_font);
		tableLayer.setBorder(ticket_name);
		this.add(tableLayer);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		ticketRender test = new ticketRender("name");
		ticketRender test2 = new ticketRender("name2");
		//frame.setLayout(new FlowLayout());
		frame.add(test);
		frame.add(test2);
		frame.pack();
		frame.setVisible(true);
	}
}
