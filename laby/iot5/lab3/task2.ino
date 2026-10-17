#define LED_RED 6
#define LED_GREEN 5
#define LED_BLUE 3

#define BLINK_CHANGE_PERIOD_IN_MS_RED 900UL
#define BLINK_CHANGE_PERIOD_IN_MS_GREEN 1000UL
#define BLINK_CHANGE_PERIOD_IN_MS_BLUE 1100UL

static int ledStateRed = LOW;
static int ledStateGreen = LOW;
static int ledStateBlue = LOW;
static unsigned long lastBlinkChangeMsRed = 0UL;
static unsigned long lastBlinkChangeMsGreen = 0UL;
static unsigned long lastBlinkChangeMsBlue = 0UL;

void setup() {
  pinMode(LED_RED, OUTPUT);
  digitalWrite(LED_RED, LOW);

  pinMode(LED_GREEN, OUTPUT);
  digitalWrite(LED_GREEN, LOW);

  pinMode(LED_BLUE, OUTPUT);
  digitalWrite(LED_BLUE, LOW);
}

void blinkLed(int *ledState_p, unsigned long *lastBlinkChangeMs_p,
              unsigned long blinkChangePeriodInMs, int led) {
  int ledState = *ledState_p;
  unsigned long lastBlinkChangeMs = *lastBlinkChangeMs_p;

  if (millis() - lastBlinkChangeMs >= blinkChangePeriodInMs) {
    if (ledState == HIGH) {
      ledState = LOW;
    } else {
      ledState = HIGH;
    }

    digitalWrite(led, ledState);
    lastBlinkChangeMs += blinkChangePeriodInMs;
  }

  *lastBlinkChangeMs_p = lastBlinkChangeMs;
  *ledState_p = ledState;
}

void loop() {
  blinkLed(&ledStateRed, &lastBlinkChangeMsRed,
           BLINK_CHANGE_PERIOD_IN_MS_RED, LED_RED);
  blinkLed(&ledStateGreen, &lastBlinkChangeMsGreen,
           BLINK_CHANGE_PERIOD_IN_MS_GREEN, LED_GREEN);
  blinkLed(&ledStateBlue, &lastBlinkChangeMsBlue,
           BLINK_CHANGE_PERIOD_IN_MS_BLUE, LED_BLUE);
}
