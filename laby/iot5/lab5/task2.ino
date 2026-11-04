#include <LiquidCrystal_I2C.h>
#include <util/atomic.h>

// Stałe LED
#define LED_RED 6
#define LED_GREEN 5
#define LED_BLUE 3

// Stałe ekranu
#define SCREEN_WIDTH 16

// Stałe enkodera
#define ENCODER1 A2
#define ENCODER2 A3
#define MAX_ENCODER_VALUE 255
#define ENCODER_STEP 15
#define DEBOUNCING_PERIOD_ENCODER 100

// Stałe guzików
#define RED_BUTTON 2
#define GREEN_BUTTON 4
#define DEBOUNCING_PERIOD_BUTTONS 10UL

// Stałe menu
#define NO_MENU_OPTIONS 3
typedef enum MenuOptions {
  MENU_OPTION_RED,
  MENU_OPTION_GREEN,
  MENU_OPTION_BLUE
} MenuOptions;

// Zmienne dla guzików
int previous_reading_green = HIGH;
int previous_reading_red = HIGH;
int debounced_button_state_green = HIGH;
int debounced_button_state_red = HIGH;
unsigned long last_change_time_green = 0UL;
unsigned long last_change_time_red = 0UL;

// Zmienne enkodera
volatile int encoder1 = HIGH;
volatile int encoder2 = HIGH;
volatile unsigned long encoderTimestamp = 0UL;
int encoderValue = 0;
int lastEn1 = LOW;
unsigned long lastChangeTimestamp = 0UL;

// Obecna opcja w menu
MenuOptions currentMenuOption = MENU_OPTION_RED;

// Zmienna ekranu
LiquidCrystal_I2C lcd(0x27, 16, 2);

void handleButtons() {
  bool is_green_pressed =
      isButtonPressed(&previous_reading_green, &debounced_button_state_green,
                      &last_change_time_green, GREEN_BUTTON);
  bool is_red_pressed =
      isButtonPressed(&previous_reading_red, &debounced_button_state_red,
                      &last_change_time_red, RED_BUTTON);

  if (is_red_pressed || is_green_pressed) {
    uint8_t colorPin = 0;
    switch (currentMenuOption) {
    case MENU_OPTION_RED:
      colorPin = LED_RED;
      break;
    case MENU_OPTION_GREEN:
      colorPin = LED_GREEN;
      break;
    case MENU_OPTION_BLUE:
      colorPin = LED_BLUE;
      break;
    }
    digitalWrite(colorPin, is_red_pressed ? HIGH : LOW);
  }
}

bool isButtonPressed(int *previous_reading_ptr, int *debounced_button_state_ptr,
                     unsigned long *last_change_time_ptr, int button) {

  int previous_reading = *previous_reading_ptr;
  int debounced_button_state = *debounced_button_state_ptr;
  unsigned long last_change_time = *last_change_time_ptr;

  bool isPressed = false;

  int current_reading = digitalRead(button);

  if (previous_reading != current_reading) {
    last_change_time = millis();
  }

  if (millis() - last_change_time > DEBOUNCING_PERIOD_BUTTONS) {
    if (current_reading != debounced_button_state) {
      if (debounced_button_state == HIGH && current_reading == LOW) {
        isPressed = true;
      }
      debounced_button_state = current_reading;
    }
  }

  previous_reading = current_reading;

  *previous_reading_ptr = previous_reading;
  *debounced_button_state_ptr = debounced_button_state;
  *last_change_time_ptr = last_change_time;

  return isPressed;
}

// Obsługa enkodera
ISR(PCINT1_vect) {
  encoder1 = digitalRead(ENCODER1);
  encoder2 = digitalRead(ENCODER2);
  encoderTimestamp = millis();
}

void handleEncoder() {

  int en1;
  int en2;
  unsigned long timestamp;

  ATOMIC_BLOCK(ATOMIC_RESTORESTATE) {
    en1 = encoder1;
    en2 = encoder2;
    timestamp = encoderTimestamp;
  }

  if (en1 == LOW &&
      timestamp > lastChangeTimestamp + DEBOUNCING_PERIOD_ENCODER) {
    if (en2 == HIGH) {
      if (encoderValue < MAX_ENCODER_VALUE)
        encoderValue += ENCODER_STEP;
    } else {
      if (encoderValue > 0)
        encoderValue -= ENCODER_STEP;
    }
    lastChangeTimestamp = timestamp;

    handleEncoderChange(encoderValue);
  }
  lastEn1 = en1;
}

void handleEncoderChange(int encoderValue) {
  currentMenuOption = encoderValue * NO_MENU_OPTIONS / (MAX_ENCODER_VALUE + 1);
  printCurrentOptions(currentMenuOption);
}

void printCurrentOptions(MenuOptions menuOption) {
  // Napisanie "ON          OFF" w drugim wierszu.
  char fstLine[SCREEN_WIDTH + 1] = {0};
  uint8_t spaceCnt = SCREEN_WIDTH - strlen("ON") - strlen("OFF");
  snprintf(fstLine, SCREEN_WIDTH + 1, "ON%*sOFF", spaceCnt, "");

  lcd.setCursor(0, 1);
  lcd.print(fstLine);

  // Napisanie nazwy koloru w pierwszym wierszu.
  char *colorName = NULL;
  switch (menuOption) {
  case MENU_OPTION_RED:
    colorName = "RED";
    break;
  case MENU_OPTION_GREEN:
    colorName = "GREEN";
    break;
  case MENU_OPTION_BLUE:
    colorName = "BLUE";
    break;
  }
  char secLine[SCREEN_WIDTH + 1] = {0};
  snprintf(secLine, SCREEN_WIDTH + 1, "%*s", SCREEN_WIDTH, colorName);

  lcd.setCursor(0, 0);
  lcd.print(secLine);
}

void initButtons() {
  pinMode(RED_BUTTON, INPUT_PULLUP);
  pinMode(GREEN_BUTTON, INPUT_PULLUP);
}

void initLeds() {
  pinMode(LED_RED, OUTPUT);
  pinMode(LED_GREEN, OUTPUT);
  pinMode(LED_BLUE, OUTPUT);
}

void initScreen() {
  lcd.init();
  lcd.backlight();
}

void setup() {
  initButtons();
  initLeds();
  initScreen();

  // inicjalizacja ekranu
  printCurrentOptions(currentMenuOption);
  lastChangeTimestamp = millis();

  // Włączenie obsługi przerwań enkodera
  pinMode(ENCODER1, INPUT_PULLUP);
  pinMode(ENCODER2, INPUT_PULLUP);
  PCICR |= (1 << PCIE1);
  PCMSK1 |= (1 << PCINT10);
}

void loop() {
  handleEncoder();
  handleButtons();
}
