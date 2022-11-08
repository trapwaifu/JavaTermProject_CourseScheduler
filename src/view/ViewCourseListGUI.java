package view;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import course.CourseData;

public class ViewCourseListGUI implements View {
	JTable table;
	ViewCourseListGUI(JTable tab) {
		this.table = tab;
	}
	@Override
	public void view() {
		CourseDataTableModel tableModel = new CourseDataTableModel();
		table.setModel(tableModel);
	}
	class CourseDataTableModel extends AbstractTableModel {
		private String[] columnNames =  {"코드", "과목명", "분반", "교수님", "대상", "학점", "비고", "정원", "설계", "개설학부"};
		private String[][] formatted_data = null;
		CourseDataTableModel() {
			CourseData courseData = CourseData.getInstance();
			formatted_data = new String[courseData.size()][columnNames.length];
			
			// delete later by supporting iterator to CourseData
			// change credit variables to int
			var raw_data = courseData.getData();
			for(int i = 0; i < courseData.size(); ++i) {
				formatted_data[i][0] = raw_data.get(i).code;
				formatted_data[i][1] = raw_data.get(i).courseName;
				formatted_data[i][2] = raw_data.get(i).division;
				formatted_data[i][3] = raw_data.get(i).professor;
				formatted_data[i][4] = raw_data.get(i).target;
				formatted_data[i][5] = String.valueOf(raw_data.get(i).credit);
				formatted_data[i][6] = raw_data.get(i).misc;
				formatted_data[i][7] = raw_data.get(i).max_students;
				formatted_data[i][8] = String.valueOf(raw_data.get(i).designCredit);
				formatted_data[i][9] = raw_data.get(i).department;
				
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
}
