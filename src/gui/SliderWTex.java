/**
 * Author: Rubén Labrador Páez.
 * Email: alu0100309553@ull.edu.es
 * Tit: Grado Ingeniería Informática - Universidad de La Laguna
 * Course: 3 - Computación
 * Subject: Programación de aplicaciones interactivas.
 * Practice: 11
 * Class/Program: Parabolic
 * File: SliderWTex.java
 * Description: This is a program to simulate parabolic movement.
 * @author Rubén Labrador Páez
 * @version 1.0.0 02/05/2016
 **/

package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderWTex extends JPanel {
  private JTextField text;
  private JSlider slider;
  private final Dimension textDim = new Dimension(40, 20);
  private final Dimension titleDim = new Dimension(80, 20);
  private final Dimension sliderDim = new Dimension(200, 40);
  private JLabel title;
  private final int MX_T_S = 20;
  private final int MN_T_S = 1;

  //Getters
  private JTextField getText() {
    return text;
  }

  private JSlider getSlider() {
    return slider;
  }

  public double getValue() {
    return slider.getValue();
  }

  //Class Constructor method
  SliderWTex(String name, int max, int min, int value) {
    text = new JTextField();
    text.addActionListener(new MyActionListener());
    text.setPreferredSize(textDim);
    slider = new JSlider();
    slider.addChangeListener(new SliderListener());
    slider.setMaximum(max);
    slider.setValue(value);
    slider.setMinimum(min);
    slider.setMajorTickSpacing(MX_T_S);
    slider.setMinorTickSpacing(MN_T_S);
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);
    slider.setPreferredSize(sliderDim);
    setLayout(new BorderLayout());
    add(slider, BorderLayout.CENTER);
    add(text, BorderLayout.EAST);
    title = new JLabel(name);
    title.setPreferredSize(titleDim);
    add(title, BorderLayout.WEST);
  }

  //Inner class to handle the slider change
  protected class SliderListener implements ChangeListener {
    @Override
    public void stateChanged(ChangeEvent e) {
      if (e.getSource() == getSlider()) {
        getText().setText("" + getSlider().getValue());
      }
    }
  }

  //Inner class to handle text change
  protected class MyActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == getText()) {
        getSlider().setValue(Integer.parseInt(getText().getText()));
      }
    }
  }
}
