package com.romario.misoilab2.logic;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by romario on 11/17/14.
 */
public class Sign {

	/*public int getArea(int[][] binaryImage) {

	}*/

	public Map<Integer, List<Integer>> convertArrayToMapAreas(int[][] areas, int w, int h) {
		Map<Integer, List<Integer>> map = new HashMap<>();
		int counter = 0;
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				if (areas[i][j] != 0) {
					if (map.containsKey(areas[i][j])) {
						List<Integer> tmp = map.get(areas[i][j]);
						tmp.add(counter++);
						map.put(areas[i][j], tmp);
					} else {
						List<Integer> list = new ArrayList<>();
						list.add(counter);
						map.put(areas[i][j], list);
					}

				}
			}
		}

		return map;
	}

  public Map<Integer, List<Point>> defineAreasToMap(int[][] binaryImage, int w, int h) {
		Map<Integer, List<Point>> map = new HashMap<>();
		 for (int i = 0; i < w; i++) {
			 for (int j = 0; j < h; j++) {
				 if (binaryImage[i][j] != 0) {
					 if (map.containsKey(binaryImage[i][j])) {
						 List<Point> tmpList = map.get(binaryImage[i][j]);
					   Point point = new Point();
						 point.setLocation(i, j);
						 tmpList.add(point);
						 map.put(binaryImage[i][j], tmpList);
					 } else {
						 List<Point> listPoint = new ArrayList<>();
						 Point point = new Point();
						 point.setLocation(i, j);
						 listPoint.add(point);
						 map.put(binaryImage[i][j], listPoint);
					 }
				 }
			 }
		 }
		return map;
	}

  public Map<Integer, Point> calculateCenterOfMass(Map<Integer, List<Point>> areasIndexesMap) {

	  Map<Integer, Point> map = new HashMap<>();

	  for (Integer index : areasIndexesMap.keySet()) {
		  List<Point> listPoint = areasIndexesMap.get(index);
		  int ySum = 0;
		  int xSum = 0;
		  for (Point point : listPoint) {
				xSum += point.getX();
			  ySum += point.getY();
		  }
		  Point elem = new Point();
		  elem.setLocation(xSum/areasIndexesMap.get(index).size(), ySum/areasIndexesMap.get(index).size());
		  map.put(index, elem);
	  }

	  return map;
  }

  public Map<Integer, Integer> calculatePerimeter(Map<Integer, List<Point>> areasIndexesMap,
		  int[][] binaryImage, int w, int h) {

		Map<Integer, Integer> perimeterMap = new HashMap<>();

	  for (Integer indexMap : areasIndexesMap.keySet()) {
			List<Point> points = areasIndexesMap.get(indexMap);
		  int counter = 0;
		  for (Point point : points) {
			  if (checkerPixel(binaryImage, (int)point.getX() - 1, (int)point.getY(), w, h)
					  && checkerPixel(binaryImage, (int)point.getX() + 1, (int)point.getY(), w, h)
					  && checkerPixel(binaryImage, (int)point.getX(), (int)point.getY() - 1, w, h)
					  && checkerPixel(binaryImage, (int)point.getX(), (int)point.getY() + 1, w, h)) {
				  counter++;
			  }
		  }
		  perimeterMap.put(indexMap, points.size() - counter);
	  }

		return perimeterMap;
	}

  private boolean checkerPixel(final int[][] image, final int x, final int y, final int w,
      final int h) {
    if (x < w && y < h) {
      if (image[x][y] != 0) {
        return true;
      }
    }
    return false;
  }

	/*public Map<Integer, Integer> calculateElongation() {

	}*/

  public Map<Integer, Integer> calculateDensity(Map<Integer, List<Point>> areasIndexesMap, Map<Integer, Integer> perimeterMap) {
		Map<Integer, Integer> densityMap = new HashMap<>();

		for (Integer indexMap : areasIndexesMap.keySet()) {
			int density =
					(int) (Math.pow(perimeterMap.get(indexMap), 2) / areasIndexesMap.get(indexMap).size());
			densityMap.put(indexMap, density);
		}

		return densityMap;
	}

}
