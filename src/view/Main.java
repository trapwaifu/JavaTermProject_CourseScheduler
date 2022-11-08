package view;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import cart.CourseCart;
import course.CourseData;
import sort.Sort;
import sort.SortCourseName;
import trim.Filter;
import trim.FilterDepartment;
import trim.Search;

import javax.swing.JScrollPane;

public class Main extends JFrame {
	private JTable courseList;
	private JTable courseCart;
	private JTextField searchQueueTextfield;
	private JTable courseImageTable;
	Main() {
		setTitle("Test");
		setSize(1200, 1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		courseList = new JTable();
		courseList.setBounds(10, 80, 651, 501);
		courseList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		getContentPane().add(courseList);
		JScrollPane courseListScrollPane = new JScrollPane();
		courseListScrollPane.setBounds(10, 80, 651, 501);
		courseListScrollPane.setViewportView(courseList);
		getContentPane().add(courseListScrollPane);
		
		
		courseCart = new JTable();
		courseCart.setBounds(10, 592, 651, 358);
		courseCart.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		getContentPane().add(courseCart);
		JScrollPane courseCartPane = new JScrollPane();
		courseCartPane.setBounds(10, 592, 651, 358);
		courseCartPane.setViewportView(courseCart);
		getContentPane().add(courseCartPane);
		
		JButton searchButton = new JButton("New button");
		searchButton.setBounds(197, 39, 117, 29);
		getContentPane().add(searchButton);
		
		JButton addToCartButton = new JButton("New button");
		addToCartButton.setBounds(463, 39, 117, 29);
		getContentPane().add(addToCartButton);
		
		JComboBox selectDepartmentCombobox = new JComboBox();
		selectDepartmentCombobox.setBounds(349, 40, 104, 27);
		getContentPane().add(selectDepartmentCombobox);
		
		searchQueueTextfield = new JTextField();
		searchQueueTextfield.setBounds(32, 39, 155, 26);
		getContentPane().add(searchQueueTextfield);
		searchQueueTextfield.setColumns(10);
		
		courseImageTable = new JTable();
		courseImageTable.setBounds(671, 80, 503, 870);
		getContentPane().add(courseImageTable);
		
		JButton saveImageButton = new JButton("New button");
		saveImageButton.setBounds(671, 39, 117, 29);
		getContentPane().add(saveImageButton);
		
		
		
		setVisible(true);
		
		actionTest();
	}
	
	public static void main(String[] args) {
		Main m = new Main();
	}
	
	private void actionTest() {
		View viewCourseList = new ViewCourseListGUI(courseList);
		viewCourseList.view();
		
		Filter filter = new FilterDepartment();
		Search search = new Search();
		Sort sort = new SortCourseName();
		filter.filter("컴퓨터공학부");
//		search.search("프로그래밍");
		sort.sort();
		viewCourseList.view();
//		
//		CourseData cd = CourseData.getInstance();
//		CourseCart cc = CourseCart.getInstance();
//		cc.add(cd.getCourse(4));
//		cc.add(cd.getCourse(5));
////		viewCart.view();
//		
//		filter.filter("교양학부");
//		sort.sort();
////		viewWorking.view();
//		
//		for(int i = 0; i < 60; ++i) {
//			cc.add(cd.getCourse(i));
//		}
//		viewCourseList.view();
	}
}
