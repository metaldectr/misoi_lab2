package com.romario.misoilab2.logic;

import java.awt.image.BufferedImage;

/**
 * Created by romario on 11/16/14.
 */
public class Place {

  private boolean flag = false;

  public int[][] labeling(BufferedImage image) {

    int[][] imageArray = convertImageToArray(image);
    int[][] labels = new int[image.getWidth()][image.getHeight()];

    labels = initializeArray(labels, image.getWidth(), image.getHeight());

    int l = 1;
    int width = image.getWidth();
    int height = image.getHeight();

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        if (flag == true) {
          l++;
          flag = false;
        }
        fillArea(imageArray, labels, i, j, l, width, height);
      }
    }
    return labels;
  }

  private void fillArea(int[][] imageArray, int[][] labels, int x, int y, final int l, int w, int h) {
    if (labels[x][y] == 0 && imageArray[x][y] == 1) {
      labels[x][y] = l;
      this.flag = true;
      if (x > 0) {
        fillArea(imageArray, labels, x - 1, y, l, w, h);
      }
      if (x < w - 1) {
        fillArea(imageArray, labels, x + 1, y, l, w, h);
      }
      if (y > 0) {
        fillArea(imageArray, labels, x, y - 1, l, w, h);
      }
      if (y < h - 1) {
        fillArea(imageArray, labels, x, y + 1, l, w, h);
      }
    }
  }

  private int[][] convertImageToArray(BufferedImage image) {
    int[][] tmpImageArray = new int[image.getWidth()][image.getHeight()];

    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        if ((image.getRGB(i, j) & 0xFF) == 0) {
          tmpImageArray[i][j] = 0;
        } else {
          tmpImageArray[i][j] = 1;
        }
      }
    }

    return tmpImageArray;
  }

  private int[][] initializeArray(int[][] array, int xLength, int yLength) {

    for (int i = 0; i < xLength; i++) {
      for (int j = 0; j < yLength; j++) {
        array[i][j] = 0;
      }
    }

    return array;
  }

}
