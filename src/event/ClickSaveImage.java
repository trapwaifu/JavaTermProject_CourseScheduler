package event;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

public class ClickSaveImage implements ActionListener {
	JLayeredPane pane;

	public ClickSaveImage(JLayeredPane pane) {
		super();
		this.pane = pane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			//			BufferedImage im = new BufferedImage(
			//					pane.getWidth(), pane.getHeight(), BufferedImage.TYPE_INT_ARGB);
			//			pane.paint(im.getGraphics());
			//			ImageIO.write(im, "PNG", new File("shot.png"));
			//			JOptionPane.showMessageDialog(null, "이미지 저장 완료");

			FileDialog fd = new FileDialog(new JFrame(), "save", FileDialog.SAVE);
			fd.setVisible(true);

			String path = fd.getDirectory() + fd.getFile();
			if(!path.endsWith(".png"))
				path = path + ".png";

			System.out.println(path);
			try {
				BufferedImage im = new BufferedImage(
						pane.getWidth(), pane.getHeight(), BufferedImage.TYPE_INT_ARGB);
				pane.paint(im.getGraphics());
				ImageIO.write(im, "PNG", new File(path));

			} 
			catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("cannot save image");
			}


	}
}
