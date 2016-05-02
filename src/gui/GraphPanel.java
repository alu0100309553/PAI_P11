/**
 * Author: Rubén Labrador Páez.
 * Email: alu0100309553@ull.edu.es
 * Tit: Grado Ingeniería Informática - Universidad de La Laguna
 * Course: 3 - Computación
 * Subject: Programación de aplicaciones interactivas.
 * Practice: 11
 * Class/Program: Parabolic
 * File: GraphPanel.java
 * Description: This is a program to simulate parabolic movement.
 * @author Rubén Labrador Páez
 * @version 1.0.0 02/05/2016
 **/

package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JPanel;

public class GraphPanel extends JPanel {
  private int scale = 0;
  private Hashtable<Integer, ArrayList<Point>> painted = new Hashtable<Integer, ArrayList<Point>>();
  private Color[] colors = { Color.RED, Color.BLUE, Color.YELLOW, Color.CYAN,
      Color.GREEN, Color.PINK, Color.ORANGE, Color.BLACK, Color.WHITE };
  private boolean axis = false;
  private Point actualPoint = new Point(-5,-5);
  private int actualShoot = 0;
  private int vectorXA = 0;
  private int vectorYA = 0;
  private int vectorXB = 0;
  private int vectorYB = 0;
  private boolean vector = false;
  private final double A_MIN_L = 0.05;
  private final double A_MAX_L = 0.95;
  private final double A_RED_F = 0.9;
  private final double M_OFFSET_H = 0.01;
  private final double M_OFFSET_V = 0.005;
  private final int V_X_N_OFF = 40;
  private final int V_Y_N_OFF = 5;
  private final int H_X_N_OFF = 10;
  private final int H_Y_N_OFF = 25;
  private final int SM_OFF = 10;
  private final int AH_OFF = 10;
  private final int POINT_D = 6;
  private final int POINT_OFF = 3;
  private final int L_SM_LAPSE = 1;
  private final int M_SM_LAPSE = 10;
  private final int S_SM_LAPSE = 100;
  private final int L_SM_PX_L = 3;
  private final int M_SP_PX_L = 20;
  
  //Getters and setters
  private int getScale() {
    return scale;
  }

  private Hashtable<Integer, ArrayList<Point>> getPainted() {
    return painted;
  }

  private Color[] getColors() {
    return colors;
  }

  private boolean isAxis() {
    return axis;
  }

  private Point getActualPoint() {
    return actualPoint;
  }

  private int getActualShoot() {
    return actualShoot;
  }

  private int getVectorXA() {
    return vectorXA;
  }

  private int getVectorYA() {
    return vectorYA;
  }

  private int getVectorXB() {
    return vectorXB;
  }

  private int getVectorYB() {
    return vectorYB;
  }

  private boolean isVector() {
    return vector;
  }

  private void setScale(int scale) {
    this.scale = scale;
  }

  private void setAxis(boolean axis) {
    this.axis = axis;
  }

  private void setActualPoint(Point actualPoint) {
    this.actualPoint = actualPoint;
  }

  private void setActualShoot(int actualShoot) {
    this.actualShoot = actualShoot;
  }

  private void setVectorXA(int vectorXA) {
    this.vectorXA = vectorXA;
  }

  private void setVectorYA(int vectorYA) {
    this.vectorYA = vectorYA;
  }

  private void setVectorXB(int vectorXB) {
    this.vectorXB = vectorXB;
  }

  private void setVectorYB(int vectorYB) {
    this.vectorYB = vectorYB;
  }

  private void setVector(boolean vector) {
    this.vector = vector;
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(getColors()[getActualShoot() % getColors().length]);
    g.fillOval((getActualPoint().x) - POINT_OFF,
        (getActualPoint().y) - POINT_OFF, POINT_D, POINT_D);
    for (int tiro : getPainted().keySet()) {
      g.setColor(getColors()[tiro % getColors().length]);
      for (Point p : getPainted().get(tiro)) {
        g.fillOval((p.x) - POINT_OFF, (p.y) - POINT_OFF, POINT_D, POINT_D);
      }
    }
    if (isAxis()) {
      g.setColor(Color.BLACK);
      drawAx(g);
    }
    if (isVector()) {
      g.setColor(Color.BLACK);
      g.drawLine(getVectorXA(), getVectorYA(), getVectorXB(), getVectorYB());
      g.drawLine(getVectorXB(), getVectorYB(), getVectorXB(),
          (getVectorYB() + AH_OFF));
      g.drawLine(getVectorXB(), getVectorYB(), (getVectorXB() - AH_OFF),
          getVectorYB());
    }

  }

