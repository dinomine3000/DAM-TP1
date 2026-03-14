package dam.exer_1

fun main() {
//    val name = "Kotlin"
//    println("Hello, " + name + "!")
//
//    for (i in 1..5) {
//        println("i = $i")
//    }

    val answer1 = IntArray(50) { i -> (i+1) * (i + 1) }
    println(answer1.contentToString())

    val nums = 1..50
    val answer2 = nums.map { i -> i*i }
    println(answer2)

    val answer3 = Array(50){ i -> (i+1) * (i + 1) }
    println(answer3.contentToString() == answer1.contentToString())
}