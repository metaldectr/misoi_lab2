package com.romario.misoilab2.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import com.romario.misoilab2.cluster.Cluster;
import com.romario.misoilab2.filter.BinaryFilter;
import com.romario.misoilab2.form.Form;
import com.romario.misoilab2.gui.gbc.GBC;
import com.romario.misoilab2.logic.*;

/**
 * Created by romario on 9/20/14.
 */
public final class MyControlPanel extends JPanel {

  private static final int CELL_INSETS = 4;

  private final JButton openFileButton = new JButton("Choose File");
  private final JButton convertImageButton = new JButton("Convert Image");
  private final JButton removeNoiseButton = new JButton("Remove noise");
  private final JButton defineAreasButton = new JButton("Define areas");
	private final JButton defineSignsButton = new JButton("Define signs");
  private final JButton clusteringButton = new JButton("Clustering");

  private final JFileChooser fileChooser = new JFileChooser(new File(this.getClass()
      .getClassLoader().getResource("").getPath()));

  private final MyFrame frame;

  public MyControlPanel(MyFrame frame) {

    this.frame = frame;

    setBackground(Color.LIGHT_GRAY);
    setLayout(new GridBagLayout());

    initializeGUI();
    setListeners();
  }

  private void setListeners() {

    openFileButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        fileChooser.setFileFilter(new FileFilter() {
          @Override
          public boolean accept(File f) {
            if (f.isDirectory()) {
              return true;
            }
            final String name = f.getName();
            return name.endsWith(".jpg") || name.endsWith(".png");

          }

          @Override
          public String getDescription() {
            return "*.png, *.jpg";
          }
        });
        if (e.getSource() == openFileButton) {
          int returnVal = fileChooser.showOpenDialog(frame);
          if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            System.out.println(file);

            BufferedImage img = openImage(file);
            frame.getForm().setSourceBufferedImage(img);
            frame.getForm().setResultBufferedImage(img);
            /* frame.getViewPanel().distributePanels(); */

            frame.getViewPanel().repaint();
          }
        }
      }
    });


    convertImageButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        BufferedImage image = frame.getForm().getSourceBufferedImage();
        frame.getForm().setResultBufferedImage(BinaryFilter.bynaryImage(image));
        frame.getViewPanel().repaint();
	      System.out.println("end convert image");
      }
    });

    removeNoiseButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

	      System.out.println("end remove noise");
      }
    });

    defineAreasButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Place place = new Place();
        int[][] result = place.labeling(frame.getForm().getResultBufferedImage());
	      frame.getForm().setAreas(result);
	      System.out.println("end define areasButton");
      }
    });

    defineSignsButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
				Form form =  frame.getForm();
        int[][] results = form.getAreas();
        int w = form.getResultBufferedImage().getWidth();
        int h = form.getResultBufferedImage().getHeight();
        viewArray(results, w, h);
        Sign sign = new Sign();
        form.setAreasMap(sign.convertArrayToMapAreas(results, w, h));
        form.setAreasIndexesMap(sign.defineAreasToMap(results, w, h));
        Map<Integer, List<Point>> areasIndexesMap = form.getAreasIndexesMap();
        form.setCenterOfMass(sign.calculateCenterOfMass(areasIndexesMap));
        form.setPerimeterMap(sign.calculatePerimeter(areasIndexesMap, results, w, h));
	      form.setDensityMap(sign.calculateDensity(areasIndexesMap, form.getPerimeterMap()));

	      ///////////////////////////////////////////////noise
	      frame.getForm().setAreasIndexesMap(
			      BinaryFilter.simpleFilter(frame.getForm().getResultBufferedImage(),
					      frame.getForm().getAreasIndexesMap()));
	      frame.getViewPanel().repaint();
	      System.out.println("end remove noise");
	      ////////////////////////////////////

	      form.setClusters(Converter.convertAreasToClusters(frame.getForm().getAreasIndexesMap(), form.getCenterOfMass(),
			      form.getPerimeterMap(), form.getDensityMap(), form.getElongationMap()));

	      System.out.println("end defineSigns");
      }
    });

    clusteringButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
	      KMeans kMeans = new KMeans();
        frame.getForm().setClusters(kMeans.clustering(frame.getForm().getClusters()));
        Common common = new Common();
        frame.getForm().setResultBufferedImage(
            common.markClusters(frame.getForm().getResultBufferedImage(), frame.getForm()
                .getClusters()));

        // repaint and coloring image
        frame.getViewPanel().repaint();
        System.out.println("end clustering");
      }
    });

  }

  /*
   * private int[][] viewImage(BufferedImage image) { int[][] tp = new int[651][400];
   * 
   * for(int i = 0; i < image.getWidth(); i++) { for(int j = 0; j < image.getHeight(); j++) {
   * tp[i][j] = image.getRGB(i, j) & 0XFF; } }
   * 
   * return tp; }
   */

  private void viewArray(int[][] array, int xLength, int yLength) {
    for (int i = 0; i < xLength; i++) {
      for (int j = 0; j < yLength; j++) {
        System.out.print(array[i][j]);
      }
      System.out.println();
    }
  }

  private void initializeGUI() {

    add(openFileButton, new GBC(0, 0).setInsets(CELL_INSETS).setAnchor(GridBagConstraints.WEST));
    add(convertImageButton, new GBC(1, 0).setInsets(CELL_INSETS).setAnchor(GridBagConstraints.WEST));
    add(defineAreasButton, new GBC(2, 0).setInsets(CELL_INSETS).setAnchor(GridBagConstraints.WEST));
	  add(defineSignsButton, new GBC(3, 0).setInsets(CELL_INSETS).setAnchor(GridBagConstraints.WEST));
	  //add(removeNoiseButton, new GBC(4, 0).setInsets(CELL_INSETS).setAnchor(GridBagConstraints.WEST));
    add(clusteringButton, new GBC(5, 0).setInsets(CELL_INSETS).setAnchor(GridBagConstraints.WEST));

  }

  private BufferedImage openImage(final File file) {
    BufferedImage out = null;
    try {
      out = ImageIO.read(file);
      System.out.println(out);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return out;
  }



}
