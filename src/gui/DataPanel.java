/**
 * Author: Rubén Labrador Páez.
 * Email: alu0100309553@ull.edu.es
 * Tit: Grado Ingeniería Informática - Universidad de La Laguna
 * Course: 3 - Computación
 * Subject: Programación de aplicaciones interactivas.
 * Practice: 11
 * Class/Program: Parabolic
 * File: DataPanel.java
 * Description: This is a program to simulate parabolic movement.
 * @author Rubén Labrador Páez
 * @version 1.0.0 02/05/2016
 **/

package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DataPanel extends JPanel {
  private JLabel time;
  private JLabel vx;
  private JLabel vy;
  private JLabel v;
  private JLabel posx;
  private JLabel posy;
  private JLabel maxy;
  private final Dimension dim = new Dimension(150, 500);
  
  //Getters
  private JLabel getTime() {
    return time;
  }

  private JLabel getVx() {
    return vx;
  }

  private JLabel getVy() {
    return vy;
  }

  private JLabel getV() {
    return v;
  }

  private JLabel getPosx() {
    return posx;
  }

  private JLabel getPosy() {
    return posy;
  }

  private JLabel getMaxy() {
    return maxy;
  }

  private Dimension getDim() {
    return dim;
  }

  //Class contructor method
  DataPanel() {
    setLayout(new GridLayout(20, 1, 1, 1));
    time = new JLabel();
    vx = new JLabel();
    vy = new JLabel();
    v = new JLabel();
    posx = new JLabel();
    posy = new JLabel();
    maxy = new JLabel();
    add(time);
    add(posx);
    add(posy);
    add(vx);
    add(vy);
    add(v);
    add(maxy);
    setPreferredSize(dim);
  }

  //Set the values of JLabel during the movement
  public void setData(Hashtable<String, Double> data) {
    getTime().setText("t: " + String.format("%1$,.1f", data.get("t")) + "s.");
    getPosx().setText("Pos X: " + String.format("%1$,.1f", data.get("x")) + "m.");
    getPosy().setText("Pos Y: " + String.format("%1$,.1f", data.get("y")) + "m.");
    getVx().setText("Vx: " + String.format("%1$,.1f", data.get("vx")) + "m/s.");
    getVy().setText("Vy: " + String.format("%1$,.1f", data.get("vy")) + "m/s.");
    getV().setText("V: " + String.format("%1$,.1f", data.get("v")) + "m/s.");
    getMaxy().setText("Y max: " + String.format("%1$,.1f", data.get("ymax")) + "m.");
  }
}
