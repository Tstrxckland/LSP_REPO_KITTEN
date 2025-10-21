package org.howard.edu.lsp.midterm_exam.question4;

public interface BatteryPowered {
  int getBatteryPercent();           // 0..100 inclusive
  void setBatteryPercent(int percent);
}
