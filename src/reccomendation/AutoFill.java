package reccomendation;

import java.util.ArrayList;
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
	ArrayList<Course> candidate = null;
	public void autoFill() {
		int creditToReplace = 0;
		
		// clear all refinement and hrd courses
		// count how many credits to fill
		var original = CourseCart.getInstance().getCart();
		for(var course : original) {
			if(course.department.equals("HRD학과") || course.department.equals("교양학부"))
				creditToReplace += course.credit;
		}
		
		var data = original.stream().filter(p -> !(p.department.equals("HRD학과") || p.department.equals("교양학부")))
				.collect(Collectors.toCollection(ArrayList::new));
		CourseCart.getInstance().reset();
		for(var course : data)
			CourseCart.getInstance().add(course);
		
		// perform dfs and find all possible candidates
		// if we can use the cached result, use it
		data = CourseData.getInstance().getAllData();
		data = data.stream().filter(p -> (p.department.equals("HRD학과") || p.department.equals("교양학부")))
				.collect(Collectors.toCollection(ArrayList::new));
		
		candidate = new ArrayList<Course>();
		Random rand = new Random();
		
		int start = rand.nextInt(data.size());
		int end = start == 0 ? data.size() - 1 : start - 1;
		dfs(data, original, creditToReplace, start, end);
		
		// add to cart
		
//		int pick = rand.nextInt(cache.size());
		for(var course : candidate) {
			CourseCart.getInstance().add(course);
		}		
	}
	
	private boolean dfs(ArrayList<Course> data, ArrayList<Course> original,
			int remainingCredits, int index, int endIndex) {
		
		if(index >= data.size() || remainingCredits < 0 
				|| notPossibleComination(candidate, original)) { 
			return false;
		}
		if(remainingCredits == 0) {
			return true;
		}
		
		for(int i = index; i != endIndex; i = (i + 1) % data.size()) {
			candidate.add(data.get(i));
			
			if(dfs(data, original, remainingCredits - data.get(i).credit, (i + 1) % data.size(), endIndex)) return true;
			
			candidate.remove(candidate.size() - 1);
		}
		return false;
	}
	// Could be more efficient by checking only the last element that was added
	// and keeping track of the distinct names and class times
	private boolean notPossibleComination(ArrayList<Course> current, ArrayList<Course> original) {
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
		for(var course : original) {
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
	private void clearCart() {
		;
	}
	private int getRefinementHRDCredit() {
		return 0;
	}
	private void find_candidate() {
		;
	}
	private void hash_courses() {
		;
	}
	public void init() {
		
	}
}
