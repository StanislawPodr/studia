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
    BigInteger &copyObject(const BigInteger &other);
    static void borrow(int *ptr);
    static int* substractFromBigger(std::size_t &resSize, const BigInteger *max, const BigInteger *min);
    static int* addSmallToBig(std::size_t &resSize, const BigInteger *max, const BigInteger *min);
    static BigInteger substractPositive(const BigInteger &a, const BigInteger &b);
    static BigInteger addPositive(const BigInteger &a, const BigInteger &b, bool isResNegative);
    static BigInteger orderMultPositive(const BigInteger &max, const BigInteger &min, bool isResNegative);
    static BigInteger dividePositive(BigInteger dividend, const BigInteger &divisor, bool isResNegative);
    BigInteger(int *digits, bool isNegative, std::size_t numberOfDigits);
    bool isZero() const;

public:
    BigInteger();
    ~BigInteger();
    BigInteger(int value);
    BigInteger(const BigInteger &other);
    BigInteger &operator=(int value);
    BigInteger &operator=(const BigInteger &other);
    static int bigIntegerCmp(const BigInteger &a, const BigInteger &b);
    BigInteger operator+(const BigInteger &other);
    BigInteger operator-(const BigInteger &other);
    BigInteger operator-();
    BigInteger operator*(const BigInteger &other);
    BigInteger operator/(const BigInteger &other);
    BigInteger &operator++();
    BigInteger operator++(int);
    BigInteger &operator--();
    BigInteger operator--(int);
    std::string toString();
};