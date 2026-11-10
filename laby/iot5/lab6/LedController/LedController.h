#ifndef LEDCONTROLLER_H_
#define LEDCONTROLLER_H_

#include "Arduino.h"

// Stałe LED
#define DEFAULT_LED_RED_ 6
#define DEFAULT_LED_GREEN_ 5
#define DEFAULT_LED_BLUE_ 3

enum class LedColors_e : byte {
  RED,
  GREEN,
  BLUE,
  YELLOW,
  CYAN,
  MAGENTA,
  BLACK,
  WHITE
};

class LedController {
public:
  LedController(byte ledRed = DEFAULT_LED_RED_,
                byte ledGreen = DEFAULT_LED_GREEN_,
                byte ledBlue = DEFAULT_LED_BLUE_);
  void updateColors(byte r = 0, byte g = 0, byte b = 0);
  void updateColors(LedColors_e color);
  void begin();

private:
  byte ledRed;
  byte ledGreen;
  byte ledBlue;

  byte redPow;
  byte greenPow;
  byte bluePow;
};

#endif
