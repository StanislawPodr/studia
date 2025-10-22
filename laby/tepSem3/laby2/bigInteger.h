#include <cstddef>
class BigInteger
{
    std::size_t numberOfDigits;
    int *digits;

public:
    BigInteger();
    ~BigInteger();
    void operator=(const int value);
    void operator=(BigInteger &other);
};