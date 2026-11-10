#include <DallasTemperature.h>
#include <LiquidCrystal_I2C.h>
#include <OneWire.h>
#include <Wire.h>
#include <float.h>

// Stałe LED
#define LED_RED 6
#define LED_GREEN 5
#define LED_BLUE 3

// Zakładamy temperaturę komfortu na 20 stopni celcjusza
#define IS_COLD(temp) ((temp) < 20.0)
#define IS_HOT(temp) ((temp) >= 28.0)

typedef enum Temperature_e { COLD, WARM, HOT } Temperature_e;

LiquidCrystal_I2C lcd(0x27, 16, 2);

OneWire oneWire(A1);
DallasTemperature tempSensors(&oneWire);
Temperature_e currentTempComfort = COLD;
float maxTemp = -FLT_MAX;
float minTemp = FLT_MAX;

void printTemp(float tempIn, float tempOut, float minTemp, float maxTemp) {
  char buffer[40];
  sprintf(buffer, "IN%4s  MAX:%4s", String(tempIn, 1).c_str(),
          String(maxTemp, 1).c_str());
  lcd.setCursor(0, 0);
  lcd.print(buffer);
  sprintf(buffer, "OUT%4s MIN:%4s", String(tempOut, 1).c_str(),
          String(minTemp, 1).c_str());
  lcd.setCursor(0, 1);
  lcd.print(buffer);
}

void diodeTempHandler(Temperature_e currentTempComfort) {
  switch (currentTempComfort) {
  case COLD:
    digitalWrite(LED_GREEN, LOW);
    digitalWrite(LED_BLUE, HIGH);
    break;
  case WARM:
    digitalWrite(LED_BLUE, LOW);
    digitalWrite(LED_RED, LOW);
    digitalWrite(LED_GREEN, HIGH);
    break;
  case HOT:
    digitalWrite(LED_GREEN, LOW);
    digitalWrite(LED_RED, HIGH);
    break;
  }
}

Temperature_e getTempComfort(float temp) {
  if (IS_COLD(temp)) {
    return COLD;
  } else if (IS_HOT(temp)) {
    return HOT;
  } else {
    return WARM;
  }
}

void setup() {
  tempSensors.begin();

  lcd.init();
  lcd.backlight();
  lcd.clear();

  pinMode(LED_RED, OUTPUT);
  pinMode(LED_GREEN, OUTPUT);
  pinMode(LED_BLUE, OUTPUT);

  digitalWrite(LED_RED, LOW);
  digitalWrite(LED_GREEN, LOW);
  digitalWrite(LED_BLUE, LOW);

  tempSensors.requestTemperatures();
  float tempOut = tempSensors.getTempCByIndex(0);
  currentTempComfort = getTempComfort(tempOut);
  diodeTempHandler(currentTempComfort);
}

void loop() {
  tempSensors.requestTemperatures();
  float tempIn = tempSensors.getTempCByIndex(1);
  float tempOut = tempSensors.getTempCByIndex(0);

  minTemp = fminf(minTemp, tempOut);
  maxTemp = fmaxf(maxTemp, tempOut);

  Temperature_e tempComfort = getTempComfort(tempOut);
  if (currentTempComfort != tempComfort) {
    diodeTempHandler(tempComfort);
  }
  currentTempComfort = tempComfort;

  printTemp(tempIn, tempOut, minTemp, maxTemp);
}
