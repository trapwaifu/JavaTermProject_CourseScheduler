package event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import cart.CourseCart;
import course.Course;
import course.CourseData;
import view.View;

public class ClickAddToCartButton implements ActionListener {
	JTable courseList;
	View courseCartView;
	
	public ClickAddToCartButton(JTable courseList, View courseCartView) {
		super();
		this.courseList = courseList;
		this.courseCartView = courseCartView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int row = courseList.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(null, "Select a course!");
			return;
		}
		
		Course selected = CourseData.getInstance().getData().get(row); 
		CourseCart.getInstance().add(selected);
		
		courseCartView.view();
	}

}