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
	String nameOfHolder;
	int countOfDone = 0;
	rc_container[][] onlyNumsInOrder;
	TickTable table;
	ticketRender (String name) {
		super();
		Ticket ob = new Ticket();
		try {
		nameOfHolder = name;
		}catch(NullPointerException except) {
			nameOfHolder = "Name Not Availible";
		}
		table = new TickTable(ob.generator(), StaticItems.col_names, ob.numStatus, 50);
		onlyNumsInOrder = numbersInRowFinder(this);
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
		this.setBackground(new Color(110, 202, 255));
	}
	
	rc_container checkForNum(int num) {
		rc_container container = new rc_container();
		if(num < 10 && num > 0) {
			for(int i = 0; i< StaticItems.rows; i++) { 
				try {
					int table_val = (int) table.getValueAt(i, 0);
					if(table_val == num) {
						this.countOfDone++;
						onlyNumsInOrder[i][0].present = false;
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
			int col = (int)num/StaticItems.rangeOfCol; 
			if(num == StaticItems.upperLimit) {
				col--;
			}
			//System.out.println(col);
			for(int i = 0; i<StaticItems.rows; i++) {
				try {
					int table_val = (int) table.getValueAt(i, col);
					//System.out.println(table_val);
					if(table_val == num) {
						this.countOfDone++;
						table.numStatus_table[i][col] = false; //cut
						for(int column = 0; column<StaticItems.numPerRow; column++) {
							if(onlyNumsInOrder[i][column].col == col) {
								onlyNumsInOrder[i][column].present = false; //because column "1" represents column number 2 
								break;
							}
						}
						
						container.present = true; //denoting change
						container.row = i;
						container.col = col;
						return container;
					}
				}catch (NullPointerException e) {
					//System.out.println("NPE");
				}
			}
			
		}
		container.present = false;//if no changes were reported then return false
		return container;
	}
	
	rc_container[][] numbersInRowFinder(ticketRender ob) {
		rc_container[][] numInRow = new rc_container[StaticItems.rows][StaticItems.numPerRow];
		for(int i = 0; i<StaticItems.rows; i++) {
			int col = 0;
			for(int j = 0; j<StaticItems.cols; j++) {
				try {
					Boolean a = ob.table.numStatus_table[i][j];
					if(a == null)
						continue;
					else {
						rc_container current = new rc_container();
						current.row = i;
						current.col = j;
						current.present = a;
						numInRow[i][col] = current;
						col++;
					}
				}catch(NullPointerException e) {
					
				}
			}
		}
		return numInRow;
	}

	
}
