package com.romario.misoilab2.filter;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * Created by romario on 11/20/14.
 */
public class OpenCloseFilter {

	/*public BufferedImage openFilter() {

	}*/

	/*public BufferedImage closeFilter(BufferedImage image) {

		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				if (image.getRGB(i, j) == FilterConstant.WHITE_VALUE) {
					if (isBounded(image, i, j)) {

					}
				}
			}
		}

	}*/

	/*private boolean isBounded(BufferedImage image, int i, int j) {
		boolean flag = false;
		if (image.getRGB(i+1, j) == FilterConstant.BLACK_VALUE) {
			return false;
		}
		if (image.getRGB(i-1, j) == FilterConstant.BLACK_VALUE) {
			return false;
		}
		if (image.getRGB(i, j+1) == FilterConstant.BLACK_VALUE) {
			return false;
		}
		if (image.getRGB(i, j-1) == FilterConstant.BLACK_VALUE) {
			return false;
		}

	}*/

}
