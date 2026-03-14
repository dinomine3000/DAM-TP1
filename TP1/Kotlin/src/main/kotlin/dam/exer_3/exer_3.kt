package org.example.dam.exer_3

fun main() {
    println("-----------Main Method----------------")
    val initHeight: Float = 100000f
    val heightLossPerBounce: Float = 0.4f
    var height: Float = initHeight
    var i: Int = 0
    println("Starting at a height of $initHeight, the (up to the first 15) final bounces are: " +
        generateSequence { i++; height -= height * heightLossPerBounce; height.takeIf { it > 1 &&  i < 15} }.toList().map { value -> Math.round(value*100)/100f }
    )
    println("-----------Method Over----------------")
}