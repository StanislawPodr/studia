#define LED_RED 6
#define LED_GREEN 5
#define LED_BLUE 3

void setup()
{
    pinMode(LED_RED, OUTPUT);
    digitalWrite(LED_RED, LOW);
    pinMode(LED_GREEN, OUTPUT);
    digitalWrite(LED_GREEN, LOW);
    pinMode(LED_BLUE, OUTPUT);
    digitalWrite(LED_BLUE, LOW);
    Serial.begin(9600);
    while (!Serial)
    { /* just wait */
    }
}

void loop()
{
    if (Serial.available() > 0)
    {
        uint8_t redPow = constrain(Serial.parseInt(), 0, 255);
        uint8_t greenPow = constrain(Serial.parseInt(), 0, 255);
        uint8_t bluePow = constrain(Serial.parseInt(), 0, 255);

        analogWrite(LED_RED, redPow);
        analogWrite(LED_GREEN, greenPow);
        analogWrite(LED_BLUE, bluePow);
    }
}
