#include "bigInteger.h"
#include <iostream>


int main()
{
    BigInteger integer1, 
    integer2(-10), 
    integer3(integer2);
    std::cout << integer1.toString() << std::endl << integer2.toString() << std::endl << integer3.toString() << std::endl;
    integer1 = integer2;
    std::cout << integer1.toString() << std::endl;
    integer1 = 50;
    std::cout << integer1.toString() << std::endl;
    return 0;
}