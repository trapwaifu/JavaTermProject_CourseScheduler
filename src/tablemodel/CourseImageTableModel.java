package tablemodel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import course.Course;
import course.CourseData;

public class CourseImageTableModel extends AbstractTableModel {
	private String[] columnNames =  {"", "월요일", "화요일", "수요일", "목요일", "금요일"};
	private String[][] table = null;
	
	private static final int row_count = 20;
	private static final int col_count = 6;
	
	public CourseImageTableModel() {
		table = new String[row_count][col_count];
		for(int j = 0; j < col_count; ++j) {
			table[0][j] = columnNames[j];
		}
		for(int i = 1; i < row_count; ++i) {
			String time = String.valueOf((i / 2) + 1) + (i % 2 == 0 ? "A" : "B")
					+ " - "
					+ String.valueOf((i / 2) + 9) + ":" + (i % 2 == 0 ? "00" : "30");
			table[i][0] = time;
			for(int j = 1; j < col_count; ++j) {
				table[i][j] = "";
			}
		}
	}

	@Override
	public int getRowCount() {
		return table.length;
	}
	@Override 
	public int getColumnCount() {
		return columnNames.length;
	}
	@Override 
	public String getColumnName(int column) {
		return columnNames[column];
	}	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return table[rowIndex][columnIndex];
	}
}