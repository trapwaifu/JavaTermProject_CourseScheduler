package observerinterface;

import java.util.ArrayList;

import course.Course;

public interface CourseCartObserver {
	public void updateAdd(Course course);
	public void updateRemove(Course course);
	public void updateReset();
}
