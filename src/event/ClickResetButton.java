package event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cart.CourseCart;
import view.View;

public class ClickResetButton implements ActionListener {
	View view;
	public ClickResetButton(View view) {
		this.view = view;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		CourseCart.getInstance().reset();
		view.view();
	}
}
