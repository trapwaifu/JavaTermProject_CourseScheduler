package event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import reccomendation.AutoFill;
import view.View;

public class ClickAutoFill implements ActionListener {
	AutoFill autoFill = new AutoFill();
	View view;
	
	public ClickAutoFill(View view) {
		super();
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		autoFill.autoFill();
		view.view();
	}
}
