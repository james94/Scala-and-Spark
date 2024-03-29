def simple(): Unit = {
    println("simple print")
}

simple()

def adder(num1:Int, num2:Int): Int = {
    return num1 + num2
}

adder(4,5)
adder("4","5")

def greetName(name:String): String = {
    return s"Hello $name"
}

val fullgreet = greetName("Jose")

println(fullgreet)

def isPrime(numcheck:Int): Boolean = {
    for(n <- Range(2, numcheck)){
        if(numcheck%n == 0){
            return false
        }
    }
    return true
}

println(isPrime(10)) // false
println(isPrime(23)) // true

// Use collections for functions
val numbers = List(1,2,3,7)

def check(nums:List[Int]): List[Int] = {
    return nums
}

println(check(numbers))