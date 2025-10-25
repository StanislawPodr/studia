#include <cstddef>
#include <string>

class BigInteger
{
    std::size_t numberOfDigits;
    int *digits;
    unsigned int numOfDigits(int value);
    bool isNegative;
    static int *getDigitsAsTable(unsigned int value, unsigned int numberOfDigits);
    BigInteger &initWithValue(int value);
    BigInteger &copyObject(BigInteger &other);
    static void borrow(int *ptr);
    static int* substractFromBigger(std::size_t &resSize, BigInteger *max, BigInteger *min);
    static int* addSmallToBig(std::size_t &resSize, BigInteger *max, BigInteger *min);
    static BigInteger substractPositive(BigInteger &a, BigInteger &b);
    static BigInteger addPositive(BigInteger &a, BigInteger &b, bool isResNegative);
    static BigInteger orderMultPositive(BigInteger &max, BigInteger &min, bool isResNegative);
    static BigInteger dividePositive(BigInteger dividend, BigInteger &divisor, bool isResNegative);
    BigInteger(int *digits, bool isNegative, std::size_t numberOfDigits);
    bool isZero();

public:
    BigInteger();
    ~BigInteger();
    BigInteger(int value);
    BigInteger(BigInteger &other);
    BigInteger &operator=(int value);
    BigInteger &operator=(BigInteger &other);
    static int bigIntegerCmp(BigInteger &a, BigInteger &b);
    BigInteger operator+(BigInteger &other);
    BigInteger operator-(BigInteger &other);
    BigInteger operator-();
    BigInteger operator*(BigInteger &other);
    BigInteger operator/(BigInteger &other);
    std::string toString();
};