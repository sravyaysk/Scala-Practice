object FoldLeftExample {
  def main(args:Array[String]): Unit ={

   // var a=List(1,2,3,4)
   // println(a.reduceLeft(_+_)) //walk a sequence from left to right
   // println( a.foldLeft(20)(_ + _)) //starts with a seed value and reduceleft

    println(factorial(5))
    println(factorial1(5))
    println(factorial2(5))
    println(factorial3(5))
    println(factorial4(5))
  }
  def factorial1(i: Int)=(2 to i).reduce(_*_)       //reduce

  def factorial2(j: Int)=(2 to j).foldLeft(1)(_*_)  //foldleft

  def factorial4(j: Int)=(2 to j).reduceLeft(_*_)  //reduceleft

  def factorial(n: Int): Int =                    //recursion
    if (n == 0) 1 else n * factorial(n - 1)

  def factorial3(n: Int): Int = {               //tail recursion
    def iter(x: Int, result: Int): Int =
      if (x == 0) result
      else iter(x - 1, result * x)
    iter(n, 1)
  }

}
