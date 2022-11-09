package event;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import cart.CourseCart;
import course.Course;
import course.CourseData;
import view.View;

public class DoubleClickCourseCartRow extends MouseAdapter{
	private View viewCourseCart;
	public DoubleClickCourseCartRow(View view) {
		this.viewCourseCart = view;
	}
	@Override
	public void mouseClicked(MouseEvent event) {
		if(event.getClickCount() == 2) {
			JTable table = (JTable) event.getSource();
			int row = table.getSelectedRow();
			
			Course selected = CourseCart.getInstance().getCart().get(row); 
			CourseCart.getInstance().remove(row);
//			JOptionPane.showMessageDialog(table, selected);
			viewCourseCart.view();
		}
	}
}
