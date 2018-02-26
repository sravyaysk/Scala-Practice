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

    //exists
    val coll = List(1,2,3,4,5)
    println(coll.exists( x => x % 2 == 0 ))

    //filter -> Returns all the elements of this list that satisfy the predicate p. The order of the elements is preserved
    val x = List.range(1, 10)
    println(x.filter(_ % 2 == 0))

    //find -> Find and return the first element of the list satisfying a predicate, if any

  }
}
