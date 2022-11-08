package view;

import javax.swing.JTable;

import tablemodel.CourseDataTableModel;

public class ViewCourseImageTableGUI implements View {
	JTable table = null;
	CourseDataTableModel tableModel = null;
	ViewCourseImageTableGUI(JTable tab) {
		this.table = tab;
	}
	
	@Override
	public void view() {
		;
	}
}
