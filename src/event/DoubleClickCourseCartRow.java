package event;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTable;

import cart.CourseCart;
import course.Course;
import observerinterface.GeneralObservable;
import observerinterface.GeneralObserver;
import view.View;

public class DoubleClickCourseCartRow extends MouseAdapter implements GeneralObservable{
	private View viewCourseCart;

	public DoubleClickCourseCartRow(View view) {
		this.viewCourseCart = view;
	}
	@Override
	public void mouseClicked(MouseEvent event) {
		if(event.getClickCount() == 2) {
			JTable table = (JTable) event.getSource();
			int row = table.getSelectedRow();
			
			Course selected = CourseCart.getInstance().getCart().get(row); 
			CourseCart.getInstance().remove(row);
//			JOptionPane.showMessageDialog(table, selected);
			notifyObservers();
			viewCourseCart.view();
			
		}
		
	}
	
	ArrayList<GeneralObserver> observers = new ArrayList<>();

	@Override
	public void addObserver(GeneralObserver o) {
		observers.add(o);
	}
	@Override
	public void notifyObservers() {
		for(var o : observers)
			o.update();
	}
}
