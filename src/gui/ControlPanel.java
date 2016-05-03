/**
 * Author: Rubén Labrador Páez.
 * Email: alu0100309553@ull.edu.es
 * Tit: Grado Ingeniería Informática - Universidad de La Laguna
 * Course: 3 - Computación
 * Subject: Programación de aplicaciones interactivas.
 * Practice: 11
 * Class/Program: Parabolic
 * File: ControlPanel.java
 * Description: This is a program to simulate parabolic movement.
 * @author Rubén Labrador Páez
 * @version 1.0.0 02/05/2016
 **/

package gui;

import java.awt.BorderLayout;

import event.ControlPanelEventObject;
import event.ControlPanelListener;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class ControlPanel extends JPanel implements ControlPanelListener {
  protected JButton lanzarBt;
  protected JButton pausaBt;
  protected JButton borrarBt;
  private SliderWTex angulSl;
  private SliderWTex velSl;
  private SliderWTex alturaSl;
  protected JCheckBox rastroCB;
  private JPanel btPanel;
  private JPanel slPanel;
  private JPanel chPanel;
  private ArrayList<ControlPanelListener> listeners = new ArrayList<ControlPanelListener>();
  private final int MIN_VEL = 1;
  private final int SET_VEL = 50;
  private final int MAX_VEL = 100;
  private final int MIN_ANG = 0;
  private final int SET_ANG = 45;
  private final int MAX_ANG = 90;
  private final int MIN_ALT = 0;
  private final int SET_ALT = 0;
  private final int MAX_ALT = 100;

  //Getters and Setters
  public JButton getLanzarBt() {
    return lanzarBt;
  }

  public JButton getPausaBt() {
    return pausaBt;
  }

  public JButton getBorrarBt() {
    return borrarBt;
  }

  public JCheckBox getRastroCB() {
    return rastroCB;
  }

  private ArrayList<ControlPanelListener> getListeners() {
    return listeners;
  }

  public double getVel() {
    return velSl.getValue();
  }

  public double getAngulo() {
    return angulSl.getValue();
  }

  public double getAltura() {
    return alturaSl.getValue();
  }

  //Class Constructor
  ControlPanel() {
    setLayout(new BorderLayout());
    btPanel = new JPanel();
    slPanel = new JPanel();
    chPanel = new JPanel();
    btPanel.setLayout(new GridLayout(3, 1, 1, 1));
    slPanel.setLayout(new GridLayout(3, 1, 1, 1));
    chPanel.setLayout(new GridLayout(3, 1, 1, 1));
    lanzarBt = new JButton("Lanzar");
    pausaBt = new JButton("Pausa");
    borrarBt = new JButton("Borrar");
    lanzarBt.setName("lanzar");
    pausaBt.setName("pausa");
    borrarBt.setName("borrar");
    btPanel.add(lanzarBt);
    btPanel.add(pausaBt);
    btPanel.add(borrarBt);
    lanzarBt.addActionListener(new BtListener());
    pausaBt.addActionListener(new BtListener());
    borrarBt.addActionListener(new BtListener());
    add(btPanel, BorderLayout.WEST);
    velSl = new SliderWTex("Velocidad", MAX_VEL, MIN_VEL, SET_VEL);
    angulSl = new SliderWTex("Ángulo", MAX_ANG, MIN_ANG, SET_ANG);
    alturaSl = new SliderWTex("Altura", MAX_ALT, MIN_ALT, SET_ALT);
    slPanel.add(velSl);
    slPanel.add(angulSl);
    slPanel.add(alturaSl);
    add(slPanel, BorderLayout.CENTER);
    rastroCB = new JCheckBox("Mostrar Rastro");
    chPanel.add(rastroCB);
    add(chPanel, BorderLayout.EAST);
  }

  //Add listeners to this class method
  public void addControlPanelListener(ControlPanelListener e) {
    getListeners().add(e);
  }

  @Override
  public void ControlPanel(ControlPanelEventObject e) {

  }
  
  //Inner class for button events
  protected class BtListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      for (ControlPanelListener listener : getListeners()) {
        ControlPanelEventObject evObject = new ControlPanelEventObject(
            e.getSource());
        listener.ControlPanel(evObject);
      }
    }
  }
}
