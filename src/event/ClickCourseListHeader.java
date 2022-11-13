package event;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import sort.Sort;
import sort.SortCode;
import sort.SortCourseName;
import sort.SortCredit;
import sort.SortDepartment;
import sort.SortDesignCredit;
import sort.SortDivision;
import sort.SortMISC;
import sort.SortMaxStudents;
import sort.SortProfessor;
import sort.SortTarget;
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
		
		// JOptionPane.showMessageDialog(table, "Column header #" + column + " is clicked");
		
		Sort sort = null;
		switch(column) {
		case 0:
			sort = new SortCode();
			break;
		case 1:
			sort = new SortCourseName();
			break;
		case 2:
			sort = new SortDivision();
			break;
		case 3:
			sort = new SortProfessor();
			break;
		case 4:
			sort = new SortTarget();
			break;
		case 5:
			sort = new SortCredit();
			break;
		case 6:
			sort = new SortMISC();
			break;
		case 7:
			sort = new SortMaxStudents();
			break;
		case 8:
			sort = new SortDesignCredit();
			break;
		default:
			sort = new SortDepartment();
			break;
		}
		
		
		
		
		sort.sort();
		
		view.view();
	}
}
