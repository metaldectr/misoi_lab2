package com.romario.misoilab2.logic;

import com.romario.misoilab2.cluster.Cluster;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by romario on 11/19/14.
 */
public class Converter {

	public static List<Cluster> convertAreasToClusters(Map<Integer, List<Point>> areasIndexesMap,
			Map<Integer, Point> centerOfMass, Map<Integer, Integer> perimeterMap,
			Map<Integer, Integer> densityMap, Map<Integer, Integer> elongationMap) {


		List<Cluster> clusters = new ArrayList<>();
		for (Integer indexMap : areasIndexesMap.keySet()) {
			Cluster cluster = new Cluster();
			cluster.setPoints(areasIndexesMap.get(indexMap));
			cluster.setNumberCluster(indexMap);
			cluster.setArea(areasIndexesMap.get(indexMap).size());
			cluster.setCenterOfMass(centerOfMass.get(indexMap));
			cluster.setDensity(densityMap.get(indexMap));
			cluster.setPerimeter(perimeterMap.get(indexMap));
			//cluster.setElongation(elongationMap.get(indexMap));

			clusters.add(cluster);
		}

		return clusters;
	}


}
