package com.romario.misoilab2.form;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Created by romario on 9/21/14.
 */
public class Form implements Serializable {

  private BufferedImage sourceBufferedImage;
  private BufferedImage resultBufferedImage;

  private int[][] areas;

  private static Form instance;

  private Form() {

  }

  public static Form getInstance() {
    if (instance == null) {
      instance = new Form();
    }
    return instance;
  }

  public BufferedImage getSourceBufferedImage() {
    return sourceBufferedImage;
  }

  public void setSourceBufferedImage(BufferedImage sourceBufferedImage) {
    this.sourceBufferedImage = sourceBufferedImage;
    areas = new int[sourceBufferedImage.getWidth()][sourceBufferedImage.getHeight()];
  }

  public BufferedImage getResultBufferedImage() {
    return resultBufferedImage;
  }

  public void setResultBufferedImage(BufferedImage resultBufferedImage) {
    this.resultBufferedImage = resultBufferedImage;
  }

  public int[][] getAreas() {
    return areas;
  }

  public void setAreas(int[][] areas) {
    this.areas = areas;
  }
}
