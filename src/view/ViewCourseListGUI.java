package view;

import java.util.ArrayList;

import javax.swing.JTable;

import course.Course;
import course.CourseData;
import tablemodel.CourseDataTableModel;

public class ViewCourseListGUI implements View{
	JTable table = null;
	CourseDataTableModel tableModel = null;
	ViewCourseListGUI(JTable tab) {
		this.table = tab;
	}
	
	@Override
	public void view() {
		ArrayList<Course> data = CourseData.getInstance().getData();
		CourseDataTableModel tableModel = new CourseDataTableModel(data);
		table.setModel(tableModel);
	}
	
}
