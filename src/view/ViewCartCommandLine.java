package view;

import java.util.ArrayList;

import cart.CourseCart;
import course.Course;

public class ViewCartCommandLine implements View {

	@Override
	public void view() {
		ArrayList<Course> data = CourseCart.getInstance().getCart();
		for(int i = 0; i < data.size(); ++i)
			System.out.println(i + " : " + data.get(i));
	}

}
