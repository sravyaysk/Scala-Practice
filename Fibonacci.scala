object Fibonacci {
  def main(args:Array[String]): Unit ={
    for(i <- 1 to 10)println(fibonacci(i))
  }
  def fibonacci(i: Int): Int ={
    if(i == 1 || i == 2) 1
    else
      fibonacci( i-1 )+fibonacci( i-2 )
  }
}
