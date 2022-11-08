package view;

import java.util.ArrayList;

import javax.swing.JTable;

import cart.CourseCart;
import course.Course;
import tablemodel.CourseDataTableModel;

public class ViewCourseCartGUI implements View {
	JTable table = null;
	CourseDataTableModel tableModel = null;
	ViewCourseCartGUI(JTable tab) {
		this.table = tab;
	}
	
	@Override
	public void view() {
		ArrayList<Course> data = CourseCart.getInstance().getCart();
		CourseDataTableModel tableModel = new CourseDataTableModel(data);
		table.setModel(tableModel);
	}
}
