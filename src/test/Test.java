package test;

import cart.CourseCart;
import course.CourseData;
import sort.Sort;
import sort.SortCourseName;
import trim.Filter;
import trim.FilterDepartment;
import trim.Search;
import view.View;
import view.ViewCartCommandLine;
import view.ViewWorkingCommandLine;

public class Test {
	public static void main(String[] args) {
		View viewWorking = new ViewWorkingCommandLine();
		View viewCart = new ViewCartCommandLine();
		Filter filter = new FilterDepartment();
		Search search = new Search();
		Sort sort = new SortCourseName();
		filter.filter("컴퓨터공학부");
		search.search("프로그래밍");
		sort.sort();
		viewWorking.view();
		
		CourseData cd = CourseData.getInstance();
		CourseCart cc = CourseCart.getInstance();
		cc.add(cd.getCourse(4));
		cc.add(cd.getCourse(5));
//		viewCart.view();
		
		filter.filter("교양학부");
		sort.sort();
//		viewWorking.view();
		
		for(int i = 0; i < 60; ++i) {
			cc.add(cd.getCourse(i));
		}
		viewCart.view();
		
		System.out.println("success");
	}
}
