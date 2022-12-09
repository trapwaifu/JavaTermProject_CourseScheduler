package reccomendation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import cart.CourseCart;
import course.Course;
import course.CourseData;
import view.View;

public class AutoFill {
	
	
//	String hash;
//	ArrayList<ArrayList<Course>> cache = new ArrayList<>();
	public void autoFill() {
		int creditToReplace = 0;
		
		// clear all refinement and hrd courses
		// count how many credits to fill
		var original = CourseCart.getInstance().getCart();
		for(var course : original) {
			if(course.department.equals("HRD학과") || course.department.equals("교양학부"))
				creditToReplace += course.credit;
		}
		
		var coursesToKeep = original.stream().filter(p -> !(p.department.equals("HRD학과") || p.department.equals("교양학부")))
				.collect(Collectors.toCollection(ArrayList::new));
		
		// perform dfs and find all possible candidates
		// if we can use the cached result, use it
		var data = CourseData.getInstance().getAllData();
		data = data.stream().filter(p -> (p.department.equals("HRD학과") || p.department.equals("교양학부")))
				.collect(Collectors.toCollection(ArrayList::new));
		
		ArrayList<Course> candidate = new ArrayList<Course>();
		for(var course : coursesToKeep) candidate.add(course);
		
		Random rand = new Random();
		
		Collections.shuffle(data);
		dfs(data, candidate, creditToReplace, 0);
		
		// add to cart
		
		if(candidate.size() > coursesToKeep.size()) {
			CourseCart.getInstance().reset();
			for(var course : candidate) {
				CourseCart.getInstance().add(course);
			}		
		}
//		else {
//			for(var course : original) {
//				CourseCart.getInstance().add(course);
//			}
//		}
	}
	
	private boolean dfs(ArrayList<Course> data, ArrayList<Course> candidate, int remainingCredits, int index) {
		
		if(index >= data.size() || remainingCredits < 0 
				|| notPossibleComination(candidate)) { 
			return false;
		}
		if(remainingCredits == 0) {
			return true;
		}
		
		for(int i = index; i < data.size(); ++i) {
			candidate.add(data.get(i));
			
			if(dfs(data, candidate, remainingCredits - data.get(i).credit, i+1)) return true;
			
			candidate.remove(candidate.size() - 1);
		}
		return false;
	}
	// Could be more efficient by checking only the last element that was added
	// and keeping track of the distinct names and class times
	private boolean notPossibleComination(ArrayList<Course> current) {
		Set<String> distinctCourseNames = new HashSet<>();
		Set<Integer> distinctClassTimes = new HashSet<>();
		
		for(var course : current) {
			if(distinctCourseNames.contains(course.courseName))
				return true;
			distinctCourseNames.add(course.courseName);
			
			for(var time : course.time) {
				if(distinctClassTimes.contains(time))
					return true;
				distinctClassTimes.add(time);
			}
		}	
		
		return false;
	}
}
