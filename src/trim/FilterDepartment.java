package trim;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import course.CourseData;

public class FilterDepartment implements Filter {

	@Override
	public void filter(String department) {
		ArrayList<String> departmentList = new ArrayList<>(
				List.of("컴퓨터공학부", "HRD학과", "교양학부", "기계공학부", "메카트로닉스공학부","에너지신소재화학공학부", 
						"전기ㆍ전자ㆍ통신공학부", "산업경영학부", "메카트로닉스공학부", "디자인ㆍ건축공학부", "융합학과"));
		
		if(!departmentList.contains(department)) {
//			System.out.println("Invalid department: " + department);
			CourseData.getInstance().reset();
			return;
		}
//		System.out.println("here");
		CourseData.getInstance().reset();
		var data = CourseData.getInstance().getData();
		data = data.stream().filter(p -> p.department.equals(department)).collect(Collectors.toCollection(ArrayList::new));
		CourseData.getInstance().update(data);
	}

}
