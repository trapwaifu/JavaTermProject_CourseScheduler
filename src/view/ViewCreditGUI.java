package view;

import javax.swing.JLabel;

import course.Course;
import observerinterface.CourseCartObserver;

public class ViewCreditGUI implements View, CourseCartObserver{
	private int credit = 0;
	private JLabel label;
	public ViewCreditGUI(JLabel label) {
		this.label = label;
	}
	
	@Override
	public void view() {
		label.setText("현재 학점 : " + credit);
	}
	
	@Override
	public void updateAdd(Course course) {
		credit += course.credit;
		view();
	}
	
	@Override
	public void updateRemove(Course course) {
		credit -= course.credit;
		view();
	}
	
	@Override 
	public void updateReset() {
		credit = 0;
		view();
	}
}
