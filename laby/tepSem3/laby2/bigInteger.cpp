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
    return value;
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
}
