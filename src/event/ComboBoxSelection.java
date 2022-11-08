package event;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import trim.Filter;
import trim.FilterDepartment;
import view.View;

public class ComboBoxSelection implements ItemListener {
	View view;
	public ComboBoxSelection(View view) {
		this.view = view;
	}
	
	@Override
	public void itemStateChanged(ItemEvent event) {
		if(event.getStateChange() == ItemEvent.SELECTED) {
			String item = (String)event.getItem();
			Filter filter = new FilterDepartment();
			filter.filter(item);
			view.view();
		}
	}
}
