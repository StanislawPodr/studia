#include "LedController.h"

LedController::LedController(byte ledRed, byte ledGreen, byte ledBlue) {
  this->ledRed = ledRed;
  this->ledGreen = ledGreen;
  this->ledBlue = ledBlue;
  this->redPow = 0;
  this->greenPow = 0;
  this->bluePow = 0;
}

void LedController::updateColors(byte r, byte g, byte b) {
  // Zaaktualizuj kolory tylko wtedy gdy trzeba
  if (r != redPow) {
    analogWrite(ledRed, r);
    redPow = r;
  }

  if (g != greenPow) {
    analogWrite(ledGreen, g);
    greenPow = g;
  }

  if (b != bluePow) {
    analogWrite(ledBlue, b);
    bluePow = b;
  }
}

void LedController::updateColors(LedColors_e color) {
  switch (color) {
  case LedColors_e::RED:
    updateColors(255, 0, 0);
    break;
  case LedColors_e::GREEN:
    updateColors(0, 255, 0);
    break;
  case LedColors_e::BLUE:
    updateColors(0, 0, 255);
    break;
  case LedColors_e::YELLOW:
    updateColors(255, 255, 0);
    break;
  case LedColors_e::CYAN:
    updateColors(0, 255, 255);
    break;
  case LedColors_e::MAGENTA:
    updateColors(255, 0, 255);
    break;
  case LedColors_e::BLACK:
    updateColors(0, 0, 0);
    break;
  case LedColors_e::WHITE:
    updateColors(255, 255, 255);
    break;
  }
}

void LedController::begin() {
  pinMode(ledRed, OUTPUT);
  pinMode(ledGreen, OUTPUT);
  pinMode(ledBlue, OUTPUT);
  analogWrite(ledRed, 0);
  analogWrite(ledGreen, 0);
  analogWrite(ledBlue, 0);
  this->redPow = 0;
  this->greenPow = 0;
  this->bluePow = 0;
}
