package trim;

import java.util.ArrayList;
import java.util.stream.Collectors;

import course.CourseData;

public class Search {
	public void search(String query) {
		if(query == null || query.equals("")) return;
		
		var data = CourseData.getInstance().getData();
		data = data.stream().filter(p -> p.courseName.contains(query)).collect(Collectors.toCollection(ArrayList::new));
		CourseData.getInstance().update(data);
	}
}
