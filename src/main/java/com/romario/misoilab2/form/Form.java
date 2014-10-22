package com.romario.misoilab2.form;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Created by romario on 9/21/14.
 */
public class Form implements Serializable {

	private static final int MIN_VALUE_PIXEL = 0;
	private static final int MAX_VALUE_PIXEL = 255;

	private BufferedImage sourceBufferedImage;
	private BufferedImage resultBufferedImage;

	private int gMin = MIN_VALUE_PIXEL;
	private int gMax = MAX_VALUE_PIXEL;
	private int fMin = MIN_VALUE_PIXEL;
	private int fMax = MAX_VALUE_PIXEL;

	private String nameMethod;

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
	}

	public BufferedImage getResultBufferedImage() {
		return resultBufferedImage;
	}

	public void setResultBufferedImage(BufferedImage resultBufferedImage) {
		this.resultBufferedImage = resultBufferedImage;
	}

	public int getgMin() {
		return gMin;
	}

	public void setgMin(int gMin) {
		this.gMin = gMin;
	}

	public int getgMax() {
		return gMax;
	}

	public void setgMax(int gMax) {
		this.gMax = gMax;
	}

	public int getfMin() {
		return fMin;
	}

	public void setfMin(int fMin) {
		this.fMin = fMin;
	}

	public int getfMax() {
		return fMax;
	}

	public void setfMax(int fMax) {
		this.fMax = fMax;
	}

	public String getNameMethod() {
		return nameMethod;
	}

	public void setNameMethod(String nameMethod) {
		this.nameMethod = nameMethod;
	}
}
