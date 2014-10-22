package com.romario.misoilab2.gui;

import com.romario.misoilab1.form.Form;

import javax.swing.*;
import java.awt.*;

/**
 * Created by romario on 9/21/14.
 */
public class MyViewPanel extends JPanel {

	private MyFrame frame;
	private Form form;
	private GridBagConstraints c = new GridBagConstraints();

	private PicturePanel picturePanel;
	private BuildHistogramPanel redHistogramPanel;
	private BuildHistogramPanel greenHistogramPanel;
	private BuildHistogramPanel blueHistogramPanel;

	public MyViewPanel(MyFrame frame) {
		this.frame = frame;
		this.form = frame.getForm();

		redHistogramPanel = new BuildHistogramPanel(frame);
		greenHistogramPanel = new BuildHistogramPanel(frame);
		blueHistogramPanel = new BuildHistogramPanel(frame);

		picturePanel = new PicturePanel(frame);

		initializePanels();

	}

	private void initializePanels() {

		setLayout(new GridBagLayout());

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 100;
		c.weighty = 100;
		c.gridheight = 3;
		add(picturePanel, c);

		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 80;
		c.weighty = 50;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(redHistogramPanel, c);

		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 80;
		c.weighty = 50;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(greenHistogramPanel, c);

		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 80;
		c.weighty = 50;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(blueHistogramPanel, c);



	}

	public void viewCharts() {

		redHistogramPanel.buildHistogramPanel("RED", frame.getForm().getSourceBufferedImage());
		greenHistogramPanel.buildHistogramPanel("GREEN", frame.getForm().getSourceBufferedImage());
		blueHistogramPanel.buildHistogramPanel("BLUE", frame.getForm().getSourceBufferedImage());

	}

	public PicturePanel getPicturePanel() {
		return picturePanel;
	}

	public void setPicturePanel(PicturePanel picturePanel) {
		this.picturePanel = picturePanel;
	}

}
