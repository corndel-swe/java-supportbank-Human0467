package com.corndel.supportbank.exercises;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class D2E3Test {
  @Test
  public void testLithiumFromJSONFile() {
    Element element;
    try {
      element = Element.fromJSONFile("lithium.json");
      assertEquals("Lithium", element.getName());
      assertEquals(3, element.getAtomicNumber());
      assertEquals("Li", element.getSymbol());
      assertEquals(6.94, element.getAtomicMass());
      assertEquals(2, element.getPeriod());
      assertEquals(1, element.getGroup());
    } catch (IOException e) {
      System.out.println("Test went wrong..");
      e.printStackTrace();
    }
  }
}
