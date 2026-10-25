#define RED_BUTTON 2
#define GREEN_BUTTON 4
#define DEBOUNCE_PERIOD 10UL
#define UART_SPEED_BPS 115200

int previous_reading_green = HIGH;
int previous_reading_red = HIGH;
bool previous_btn_pressed_green = false;
bool previous_btn_pressed_red = false;
unsigned long last_change_time_green = 0UL;
unsigned long last_change_time_red = 0UL;

void initButtons() {
  pinMode(RED_BUTTON, INPUT_PULLUP);
  pinMode(GREEN_BUTTON, INPUT_PULLUP);
}

bool isButtonPressed(int *previous_reading_ptr, bool *previous_btn_pressed_p,
                     unsigned long *last_change_time_ptr, int button) {

  int previous_reading = *previous_reading_ptr;
  bool previous_btn_pressed = *previous_btn_pressed_p;
  unsigned long last_change_time = *last_change_time_ptr;

  bool isPressed = previous_btn_pressed;

  int current_reading = digitalRead(button);

  if (previous_reading != current_reading) {
    last_change_time = millis();
  }

  if (millis() - last_change_time > DEBOUNCE_PERIOD) {
    isPressed = (current_reading == LOW);
    previous_btn_pressed = isPressed;
  }

  previous_reading = current_reading;

  *previous_reading_ptr = previous_reading;
  *previous_btn_pressed_p = previous_btn_pressed;
  *last_change_time_ptr = last_change_time;

  return isPressed;
}

void setup() {
  Serial.begin(UART_SPEED_BPS);
  while (!Serial) { /* just wait */
  }
  initButtons();
}

int i = 0;
void loop() {
  bool is_green_pressed =
      isButtonPressed(&previous_reading_green, &previous_btn_pressed_green,
                      &last_change_time_green, GREEN_BUTTON);
  bool is_red_pressed =
      isButtonPressed(&previous_reading_red, &previous_btn_pressed_red,
                      &last_change_time_red, RED_BUTTON);

  Serial.println("green:" + String(is_green_pressed) +
                 " red:" + String(is_red_pressed));
}
