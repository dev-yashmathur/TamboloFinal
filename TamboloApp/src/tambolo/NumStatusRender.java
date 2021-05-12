package tambolo;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class NumStatusRender extends JLabel implements TableCellRenderer {
	
	NumStatusRender() {
		Font num_font = new Font(Font.MONOSPACED, Font.BOLD, 20);
		this.setFont(num_font);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setOpaque(true);
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Integer current = (Integer) value;
		if(current == null) {
			this.setText("");
			return this;
		}
		
		else {
			this.setText(Integer.toString(current));
			return this;
		}
		
	}

}
