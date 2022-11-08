package view;

import javax.swing.JTable;

import tablemodel.CourseDataTableModel;
import tablemodel.CourseImageTableModel;

public class ViewCourseImageTableGUI implements View {
	JTable table = null;
	CourseDataTableModel tableModel = null;
	ViewCourseImageTableGUI(JTable tab) {
		this.table = tab;
	}
	
	@Override
	public void view() {
		CourseImageTableModel tableModel = new CourseImageTableModel();
		table.setModel(tableModel);
	}
}
