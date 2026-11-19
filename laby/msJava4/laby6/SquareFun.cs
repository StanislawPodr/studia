using System;

class Program
{
    static void Main()
    {
        double a = ReadDouble("a");
        double b = ReadDouble("b");
        double c = ReadDouble("c");

        (int count, double x1, double x2) = Solve(a, b, c);

        if (count == -1)
        {
            Console.WriteLine("The equation has infinitely many solutions.");
        }
        else if (count == 0)
        {
            Console.WriteLine("The equation has no real solutions.");
        }
        else if (count == 1)
        {
            Console.WriteLine("One solution: x = {0:F4}", x1);
        }
        else
        {
            Console.WriteLine($"Two solutions: x1 = {x1:F4}, x2 = {x2:F4}");
        }
    }

    static double ReadDouble(string name)
    {
        double value;
        while (true)
        {
            Console.Write($"Enter {name}: ");
            string? input = Console.ReadLine();
            if (input != null && double.TryParse(input, out value))
            {
                return value;
            }
            Console.WriteLine("Invalid number, try again.");
        }
    }

    static (int count, double x1, double x2) Solve(double a, double b, double c)
    {
        if (a == 0)
        {
            if (b == 0)
            {
                if (c == 0)
                {
                    return (-1, 0, 0);
                }
                return (0, 0, 0);
            }
            return (1, -c / b, 0);
        }

        double delta = b * b - 4 * a * c;

        if (delta < 0)
        {
            return (0, 0, 0);
        }

        if (delta == 0)
        {
            return (1, -b / (2 * a), 0);
        }

        double sqrt = Math.Sqrt(delta);
        double x1 = (-b - sqrt) / (2 * a);
        double x2 = (-b + sqrt) / (2 * a);
        return (2, x1, x2);
    }
}
