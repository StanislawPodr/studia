#include <LiquidCrystal_I2C.h>

#define POTENTIOMETER A0
#define OUTPUT_BYTE_SIZE_VOLT 6 // need 5 bytes for 0.00V - 5.00V and 1 '\0'
#define OUTPUT_BYTE_SIZE_ADC 5  // need 4 bytes for 0000 - 1023 and 1 '\0'
#define MAX_INPUT_LEVEL 1023.0
#define MAX_VOLTAGE 5.0

LiquidCrystal_I2C lcd(0x27, 16, 2);
int prevAdcValue = 0;

void setup() {
  lcd.init();
  lcd.backlight();
  lcd.clear();
}

void loop() {
  int adcValue = analogRead(POTENTIOMETER);
  if (prevAdcValue == adcValue) {
    return;
  }

  float voltValue = adcValue * MAX_VOLTAGE / MAX_INPUT_LEVEL;
  char voltValueStr[OUTPUT_BYTE_SIZE_VOLT] = {0};
  snprintf(&voltValueStr[0], OUTPUT_BYTE_SIZE_VOLT, "%.2fV", voltValue);
  lcd.setCursor(0, 0);
  lcd.print(voltValueStr);
  char adcValueStr[OUTPUT_BYTE_SIZE_ADC] = {0};
  snprintf(&adcValueStr[0], OUTPUT_BYTE_SIZE_ADC, "%.4d", adcValue);
  lcd.setCursor(0, 1);
  lcd.print(adcValueStr);
  prevAdcValue = adcValue;
}
