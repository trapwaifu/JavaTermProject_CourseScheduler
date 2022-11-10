package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import course.Course;
import observerinterface.CourseCartObserver;

public class ViewCourseImageGUI implements View, CourseCartObserver {
	JLayeredPane panel;
	GridBagConstraints gbc;
	Map<Integer, GridLocationInfo> contentLocationInfo;
	Map<Course, ArrayList<JScrollPane>> labelMap = new HashMap<>();
	
	public ViewCourseImageGUI(JLayeredPane panel, GridBagConstraints gbc,
			Map<Integer, GridLocationInfo> contentLocationInfo) {
		super();
		this.panel = panel;
		this.gbc = gbc;
		this.contentLocationInfo = contentLocationInfo;
	}


	@Override
	public void view() {
		;
	}
	
	@Override
	public void updateAdd(Course course) {
		ArrayList<JScrollPane> labelList = new ArrayList<JScrollPane>();
		
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
					JTextArea prev = new JTextArea(course.courseName + " " + course.division + " " + course.professor);
					prev.setColumns(5);
					prev.setOpaque(true);
					prev.setLineWrap(true);
					prev.setBackground(Color.yellow);
					JScrollPane areaScrollPane = new JScrollPane(prev);
					labelList.add(areaScrollPane);

					panel.add(areaScrollPane, gbc, 5);
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
			JTextArea prev = new JTextArea(course.courseName + " " + course.division + " " + course.professor);
			prev.setColumns(5);
			prev.setLineWrap(true);
			prev.setOpaque(true);
			prev.setBackground(Color.yellow);

			JScrollPane areaScrollPane = new JScrollPane(prev);
			labelList.add(areaScrollPane);

			panel.add(areaScrollPane, gbc, 5);
		}

		labelMap.put(course, labelList);
		
		panel.revalidate();
		panel.repaint();
	}
	
	@Override
	public void updateRemove(Course course) {
		var removeList = labelMap.get(course);
		for(JScrollPane label : removeList) {
			panel.remove(label);
		}
		labelMap.remove(course);
		
		panel.revalidate();
		panel.repaint();
	}
	
	@Override
	public void updateReset() {
		for(Map.Entry<Course, ArrayList<JScrollPane>> entry : labelMap.entrySet()) {
			for(var label : entry.getValue()) {
				panel.remove(label);
			}
		}
		
		labelMap = new HashMap<Course, ArrayList<JScrollPane>>();
		
		panel.revalidate();
		panel.repaint();

	}
}
