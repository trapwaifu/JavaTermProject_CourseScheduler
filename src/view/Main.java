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
import javax.swing.table.JTableHeader;

import cart.CourseCart;
import course.CourseData;
import event.ComboBoxSelection;
import event.DoubleClickCourseListHeader;
import event.DoubleClickTableRow;
import sort.Sort;
import sort.SortCourseName;
import trim.Filter;
import trim.FilterDepartment;
import trim.Search;

public class Main extends JFrame {
	private JTable courseList;
	private JTable courseCart;
	private JTextField searchQueueTextfield;
	
	private Map<Integer, JLabel> viewScheduleImageMap = new HashMap<>();
	private ViewCourseListGUI viewCourseList = null;
	private ViewCourseCartGUI viewCourseCart = null;
	
	Main() {
		setTitle("Test");
		setSize(1200, 749);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		courseList = new JTable();
		courseList.setBounds(10, 80, 651, 501);
		courseList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		getContentPane().add(courseList);
		JScrollPane courseListScrollPane = new JScrollPane();
		courseListScrollPane.setBounds(10, 80, 651, 347);
		courseListScrollPane.setViewportView(courseList);
		getContentPane().add(courseListScrollPane);
		
		
		courseCart = new JTable();
		courseCart.setBounds(10, 592, 651, 358);
		courseCart.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		getContentPane().add(courseCart);
		JScrollPane courseCartPane = new JScrollPane();
		courseCartPane.setBounds(10, 438, 651, 273);
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
		String[] departmentList = {"개설학과", "컴퓨터공학부", "HRD학과", "교양학부", "기계공학부", "메카트로닉스공학부","에너지신소재화학공학부", 
				"전기ㆍ전자ㆍ통신공학부", "산업경영학부", "메카트로닉스공학부", "디자인ㆍ건축공학부", "융합학과"};
//		String[] departmentList = {"개설학과", "컴공", "디공,건축", "기계", "전전통", "에신화", "산경", "메카", "교양학부", "HRD학과", "융합"};
		for(var department : departmentList) selectDepartmentCombobox.addItem(department);
		getContentPane().add(selectDepartmentCombobox);
		
		searchQueueTextfield = new JTextField();
		searchQueueTextfield.setBounds(32, 39, 155, 26);
		getContentPane().add(searchQueueTextfield);
		searchQueueTextfield.setColumns(10);
		
		JButton saveImageButton = new JButton("New button");
		saveImageButton.setBounds(671, 39, 117, 29);
		getContentPane().add(saveImageButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(671, 80, 503, 631);
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
			String time = String.valueOf(((i - 1) / 2) + 1) + (i % 2 == 1 ? "A" : "B")
					+ " - "
					+ String.valueOf(((i - 1) / 2) + 9) + ":" + (i % 2 == 1 ? "00" : "30");
			JLabel time_dummy = new JLabel(time);
			panel.add(time_dummy);
			for(int j = 1; j < col_count; ++j) {
				int key = j * 100 + i - 1;
				JLabel label = new JLabel(Integer.toString(key));
				label.setOpaque(true);
				viewScheduleImageMap.put(key, label);
				panel.add(label);
			}
		}
		
		JLabel tail_dummy0 = new JLabel("이후");
		panel.add(tail_dummy0);
		for(int j = 1; j < col_count; ++j) {
			JLabel tail_dummy = new JLabel("");
			panel.add(tail_dummy);
		}
		
		
		setVisible(true);
		
		
		viewCourseList = new ViewCourseListGUI(courseList);
		viewCourseCart = new ViewCourseCartGUI(courseCart);

		JTableHeader courseListHeader = courseList.getTableHeader();
		courseListHeader.addMouseListener(new DoubleClickCourseListHeader(courseList, viewCourseList));
		courseList.addMouseListener(new DoubleClickTableRow(viewCourseList, viewCourseCart));
		JTableHeader courseCartHeader = courseCart.getTableHeader();
		courseCartHeader.addMouseListener(new DoubleClickCourseListHeader(courseCart, viewCourseCart));
		
		selectDepartmentCombobox.addItemListener(new ComboBoxSelection(viewCourseList));
		
		viewCourseList.view();
		viewCourseCart.view();
//		actionTest();
	}
	
	public static void main(String[] args) {
		Main m = new Main();
	}
	
	private void actionTest() {
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
