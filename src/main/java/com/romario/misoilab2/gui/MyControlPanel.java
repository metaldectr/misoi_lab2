package com.romario.misoilab2.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import com.romario.misoilab2.gui.gbc.GBC;

/**
 * Created by romario on 9/20/14.
 */
public final class MyControlPanel extends JPanel {

  private static final int CELL_INSETS = 4;

  private final JButton openFileButton = new JButton("Choose File");
  private final JButton convertImageButton = new JButton("Convert Image");

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

      }
    });

  }

  private void initializeGUI() {
    add(openFileButton, new GBC(0, 0).setInsets(CELL_INSETS).setAnchor(GridBagConstraints.WEST));

    add(convertImageButton, new GBC(3, 0).setInsets(CELL_INSETS).setAnchor(GridBagConstraints.WEST));
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
