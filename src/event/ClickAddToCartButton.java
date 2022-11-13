package event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import cart.CourseCart;
import course.Course;
import course.CourseData;
import observerinterface.GeneralObservable;
import observerinterface.GeneralObserver;
import view.View;

public class ClickAddToCartButton implements ActionListener, GeneralObservable{
	JTable courseList;
	View courseCartView;
	
	public ClickAddToCartButton(JTable courseList, View courseCartView) {
		super();
		this.courseList = courseList;
		this.courseCartView = courseCartView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int row = courseList.getSelectedRow();
		
		
		if(row == -1) {
			JOptionPane.showMessageDialog(null, "Select a course!");
			return;
		}
		 
		Course selected = CourseData.getInstance().getData().get(row); 
		CourseCart.getInstance().add(selected);
		notifyObservers();

		courseCartView.view();
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