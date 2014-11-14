package com.romario.misoilab2.gui;

import com.romario.misoilab2.form.Form;

import java.awt.*;

import javax.swing.*;

/**
 * Created by romario on 9/20/14.
 */
public final class MyFrame extends JFrame {

	private static final int DEFAULT_HEIGHT = 500;
	private static final int DEFAULT_WIDTH = 650;

	private MyControlPanel controlPanel;
	private MyViewPanel viewPanel;

	private Container contentPane = null;

	private Form form = Form.getInstance();

	public MyFrame() {
		setTitle("Misoi Lab1");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		MyControlPanel controlPanel = new MyControlPanel(this);
		MyViewPanel viewPanel = new MyViewPanel(this);

		this.controlPanel = controlPanel;
		this.viewPanel = viewPanel;


		contentPane = super.getContentPane();

		contentPane.add(controlPanel, BorderLayout.NORTH);
		contentPane.add(viewPanel, BorderLayout.CENTER);


	}

	public MyControlPanel getControlPanel() {
		return controlPanel;
	}

	public void setControlPanel(MyControlPanel controlPanel) {
		this.controlPanel = controlPanel;
	}

	public MyViewPanel getViewPanel() {
		return viewPanel;
	}

	public void setViewPanel(MyViewPanel viewPanel) {
		this.viewPanel = viewPanel;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public Container getContentPane() {
		return contentPane;
	}

	public void setContentPane(Container contentPane) {
		this.contentPane = contentPane;
	}

}
