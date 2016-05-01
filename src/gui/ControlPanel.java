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

  ControlPanel() {
    this.setLayout(new BorderLayout());
    btPanel = new JPanel();
    slPanel = new JPanel();
    chPanel = new JPanel();
    btPanel.setLayout(new GridLayout(3, 1, 1, 1));
    slPanel.setLayout(new GridLayout(3, 1, 1, 1));
    chPanel.setLayout(new GridLayout(3, 1, 1, 1));
    lanzarBt = new JButton("Lanzar");
    pausaBt = new JButton("Pausa");
    borrarBt = new JButton("Borrar");
    btPanel.add(lanzarBt);
    btPanel.add(pausaBt);
    btPanel.add(borrarBt);
    lanzarBt.addActionListener(new BtListener());
    pausaBt.addActionListener(new BtListener());
    borrarBt.addActionListener(new BtListener());
    this.add(btPanel, BorderLayout.WEST);
    velSl = new SliderWTex("Velocidad", 100, 1, 50);
    angulSl = new SliderWTex("Ángulo", 90, 0, 45);
    alturaSl = new SliderWTex("Altura", 100, 0, 0);
    slPanel.add(velSl);
    slPanel.add(angulSl);
    slPanel.add(alturaSl);
    this.add(slPanel, BorderLayout.CENTER);
    rastroCB = new JCheckBox("Mostrar Rastro");
    chPanel.add(rastroCB);
    this.add(chPanel, BorderLayout.EAST);
  }

  public void addControlPanelListener(ControlPanelListener e) {
    listeners.add(e);
  }

  @Override
  public void ControlPanel(ControlPanelEventObject e) {

  }
  
  public double getVel (){
    return velSl.getValue();
  }
  
  public double getAngulo (){
    return angulSl.getValue();
  }
  
  public double getAltura () {
    return alturaSl.getValue();
  }

  protected class BtListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      for (ControlPanelListener listener : listeners) {
        ControlPanelEventObject evObject = new ControlPanelEventObject(e.getSource());
        listener.ControlPanel(evObject);
      }

    }

  }

}
