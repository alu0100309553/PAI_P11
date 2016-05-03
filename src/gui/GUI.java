/**
 * Author: Rubén Labrador Páez.
 * Email: alu0100309553@ull.edu.es
 * Tit: Grado Ingeniería Informática - Universidad de La Laguna
 * Course: 3 - Computación
 * Subject: Programación de aplicaciones interactivas.
 * Practice: 11
 * Class/Program: Parabolic
 * File: GUI.java
 * Description: This is a program to simulate parabolic movement.
 * @author Rubén Labrador Páez
 * @version 1.0.0 02/05/2016
 **/

package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import event.ControlPanelEventObject;
import event.ControlPanelListener;

public class GUI extends JApplet {
  private DataPanel data;
  private GraphPanel graph;
  private ControlPanel control;
  private Parabolic calc;
  private JPanel infoPanel;
  private Timer timer;
  private int timeCounter;
  private int shootCounter;
  private boolean onCourse = false;
  private boolean trail = false;
  private ArrayList<Hashtable<String, Double>> shoot;
  private final int T_LAP = 100;

  //Getters and setters
  private DataPanel getData() {
    return data;
  }

  private GraphPanel getGraph() {
    return graph;
  }

  public ControlPanel getControl() {
    return control;
  }

  private Parabolic getCalc() {
    return calc;
  }

  public Timer getTimer() {
    return timer;
  }

  private int getTimeCounter() {
    return timeCounter;
  }

  public int getShootCounter() {
    return shootCounter;
  }

  private boolean isOnCourse() {
    return onCourse;
  }

  private boolean isTrail() {
    return trail;
  }

  private ArrayList<Hashtable<String, Double>> getShoot() {
    return shoot;
  }

  private void setTimeCounter(int timeCounter) {
    this.timeCounter = timeCounter;
  }

  private void setShootCounter(int shootCounter) {
    this.shootCounter = shootCounter;
  }

  private void setOnCourse(boolean onCourse) {
    this.onCourse = onCourse;
  }

  private void setShoot(ArrayList<Hashtable<String, Double>> shoot) {
    this.shoot = shoot;
  }

  private void setTrail(boolean trail) {
    this.trail = trail;
  }

  //Init method to use as an applet
  public void init() {
    timer = new Timer(T_LAP, new MyActionListener());
    infoPanel = new JPanel();
    data = new DataPanel();
    graph = new GraphPanel();
    control = new ControlPanel();
    calc = new Parabolic();
    control.addControlPanelListener(new MyEventHandler());
    setLayout(new BorderLayout());
    add(control, BorderLayout.SOUTH);
    infoPanel.setLayout(new BorderLayout());
    infoPanel.add(graph, BorderLayout.CENTER);
    infoPanel.add(data, BorderLayout.EAST);
    add(infoPanel, BorderLayout.CENTER);
  }

  //Main method to use as an stand alone app
  public static void main(String[] args) {
    JFrame frame = new JFrame("Parabolic");
    GUI applet = new GUI();
    frame.add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setSize(screenSize);
    frame.setVisible(true);
  }

  //Inner class for control panel events
  class MyEventHandler implements ControlPanelListener {
    @Override
    public void ControlPanel(ControlPanelEventObject e) {
      if (e.getSource() == getControl().getLanzarBt()) {
        if (!isOnCourse()) {
          setShoot(getCalc().calc(getControl().getAltura(),
              getControl().getVel(), getControl().getAngulo()));
          getGraph().setVector(getShoot().get(0).get("vx"),
              getShoot().get(0).get("vy"));

          setTrail(getControl().getRastroCB().isSelected());
          if (getShootCounter() == 0) {
            getGraph().drawAxis(getShoot().get(getShoot().size() - 1).get("x"),
                getShoot().get(getShoot().size() - 1).get("ymax"));
          }
          setShootCounter(getShootCounter() + 1);
          setOnCourse(true);
        }
        getTimer().start();

      } else if (e.getSource() == getControl().getPausaBt()) {
        getTimer().stop();
      } else if (e.getSource() == getControl().getBorrarBt()) {
        setOnCourse(false);
        getTimer().stop();
        setTimeCounter(0);
        getGraph().reset();
        setShootCounter(0);
      }
    }
  }

  //Inner class for timer events
  class MyActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == getTimer()) {
        if (getShoot().size() > getTimeCounter()) {
          getGraph().drawPoint(getShoot().get(getTimeCounter()).get("x"),
              getShoot().get(getTimeCounter()).get("y"), isTrail(),
              getShootCounter());
          getData().setData(getShoot().get(getTimeCounter()));
          setTimeCounter(getTimeCounter() + 1);
        } else {
          setOnCourse(false);
          getTimer().stop();
          setTimeCounter(0);
        }
      }
    }
  }
}
