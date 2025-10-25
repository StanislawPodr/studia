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
    digits = new int[1]();
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

BigInteger &BigInteger::initWithValue(int value)
{
    this->numberOfDigits = (std::size_t)numOfDigits(value);

    this->isNegative = value < 0;
    if (isNegative)
        value = -value;

    this->digits = getDigitsAsTable(value, numberOfDigits);
    return *this;
}

BigInteger &BigInteger::operator=(int value)
{
    delete[] digits;
    return initWithValue(value);
}

BigInteger &BigInteger::operator=(BigInteger &other)
{
    delete[] this->digits;
    return copyObject(other);
}

int BigInteger::bigIntegerCmp(BigInteger &a, BigInteger &b)
{
    if (a.numberOfDigits > b.numberOfDigits)
        return 1;
    else if (a.numberOfDigits == b.numberOfDigits)
        return memcmp(a.digits, b.digits, a.numberOfDigits * sizeof(int)); // zadziała bo cyfry zajmują mniej niż 1B
    else
        return -1;
}

BigInteger &BigInteger::copyObject(BigInteger &other)
{
    this->digits = new int[other.numberOfDigits];
    this->isNegative = other.isNegative;
    this->numberOfDigits = other.numberOfDigits;

    memcpy(this->digits, other.digits, sizeof(int) * other.numberOfDigits);
    return *this;
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

bool BigInteger::isZero()
{
    if (isNegative)
        return false;

    for (int i = 0; i < this->numberOfDigits; i++)
        if (this->digits[i] != 0)
            return false;

    return true;
}

BigInteger BigInteger::operator+(BigInteger &other)
{
    if (this->isNegative || other.isNegative)
    {
        if (this->isNegative && other.isNegative)
        {
            return addPositive(*this, other, true);
        }
        else if (this->isNegative)
        {
            return substractPositive(other, *this);
        }
        else
        {
            return substractPositive(*this, other);
        }
    }
    return addPositive(*this, other, false);
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
    if (this->isNegative || other.isNegative)
    {
        if (this->isNegative && other.isNegative)
        {
            return substractPositive(other, *this);
        }
        else if (this->isNegative)
        {
            return addPositive(*this, other, true);
        }
        else
        {
            return addPositive(*this, other, false);
        }
    }
    return substractPositive(*this, other);
}

BigInteger BigInteger::operator-()
{
    BigInteger res = *this;
    res.isNegative = !res.isNegative;
    return res;
}

BigInteger BigInteger::operator*(BigInteger &other)
{
    bool isResNegative = this->isNegative ^ other.isNegative;
    if (this->isZero() || other.isZero())
    {
        return BigInteger();
    }

    int comp = bigIntegerCmp(*this, other);
    if (comp > 0)
    {
        return orderMultPositive(*this, other, isResNegative);
    }
    else
    {
        return orderMultPositive(other, *this, isResNegative);
    }
}

BigInteger BigInteger::operator/(BigInteger &other)
{
    if(this->isZero())
    {
        return BigInteger{};
    }
    bool isResNegative = this->isNegative ^ other.isNegative;
    return dividePositive(*this, other, isResNegative);
}

BigInteger BigInteger::substractPositive(BigInteger &a, BigInteger &b)
{
    BigInteger *max, *min;
    int cmp = bigIntegerCmp(a, b);
    bool isNegative = cmp < 0;
    if (cmp == 0)
    {
        return BigInteger();
    }
    else if (isNegative)
    {
        max = &b;
        min = &a;
    }
    else
    {
        max = &a;
        min = &b;
    }
    std::size_t size;
    int *res = substractFromBigger(size, max, min);
    return BigInteger(res, isNegative, size);
}

BigInteger BigInteger::addPositive(BigInteger &a, BigInteger &b, bool isResNegative)
{
    BigInteger *max, *min;
    int cmp = bigIntegerCmp(a, b);
    bool isSwaped = cmp < 0;
    if (isSwaped)
    {
        max = &b;
        min = &a;
    }
    else
    {
        max = &a;
        min = &b;
    }

    std::size_t size;
    int *res = addSmallToBig(size, max, min);
    return BigInteger(res, isResNegative, size);
}

BigInteger BigInteger::orderMultPositive(BigInteger &max, BigInteger &min, bool isResNegative)
{
    BigInteger result{max};
    for (BigInteger i{1}, inc{1}; bigIntegerCmp(i, min) < 0;)
    {
        std::size_t size;
        i.digits = addSmallToBig(size, &i, &inc);
        i.numberOfDigits = size;
        result.digits = addSmallToBig(size, &result, &max);
        result.numberOfDigits = size;
    }
    result.isNegative = isResNegative;
    return result;
}

BigInteger BigInteger::dividePositive(BigInteger dividend, BigInteger &divisor, bool isResNegative)
{
    if (divisor.isZero())
        return BigInteger();

    BigInteger result{}, iter{1};
    int comp;
    while ((comp = bigIntegerCmp(dividend, divisor)) > 0)
    {
        std::size_t size;
        dividend.digits = substractFromBigger(size, &dividend, &divisor);
        dividend.numberOfDigits = size;
        result.digits = addSmallToBig(size, &result, &iter);
        result.numberOfDigits = size;
    }
    if (comp == 0)
    {
        std::size_t size;
        result.digits = addSmallToBig(size, &result, &iter);
        result.numberOfDigits = size;
    }
    result.isNegative = isResNegative;
    return result;
}

int *BigInteger::substractFromBigger(std::size_t &resSize, BigInteger *max, BigInteger *min)
{

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
    while (diffPtr < diff + maxDigits && *diffPtr++ == 0)
        zerosCounter++;

    std::size_t diffNumOfDigits = zerosCounter == maxDigits ? 1 : maxDigits - zerosCounter;
    memmove(diff, diff + zerosCounter, diffNumOfDigits * sizeof(int));

    resSize = diffNumOfDigits;
    return diff;
}

int *BigInteger::addSmallToBig(std::size_t &resSize, BigInteger *max, BigInteger *min)
{
    int maxDigits = max->numberOfDigits;
    int minDigits = min->numberOfDigits;

    int *sum = new int[maxDigits + 1];

    int accu = 0;
    for (int i = maxDigits - 1; i >= maxDigits - minDigits; i--)
    {
        int adder = max->digits[i] + min->digits[i - maxDigits + minDigits] + accu;
        sum[i + 1] = adder % NUMERIC_SYSTEM;
        accu = adder / NUMERIC_SYSTEM;
    }

    int index = maxDigits - minDigits;
    while (--index >= 0)
    {
        sum[index + 1] = (max->digits[index] + accu) % NUMERIC_SYSTEM;
        accu = (max->digits[index] + accu) / NUMERIC_SYSTEM;
    }
    sum[0] = accu;

    int sizeOfSum = accu ? maxDigits + 1 : maxDigits;
    resSize = sizeOfSum;
    if (accu)
        return sum;

    memmove(sum, sum + 1, sizeOfSum * sizeof(int));

    return sum;
}
