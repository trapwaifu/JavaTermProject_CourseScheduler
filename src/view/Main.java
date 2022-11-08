package view;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import cart.CourseCart;
import course.CourseData;
import sort.Sort;
import sort.SortCourseName;
import trim.Filter;
import trim.FilterDepartment;
import trim.Search;

public class Main extends JFrame {
	private JTable courseList;
	private JTable courseCart;
	private JTextField searchQueueTextfield;
	
	private Map<Integer, JLabel> scheduleImageMap = new HashMap<>();
	
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
		
		JButton saveImageButton = new JButton("New button");
		saveImageButton.setBounds(671, 39, 117, 29);
		getContentPane().add(saveImageButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(671, 80, 503, 870);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(20, 6, 0, 0));
		
		int row_count = 20;
		int col_count = 6;
		String[] imageHeader = {"월요일", "화요일", "수요일", "목요일", "금요일"};
		JLabel dummy = new JLabel("");
		panel.add(dummy);
		for(int j = 0; j < imageHeader.length; ++j) {
			JLabel header_dummy = new JLabel(imageHeader[j]);
			panel.add(header_dummy);
		}
		for(int i = 1; i < row_count - 1; ++i) {
			String time = String.valueOf((i / 2) + 1) + (i % 2 == 0 ? "A" : "B")
					+ " - "
					+ String.valueOf((i / 2) + 9) + ":" + (i % 2 == 0 ? "00" : "30");
			JLabel time_dummy = new JLabel(time);
			panel.add(time_dummy);
			for(int j = 1; j < col_count; ++j) {
				int key = j * 100 + i - 1;
				JLabel label = new JLabel(Integer.toString(key));
				label.setOpaque(true);
				scheduleImageMap.put(key, label);
				panel.add(label);
			}
		}
		
		
		setVisible(true);
		
		actionTest();
	}
	
	public static void main(String[] args) {
		Main m = new Main();
	}
	
	private void actionTest() {
		// later separate this to a init function
		View viewCourseList = new ViewCourseListGUI(courseList);
		viewCourseList.view();
		View viewCourseCart = new ViewCourseCartGUI(courseCart);

		
		
		
		
		
		
		
		
		
		Filter filter = new FilterDepartment();
		Search search = new Search();
		Sort sort = new SortCourseName();
		filter.filter("컴퓨터공학부");
		search.search("프로그래밍");
		sort.sort();
		viewCourseList.view();
		
		CourseData cd = CourseData.getInstance();
		CourseCart cc = CourseCart.getInstance();
		cc.add(cd.getCourse(4));
		cc.add(cd.getCourse(5));
		viewCourseCart.view();
		
		filter.filter("교양학부");
		sort.sort();
		
		for(int i = 0; i < 60; ++i) {
			cc.add(cd.getCourse(i));
		}
		viewCourseCart.view();
	}
}
