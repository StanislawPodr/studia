#include <result.h>

template <typename T, typename E>
Result<T, E>::Result(const T &value)
{
    this->value = &value;
}

template <typename T, typename E>
Result<T, E>::Result(E *error)
{
}