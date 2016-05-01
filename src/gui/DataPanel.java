package gui;

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
  
  DataPanel(){
    this.setLayout(new GridLayout(7,1,1,1));
    time = new JLabel();
    vx = new JLabel();
    vy = new JLabel();
    v = new JLabel ();
    posx = new JLabel ();
    posy = new JLabel ();
    maxy = new JLabel ();
    this.add(time);
    this.add(posx);
    this.add(posy);
    this.add(vx);
    this.add(vy);
    this.add(v);
    this.add(maxy);
  }
  
  public void setData(Hashtable<String, Double> data){
    time.setText("t: " + String.format("%1$,.1f",data.get("t")) + "s.");
    posx.setText("Pos X: " + String.format("%1$,.1f",data.get("x")) + "m.");
    posy.setText("Pos Y: " + String.format("%1$,.1f",data.get("y")) + "m.");
    vx.setText("Vx: " + String.format("%1$,.1f",data.get("vx")) + "m/s.");
    vy.setText("Vy: " + String.format("%1$,.1f",data.get("vy")) + "m/s.");
    v.setText("V: " + String.format("%1$,.1f",data.get("v")) + "m/s.");
    maxy.setText("Y max: " + String.format("%1$,.1f",data.get("ymax")) + "m.");    
  }

}
