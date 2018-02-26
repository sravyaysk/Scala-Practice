object AnonymousExample {
  def abs(n: Int): Int =
    if (n < 0) -n
    else n

  def factorial(n: Int): Int = {
    def go(n: Int, acc: Int): Int =
      if (n <= 0) acc
      else go(n-1, n*acc)
    go(n, 1)
  }

  def main(args: Array[String]): Unit = {
    println(formatResult("absolute value", -42, abs))
    println(formatResult("factorial", 7, factorial))
    println(formatResult("increment", 7, (x: Int) => x + 1))
    println(formatResult("increment2", 7, (x) => x + 1))
    println(formatResult("increment3", 7, x => x + 1))
    println(formatResult("increment4", 7, _ + 1))
    println(formatResult("increment5", 7, x => { val r = x + 1; r }))
    val multiplyByTwo = partial1(2, (a:Int, b:Int) => a * b)
    println(multiplyByTwo(3))
  }

  def formatResult(name: String, n: Int, f: Int => Int) = {
    val msg = "The %s of %d is %d."
    msg.format(name, n, f(n))
  }

  def partial1[A,B,C](a: A, f: (A,B) => C): B => C = { (b: B) => f(a, b) }
  //  return a function that takes a B and returns a C
  // (b: B) => f(a, b)

  def curry[A,B,C](f: (A, B) => C): A => (B => C) = { (a: A) => (b: B) => f(a,b)}

  def uncurry[A,B,C](f: A => B => C): (A, B) => C = { (a, b) => f(a)(b) }

}
