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
#define ENCODER_STEP_POWER 15
#define ENCODER_STEP_MENU 86
#define DEBOUNCING_PERIOD_ENCODER 100

// Stałe guzików
#define RED_BUTTON 2
#define GREEN_BUTTON 4
#define DEBOUNCING_PERIOD_BUTTONS 10UL

// Stałe menu
#define NO_MENU_OPTIONS 3
typedef enum MenuOptions_e {
  MENU_OPTION_RED,
  MENU_OPTION_GREEN,
  MENU_OPTION_BLUE,
  MENU_OPTION_CHANGE_RED,
  MENU_OPTION_CHANGE_GREEN,
  MENU_OPTION_CHANGE_BLUE
} MenuOptions_e;

// Zmienne dla guzików
typedef struct ButtonState_s {
  int previousReading;
  int debouncedButtonState;
  int buttonPin;
  unsigned long lastChangeTime;
} ButtonState_s;

ButtonState_s redButtonState = {.previousReading = HIGH,
                                .debouncedButtonState = HIGH,
                                .buttonPin = RED_BUTTON,
                                .lastChangeTime = 0UL};

ButtonState_s greenButtonState = {.previousReading = HIGH,
                                  .debouncedButtonState = HIGH,
                                  .buttonPin = GREEN_BUTTON,
                                  .lastChangeTime = 0UL};

// Zmienne enkodera
struct EncoderValues_s {
  int menu;
  int redDiodePow;
  int greenDiodePow;
  int blueDiodePow;
} encoderValues = {0};

volatile int encoder1 = HIGH;
volatile int encoder2 = HIGH;
volatile unsigned long encoderTimestamp = 0UL;
int encoderValue = 0;
int lastEn1 = LOW;
int encoderStep = ENCODER_STEP_MENU;
unsigned long lastChangeTimestamp = 0UL;

// Obecna opcja w menu
MenuOptions_e currentMenuOption = MENU_OPTION_RED;

// Zmienna ekranu
LiquidCrystal_I2C lcd(0x27, 16, 2);

bool isMenuChange(MenuOptions_e option) {
  return option == MENU_OPTION_CHANGE_RED ||
         option == MENU_OPTION_CHANGE_GREEN ||
         option == MENU_OPTION_CHANGE_BLUE;
}

bool isMenuSelect(MenuOptions_e option) {
  return option == MENU_OPTION_RED || option == MENU_OPTION_GREEN ||
         option == MENU_OPTION_BLUE;
}

void handleButtons(void) {

  if (isMenuSelect(currentMenuOption) && isButtonPressed(&greenButtonState)) {
    encoderValues.menu = encoderValue;
    switch (currentMenuOption) {
    case MENU_OPTION_RED:
      currentMenuOption = MENU_OPTION_CHANGE_RED;
      encoderValue = encoderValues.redDiodePow;
      break;
    case MENU_OPTION_GREEN:
      currentMenuOption = MENU_OPTION_CHANGE_GREEN;
      encoderValue = encoderValues.greenDiodePow;
      break;
    case MENU_OPTION_BLUE:
      currentMenuOption = MENU_OPTION_CHANGE_BLUE;
      encoderValue = encoderValues.blueDiodePow;
      break;
    }
    encoderStep = ENCODER_STEP_POWER;
    printCurrentOptions();
  }

  if (isMenuChange(currentMenuOption) && isButtonPressed(&redButtonState)) {
    switch (currentMenuOption) {
    case MENU_OPTION_CHANGE_RED:
      currentMenuOption = MENU_OPTION_RED;
      encoderValues.redDiodePow = encoderValue;
      break;
    case MENU_OPTION_CHANGE_GREEN:
      currentMenuOption = MENU_OPTION_GREEN;
      encoderValues.greenDiodePow = encoderValue;
      break;
    case MENU_OPTION_CHANGE_BLUE:
      currentMenuOption = MENU_OPTION_BLUE;
      encoderValues.blueDiodePow = encoderValue;
      break;
    }
    encoderValue = encoderValues.menu;
    encoderStep = ENCODER_STEP_MENU;
    printCurrentOptions();
  }
}

