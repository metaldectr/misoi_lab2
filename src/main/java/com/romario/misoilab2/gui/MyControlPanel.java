package com.romario.misoilab2.gui;

import com.romario.misoilab1.factory.AbstractFilterFactory;
import com.romario.misoilab1.factory.MinMaxFilterFactory;
import com.romario.misoilab1.factory.PreparationFilterFactory;
import com.romario.misoilab1.filter.AbstractFilter;
import com.romario.misoilab1.gui.gbc.GBC;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by romario on 9/20/14.
 */
public final class MyControlPanel extends JPanel {

  private static final int CELL_INSETS = 4;

  private final JButton openFileButton = new JButton("Choose File");
	private final JButton viewHistogram = new JButton("View Histogram");
  private final JButton convertImageButton = new JButton("Convert Image");

  private final JFileChooser fileChooser = new JFileChooser(new File(this.getClass()
      .getClassLoader().getResource("").getPath()));

  private final String[] filters = {"Preparation", "MinFilter", "MaxFilter", "MinMaxFilter"};
  private final JComboBox<String> filterComboBox = new JComboBox<String>(filters);

  private final Map<String, AbstractFilterFactory> filtersMap = initializeFilterMap();

  private final MyFrame frame;
  private String item;
  private AbstractFilterFactory currentFilterFactory;

	private static final String PREPARATION = "Preparation";
	private static final String MIN = "MinFilter";
	private static final String MAX = "MaxFilter";
	private static final String MINMAX = "MinMaxFilter";

  public MyControlPanel(MyFrame frame) {

    this.frame = frame;

    setBackground(Color.LIGHT_GRAY);
    setLayout(new GridBagLayout());

    initializeGUI();
    setListeners();

    currentFilterFactory = filtersMap.get(PREPARATION);
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
	          /*frame.getViewPanel().distributePanels();*/

	          frame.getViewPanel().getPicturePanel().repaint();
          }
        }
      }
    });

	  viewHistogram.addActionListener(new ActionListener() {
		  @Override
		  public void actionPerformed(ActionEvent e) {
			  frame.getViewPanel().viewCharts();
			  frame.getViewPanel().repaint();
		  }
	  });

    filterComboBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        item = (String) filterComboBox.getSelectedItem();
        currentFilterFactory = filtersMap.get(item);
	      frame.setPreparationPanel(new PreparationPanel(frame));
        if (PREPARATION.equals(item)) {

          add(frame.getPreparationPanel(),
		          new GBC(4, 0).setInsets(CELL_INSETS).setAnchor(GridBagConstraints.WEST));
          frame.revalidate();
        } else {
	        frame.getForm().setNameMethod(item);
					remove(frame.getPreparationPanel());
	        frame.revalidate();
        }
	      
      }
    });

    convertImageButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println(currentFilterFactory.createFilter());

	      BufferedImage tmpImage;
	      AbstractFilter filter = currentFilterFactory.createFilter();

	      if (PREPARATION.equals(item)) {
					frame.getPreparationPanel().setArguments();
	      }


	      frame.getForm().setResultBufferedImage(filter.convertImage(frame.getForm()));
	      /*frame.getForm().setSourceBufferedImage(tmpImage);*/
	      /*frame.getForm().setResultBufferedImage(tmpImage);*/
	      filter.viewResult(frame.getForm());
	      frame.getViewPanel().getPicturePanel().repaint();

      }
    });

  }

  private void initializeGUI() {
    add(openFileButton, new GBC(0, 0).setInsets(CELL_INSETS).setAnchor(GridBagConstraints.WEST));

    add(filterComboBox, new GBC(1, 0).setInsets(CELL_INSETS).setAnchor(GridBagConstraints.WEST));

	  add(viewHistogram, new GBC(2, 0).setInsets(CELL_INSETS).setAnchor(GridBagConstraints.WEST));

    add(convertImageButton, new GBC(3, 0).setInsets(CELL_INSETS).setAnchor(GridBagConstraints.WEST));
  }

  private Map<String, AbstractFilterFactory> initializeFilterMap() {
    Map<String, AbstractFilterFactory> currentMap = new HashMap<String, AbstractFilterFactory>();
    currentMap.put(PREPARATION, PreparationFilterFactory.getInstance());
		currentMap.put(MIN, MinMaxFilterFactory.getInstance());
	  currentMap.put(MAX, MinMaxFilterFactory.getInstance());
	  currentMap.put(MINMAX, MinMaxFilterFactory.getInstance());
    return currentMap;
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
