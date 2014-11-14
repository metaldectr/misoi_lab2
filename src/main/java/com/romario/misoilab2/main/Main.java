package com.romario.misoilab2.main;

import com.romario.misoilab2.gui.MyFrame;

import javax.swing.*;

/**
 * Created by romario on 10/22/14.
 */
public class Main {

  public static void main(String[] args) {
	  System.out.println("start");

	  MyFrame frame = new MyFrame();
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  frame.setVisible(true);

	  System.out.println("finish");
  }

}
