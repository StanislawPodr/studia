using System.Linq;
using System.Reflection;
using ExamplesLinq;

class Lab7
{
    public static void GroupSortedStudents(List<StudentWithTopics> students, Int32 n)
    {
        var groups = students
        .OrderBy(s => s.Name)
        .ThenBy(s => s.Index)
        .Chunk(n);

        Int32 grpIdx = 0;
        foreach (var group in groups)
        {
            foreach (var student in group)
            {
                System.Console.WriteLine($"group:{grpIdx} student:{student}");
            }
            grpIdx++;
        }

    }

    public static void SortTopicsByPopularity(List<StudentWithTopics> students)
    {
        var topicsSorted = from student in students
                           from topic in student.Topics
                           group topic by topic
                           into grp
                           orderby grp.Count() descending, grp.Key ascending
                           select new { Topic = grp.Key, Count = grp.Count() };

        foreach (var item in topicsSorted)
        {
            Console.WriteLine($"{item.Topic}: {item.Count}");
        }

        var topicsSortedWithGender = from student in students
                                     group student by student.Gender into genderGroups
                                     select new
                                     {
                                         Gender = genderGroups.Key,
                                         TopicsSorted = from student in genderGroups
                                                        from topic in student.Topics
                                                        group topic by topic into grp
                                                        orderby grp.Count() descending, grp.Key ascending
                                                        select new { Topic = grp.Key, Count = grp.Count() }
                                     };

        foreach (var gender in topicsSortedWithGender)
        {
            Console.WriteLine($"{gender.Gender}");
            foreach (var item in gender.TopicsSorted)
            {
                Console.WriteLine($"{item.Topic}: {item.Count}");
            }
        }
    }

    public static List<Student> StudentsWithTopicsToStudents(List<StudentWithTopics> studentsWithTopics, List<Topic> topics)
    {
        var studentLazy = from studentWithTopic in studentsWithTopics
                          select new Student(
                            studentWithTopic.Id,
                            studentWithTopic.Index,
                            studentWithTopic.Name,
                            studentWithTopic.Gender,
                            studentWithTopic.Active,
                            studentWithTopic.DepartmentId,
                            [.. from topic in studentWithTopic.Topics
                             from topicListElem in topics
                             where topicListElem.Name == topic
                             select topicListElem.Id]
                          );

        return [.. studentLazy];
    }

    public class Topic(Int32 id, string topicName)
    {
        public Int32 Id { get; } = id;
        public string Name { get; } = topicName;
    }

    public class Student(Int32 id, Int32 index, string name, Gender gender, bool active,
        Int32 departmentId, List<Int32> topics)
    {
        public Int32 Id { get; set; } = id;
        public Int32 Index { get; set; } = index;
        public string Name { get; set; } = name;
        public Gender Gender { get; set; } = gender;
        public bool Active { get; set; } = active;
        public Int32 DepartmentId { get; set; } = departmentId;

        public List<Int32> Topics { get; set; } = topics;

    }

    public class Reflection(Int32 value)
    {
        public Int32 Value { get; set; } = value;
        public Int32 GetValue(Int32 Val2)
        {
            Value += Val2;
            return Value;
        }
    }

    public static void Main()
    {
        //Zadanie 1
        List<StudentWithTopics> studentsWithTopics = Generator.GenerateStudentsWithTopicsEasy();
        GroupSortedStudents(studentsWithTopics, 3);

        //Zadanie 2
        SortTopicsByPopularity(studentsWithTopics);

        //Zadanie 3
        List<Topic> topics = studentsWithTopics.SelectMany(s => s.Topics).Distinct().Select((name, i) => new Topic(i + 1, name)).ToList();
        foreach (var student in StudentsWithTopicsToStudents(studentsWithTopics, topics))
        {
            Console.WriteLine($"{student.Id}) {student.Index}, {student.Name}, {student.Gender}, {student.Active}, {student.DepartmentId}, topics: {string.Join(", ", student.Topics)}");
        }

        //Zadanie 4
        Type type = Type.GetType("Lab7+Reflection") ?? throw new InvalidOperationException();
        object first = Activator.CreateInstance(type, 5)!;
        object second = Activator.CreateInstance(type, 10)!;
        object[] parameters = [21];
        MethodInfo? method = type.GetMethod("GetValue");
        object? result = method?.Invoke(first, parameters);
        object? result2 = method?.Invoke(second, parameters);
        Console.WriteLine(result);
        Console.WriteLine(result2);
    }
}
