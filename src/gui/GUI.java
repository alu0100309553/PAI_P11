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
  private boolean isStandAlone = false;
  private JPanel infoPanel;
  private Timer timer;
  private int timeCounter;
  private int tirosCounter;
  private ArrayList <Hashtable<String, Double>> tiro;


  public void init() {
    timer = new Timer(100, new MyActionListener());
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

  public static void main(String[] args) {
    JFrame frame = new JFrame("Parabolic");
    GUI applet = new GUI();
    applet.isStandAlone = true;
    frame.add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    // Dimension screenSize = new Dimension (600,300);
    frame.setSize(screenSize);
    frame.setVisible(true);

  }

  class MyEventHandler implements ControlPanelListener {
    @Override
    public void ControlPanel(ControlPanelEventObject e) {
      if (e.getSource() == control.lanzarBt) {


        tiro = calc.calc(control.getAltura(), control.getVel(), control.getAngulo());
        graph.setVector(tiro.get(0).get("vx"), tiro.get(0).get("vy"));
        timer.start();
        if (tirosCounter == 0){
          graph.drawAxis(tiro.get(tiro.size()-1).get("x"), tiro.get(tiro.size()-1).get("ymax"));

        }
        tirosCounter++;

      } else if (e.getSource() == control.pausaBt) {
        timer.stop();
      } else if (e.getSource() == control.borrarBt) {
        timer.stop();
        timeCounter = 0;
        graph.reset();
        tirosCounter = 0;


      }

    }

  }

  class MyActionListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == timer){
        if (tiro.size()>timeCounter){
          graph.drawPoint(tiro.get(timeCounter).get("x"), tiro.get(timeCounter).get("y"), control.rastroCB.isSelected(), tirosCounter );
          data.setData(tiro.get(timeCounter));
          timeCounter++;
        } else {
          timer.stop();
          timeCounter = 0;
        }
      }
    }

  }

}
