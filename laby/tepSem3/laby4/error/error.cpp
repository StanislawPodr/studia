#include <error.h>
#include <string>

Error::Error(const std::string &errorMessage)
{
    this->description = errorMessage;
}

