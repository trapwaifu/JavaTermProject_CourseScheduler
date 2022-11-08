package view;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class Main extends JFrame {
	private JTable table;
	private JTable table_1;
	private JTextField searchQueueTextfield;
	private JTable table_2;
	Main() {
		setTitle("Test");
		setSize(800, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBounds(35, 80, 416, 414);
		getContentPane().add(table);
		
		table_1 = new JTable();
		table_1.setBounds(35, 521, 416, 216);
		getContentPane().add(table_1);
		
		JButton searchButton = new JButton("New button");
		searchButton.setBounds(108, 39, 117, 29);
		getContentPane().add(searchButton);
		
		JButton addToCartButton = new JButton("New button");
		addToCartButton.setBounds(329, 39, 117, 29);
		getContentPane().add(addToCartButton);
		
		JComboBox selectDepartmentCombobox = new JComboBox();
		selectDepartmentCombobox.setBounds(225, 41, 104, 27);
		getContentPane().add(selectDepartmentCombobox);
		
		searchQueueTextfield = new JTextField();
		searchQueueTextfield.setBounds(32, 39, 75, 26);
		getContentPane().add(searchQueueTextfield);
		searchQueueTextfield.setColumns(10);
		
		table_2 = new JTable();
		table_2.setBounds(477, 80, 300, 657);
		getContentPane().add(table_2);
		
		JButton saveImageButton = new JButton("New button");
		saveImageButton.setBounds(492, 39, 117, 29);
		getContentPane().add(saveImageButton);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		Main m = new Main();
	}
}
