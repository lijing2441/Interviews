package test;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class test2 {
	public static void main(String[] args) {
		JFrame frame = new JFrame("鼠标按钮");
		JButton button = new JButton();

		button.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseExited(MouseEvent e) {
				System.out.println("out");
			}

			public void mouseEntered(MouseEvent e) {
				System.out.println("in");
			}

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		frame.add(button);
		frame.pack();
		frame.setVisible(true);
	}
}
