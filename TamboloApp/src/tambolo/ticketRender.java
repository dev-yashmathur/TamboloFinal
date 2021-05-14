package tambolo;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumnModel;

@SuppressWarnings("serial")
class TickTable extends JTable {
	Boolean numStatus_table[][];
	TickTable(Object[][] contents, Object[] colNames, Boolean ns[][], int dim) {
		super(contents, colNames);
		
		numStatus_table = ns;
		setSize(450,150);
		TableColumnModel colModel = this.getColumnModel();
		for (int column = 0; column < this.getColumnCount(); column++) {
	        colModel.getColumn(column).setPreferredWidth(dim);
	        colModel.getColumn(column).setMinWidth(25);
	    }
		this.setRowHeight(dim);
		
		//this.setDefaultEditor(Integer.class, null);
		this.setDefaultRenderer(Integer.class, new NumStatusRender(this));
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
	TickTable table;
	ticketRender (String name) {
		super();
		Ticket ob = new Ticket();
		table = new TickTable(ob.generator(), Ticket.names, ob.numStatus, 50);
		//table.numStatus_table = ob.numStatus; //added to constructor
		JPanel tableLayer = new JPanel(); //layer to store the table, so as to give it a header
		
		tableLayer.add(table);
		Font name_font = new Font(Font.MONOSPACED, Font.BOLD, 21);
		TitledBorder ticket_name = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2, true), 
				name, TitledBorder.CENTER, TitledBorder.BELOW_TOP);
		ticket_name.setTitleFont(name_font);
		tableLayer.setBorder(ticket_name);
		tableLayer.setBackground(new Color(204, 221, 238));
		this.add(tableLayer);
		this.setBackground(new Color(196, 250, 248));
	}
	
	rc_container checkForNum(int num) {
		rc_container container = new rc_container();
		if(num < 10 && num > 0) {
			for(int i = 0; i< Ticket.rows; i++) { 
				try {
					int table_val = (int) table.getValueAt(i, 0);
					if(table_val == num) {
						table.numStatus_table[i][0] = false; //cut
						container.present = true; //denoting change
						container.row = i;
						container.col = 0;
						return container;
					}
				}catch(NullPointerException e) {
					
				}
			}
		}
		else {
			int col = (int)num/Ticket.rangeOfCol; 
			if(num == Ticket.upperLimit) {
				col--;
			}
			//System.out.println(col);
			for(int i = 0; i<Ticket.rows; i++) {
				try {
					int table_val = (int) table.getValueAt(i, col);
					//System.out.println(table_val);
					if(table_val == num) {
						table.numStatus_table[i][col] = false; //cut
						container.present = true; //denoting change
						container.row = i;
						container.col = col;
						return container;
					}
				}catch (NullPointerException e) {
					
				}
			}
			
		}
		container.present = false;//if no changes were reported then return false
		return container;
	}
	

	
}
