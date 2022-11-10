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
	
	
	String hash;
	ArrayList<ArrayList<Course>> cache = new ArrayList<>();
	
	public void autoFill() {
		int creditToReplace = 0;
		
		// clear all refinement and hrd courses
		// count how many credits to fill
		var data = CourseCart.getInstance().getCart();
		for(var course : data) {
			if(course.department.equals("HRD학과") || course.department.equals("교양학부"))
				creditToReplace += course.credit;
		}
		
		data = data.stream().filter(p -> !(p.department.equals("HRD학과") || p.department.equals("교양학부")))
				.collect(Collectors.toCollection(ArrayList::new));
		CourseCart.getInstance().reset();
		for(var course : data)
			CourseCart.getInstance().add(course);
		
		// perform dfs and find all possible candidates
		// if we can use the cached result, use it
		data = CourseData.getInstance().getAllData();
		data = data.stream().filter(p -> (p.department.equals("HRD학과") || p.department.equals("교양학부")))
				.collect(Collectors.toCollection(ArrayList::new));
		
		ArrayList<Course> current = new ArrayList<Course>();
		dfs(data, current, creditToReplace, 0);
		
		// add to cart
		System.out.println(cache.size());
		Random rand = new Random();
		int pick = rand.nextInt(cache.size());
		for(var course : cache.get(pick)) {
			CourseCart.getInstance().add(course);
		}		
	}
	
	private void dfs(ArrayList<Course> data, ArrayList<Course> current,
			int remainingCredits, int index) {
		
		if(index >= data.size() || remainingCredits < 0 
				|| notPossibleComination(current)) { 
			return;
		}
		if(remainingCredits == 0) {
			cache.add(current);
			return;
		}
		
		for(int i = index; i < data.size(); ++i) {
			current.add(data.get(i));
			
			dfs(data, current, remainingCredits - data.get(i).credit, index + 1);
			
			current.remove(current.size() - 1);
		}
		
		
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
					return false;
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
