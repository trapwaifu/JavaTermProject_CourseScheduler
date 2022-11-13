package sort;

import java.util.Comparator;

import course.Course;
import course.CourseData;

public class SortDivision implements Sort {
	@Override
	public void sort() {
		var data = CourseData.getInstance().getData();
		
		var copy = data.clone();

		data.sort(
				new Comparator<Course>() {
					@Override
					public int compare(Course c1, Course c2) {
						return c1.division.compareTo(c2.division);
					}
				});
		
		if(data.equals(copy)) 
			data.sort(
					new Comparator<Course>() {
						@Override
						public int compare(Course c1, Course c2) {
							return c2.division.compareTo(c1.division);
						}
					});

	}
}
