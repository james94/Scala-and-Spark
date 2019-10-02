// for(item <- List(1,2,3)){
//     println(item)
// }

// for(num <- Array.range(0,5)){
//     println(num)
// }

//keep in mind with Set, it doesn't have an order
// for(num <- Set(1,2,3)){
//     println(num)
// }

// loop print even or odd numbers
// for(num <- Range(0,10)){
//     if(num%2 == 0){
//         println(s"$num is even")
//     }else{
//         println(s"$num is odd")
//     }
// }

val names = List("John", "Abe", "Cindy", "Cat")

for(name <- names){
    if(name.startsWith("C")){
        println(s"$name starts with a C")
    }
}