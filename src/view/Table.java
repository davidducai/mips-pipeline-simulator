package view;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Table {

	private JTable table;
	private DefaultTableModel model;
	
	private boolean editable;
	
	@SuppressWarnings("serial")
	public Table(boolean isRegisterTable) {
		int rows = isRegisterTable ? 32 : 64;
		int columns = 2;
		
		editable = true;
		
		model = new DefaultTableModel(rows, columns) {
		    
			@Override 
		    public boolean isCellEditable(int row, int column) {
		        return !editable || column == 0 || (row == 0 && isRegisterTable) ? false : true;
		    }
		};
		
		table = new JTable(model);
		table.setTableHeader(null);
		setRows(isRegisterTable);
	}
	
	private void setRows(boolean isRegisterTable) {
		String prefix = isRegisterTable ? "$" : "M";
		
		for(int row = 0; row < model.getRowCount(); row++) {
			model.setValueAt(prefix + row, row, 0);
			model.setValueAt(0, row, 1);
		}
	}
	
	public void resetTable() {
		for(int row = 0; row < model.getRowCount(); row++) {
			model.setValueAt(0, row, 1);
		}
	}
	
	public void updateTable(ArrayList<Integer> values) {
		for(int row = 0; row < model.getRowCount(); row++) {
			model.setValueAt(values.get(row), row, 1);
		}
	}

	public JTable getTable() {
		return table;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}
}
