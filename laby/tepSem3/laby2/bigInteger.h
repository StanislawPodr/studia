#include <cstddef>
#include <string>

class BigInteger
{
    std::size_t numberOfDigits;
    int *digits;
    unsigned int numOfDigits(int value);
    bool isNegative;
    static int *getDigitsAsTable(unsigned int value, unsigned int numberOfDigits);
    void initWithValue(int value);
    void copyObject(BigInteger &other);

public:
    BigInteger();
    ~BigInteger();
    BigInteger(int value);
    BigInteger(BigInteger &other);
    void operator=(const int value);
    void operator=(BigInteger &other);
    std::string toString();
};