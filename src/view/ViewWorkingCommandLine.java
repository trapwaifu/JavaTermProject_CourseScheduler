package view;

import java.util.ArrayList;

import course.Course;
import course.CourseData;

public class ViewWorkingCommandLine implements View{
	public void view() {
		ArrayList<Course> data = CourseData.getInstance().getData();
		for(int i = 0; i < data.size(); ++i)
			System.out.println(i + " : " + data.get(i));
	}
}
