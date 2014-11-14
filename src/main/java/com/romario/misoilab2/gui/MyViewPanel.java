package com.romario.misoilab2.gui;

import java.awt.*;

import javax.swing.*;

import com.romario.misoilab2.form.Form;

/**
 * Created by romario on 9/21/14.
 */
public class MyViewPanel extends JPanel {

	private MyFrame frame;
	private Form form;
	private Graphics2D graphics2D;

	public MyViewPanel(MyFrame frame) {
		this.frame = frame;
		this.form = frame.getForm();

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		graphics2D = (Graphics2D) g;

		graphics2D.drawImage(form.getResultBufferedImage(), 0, 0, 400, 400, this);
	}

}
