package tambolo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class NumStatusRender extends JLabel implements TableCellRenderer {
	int cnt = 0;
	TickTable tab;
	Boolean[][] NUM_STATUS;
	
	NumStatusRender(TickTable tb) {
		tab = tb;
		Font num_font = new Font(Font.MONOSPACED, Font.BOLD, 20);
		this.setFont(num_font);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setOpaque(true);
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		NUM_STATUS = tab.numStatus_table;
//		for(int i = 0; i<3; i++) {
//			for(int j = 0; j<9; j++) {
//				System.out.print(NUM_STATUS[i][j] + "  ");
//			}
//			System.out.println();
//		}
//		System.out.println(cnt++ + " ");
//		System.out.println();
		Integer current = (Integer) value;
		try {
			if(NUM_STATUS[row][column] == null  ) {
				this.setBackground(new Color(204, 255, 204));
				this.setText("");
				return this;
			}
			
			else if(NUM_STATUS[row][column] == false){
				this.setBackground(new Color(255, 204, 255));
				this.setText(Integer.toString(current));
				return this;
			}
			else {
				this.setBackground(new Color(204, 255, 204));
				this.setText(Integer.toString(current));
				return this;
			}
		}catch (NullPointerException e) {
			System.out.println("NPE");
			return this;
		}
		
	}

}
