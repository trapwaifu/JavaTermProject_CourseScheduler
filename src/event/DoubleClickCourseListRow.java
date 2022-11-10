package event;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import cart.CourseCart;
import course.Course;
import course.CourseData;
import view.View;

public class DoubleClickCourseListRow extends MouseAdapter{
	private View viewCourseCart;
	public DoubleClickCourseListRow(View view2) {
		this.viewCourseCart = view2;
	}
	@Override
	public void mouseClicked(MouseEvent event) {
		if(event.getClickCount() == 2) {
			JTable table = (JTable) event.getSource();
			int row = table.getSelectedRow();
			
			Course selected = CourseData.getInstance().getData().get(row); 
			CourseCart.getInstance().add(selected);
//			JOptionPane.showMessageDialog(table, selected);
			viewCourseCart.view();
		}
	}
}
