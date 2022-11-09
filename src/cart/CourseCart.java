package cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import course.Course;

public class CourseCart {
	private static CourseCart instance;
	ArrayList<Course> cart;
	Map<Integer, Boolean> bookedTime;
	
	private CourseCart() {
		reset();
	}
	
	public static CourseCart getInstance() {
		if(instance == null)
			instance = new CourseCart();
		return instance;
	}
	public ArrayList<Course> getCart() {
		return cart;
	}
	
	public boolean add(Course c) {
		// check for conflicts
		// return true if there are no conflicts
		
		for(var time : c.time) {
			if(bookedTime.containsKey(time) && bookedTime.get(time) == true) {
				JOptionPane.showMessageDialog(null, "시간표 충돌");
				return false;
			}
		}
		for(var bookedCourses : cart) {
			if(bookedCourses.code.equals(c.code)) {
				JOptionPane.showMessageDialog(null, "이미 수강한 과목");
				return false;
			}
		}
		
		for(var time : c.time) {
			bookedTime.put(time, true);
		}
		
		cart.add(c);
		
		return true;
	}
	public void remove(int index) {
		Course course = cart.get(index);
		for(var time : course.time)
			bookedTime.put(time, false);
		
		cart.remove(index);
	}
	public void reset() {
		cart = new ArrayList<Course>();
		bookedTime = new HashMap<Integer, Boolean>();
	}
}
