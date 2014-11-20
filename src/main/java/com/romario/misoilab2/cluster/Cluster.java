package com.romario.misoilab2.cluster;



import java.awt.*;
import java.util.List;

/**
 * Created by romario on 11/19/14.
 */
public class Cluster {

	private int numberCluster;

	private int belongCluster;
	private boolean center = false;
	private List<Point> points;

	private int area;
	private int perimeter;
	private Point centerOfMass;
	private int density;
	private int elongation;

	private int color;

	public Cluster() {

	}

	public int getNumberCluster() {
		return numberCluster;
	}

	public void setNumberCluster(int numberCluster) {
		this.numberCluster = numberCluster;
	}

	public boolean isCenter() {
		return center;
	}

	public void setCenter(boolean center) {
		this.center = center;
	}

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getPerimeter() {
		return perimeter;
	}

	public void setPerimeter(int perimeter) {
		this.perimeter = perimeter;
	}

	public Point getCenterOfMass() {
		return centerOfMass;
	}

	public void setCenterOfMass(Point centerOfMass) {
		this.centerOfMass = centerOfMass;
	}

	public int getDensity() {
		return density;
	}

	public void setDensity(int density) {
		this.density = density;
	}

	public int getElongation() {
		return elongation;
	}

	public void setElongation(int elongation) {
		this.elongation = elongation;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getBelongCluster() {
		return belongCluster;
	}

	public void setBelongCluster(int belongCluster) {
		this.belongCluster = belongCluster;
	}
}
