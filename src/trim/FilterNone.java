package trim;

import course.CourseData;

public class FilterNone implements Filter {

	@Override
	public void filter(String Department) {
		// TODO Auto-generated method stub
		CourseData.getInstance().reset();
	}

}
