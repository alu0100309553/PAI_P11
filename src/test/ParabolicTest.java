/**
 * Author: Rubén Labrador Páez.
 * Email: alu0100309553@ull.edu.es
 * Tit: Grado Ingeniería Informática - Universidad de La Laguna
 * Course: 3 - Computación
 * Subject: Programación de aplicaciones interactivas.
 * Practice: 11
 * Class/Program: Parabolic
 * File: ParabolicTest.java
 * Description: This is a program to simulate parabolic movement.
 * @author Rubén Labrador Páez
 * @version 1.0.0 02/05/2016
 **/

package test;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gui.*;

public class ParabolicTest {
  private FrameFixture frame;
  private GUI applet;
  
  @Before
  public void initialize() {
    applet = new GUI();
    JFrame window = new JFrame();
    window.setSize(800, 600);
    window.add(applet);
    applet.init();
    applet.start();
    window.setVisible(true);
    frame = new FrameFixture(window);
  }

  //Test for lanzar button
  @Test
  public void testLanzar() {
    frame.button("lanzar").click();
    assertEquals(applet.getTimer().isRunning(),true);
  }
  
  //Test for pausa button
  @Test
  public void testPausa() {
    frame.button("pausa").click();
    assertEquals(applet.getTimer().isRunning(),false);
  }
  
  //Test for borrar button
  @Test
  public void testBorrar() {
    frame.button("borrar").click();
    assertEquals(applet.getShootCounter(),0);
  }
  
  //Test for speed slider
  @Test
  public void testVel() {
    frame.slider("slider_Velocidad").slideTo(55);
    int aux = (int)applet.getControl().getVel();
    assertEquals(aux,55);

  }
  
  //Test for degree slider
  @Test
  public void testAng() {
    frame.slider("slider_Ángulo").slideTo(49);
    int aux = (int)applet.getControl().getAngulo();
    assertEquals(aux,49);

  }
  
  //Test for height slider
  @Test
  public void testAlt() {
    frame.slider("slider_Altura").slideTo(20);
    int aux = (int)applet.getControl().getAltura();
    assertEquals(aux,20);

  }
 
  @After
  public void clear() {
    frame.cleanUp();
  }
}