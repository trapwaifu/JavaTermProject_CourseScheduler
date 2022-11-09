package view;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;

import cart.CourseCart;
import course.CourseData;
import event.ClickAddToCartButton;
import event.ClickCourseListHeader;
import event.ClickSearchButton;
import event.ComboBoxSelection;
import event.DoubleClickCourseCartRow;
import event.DoubleClickCourseListRow;
import sort.Sort;
import sort.SortCourseName;
import trim.Filter;
import trim.FilterDepartment;
import trim.Search;

public class Main extends JFrame {
	private JTable courseList;
	private JTable courseCart;
	private JTextField searchQueueTextfield;
	
	private Map<Integer, GridLocationInfo> contentLocationInfo = new HashMap<>();
	private ViewCourseListGUI viewCourseList = null;
	private ViewCourseCartGUI viewCourseCart = null;
	private ViewCourseImageGUI viewCourseImage = null;
	
	Main() {
		setTitle("Test");
		setSize(1200, 749);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		courseList = new JTable();
		courseList.setBounds(10, 80, 651, 347);
		courseList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		getContentPane().add(courseList);
		JScrollPane courseListScrollPane = new JScrollPane();
		courseListScrollPane.setBounds(10, 80, 651, 347);
		courseListScrollPane.setViewportView(courseList);
		getContentPane().add(courseListScrollPane);
		
		
		courseCart = new JTable();
		courseCart.setBounds(10, 438, 651, 273);
		courseCart.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		getContentPane().add(courseCart);
		JScrollPane courseCartPane = new JScrollPane();
		courseCartPane.setBounds(10, 475, 651, 224);
		courseCartPane.setViewportView(courseCart);
		getContentPane().add(courseCartPane);
		
		JButton searchButton = new JButton("검색");
		searchButton.setBounds(197, 39, 117, 29);
		getContentPane().add(searchButton);
		
		JButton addToCartButton = new JButton("과목 담기");
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
		
		JButton removeCourseButton = new JButton("New button");
		removeCourseButton.setBounds(20, 438, 89, 23);
		getContentPane().add(removeCourseButton);
		
		JButton resetButton = new JButton("New button");
		resetButton.setBounds(120, 438, 89, 23);
		getContentPane().add(resetButton);
		
		JLabel currentCreditLabel = new JLabel("New label");
		currentCreditLabel.setBounds(219, 442, 132, 14);
		getContentPane().add(currentCreditLabel);
		
		JButton recommendCourseButton = new JButton("New button");
		recommendCourseButton.setBounds(553, 438, 89, 23);
		getContentPane().add(recommendCourseButton);
		
		
		
		JLayeredPane panel = new JLayeredPane();
		panel.setBounds(671, 80, 503, 619);
		getContentPane().add(panel);
		panel.setLayout(new GridBagLayout());
//		panel.setLayout(new GridLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		int row_count = 20;
		int col_count = 6;
		String[] imageHeader = {"   ", "월요일", "화요일", "수요일", "목요일", "금요일"};
		for(int j = 0; j < imageHeader.length; ++j) {
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = j;
			gbc.gridy = 0;
			gbc.weightx = 0.5;
			gbc.weighty = 0.5;
			JLabel dummy = new JLabel(imageHeader[j]);
			panel.add(dummy, gbc);
		}
		
		for(int i = 1; i < row_count - 1; ++i) {						
			for(int j = 0; j < col_count; ++j) {
				gbc.gridx = j;
				gbc.gridy = i;
				gbc.weightx = 0.5;
				gbc.weighty = 0.5;
				if(j == 0) {
					String time = String.valueOf(((i - 1) / 2) + 1) + (i % 2 == 1 ? "A" : "B")
							+ " - "
							+ String.valueOf(((i - 1) / 2) + 9) + ":" + (i % 2 == 1 ? "00" : "30");
					JLabel time_dummy = new JLabel(time);
					panel.add(time_dummy, gbc);
				}
				else {
					int key = (j-1) * 100 + i - 1;
					JLabel label = new JLabel(Integer.toString(key));
					label.setOpaque(true);
					contentLocationInfo.put(key, new GridLocationInfo(gbc.gridx, gbc.gridy, gbc.weightx, gbc.weighty));
					panel.add(label, gbc);
				}
			}
		}
		for(int j = 0; j < col_count; ++j) {
			JLabel dummy = null;
			if(j == 0) {
				dummy = new JLabel("이후");
			}
			else {
				dummy = new JLabel("");
			}
			gbc.gridx = j;
			gbc.gridy = row_count - 1;
			gbc.weightx = 0;
			gbc.weighty = 0;
			
			panel.add(dummy, gbc);		
		}
		
		// Test
//		gbc.gridx = 3;
//		gbc.gridy = 3;
//		gbc.gridheight = 3;
//		gbc.gridwidth = 1;
//		JLabel testUnit = new JLabel("Test Unit");
//		testUnit.setOpaque(true);
//		panel.add(testUnit, gbc, 2);
		//panel.remove(testUnit);
		
		
		
		setVisible(true);

		
		viewCourseList = new ViewCourseListGUI(courseList);
		viewCourseCart = new ViewCourseCartGUI(courseCart);

		JTableHeader courseListHeader = courseList.getTableHeader();
		courseListHeader.addMouseListener(new ClickCourseListHeader(courseList, viewCourseList));
		courseList.addMouseListener(new DoubleClickCourseListRow(viewCourseList, viewCourseCart));
		addToCartButton.addActionListener(new ClickAddToCartButton(courseList, viewCourseCart));
		
		JTableHeader courseCartHeader = courseCart.getTableHeader();
		courseCartHeader.addMouseListener(new ClickCourseListHeader(courseCart, viewCourseCart));
		courseCart.addMouseListener(new DoubleClickCourseCartRow(viewCourseCart));
		
		selectDepartmentCombobox.addItemListener(new ComboBoxSelection(viewCourseList));
		
		searchButton.addActionListener(new ClickSearchButton(searchQueueTextfield, selectDepartmentCombobox, viewCourseList));
		searchQueueTextfield.addActionListener(new ClickSearchButton(searchQueueTextfield, selectDepartmentCombobox, viewCourseList));
		
		
		viewCourseImage = new ViewCourseImageGUI(panel, gbc, contentLocationInfo);
		CourseCart.getInstance().addObserver(viewCourseImage);
		
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
