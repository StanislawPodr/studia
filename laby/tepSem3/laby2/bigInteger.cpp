#include "bigInteger.h"
#include <cstring>
#define NUMERIC_SYSTEM 10

unsigned int BigInteger::numOfDigits(int value)
{
    int numOfDigits = 0;
    do
    {
        numOfDigits++;
        value /= NUMERIC_SYSTEM;
    } while (value != 0);
    return numOfDigits;
}

BigInteger::BigInteger()
{
    digits = new int[1];
    numberOfDigits = 1;
    *digits = 0;
    isNegative = false;
}

BigInteger::BigInteger(int value)
{
    initWithValue(value);
}

BigInteger::BigInteger(BigInteger &other)
{
    copyObject(other);
}

BigInteger::~BigInteger()
{
    delete[] digits;
}

int *BigInteger::getDigitsAsTable(unsigned int value, unsigned int numberOfDigits)
{
    int *digits = new int[numberOfDigits];

    int currentDigitIndex = numberOfDigits - 1;
    while (currentDigitIndex >= 0)
    {
        digits[currentDigitIndex--] = value % NUMERIC_SYSTEM;
        value /= NUMERIC_SYSTEM;
    }

    return digits;
}

void BigInteger::initWithValue(int value)
{
    numberOfDigits = (std::size_t)numOfDigits(value);

    isNegative = value < 0;
    if (isNegative)
        value = -value;

    digits = getDigitsAsTable(value, numberOfDigits);
}

void BigInteger::operator=(int value)
{
    delete[] digits;
    initWithValue(value);
}

void BigInteger::operator=(BigInteger &other)
{
    delete[] this->digits;
    copyObject(other);
}

void BigInteger::copyObject(BigInteger &other)
{
    this->digits = new int[other.numberOfDigits];
    this->isNegative = other.isNegative;
    this->numberOfDigits = other.numberOfDigits;

    memcpy(this->digits, other.digits, sizeof(int) * other.numberOfDigits);
}

std::string BigInteger::toString()
{
    std::string result;
    result.reserve(this->numberOfDigits + 1);

    result += this->isNegative ? "-" : "+";
    for (int i = 0; i < this->numberOfDigits; i++)
        result += std::to_string(this->digits[i]);

    return result;
}

BigInteger::BigInteger(int *digits, bool isNegative, std::size_t numberOfDigits)
{
    this->digits = digits;
    this->isNegative = isNegative;
    this->numberOfDigits = numberOfDigits;
}

BigInteger BigInteger::operator+(BigInteger &other)
{
    BigInteger *max, *min;
    if (this->numberOfDigits > other.numberOfDigits)
    {
        max = this;
        min = &other;
    }
    else
    {
        min = this;
        max = &other;
    }
    int maxDigits = max->numberOfDigits;
    int minDigits = min->numberOfDigits;

    int *sum = new int[maxDigits + 1];

    int accu = 0;
    for (int i = maxDigits - 1; i >= maxDigits - minDigits; i--)
    {
        int adder = max->digits[i] + min->digits[i - maxDigits + minDigits] + accu;
        sum[i + 1] = adder % NUMERIC_SYSTEM;
        accu = adder / NUMERIC_SYSTEM; // dodawanie od prawej
    }

    int index = maxDigits - minDigits;
    while (--index >= 0)
    {
        sum[index + 1] = (max->digits[index] + accu) % NUMERIC_SYSTEM;
        accu = (max->digits[index] + accu) / NUMERIC_SYSTEM;
    }
    sum[0] = accu;

    int sizeOfSum = accu ? maxDigits + 1 : maxDigits;
    if (accu)
        return BigInteger(sum, isNegative, sizeOfSum);

    memmove(sum, sum + 1, sizeOfSum * sizeof(int));

    bool isNegative = false; // to be implemented
    return BigInteger(sum, isNegative, sizeOfSum);
}

void BigInteger::borrow(int *current)
{
    int *position = current;
    while (*--position == 0)
        ;
    (*position)--;

    while (++position < current)
        *position += NUMERIC_SYSTEM - 1;

    *position += NUMERIC_SYSTEM;
}

BigInteger BigInteger::operator-(BigInteger &other)
{
    BigInteger *max, *min;
    if (this->numberOfDigits > other.numberOfDigits ||
        (this->numberOfDigits == other.numberOfDigits && memcmp(this->digits, other.digits, this->numberOfDigits * sizeof(int)) > 0))
    // tylko pierwszy bajt jest zapełniony (zadziała i w little i big-endian)
    {
        max = this;
        min = &other;
    }
    else
    {
        min = this;
        max = &other;
    }
    int maxDigits = max->numberOfDigits;
    int minDigits = min->numberOfDigits;

    int *diff = new int[maxDigits];
    memcpy(diff, max->digits, maxDigits * sizeof(int));

    for (int i = maxDigits - 1; i >= maxDigits - minDigits; i--)
    {
        int difference = diff[i] - min->digits[i - maxDigits + minDigits];
        if (difference < 0)
        {
            borrow(diff + i);
            difference += NUMERIC_SYSTEM;
        }

        diff[i] = difference;
    }

    int *diffPtr = diff;
    std::size_t zerosCounter = 0;
    while(diffPtr < diff + maxDigits && *diffPtr++ == 0)
        zerosCounter++;

    std::size_t diffNumOfDigits = zerosCounter == maxDigits ? 1 : maxDigits - zerosCounter;
    memmove(diff, diff + zerosCounter, diffNumOfDigits);
    return BigInteger(diff, false, diffNumOfDigits);
}
