#include <error.h>
#include <result.h>
#include <iostream>
#include <tree.h>
#include <treeCli.h>
#include <errorToFile.h>

Result<double, Error> divide(double dividend, double divisor)
{
    if (divisor == 0)
    {
        return Result<double, Error>::fail(new Error("cannot divide by zero"));
    }
    return Result<double, Error>::ok(dividend / divisor);
}

Result<void, Error> isDivisionLegal(double dividend, double divisor)
{
    if (divisor == 0)
    {
        return Result<void, Error>::fail(new Error("cannot divide by zero"));
    }
    return Result<void, Error>::ok();
}

int main()
{
    TreeCli cli{};
    cli.cliInterfaceInit();
    return 0;
}

