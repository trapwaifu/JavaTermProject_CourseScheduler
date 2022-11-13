package sort;

import java.util.Comparator;

import course.Course;
import course.CourseData;

public class SortDepartment implements Sort {
	@Override
	public void sort() {
		var data = CourseData.getInstance().getData();
		
		// determine whether the data is sorted already
		var copy = data.clone();
		
		data.sort(
				new Comparator<Course>() {
					@Override
					public int compare(Course c1, Course c2) {
						return c1.department.compareTo(c2.department);
					}
				});
		
		if(data.equals(copy)) 
			data.sort(
					new Comparator<Course>() {
						@Override
						public int compare(Course c1, Course c2) {
							return c2.department.compareTo(c1.department);
						}
					});
	}
}
