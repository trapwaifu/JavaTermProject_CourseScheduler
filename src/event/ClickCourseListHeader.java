package event;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import sort.Sort;
import sort.SortCode;
import view.View;

public class ClickCourseListHeader extends MouseAdapter {
	private JTable table;
	private View view;
	
	public ClickCourseListHeader(JTable table, View view) {
		this.table = table;
		this.view = view;
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		Point point = event.getPoint();
		int column = table.columnAtPoint(point);
		
		JOptionPane.showMessageDialog(table, "Column header #" + column + " is clicked");
		
		Sort sort = null;
		if(column == 0)
			sort = new SortCode();
		
		sort.sort();
		
		view.view();
	}
}
