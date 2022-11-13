package event;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import course.Course;
import course.CourseData;
import observerinterface.GeneralObservable;
import observerinterface.GeneralObserver;
import view.GridLocationInfo;

public class ClickCourseListRow extends MouseAdapter implements GeneralObserver{
	JLayeredPane panel;
	GridBagConstraints gbc;
	Map<Integer, GridLocationInfo> contentLocationInfo;

	ArrayList<JPanel> panelList = new ArrayList<>();

	@Override
	public void update() {
		deletePrevious();
	}
	
	public ClickCourseListRow(JLayeredPane panel, GridBagConstraints gbc,
			Map<Integer, GridLocationInfo> contentLocationInfo) {
		super();
		this.panel = panel;
		this.gbc = gbc;
		this.contentLocationInfo = contentLocationInfo;
	}

	
	@Override
	public void mouseClicked(MouseEvent event) {
		if(event.getClickCount() == 1) {
			execute(event);
		}
		else if(event.getClickCount() == 2) {
			deletePrevious();
		}
	}
	@Override
	public void mousePressed(MouseEvent event) {
		execute(event);
	}
	void deletePrevious() {
		// delete previous panels
		System.out.println("removing red and blue outlines");
		for(var pn : panelList) {
			panel.remove(pn);
		}
		panelList.clear();
	}
	void execute(MouseEvent event) {
		JTable table = (JTable) event.getSource();
		int row = table.getSelectedRow();

		Course course = CourseData.getInstance().getCourse(row);
		var data = CourseData.getInstance().getData();
		ArrayList<Course> identicalCourses
			= data.stream().filter(p -> p.courseName.toLowerCase()
				.contains(course.courseName.toLowerCase()))
				.collect(Collectors.toCollection(ArrayList::new));
		
		deletePrevious();
		
		// draw panel for selected course
		draw(course, true);
		
		// draw panel for courses with same name
		for(var c : identicalCourses) {
			draw(c, false);
		}
		
		
		// update view
		panel.revalidate();
		panel.repaint();
	}
	void draw(Course course, boolean isSelectedCourse) {
		if(course.time.length == 0) return;

		Arrays.sort(course.time);

		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		// initialize first time
		if(course.time.length > 0 && contentLocationInfo.containsKey(course.time[0])) {
			gbc.gridx = contentLocationInfo.get(course.time[0]).gridx;
			gbc.gridy = contentLocationInfo.get(course.time[0]).gridy;

		}

		for(int i = 1; i < course.time.length; ++i) {
			if(course.time[i-1] + 1 == course.time[i]) {
				++gbc.gridheight;
			}
			else {
				// register previous
				if(gbc.gridx != 0) {
					JPanel outlinePanel = new JPanel();
					outlinePanel.setOpaque(false);
					panelList.add(outlinePanel);
					
					if(isSelectedCourse) {
						outlinePanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 6));
						panel.add(outlinePanel, gbc, 3);
					}
					else {
						outlinePanel.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
						panel.add(outlinePanel, gbc, 4);
					}
				}
				// initialize current
				gbc.gridheight = 1;
				if(contentLocationInfo.containsKey(course.time[i])) {
					gbc.gridx = contentLocationInfo.get(course.time[i]).gridx;
					gbc.gridy = contentLocationInfo.get(course.time[i]).gridy;
				}
			}
		}

		// register final time(final label is not registered inside the loop)
		if(course.time.length > 0  && gbc.gridx != 0) {
			JPanel outlinePanel = new JPanel();
			outlinePanel.setOpaque(false);
			panelList.add(outlinePanel);
			if(isSelectedCourse) {
				outlinePanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 6));
				panel.add(outlinePanel, gbc, 3);
			}
			else {
				outlinePanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				panel.add(outlinePanel, gbc, 4);
			}

		}
	}
}
