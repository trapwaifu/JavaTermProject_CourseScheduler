package event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import cart.CourseCart;
import course.Course;
import course.CourseData;
import view.View;

public class ClickRemoveCourseButton implements ActionListener {
	JTable courseList;
	View view;
		
	public ClickRemoveCourseButton(JTable courseList, View view) {
		super();
		this.courseList = courseList;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int row = courseList.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(courseList, "과목을 선택하세요");
			return;
		}
		
		Course selected = CourseCart.getInstance().getCart().get(row); 
		CourseCart.getInstance().remove(row);

		
		view.view();
	}
}
