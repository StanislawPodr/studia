#include "bigInteger.h"
#include <iostream>
#include <cassert>

int main()
{
    BigInteger integer1,
        integer2(-10),
        integer3(integer2);
    assert(integer3.toString() == "-10");
    integer1 = integer2;
    integer1 = -10000;
    integer2 = 9990;
    BigInteger integer4 = integer1 + integer2;
    assert(integer4.toString() == "-10");
    assert(((integer1 = 100000) - (integer2 = 99999)).toString() == "+1");
    assert(((integer1 = 99999) - (integer2 = 100000)).toString() == "-1");
    assert((-(integer1 = 99999) - (integer2 = 100000)).toString() == "-199999");
    assert(((integer1 = 99999) + (integer2 = 100000)).toString() == "+199999");
    assert((-(integer1 = 99999) + (integer2 = 100000)).toString() == "+1");
    assert((-(integer1 = 99999) + (integer2 = -100000)).toString() == "-199999");
    assert(((integer1 = 99999) + (integer2 = -100000)).toString() == "-1");
    assert(((integer1 = 99999) - (integer2 = 0)).toString() == "+99999");
    assert(((integer1 = 0) - (integer2 = 99999)).toString() == "-99999");
    assert(((integer1 = 0) + (integer2 = 0)).toString() == "+0");
    assert(((integer1 = 10) - (integer2 = 0)).toString() == "+10");
    assert(((integer1 = 10) - (integer2 = 10)).toString() == "+0");
    assert(((integer1 = 0) - (integer2 = 0)).toString() == "+0");
    assert(((integer1 = 5) * (integer2 = 10)).toString() == "+50");
    assert(((integer1 = 500) * (integer2 = 10)).toString() == "+5000");
    assert(((integer1 = 0) * (integer2 = -10)).toString() == "+0");
    assert(((integer1 = -10) * (integer2 = 0)).toString() == "+0");
    assert(((integer1 = 0) * (integer2 = 10)).toString() == "+0");
    assert(((integer1 = -567) * (integer2 = -567)).toString() == "+321489");
    assert((((integer1 = -2137) * (integer2 = 666))*(integer4 = 69)).toString() == "-98203698");
    assert(((integer1 = -567) / (integer2 = -567)).toString() == "+1");
    assert(((integer1 = -2137) / (integer2 = 666)).toString() == "-3");
    assert(((integer1 = 2137) / (integer2 = -6)).toString() == "-356");
    assert(((integer1 = 2137) / (integer2 = 6)).toString() == "+356");
    assert(((integer1 = 0) / (integer2 = 666)).toString() == "+0");
    assert(((integer1 = 0) / (integer2 = -666)).toString() == "+0");
    assert(((integer1 = -2137) / (integer2 = 0)).toString() == "+0");
    assert(((integer1 = 333) / (integer2 = 3)).toString() == "+111");
    return 0;
}