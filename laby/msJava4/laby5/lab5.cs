using System.ComponentModel.DataAnnotations;
using System.Reflection.Metadata;
using System.Text.RegularExpressions;

class Lab5
{
    static void PrintTuple((String Name, String Surname, byte Age, float Wage) tuple)
    {
        System.Console.WriteLine($"{tuple.Name} {tuple.Surname} {tuple.Age}yrs {tuple.Wage}$");
        System.Console.WriteLine($"{tuple.Item1} {tuple.Item2} {tuple.Item3}yrs {tuple.Item4}$");
        (String Name, String Surname, byte Age, float Wage) = tuple;
        System.Console.WriteLine($"{Name} {Surname} {Age}yrs {Wage}$");
    }

    static void PrintAnonym(dynamic anonym)
    {
        System.Console.WriteLine($"{anonym.Name} {anonym.Surname} {anonym.Age}yrs {anonym.Wage}$");
        // System.Console.WriteLine($"{anonym.Item1} {anonym.Item2} {anonym.Item3}yrs {anonym.Item4}$");
        // (String Name, String Surname, byte Age, float Wage) = anonym;
        // System.Console.WriteLine($"{Name} {Surname} {Age}yrs {Wage}$");
    }

    static String GetPaddedCard(String row, char borderChar, Int32 borderSize, Int32 width)
    {
        String paddingLeft = new String(borderChar, borderSize) +
                             new String(' ', (width - 2 * borderSize - row.Length) / 2);

        String paddingRight = new String(' ', width - paddingLeft.Length - row.Length - borderSize) +
                              new String(borderChar, borderSize);

        return paddingLeft + row + paddingRight;
    }
    static void DrawCard(String fstRow, String secRow, char borderChar = 'X', Int32 borderSize = 2, Int32 minWidth = 20)
    {
        if (fstRow == null || secRow == null)
        {
            throw new ArgumentException("Rows can't be null!");
        }

        if (borderSize < 0 || minWidth < 0)
        {
            throw new ArgumentException("Sizes should be >=0");
        }

        Int32 textMinWidth = Math.Max(fstRow.Length, secRow.Length) + 2 * borderSize; // border po każdej stronie
        Int32 width = Math.Max(textMinWidth, minWidth);
        for (int borderIdx = 0; borderIdx < borderSize; borderIdx++)
        {
            System.Console.WriteLine(new String(borderChar, width));
        }

        System.Console.WriteLine(GetPaddedCard(fstRow, borderChar, borderSize, width));
        System.Console.WriteLine(GetPaddedCard(secRow, borderChar, borderSize, width));

        for (int borderIdx = 0; borderIdx < borderSize; borderIdx++)
        {
            System.Console.WriteLine(new String(borderChar, width));
        }

    }

    public static (int evenInts, int positiveDoubles, int longStrings, int others) CountMyTypes(params object[] items)
    {
        int evenInts = 0;
        int positiveDoubles = 0;
        int longStrings = 0;
        int others = 0;

        foreach (var item in items)
        {
            switch (item)
            {
                case int i when i % 2 == 0:
                    evenInts++;
                    break;

                case double d when d > 0:
                    positiveDoubles++;
                    break;

                case string s when s.Length >= 5:
                    longStrings++;
                    break;

                default:
                    others++;
                    break;
            }
        }

        return (evenInts, positiveDoubles, longStrings, others);
    }

    static void Main(String[] args)
    {
        // Zadanie pierwsze
        (String Name, String Surname, byte Age, float Wage) myTuple = ("Adam", "Kowalski", 33, 8000.0f);
        PrintTuple(myTuple);
        // Zadanie drugie
        String @class = "class";
        System.Console.WriteLine(@class);
        // Zadanie trzecie
        int[] arr = [100, 93, 84, 39, 10];
        System.Array.Sort(arr);
        int idx100 = System.Array.BinarySearch(arr, 100);
        System.Console.WriteLine($"100Idx: {idx100}");
        System.Array.ForEach(arr, i => System.Console.WriteLine(i));
        System.Array.Reverse(arr);
        System.Array.ForEach(arr, i => System.Console.WriteLine(i));
        System.Array.Clear(arr);
        System.Array.ForEach(arr, i => System.Console.WriteLine(i));
        // Zadanie czwarte
        PrintAnonym(new { Name = "Jacek", Surname = "Kowal", Age = 28, Wage = 36666.22f });
        // Zadanie piąte
        DrawCard("Ryszard", "Rys");
        // Zadanie szóste
        Console.WriteLine(CountMyTypes(1, 2, 3, 1.1, 1.0, "abba", "abbab", true, null, 1.8));
    }
}