package com.romario.misoilab2.form;

import com.romario.misoilab2.cluster.Cluster;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by romario on 9/21/14.
 */
public class Form implements Serializable {

  private BufferedImage sourceBufferedImage;
  private BufferedImage resultBufferedImage;

  private int[][] areas;

	private Map<Integer, List<Integer>> areasMap = new HashMap<>();
	private Map<Integer, List<Point>> areasIndexesMap = new HashMap<>();
	private Map<Integer, Point> centerOfMass = new HashMap<>();
	private Map<Integer, Integer> perimeterMap = new HashMap<>();
	private Map<Integer, Integer> densityMap = new HashMap<>();
	private Map<Integer, Integer> elongationMap = new HashMap<>();

	private List<Cluster> clusters = new ArrayList<>();

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

	public Map<Integer, List<Integer>> getAreasMap() {
		return areasMap;
	}

	public void setAreasMap(Map<Integer, List<Integer>> areasMap) {
		this.areasMap = areasMap;
	}

	public Map<Integer, List<Point>> getAreasIndexesMap() {
		return areasIndexesMap;
	}

	public void setAreasIndexesMap(Map<Integer, List<Point>> areasIndexesMap) {
		this.areasIndexesMap = areasIndexesMap;
	}

	public Map<Integer, Point> getCenterOfMass() {
		return centerOfMass;
	}

	public void setCenterOfMass(Map<Integer, Point> centerOfMass) {
		this.centerOfMass = centerOfMass;
	}

	public Map<Integer, Integer> getPerimeterMap() {
		return perimeterMap;
	}

	public void setPerimeterMap(Map<Integer, Integer> perimeterMap) {
		this.perimeterMap = perimeterMap;
	}

	public Map<Integer, Integer> getDensityMap() {
		return densityMap;
	}

	public void setDensityMap(Map<Integer, Integer> densityMap) {
		this.densityMap = densityMap;
	}

	public List<Cluster> getClusters() {
		return clusters;
	}

	public void setClusters(List<Cluster> clusters) {
		this.clusters = clusters;
	}

	public Map<Integer, Integer> getElongationMap() {
		return elongationMap;
	}

	public void setElongationMap(Map<Integer, Integer> elongationMap) {
		this.elongationMap = elongationMap;
	}
}
