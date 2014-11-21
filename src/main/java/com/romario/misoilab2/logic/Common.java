package com.romario.misoilab2.logic;

import com.romario.misoilab2.cluster.Cluster;
import com.romario.misoilab2.filter.BinaryFilter;
import com.romario.misoilab2.filter.FilterConstant;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by romario on 11/20/14.
 */
public class Common {

	public BufferedImage markClusters(BufferedImage image, List<Cluster> clusters) {

		Map<Integer, Integer> colorClass = new HashMap<>();

		for (int i = 0; i < FilterConstant.COUNT_CLASSES; i++) {
			System.out.println(new Random().nextInt(254));
			colorClass.put(i, BinaryFilter.colorRGBValue(new Random().nextInt(254), new Random().nextInt(254), new Random().nextInt(254)));
		}

		/*colorClass.put(0, 120);
		colorClass.put(1, 255);*/

		for (Cluster cluster : clusters) {
			int color = colorClass.get(cluster.getBelongCluster());
			for (Point point : cluster.getPoints()) {
				image.setRGB(point.x, point.y, color);
			}
		}

		return image;
	}
}
