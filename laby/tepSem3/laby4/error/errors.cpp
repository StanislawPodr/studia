#include <errors.h>
#include <string>

Error::Error(const std::string &errorMessage)
{
    this->description = errorMessage;
}

std::string Error::getDescription()
{
    return this->description;
}
