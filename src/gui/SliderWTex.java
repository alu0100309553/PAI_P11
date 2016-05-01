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
  private Dimension textDim = new Dimension(40,20);
  private Dimension titleDim = new Dimension(80,20);
  private Dimension sliderDim = new Dimension(200,40);
  
  private JLabel title;

  SliderWTex (String name, int max, int min, int value) {
    text = new JTextField();
    text.addActionListener(new MyActionListener());
    text.setPreferredSize(textDim);
    slider = new JSlider();
    slider.addChangeListener(new SliderListener());
    slider.setMaximum(max);
    slider.setValue(value);
    slider.setMinimum(min);
    slider.setMajorTickSpacing(20);
    slider.setMinorTickSpacing(1);
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);
    slider.setPreferredSize(sliderDim);
    this.setLayout(new BorderLayout());
    this.add(slider, BorderLayout.CENTER);
    this.add(text, BorderLayout.EAST);
    title = new JLabel(name);
    title.setPreferredSize(titleDim);
    this.add(title, BorderLayout.WEST);
  }
  
  public double getValue (){
    return slider.getValue();
  }

  protected class SliderListener implements ChangeListener {
    @Override
    public void stateChanged(ChangeEvent e) {
      if (e.getSource() == slider){
        text.setText("" + slider.getValue());
      }

    }

  }
  
  protected class MyActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == text){
        slider.setValue(Integer.parseInt(text.getText()));
      }
   
    }

  }

}
