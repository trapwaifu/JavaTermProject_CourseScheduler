package tablemodel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import course.Course;
import course.CourseData;

public class CourseDataTableModel extends AbstractTableModel {
	private String[] columnNames =  {"코드", "과목명", "분반", "교수님", "대상", "학점", "비고", "정원", "설계", "개설학부"};
	private String[][] formatted_data = null;
	
	public CourseDataTableModel(ArrayList<Course> courseData) {
		formatted_data = new String[courseData.size()][columnNames.length];

		// change credit variables to int (maybe?)
		for(int i = 0; i < courseData.size(); ++i) {
			formatted_data[i][0] = courseData.get(i).code;
			formatted_data[i][1] = courseData.get(i).courseName;
			formatted_data[i][2] = courseData.get(i).division;
			formatted_data[i][3] = courseData.get(i).professor;
			formatted_data[i][4] = courseData.get(i).target;
			formatted_data[i][5] = String.valueOf(courseData.get(i).credit);
			formatted_data[i][6] = courseData.get(i).misc;
			formatted_data[i][7] = courseData.get(i).max_students;
			formatted_data[i][8] = String.valueOf(courseData.get(i).designCredit);
			formatted_data[i][9] = courseData.get(i).department;

		}
	}

	@Override
	public int getRowCount() {
		return formatted_data.length;
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
		return formatted_data[rowIndex][columnIndex];
	}
}