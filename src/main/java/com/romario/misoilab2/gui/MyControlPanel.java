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

import com.romario.misoilab2.filter.BinaryFilter;
import com.romario.misoilab2.gui.gbc.GBC;
import com.romario.misoilab2.logic.Place;
import com.romario.misoilab2.logic.Sign;

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
      }
    });

    removeNoiseButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
				removeNoise
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

        int[][] results = frame.getForm().getAreas();
        int w = frame.getForm().getResultBufferedImage().getWidth();
        int h = frame.getForm().getResultBufferedImage().getHeight();
        viewArray(results, w, h);
        Sign sign = new Sign();
        frame.getForm().setAreasMap(sign.convertArrayToMapAreas(results, w, h));
        frame.getForm().setAreasIndexesMap(sign.defineAreasToMap(results, w, h));
        Map<Integer, List<Point>> areasIndexesMap = frame.getForm().getAreasIndexesMap();
        frame.getForm().setCenterOfMass(sign.calculateCenterOfMass(areasIndexesMap));
        frame.getForm().setPerimeterMap(sign.calculatePerimeter(areasIndexesMap, results, w, h));
	      frame.getForm().setDensityMap(areasIndexesMap, frame.getForm().setPerimeterMap(););
      }
    });

    clusteringButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

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
    add(removeNoiseButton, new GBC(2, 0).setInsets(CELL_INSETS).setAnchor(GridBagConstraints.WEST));
    add(defineAreasButton, new GBC(3, 0).setInsets(CELL_INSETS).setAnchor(GridBagConstraints.WEST));
	  add(defineSignsButton, new GBC(4, 0).setInsets(CELL_INSETS).setAnchor(GridBagConstraints.WEST));
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
