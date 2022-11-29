package tmp;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SaveModule extends JFrame {
	
	SaveModule() {
		setTitle("Lab1");
		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JTextArea textArea = new JTextArea();
		JScrollPane scrollpanel = new JScrollPane(textArea);
		createMenu(this, textArea);
		
		getContentPane().add(scrollpanel);
		
		setVisible(true);
	}
	
	private void createMenu(JFrame frame, JTextArea textArea) {
		JMenu filemenu = new JMenu("File");
		JMenuItem open  = new JMenuItem("Open");
		JMenuItem save = new JMenuItem("Save");
		
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileDialog fd = new FileDialog(frame, "Open", FileDialog.LOAD);
				fd.setVisible(true);
								
				try {
					String path = fd.getDirectory() + fd.getFile();
					String content = new Scanner(new File(path)).useDelimiter("\\Z").next();
					textArea.setText(content);
					textArea.repaint();
//					scrollpanel.repaint();
//					cp.repaint();
				}
				catch(Exception ex) {
					
				}
			}
		});
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileDialog fd = new FileDialog(new JFrame(), "save", FileDialog.SAVE);
				fd.setVisible(true);
				
				String path = fd.getDirectory() + fd.getFile();
				if(!path.endsWith(".txt"))
					path = path + ".txt";
				
				System.out.println(path);
				
				try {
					FileWriter fw = new FileWriter(path);
					String content = textArea.getText();
					fw.write(content);
					
					fw.close();
				}
				catch(IOException ex) {} 
				
				
			}
		});
		filemenu.add(open);
		filemenu.add(save);
		JMenuBar mb = new JMenuBar();
		mb.add(filemenu);
		setJMenuBar(mb);
	}
	
	public static void main(String args[]) {
		SaveModule l1 = new SaveModule();
	}
}
