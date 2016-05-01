package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JPanel;

public class GraphPanel extends JPanel {
  private int scale = 0;
  private Hashtable <Integer, ArrayList<Point>> pintados = new Hashtable <Integer, ArrayList<Point>>();
  private Color [] colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.CYAN, Color.GREEN, Color.PINK, Color.ORANGE, Color.BLACK, Color.WHITE};
  private boolean axis = false;
  private Point actualPoint = new Point();
  private int actualTiro = 0;
  private int vectorXA = 0;
  private int vectorYA = 0;
  private int vectorXB = 0;
  private int vectorYB = 0;
  private boolean vector = false;
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (int tiro : pintados.keySet()){
      g.setColor(colors[tiro%9]);
      for (Point p : pintados.get(tiro)){
        g.fillOval((p.x)-2, (p.y)-2, 4, 4);
      }
    }
    if (axis){
      g.setColor(Color.BLACK);
      drawAx(g);
    }
    if (vector){
      g.setColor(Color.BLACK);
      g.drawLine(vectorXA, vectorYA, vectorXB, vectorYB);
      g.drawLine(vectorXB, vectorYB, vectorXB, vectorYB+10);
      g.drawLine(vectorXB, vectorYB, vectorXB-10, vectorYB);
    }
    g.setColor(colors[actualTiro%9]);
    g.fillOval((actualPoint.x)-2, (actualPoint.y)-2, 4, 4);
  }
  public void drawPoint (double x, double y, boolean safe, int tironumber){
    int i = (int)((x * scale) + (this.getWidth() * 0.05));
    int j = (int)((this.getHeight() * 0.95) - (y * scale));
    actualPoint = new Point (i, j);
    actualTiro = tironumber;
    if (safe){
      if (pintados.containsKey(tironumber)){
        pintados.get(tironumber).add(new Point (i, j));
      }else{
        pintados.put(tironumber, new ArrayList <Point>());
        pintados.get(tironumber).add(new Point (i,j));
      }
    }
    repaint();
  }

  private void drawAx (Graphics g){
    g.drawLine((int)(this.getWidth()*0.05), (int)(this.getHeight()*0.95), (int)(this.getWidth()*0.95), (int)(this.getHeight()*0.95));
    g.drawLine((int)(this.getWidth()*0.05), (int)(this.getHeight()*0.05), (int)(this.getWidth()*0.05), (int)(this.getHeight()*0.95));
    int xpos = (int)(this.getWidth()*0.05)+ scale;
    int yposA = (int)(this.getHeight()*0.95);
    int yposB = (int)(this.getHeight()*0.96);
    while (xpos < this.getWidth()*0.95){
      g.drawLine(xpos,yposA , xpos, yposB);
      xpos += scale; 
    }
    int ypos = (int)(this.getHeight()*0.95)- scale;
    int xposA = (int)(this.getWidth()*0.05);
    int xposB = (int)(this.getWidth()*0.04);
    while (ypos > this.getHeight()*0.05){
      g.drawLine(xposA,ypos , xposB, ypos);
      ypos -= scale; 
    }
  }
  public void drawAxis (double maxX, double maxY){
    if (!axis){
      int maxXpx = (int) (this.getWidth() * 0.9);
      int maxYpx = (int) (this.getHeight() * 0.9);
      scale = (int) Math.min((maxXpx/maxX), (maxYpx/maxY));
    }
    axis = true;
    repaint();
  }

  public void reset (){
    pintados.clear();
    axis = false;
    actualPoint = new Point();
    vector = false;
    repaint();
  }

  public void setVector(double vx, double vy){
    vectorXA = (int)(this.getWidth()*0.05);
    vectorYA = (int)(this.getHeight()*0.95);
    vectorXB = vectorXA+(int)(vx*40/100);
    vectorYB = vectorYA-(int)(vy*40/100);
    vector = true;
  }
}
