package view;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;

import cart.CourseCart;
import course.CourseData;
import event.ClickAddToCartButton;
import event.ClickAutoFill;
import event.ClickCourseListHeader;
import event.ClickCourseListRow;
import event.ClickRemoveCourseButton;
import event.ClickResetButton;
import event.ClickSaveImage;
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
		setTitle("한?표");
		setSize(1200, 749);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel logoPanel = new JPanel();
		logoPanel.setBounds(30, 11, 161, 65);
		getContentPane().add(logoPanel);
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("data/logo.png"));
			JLabel logoLabel = new JLabel(new ImageIcon(myPicture));
			logoPanel.add(logoLabel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		courseList = new JTable();
		courseList.setBounds(10, 80, 651, 347);
		courseList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		getContentPane().add(courseList);
		JScrollPane courseListScrollPane = new JScrollPane();
		courseListScrollPane.setBounds(10, 131, 651, 296);
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
		searchButton.setBounds(203, 86, 117, 29);
		getContentPane().add(searchButton);
		
		JButton addToCartButton = new JButton("과목 담기");
		addToCartButton.setBounds(472, 86, 117, 29);
		getContentPane().add(addToCartButton);
		
		JComboBox selectDepartmentCombobox = new JComboBox();
		selectDepartmentCombobox.setBounds(343, 87, 104, 27);
		String[] departmentList = {"개설학과", "컴퓨터공학부", "HRD학과", "교양학부", "기계공학부", "메카트로닉스공학부","에너지신소재화학공학부", 
				"전기ㆍ전자ㆍ통신공학부", "산업경영학부", "메카트로닉스공학부", "디자인ㆍ건축공학부", "융합학과"};
//		String[] departmentList = {"개설학과", "컴공", "디공,건축", "기계", "전전통", "에신화", "산경", "메카", "교양학부", "HRD학과", "융합"};
		for(var department : departmentList) selectDepartmentCombobox.addItem(department);
		getContentPane().add(selectDepartmentCombobox);
		
		searchQueueTextfield = new JTextField();
		searchQueueTextfield.setBounds(36, 86, 155, 26);
		getContentPane().add(searchQueueTextfield);
		searchQueueTextfield.setColumns(10);
		
		JButton saveImageButton = new JButton("이미지 저장");
		saveImageButton.setBounds(671, 11, 117, 29);
		getContentPane().add(saveImageButton);
		
		JButton removeCourseButton = new JButton("과목 지우기");
		removeCourseButton.setBounds(20, 438, 89, 23);
		getContentPane().add(removeCourseButton);
		
		JButton resetButton = new JButton("초기화");
		resetButton.setBounds(120, 438, 89, 23);
		getContentPane().add(resetButton);
		
		JLabel currentCreditLabel = new JLabel("현재 학점 : 0");
		currentCreditLabel.setBounds(219, 442, 132, 14);
		getContentPane().add(currentCreditLabel);
		ViewCreditGUI viewCredit = new ViewCreditGUI(currentCreditLabel);
		CourseCart.getInstance().addObserver(viewCredit);
		
		JButton recommendCourseButton = new JButton("과목 추천");
		recommendCourseButton.setBounds(553, 438, 89, 23);
		getContentPane().add(recommendCourseButton);
		
		
		
		JLayeredPane panel = new JLayeredPane();
		panel.setBounds(671, 50, 503, 649);
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
			JTextArea dummy = new JTextArea(imageHeader[j]);
			JScrollPane areaScrollPane = new JScrollPane(dummy);

			panel.add(areaScrollPane, gbc);
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
					JTextArea time_dummy = new JTextArea(time);
					time_dummy.setColumns(5);
					JScrollPane areaScrollPane = new JScrollPane(time_dummy);

					panel.add(areaScrollPane, gbc);
				}
				else {
					int key = (j-1) * 100 + i - 1;
//					JTextArea label = new JTextArea(Integer.toString(key));
					JTextArea label = new JTextArea();
					label.setOpaque(true);
					label.setColumns(5);
					contentLocationInfo.put(key, new GridLocationInfo(gbc.gridx, gbc.gridy, gbc.weightx, gbc.weighty));
					JScrollPane areaScrollPane = new JScrollPane(label);

					panel.add(areaScrollPane, gbc);
				}
			}
		}
		for(int j = 0; j < col_count; ++j) {
			JTextArea dummy = null;
			if(j == 0) {
				dummy = new JTextArea("이후");
			}
			else {
				dummy = new JTextArea("");
			}
			gbc.gridx = j;
			gbc.gridy = row_count - 1;
			gbc.weightx = 0.5;
			gbc.weighty = 0.5;
			
			JScrollPane areaScrollPane = new JScrollPane(dummy);

			panel.add(areaScrollPane, gbc);		
		}
		
		setVisible(true);

		
		viewCourseList = new ViewCourseListGUI(courseList);
		viewCourseCart = new ViewCourseCartGUI(courseCart);
		
		resetButton.addActionListener(new ClickResetButton(viewCourseCart));
		ClickAddToCartButton clickAddToCartButton = new ClickAddToCartButton(courseList, viewCourseCart);
		removeCourseButton.addActionListener(new ClickRemoveCourseButton(courseCart, viewCourseCart));
		saveImageButton.addActionListener(new ClickSaveImage(panel));
		
		JTableHeader courseListHeader = courseList.getTableHeader();
		courseListHeader.addMouseListener(new ClickCourseListHeader(courseList, viewCourseList));
		
		courseList.addMouseListener(new DoubleClickCourseListRow(viewCourseCart));
		
		JTableHeader courseCartHeader = courseCart.getTableHeader();
		courseCartHeader.addMouseListener(new ClickCourseListHeader(courseCart, viewCourseCart));
		
		DoubleClickCourseCartRow doubleClickCourseCartRow = new DoubleClickCourseCartRow(viewCourseCart);
		
		selectDepartmentCombobox.addItemListener(new ComboBoxSelection(viewCourseList));
		
		searchButton.addActionListener(new ClickSearchButton(searchQueueTextfield, selectDepartmentCombobox, viewCourseList));
		searchQueueTextfield.addActionListener(new ClickSearchButton(searchQueueTextfield, selectDepartmentCombobox, viewCourseList));
		
		
		viewCourseImage = new ViewCourseImageGUI(panel, gbc, contentLocationInfo);
		CourseCart.getInstance().addObserver(viewCourseImage);
		
		ClickCourseListRow clickCourseListRow = new ClickCourseListRow(panel, gbc, contentLocationInfo); 
		
		
		JLabel appNameLabel = new JLabel("한?표 : 2017120107 장용연");
		appNameLabel.setBounds(212, 16, 251, 16);
		getContentPane().add(appNameLabel);
		
		
		ClickAutoFill clickAutoFill = new ClickAutoFill(viewCourseCart);
		recommendCourseButton.addActionListener(clickAutoFill);
		
		doubleClickCourseCartRow.addObserver(clickCourseListRow);
		clickAutoFill.addObserver(clickCourseListRow);
		clickAddToCartButton.addObserver(clickCourseListRow);
		
		addToCartButton.addActionListener(clickAddToCartButton);
		courseList.addMouseListener(clickCourseListRow);
		courseCart.addMouseListener(doubleClickCourseCartRow);

		
		viewCourseList.view();
		viewCourseCart.view();
//		actionTest();
	}
	
	public static void main(String[] args) {
		Main m = new Main();
	}
	
	private void actionTest() {
		
	}
}
