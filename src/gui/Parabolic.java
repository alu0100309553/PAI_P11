/**
 * Author: Rubén Labrador Páez.
 * Email: alu0100309553@ull.edu.es
 * Tit: Grado Ingeniería Informática - Universidad de La Laguna
 * Course: 3 - Computación
 * Subject: Programación de aplicaciones interactivas.
 * Practice: 11
 * Class/Program: Parabolic
 * File: Parabolic.java
 * Description: This is a program to simulate parabolic movement.
 * @author Rubén Labrador Páez
 * @version 1.0.0 02/05/2016
 **/

package gui;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JApplet;

public class Parabolic extends JApplet {

  private double vx0 = 0;
  private double vx02 = 0;
  private double vy0 = 0;
  private double tFinal = 0;
  private double h0 = 0;
  private final double TIME_L = 0.1;
  private final double G = 9.81;

  //Getters and setters
  private double getVx0() {
    return vx0;
  }

  private double getVx02() {
    return vx02;
  }

  private double getVy0() {
    return vy0;
  }

  private double gettFinal() {
    return tFinal;
  }

  private double getH0() {
    return h0;
  }

  private void setVx0(double vx0) {
    this.vx0 = vx0;
  }

  private void setVx02(double vx02) {
    this.vx02 = vx02;
  }

  private void setVy0(double vy0) {
    this.vy0 = vy0;
  }

  private void settFinal(double tFinal) {
    this.tFinal = tFinal;
  }

  private void setH0(double h0) {
    this.h0 = h0;
  }

  //Class constructor
  public Parabolic() {
  };

  //Method that calculates the movement position every 100 ms. 
  public ArrayList<Hashtable<String, Double>> calc(double h0_, double v0,
      double angle) {
    setH0(h0_);
    setVx0(v0 * Math.cos(Math.toRadians(angle)));
    setVx02(getVx0() * getVx0());
    setVy0(v0 * Math.sin(Math.toRadians(angle)));
    settFinal(tFinalC());
    ArrayList<Hashtable<String, Double>> aux = new ArrayList<Hashtable<String, Double>>();
    double ymax = 0;
    for (double t = 0; t <= gettFinal(); t += TIME_L) {
      Hashtable<String, Double> auxHash = new Hashtable<String, Double>();
      auxHash.put("t", t);
      auxHash.put("x", xpos(t));
      double yp = ypos(t);
      if (yp > ymax) {
        ymax = yp;
      }
      auxHash.put("y", yp);
      auxHash.put("vx", getVx0());
      double vy = vyt(t);
      auxHash.put("vy", vy);
      auxHash.put("v", vt(vy));
      auxHash.put("ymax", ymax);
      aux.add(auxHash);
    }
    Hashtable<String, Double> auxHash2 = new Hashtable<String, Double>();
    auxHash2.put("t", gettFinal());
    auxHash2.put("x", xpos(gettFinal()));
    double yp = ypos(gettFinal());
    if (yp > ymax) {
      ymax = yp;
    }
    auxHash2.put("y", yp);
    auxHash2.put("vx", getVx0());
    double vy = vyt(gettFinal());
    auxHash2.put("vy", vy);
    auxHash2.put("v", vt(vy));
    auxHash2.put("ymax", ymax);
    aux.add(auxHash2);
    return aux;
  }

  //Y speed in time function
  private double vyt(double t) {
    return getVy0() - (G * t);
  }

  //X pos in time function
  private double xpos(double t) {
    return getVx0() * t;
  }

  //Y pos in time function
  private double ypos(double t) {
    return (-0.5 * G * t * t) + (getVy0() * t) + getH0();
  }

  //V in time function
  private double vt(double vyt) {
    return Math.sqrt((vyt * vyt) + getVx02());
  }

  //Total time of the movement
  private double tFinalC() {
    return Math.max(
        ((-getVy0() + Math.sqrt((getVy0() * getVy0()) + (2 * G * getH0())))
            / -G),
        ((-getVy0() - Math.sqrt((getVy0() * getVy0()) + (2 * G * getH0())))
            / -G));
  }
}
