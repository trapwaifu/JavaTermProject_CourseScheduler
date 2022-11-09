package event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import trim.Filter;
import trim.FilterDepartment;
import trim.Search;
import view.View;

public class ClickSearchButton implements ActionListener {
	JTextField searchQueryField;
	JComboBox departmentSelection;
	View view;
	static Filter filter = new FilterDepartment();
	private static Search search = new Search();
	public ClickSearchButton(JTextField searchQuery, JComboBox departmentSelection, View view) {
		this.searchQueryField = searchQuery;
		this.departmentSelection = departmentSelection;
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String department = String.valueOf(departmentSelection.getSelectedItem());
		filter.filter(department);
		String searchQuery = searchQueryField.getText();
		search.search(searchQuery);
		view.view();
	}

}
