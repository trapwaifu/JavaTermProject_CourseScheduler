package event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import observerinterface.GeneralObservable;
import observerinterface.GeneralObserver;
import reccomendation.AutoFill;
import view.View;

public class ClickAutoFill implements ActionListener, GeneralObservable {
	AutoFill autoFill = new AutoFill();
	View view;
	
	public ClickAutoFill(View view) {
		super();
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		autoFill.autoFill();
		notifyObservers();

		view.view();
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
