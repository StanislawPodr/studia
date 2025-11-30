#include <result.h>

template <typename T, typename E>
Result<T, E>::Result(const T &value)
{
    this->value = &value;
}

template <typename T, typename E>
Result<T, E>::Result(E *error)
{
    this->errors = std::vector<E>{error};
    this->value = nullptr;
}

template <typename T, typename E>
Result<T, E>::Result(std::vector<E *> &errors)
{
    this->errors = errors;
    this->value = nullptr;
}

template <typename T, typename E>
Result<T, E>::Result(const Result<T, E> &other)
{
    
}
