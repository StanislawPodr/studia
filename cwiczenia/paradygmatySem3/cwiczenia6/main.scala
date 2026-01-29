// zadanie pierwsze
// w pierwszym użycie var powoduje że jest automatyczna funkcja def _x(x: T) : Unit,
// co daje nam błąd bo ta funkcja występuje jako kontrwariantna przyjmując T+ zamiast T-
// nie da się pozbyć tego błędu zachowując tą klasę jako mutowalną.
// wersja kontrwariantna jest też błędna z tego samego powodu w drugą stronę, funkcja
// przyjmuje T-, więc to powoduje że powinna być kowariantna a nie jest bo zwraca też T- 
// co stawia ją w pozycji inwariantnej

// zadanie drugie
abstract class Sequence[+A]:
    def append[T>:A](x: Sequence[T]): Sequence[T]
end Sequence

// zadanie trzecie
class Queue[+A] private (front: List[A], back: List[A]):

    def this() = 
        this(List.empty, List.empty)

    def this(startVal: A) = 
        this(List(startVal), List.empty)

    def enqueue[T>:A](x: T) : Queue[T] =
        new Queue[T](front, x::back)

    def dequeue() : Queue[A] = 
        front match {
            case (head :: next) => new Queue[A](next, back)
            case _ => back.reverse match {
                case head :: next => new Queue[A](next, List.empty)
                case _ => throw new IllegalStateException("Queue should have elements!")
            }
        }
    
    def first() : A = 
        front match {
            case (head :: next) => head
            case _ => back.reverse match {
                case head :: next => head
                case _ => throw new IllegalStateException("Queue should have elements!")
            }
        }

    def isEmpty() : Boolean = 
        front.isEmpty && back.isEmpty

    def isNotEmpty() : Boolean = 
       !isEmpty()
    
end Queue



@main def main : Unit = {

}