package sort;

import java.util.Comparator;

import course.Course;
import course.CourseData;

public class SortCourseName implements Sort {
	@Override
	public void sort() {
		var data = CourseData.getInstance().getData();
		data.sort(
				new Comparator<Course>() {
					@Override
					public int compare(Course c1, Course c2) {
						return c1.courseName.compareTo(c2.courseName);
					}
				});
	}
}
