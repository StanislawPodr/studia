#define POTENTIOMETER A0
#define MAX_INPUT_LEVEL 1023.0
#define MAX_VOLTAGE 5.0
#define UART_SPEED_BPS 115200

void setup() {
  Serial.begin(UART_SPEED_BPS);
  while (!Serial) { /* just wait */
  }
}

void loop() {
  int adcValue = analogRead(POTENTIOMETER);
  float voltValue = adcValue * MAX_VOLTAGE / MAX_INPUT_LEVEL;

  Serial.println("ADC:" + String(adcValue) +
                 " volt:" + String(voltValue));
}
