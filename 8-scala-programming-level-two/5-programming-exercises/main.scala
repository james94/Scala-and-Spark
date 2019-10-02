import util.control.Breaks._
// Create functions to solve the questions

// 1. Check for Single Even:
// Write a function that takes in an integer and returns a 
// Boolean indicating whether or not it is even. See if you
// can write this in one line!
def isNumEven(num:Int): Boolean = {
    if(num%2 == 0){
        return true
    }else{
        return false
    }
}

// isNumEven(20)
// isNumEven(3)

// create above function in one line
def checkEven(num:Int) = num%2 == 0

// 2. Check for Evens in a List:
// Write a function that returns True if there is an even 
// number inside of a List, otherwise, return False
def listContainsEven(nums:List[Int]): Boolean = {
    for(num <- nums){
        if(isNumEven(num)){
            return true
        }
    }
    return false
}

// val numbers = List(1,2,3,4,5)
// val oddNumbers = List(1,3,5)

// listContainsEven(numbers)

// 3. Lucky Number Seven:
// Take in a list of integers and calculate their sum. 
// However, sevens are lucky and they should be counted twice,
// meaning their value is 14 for the sum. Assume the list isn't
// empty.
def luckyNumberSeven(nums: List[Int]): Int = {
    var sum = 0
    for(num <- nums){
        if(num == 7){
            sum = sum + (num*2)
        }
        else{
            sum = sum + num
        }
    }
    return sum
}

// val luckySeven = List(3,5,7)
// var result = luckyNumberSeven(luckySeven)

// 4. Can you Balance?
// Given a non-empty list of integers, return true if there is a place to
// split the list so that the sum of the numbers on one side is equal to
// sum of the numbers on the other side. For example, given the list
// (1,5,3,3) would return true, you can split it in the middle. Another
// example (7,3,4) would return true 3+4 = 7. Remember you just need to
// return the boolean, not the split index point.

// Solution 1
def canYouBalanceList(nums: List[Int]): Boolean = {
    var balanced = false
    var leftSum = 0
    var rightSum = 0
    var in1 = 0
    var in2 = nums.length - 1
    leftSum = leftSum + nums(in1)
    rightSum = rightSum + nums(in2)
    while(in1 < in2){
        if(leftSum < rightSum){
            in1 = in1 + 1 //inc left index to right
            leftSum = leftSum + nums(in1)
        }else if(leftSum > rightSum){
            in2 = in2 - 1 //dec right index to left
            rightSum = rightSum + nums(in2)
        }else if(leftSum == rightSum){
            return true
        }else if(leftSum != rightSum){
            break
        }
    }
    return false
}

// val balanceList1 = List(7,3,4)
// val balanceList2 = List(1,5,3,3)
// val balanceList3 = List(1,6,3,3)
// var balanced = canYouBalanceList(balanceList3)
// println(balanced)

// Solution 2
def balanceCheck(myList:List[Int]): Boolean = {
    var firsthalf = 0
    var secondhalf = 0

    secondhalf = myList.sum

    for(i <- Range(0, myList.length)){
        firsthalf = firsthalf + myList(i)
        secondhalf = secondhalf - myList(i)

        if(firsthalf == secondhalf){
            return true
        }
    }
    return false
}



// 5. Palindrome Check
// Given a String, return a boolean indicating whether or not it is a 
// palindrome. (Spelled the same forwards and backwards). Try exploring
// methods to help you.

def isPalindrome(str: String): Boolean = {
    var palindrome = false
    var in1 = 0
    var in2 = str.length - 1
    var str1 = str(in1)
    var str2 = str(in2)
    while(in1 < in2){
        if(str(in1) != str(in2)){
            return false
        }        
        else if(str(in1) == str(in2)){
            in1 = in1 + 1 //inc in1 to right
            in2 = in2 - 1 //dec in2 to left
            palindrome = true
        }
    }
    return palindrome
}

val string1 = "acd"
val string2 = "acddca"
println(isPalindrome(string1))
println(isPalindrome(string2))

// solution 2
def palindromeCheck(st:String):Boolean = {
    return (st == st.reverse)
}

// println(palindromeCheck("abccba"))
// println(palindromeCheck("hello"))