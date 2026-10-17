#include <stdbool.h>

#define LED_RED 6
#define LED_GREEN 5
#define LED_BLUE 3

#define RED_BUTTON 2
#define GREEN_BUTTON 4

int led[] = {LED_RED, LED_GREEN, LED_BLUE};
int previous_reading_green = HIGH;
int previous_reading_red = HIGH;
int debounced_button_state_green = HIGH;
int debounced_button_state_red = HIGH;
unsigned long last_change_time_green = 0UL;
unsigned long last_change_time_red = 0UL;

void initRGB() {
  pinMode(LED_BUILTIN, OUTPUT);
  digitalWrite(LED_BUILTIN, LOW);

  pinMode(LED_RED, OUTPUT);
  digitalWrite(LED_RED, HIGH);

  pinMode(LED_GREEN, OUTPUT);
  digitalWrite(LED_GREEN, LOW);

  pinMode(LED_BLUE, OUTPUT);
  digitalWrite(LED_BLUE, LOW);
}

void initButtons() {
  pinMode(RED_BUTTON, INPUT_PULLUP);
  pinMode(GREEN_BUTTON, INPUT_PULLUP);
}

#define DEBOUNCE_PERIOD 10UL

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

  if (millis() - last_change_time > DEBOUNCE_PERIOD) {
    if (current_reading != debounced_button_state) {
      if (debounced_button_state == LOW && current_reading == HIGH) {
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

void setup() {
  initRGB();
  initButtons();
}

int led_index = 0;
void loop() {
  bool is_green_pressed =
      isButtonPressed(&previous_reading_green, &debounced_button_state_green,
                      &last_change_time_green, GREEN_BUTTON);
  bool is_red_pressed =
      isButtonPressed(&previous_reading_red, &debounced_button_state_red,
                      &last_change_time_red, RED_BUTTON);

  if (is_green_pressed || is_red_pressed) {
    digitalWrite(led[led_index], LOW);
    led_index = (led_index + 1) % 3;
    digitalWrite(led[led_index], HIGH);
  }
}