bool isButtonPressed(ButtonState_s *buttonState) {
  bool isPressed = false;

  int currentReading = digitalRead(buttonState->buttonPin);

  if (buttonState->previousReading != currentReading) {
    buttonState->lastChangeTime = millis();
  }

  if (millis() - buttonState->lastChangeTime > DEBOUNCING_PERIOD_BUTTONS) {
    if (currentReading != buttonState->debouncedButtonState) {
      if (buttonState->debouncedButtonState == HIGH && currentReading == LOW) {
        isPressed = true;
      }
      buttonState->debouncedButtonState = currentReading;
    }
  }

  buttonState->previousReading = currentReading;

  return isPressed;
}

// Obsługa enkodera
ISR(PCINT1_vect) {
  encoder1 = digitalRead(ENCODER1);
  encoder2 = digitalRead(ENCODER2);
  encoderTimestamp = millis();
}

void handleEncoder(void) {

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
      if (encoderValue < MAX_ENCODER_VALUE) {
        encoderValue += encoderStep;
        encoderValue = min(encoderValue, MAX_ENCODER_VALUE);
      }
    } else {
      if (encoderValue > 0) {
        encoderValue -= encoderStep;
        encoderValue = max(encoderValue, 0);
      }
    }
    lastChangeTimestamp = timestamp;

    handleEncoderChange(encoderValue);
  }
  lastEn1 = en1;
}

void handleEncoderChange(int encoderValue) {
  switch (currentMenuOption) {
  case MENU_OPTION_RED:
  case MENU_OPTION_GREEN:
  case MENU_OPTION_BLUE:
    // Obliczenie aktualnego menu
    currentMenuOption = (MenuOptions_e)(encoderValue * NO_MENU_OPTIONS /
                                        (MAX_ENCODER_VALUE + 1));
    break;
  case MENU_OPTION_CHANGE_RED:
    analogWrite(LED_RED, encoderValue);
    break;
  case MENU_OPTION_CHANGE_GREEN:
    analogWrite(LED_GREEN, encoderValue);
    break;
  case MENU_OPTION_CHANGE_BLUE:
    analogWrite(LED_BLUE, encoderValue);
    break;
  }
  printCurrentOptions();
}

void printCurrentOptions(void) {

  if (isMenuSelect(currentMenuOption)) {
    // Napisanie "          EDIT>>" w pierwszym wierszu.
    char fstLine[SCREEN_WIDTH + 1] = {0};
    snprintf(fstLine, SCREEN_WIDTH + 1, "%*s", SCREEN_WIDTH, "EDIT>>");

    lcd.setCursor(0, 0);
    lcd.print(fstLine);

    // Napisanie nazwy koloru w drugim wierszu.
    const char *colorName = NULL;
    switch (currentMenuOption) {
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
    snprintf(secLine, SCREEN_WIDTH + 1, "%*s", -SCREEN_WIDTH, colorName);

    lcd.setCursor(0, 1);
    lcd.print(secLine);

  } else if (isMenuChange(currentMenuOption)) {
    // Napisanie wartości enkodera w pierwszym wierszu
    char fstLine[SCREEN_WIDTH + 1] = {0};
    snprintf(fstLine, SCREEN_WIDTH + 1, "%*d", SCREEN_WIDTH, encoderValue);

    lcd.setCursor(0, 0);
    lcd.print(fstLine);
    // Napisanie "<<RET            " w drugim wierszu
    char secLine[SCREEN_WIDTH + 1] = {0};
    snprintf(secLine, SCREEN_WIDTH + 1, "%*s", -SCREEN_WIDTH, "<<RET");

    lcd.setCursor(0, 1);
    lcd.print(secLine);
  }
}

void initButtons(void) {
  pinMode(RED_BUTTON, INPUT_PULLUP);
  pinMode(GREEN_BUTTON, INPUT_PULLUP);
}

void initLeds(void) {
  pinMode(LED_RED, OUTPUT);
  pinMode(LED_GREEN, OUTPUT);
  pinMode(LED_BLUE, OUTPUT);
}

void initScreen(void) {
  lcd.init();
  lcd.backlight();
}

void setup(void) {
  initButtons();
  initLeds();
  initScreen();

  // inicjalizacja ekranu
  printCurrentOptions();
  lastChangeTimestamp = millis();

  // Włączenie obsługi przerwań enkodera
  pinMode(ENCODER1, INPUT_PULLUP);
  pinMode(ENCODER2, INPUT_PULLUP);
  PCICR |= (1 << PCIE1);
  PCMSK1 |= (1 << PCINT10);
}

void loop(void) {
  handleEncoder();
  handleButtons();
}
