import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks._

object ListMethodsExamples {
  def main(args:Array[String]): Unit ={

    //Arrays preserve order, contain duplicates, mutable
    val number_array = Array(1, 2, 3, 4, 5, 1, 2, 3, 4, 5)
    println("Array: "+number_array)
    number_array(0) = 10
    println("Array: "+number_array)

    //Lists preserve order, contains duplicates, not mutable
    val number_list = List(1, 2, 3, 4, 5, 1, 2, 3, 4, 5)
    println("List: "+number_list)
    //number_list(3) = 10 //error

    //sets cannot contain duplicates
    val number_set = Set(1, 2, 3, 4, 5, 1, 2, 3, 4, 5)
    println("Set: "+number_set)

    //Tuples contain different data types
    val srav_marks = ("Maths",100,"Science",90)
    println("Tuple: "+srav_marks)

    //Maps associated with key value pairs
    val Map1 = Map(1 -> "one")
    println("Map type1: "+Map1)
    val Map2 = Map(("Maths",100),("Science",90),("Social",80))
    println("Map type2: "+Map2)
    println(Map2.get("Science"))

    //LIST METHODS
    val nums = List(1, 2, 3, 4)
    println(10::nums) //Add an element x at the beginning of this list

    println(nums:::List(5,6)) //Returns a list resulting from the concatenation of the given list prefix and this list

    println(nums.apply(3)) //returns nth element in list

    //break
    breakable {
      for( a <- nums){
        println( "Value of a: " + a );

        if( a == 4 ) break;}
    }

    //count -> counts number of elements in a list
    //to count each element occurred how many times in a list
    for(i <- nums)println(nums.count(_==i))

    //Diff -> used to find difference between two lists
    var List1 = List(10, 8, 6, 4, 2)
    var List2 = List(1, 3, 5, 3, 1)
    println(List1.diff(List2))

    //drop -> drop first n elements from the list
    var input = List(2,3,4,5)
    println(input.drop(2))

    //dropRight -> Returns the list wihout its rightmost n elements
    var dummy = List(2,3,4,5)
    println(dummy.dropRight(2))

    //dropWhile
    println(number_list.dropWhile(_ % 2 != 0))

    //elements -> Returns the elements in the list as an iterator

    //exists -> there exists atleast one element in list that satisfies the given function
    val coll = List(1,2,3,4,5)
    println(coll.exists( x => x % 2 == 0 ))

    //filter -> Returns all the elements of this list that satisfy the predicate p. The order of the elements is preserved
    val x = List.range(1, 10)
    println(x.filter(_ % 2 == 0))

    //find -> Find and return the first element of the list satisfying a predicate, if any
    println(number_list.find((i: Int) => i > 5))

    // flatMap takes a function that works on the nested lists and then concatenates the results back together.
    val nestedNumbers = List(List(1, 2), List(3, 4))
    println(nestedNumbers.flatMap(x => x.map(_ * 2)))

    val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    //fold left
    println(numbers.foldLeft(0)((m: Int, n: Int) => m + n))
    numbers.foldLeft(0) { (m: Int, n: Int) => println("m: " + m + " n: " + n); m + n }

    //fold right
    numbers.foldRight(0) { (m: Int, n: Int) => println("m: " + m + " n: " + n); m + n }

    //flatten -> flatten collapses one level of nested structure.
    List(List(1, 2), List(3, 4)).flatten

    //forall -> if all the elements in the list satisfies the function or not
    List(1,2,3).forall(x => x < 3)

    //foreach -> foreach is like map but returns nothing. foreach is intended for side-effects only
    numbers.foreach((i: Int) => i * 2)

    //head -> returns the first element of the list
    println(numbers.head)

    //indices -> Creates a list with all indices in the list. This is equivalent to a call to List.range(0, xs.length)
    println(numbers.indices)

    //init -> Returns the list without its last element
    println(numbers.init)

    //intersect -> Computes the intersection between this list and the given list that
    println(List(1,2,3).intersect(List(2,3,4,5)))

    //isEmpty -> Returns true if the list does not contain any elements
    println(numbers.isEmpty)

    //last -> Returns the last element of this list
    println(numbers.last)

    //length -> returns number of elements in the list
    println(numbers.length)

    //map -> Returns the list resulting from applying the given function f to each element of this list
    println(numbers.map((i: Int) => i * 2))

    //partition splits a list based on where it falls with respect to a predicate function
    numbers.partition(_ % 2 == 0)

    //reduce left -> iterates from left to right
    val a = Array(12, 6, 15, 2, 20, 9)
    a.reduceLeft(_ + _)

    //reduce right -> iterates from right to left
    a.reduceRight(_ + _)

    val x1 = ListBuffer(1, 2, 3, 4, 5, 6, 7, 8, 9)
    //remove -> Removes all elements of the list which satisfy the predicate p. This is like filter with the predicate inversed
    x1.remove(2)
    x1.remove(1, 3)

    //removeDuplicates -> Removes redundant elements from the list. Uses the method == to decide if two elements are identical.
    //x1.removeDuplicates()

    //reverse -> A list consisting of all elements of this list in reverse order
    val str = "scala is awesome"
    println(str.reverse)

    //reverseMap -> Apply a function to all the elements of the list, and return the reversed list of results. This is equivalent to a call to map followed by a call to reverse, but more efficient
    numbers.reverseMap((i:Int)=>i*2)

    //reverse_::: -> reverses list and appends list to start
    numbers.reverse_:::(List(3))

    //sort
    List(10, 5, 8, 1, 7).sorted
    List(10, 5, 8, 1, 7).sortWith(_ < _)

    val y = List(15, 10, 5, 8, 20, 12)
    //span -> Returns the longest prefix of the list whose elements all satisfy the given predicate, and the rest of the list
    println(y.span(_<20))

    //splitAt -> Split the list at a given point and return the two parts thus created
    println(y.splitAt(2))

    //stringPrefix
    //tail -> Returns this list without its first element
    println(numbers.tail)

    //take -> Returns the n first elements of this list, or else the whole list, if it has less than n elements
    println(numbers.take(2))

    //takeRight -> Returns the rightmost n elements from this list
    println(numbers.takeRight(2))

    //takeWhile -> Returns the longest prefix of this list whose elements satisfy the predicate p
    println(numbers.takeWhile(_ < 5))

    //toList -> converts a collection to List
    var new_list = Array(1, 2, 3, 4, 5).toList
    println(new_list)

    //union -> Computes the union of this list and the given list that
    var union_list = List(1,2,3).union(List(3,4,5))
    println(union_list)

    //zip -> aggregates the contents of two lists into a single list of pairs
    println(List(1, 2, 3).zip(List("a", "b", "c")))

    //zipWithIndex -> zips elements with indexes
    println(List("a", "b", "c").zipWithIndex)

    //zipAll -> zip the unidentical length lists
    val a1 = List("a", "b", "c", "d")
    val b1 = List(1, 2, 3)
    println(a1.zipAll(b1, "for missing values", 100))
    
  }
}
