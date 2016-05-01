package gui;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JApplet;

public class Parabolic extends JApplet{

  private final double G = 9.81;
  private double vx0=0;
  private double vx02 = 0;
  private double vy0=0;
  private double tFinal = 0;
  private double h0 = 0;
  public Parabolic(){};

  public ArrayList <Hashtable<String, Double>> calc(double h0_, double v0, double angle ){
    h0 = h0_;
    vx0 = v0 * Math.cos(Math.toRadians(angle));
    vx02 = vx0*vx0;
    vy0 = v0 * Math.sin(Math.toRadians(angle));
    tFinal = tFinalC();
    ArrayList <Hashtable<String, Double>> aux = new ArrayList <Hashtable<String, Double>>();
    double ymax = 0;
    for (double t = 0; t <= tFinal; t += 0.1){
      Hashtable<String, Double> auxHash = new Hashtable<String, Double>();
      auxHash.put("t", t);
      auxHash.put("x", xpos(t));
      double yp = ypos(t);
      if (yp > ymax){
        ymax = yp;
      }
      auxHash.put("y", yp);
      auxHash.put("vx", vx0);
      double vy = vyt(t);
      auxHash.put("vy", vy);
      auxHash.put("v", vt(vy));
      auxHash.put("ymax", ymax);
      aux.add(auxHash);
    }
    Hashtable<String, Double> auxHash2 = new Hashtable<String, Double>();
    auxHash2.put("t", tFinal);
    auxHash2.put("x", xpos(tFinal));
    double yp = ypos(tFinal);
    if (yp > ymax){
      ymax = yp;
    }
    auxHash2.put("y", yp);
    auxHash2.put("vx", vx0);
    double vy = vyt(tFinal);
    auxHash2.put("vy", vy);
    auxHash2.put("v", vt(vy));
    auxHash2.put("ymax", ymax);
    aux.add(auxHash2);
    
    return aux;
  }

  private double vyt(double t){
    return vy0-(G*t);
  }

  private double xpos(double t){
    return vx0*t;
  }

  private double ypos(double t){
    return (-0.5*G*t*t) + (vy0 * t) + h0;
  }

  private double vt (double vyt){
    return Math.sqrt((vyt*vyt)+vx02);
  }

  private double tFinalC(){
    return Math.max(((-vy0+Math.sqrt((vy0*vy0)+(2*G*h0)))/-G),((-vy0-Math.sqrt((vy0*vy0)+(2*G*h0)))/-G));  
  }


}
