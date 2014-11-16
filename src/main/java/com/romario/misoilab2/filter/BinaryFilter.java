package com.romario.misoilab2.filter;

import java.awt.image.BufferedImage;

/**
 * Created by romario on 11/14/14.
 */
public class BinaryFilter {

  public static BufferedImage bynaryImage(BufferedImage image) {

	  for (int i = 0; i < image.getWidth(); i++) {
		  for (int j = 0; j < image.getHeight(); j++) {
			  int currentPixel = image.getRGB(i, j);

			  int red = (currentPixel >> FilterConstant.RED_VALUE) & 0xFF;
			  int green = (currentPixel >> FilterConstant.GREEN_VALUE) & 0xFF;
			  int blue = (currentPixel & 0xFF);

			  int y = (int)(0.3 * red + 0.59 * green + 0.11 * blue);

				if (y > FilterConstant.AVERAGE_COLOR_VALUE) {
					image.setRGB(i, j, colorValue(FilterConstant.WHITE_VALUE));
				} else {
					image.setRGB(i, j, colorValue(FilterConstant.BLACK_VALUE));
				}

		  }
	  }

	  return image;
  }

  private static int colorValue(int val) {

    return ((val << FilterConstant.ALPHA_VALUE) | (val << FilterConstant.RED_VALUE))
        | ((val << FilterConstant.GREEN_VALUE)) | (val);
  }

}