  //Method to set a point to draw
  public void drawPoint(double x, double y, boolean safe, int shootnumber) {
    int i = (int) ((x * getScale()) + (getWidth() * A_MIN_L));
    int j = (int) ((getHeight() * A_MAX_L) - (y * getScale()));
    setActualPoint(new Point(i, j));
    setActualShoot(shootnumber);
    if (safe) {
      if (getPainted().containsKey(shootnumber)) {
        getPainted().get(shootnumber).add(new Point(i, j));
      } else {
        getPainted().put(shootnumber, new ArrayList<Point>());
        getPainted().get(shootnumber).add(new Point(i, j));
      }
    }
    repaint();
  }

  //Method to draw axis
  private void drawAx(Graphics g) {
    g.drawLine((int) (getWidth() * A_MIN_L), (int) (getHeight() * A_MAX_L),
        (int) (getWidth() * A_MAX_L), (int) (getHeight() * A_MAX_L));
    g.drawLine((int) (getWidth() * A_MIN_L), (int) (getHeight() * A_MIN_L),
        (int) (getWidth() * A_MIN_L), (int) (getHeight() * A_MAX_L));
    int xpos = (int) (getWidth() * A_MIN_L);
    int yposA = (int) (getHeight() * A_MAX_L);
    int yposB = (int) (getHeight() * (A_MAX_L + M_OFFSET_H));
    int marks = 0;
    if (getScale() <= L_SM_PX_L) {
      marks = S_SM_LAPSE;
    } else if (getScale() <= M_SP_PX_L) {
      marks = M_SM_LAPSE;
    } else {
      marks = L_SM_LAPSE;
    }

    int markCounter = 0;
    while (xpos < getWidth() * A_MAX_L) {
      g.drawLine(xpos, yposA, xpos, yposB);
      if (markCounter % marks == 0) {
        g.drawLine(xpos, yposA, xpos, yposB + SM_OFF);
        g.drawString("" + markCounter, xpos - H_X_N_OFF, yposB + H_Y_N_OFF);

      }
      xpos += getScale();
      markCounter++;

    }
    int ypos = (int) (getHeight() * A_MAX_L);
    int xposA = (int) (getWidth() * A_MIN_L);
    int xposB = (int) (getWidth() * (A_MIN_L - M_OFFSET_V));
    markCounter = 0;
    while (ypos > getHeight() * A_MIN_L) {
      g.drawLine(xposA, ypos, xposB, ypos);

      if (markCounter % marks == 0) {
        g.drawLine(xposA, ypos, xposB - SM_OFF, ypos);
        g.drawString("" + markCounter, xposB - V_X_N_OFF, ypos + V_Y_N_OFF);

      }
      ypos -= getScale();
      markCounter++;
    }
  }

  //Method to set axis
  public void drawAxis(double maxX, double maxY) {
    if (!isAxis()) {
      int maxXpx = (int) (getWidth() * A_RED_F);
      int maxYpx = (int) (getHeight() * A_RED_F);
      setScale((int) Math.min((maxXpx / maxX), (maxYpx / maxY)));
    }
    setAxis(true);
    repaint();
  }

  //Method to reset the panel
  public void reset() {
    getPainted().clear();
    setAxis(false);
    setActualPoint(new Point(-5,-5));
    setVector(false);
    repaint();
  }

  //Method to set movement initial vector
  public void setVector(double vx, double vy) {
    setVectorXA((int) (getWidth() * A_MIN_L));
    setVectorYA((int) (getHeight() * A_MAX_L));
    setVectorXB(vectorXA + (int) vx);
    setVectorYB(vectorYA - (int) vy);
    setVector(true);
  }
}
