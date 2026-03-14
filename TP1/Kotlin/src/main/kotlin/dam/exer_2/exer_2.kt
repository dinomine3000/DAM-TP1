package dam.exer_2

private const val logicTemplate: String = "%s %s %s is:\n%s"
private const val logicNotTemplate: String = "The opposite of %s is\n%s"
private const val bitshiftTemplate: String = "Shifting the bits of the value %s to the %s once results in\n%s"
private const val mathTemplate: String = "%s %s %s is\n%s"

private fun validateInput(input: String): Boolean {
    if(input.contains("help")) return true
    if(splitSegments(input).size in 2..3) return true
    val res: Boolean =  (input.contains("&&") || input.contains("||") || input.contains("!")
            || input.contains("shr") || input.contains("shl")
            || input.contains("+") || input.contains("-") || input.contains("*") || input.contains("/"))
    if(!res){
        println("Invalid input query: $input")
    }
    return res
}

private fun getOp(input: String): String{
    if(input.contains("&&") || input.contains("||") || input.contains("!")) return "logic"
    if(input.contains("shl") || input.contains("shr")) return "bitshift"
    if(input.contains("help")) return "help"
    return "math"
}

private fun splitSegments(input: String): List<String> {
//    if(input.split(" ").size > 1) return input.split(" ")
    return input.split(" ")
}


fun main(){
    while(true){
        println("Please write the desired calculation:\nPossible operations: Math, Logic, Bitshift, or Help")
        val input: String = readln()
        if(!validateInput(input)) continue
        val type = getOp(input)
        val splitInput = input.split(" ")
        when(type){
            "math" -> mathOp(splitInput)
            "help" -> helpOp(splitInput)
            "logic" -> logicOp(splitInput)
            "bitshift" -> bitshiftOp(splitInput)
        }
    }
}

private fun helpOp(input: List<String>){
    if(input.size < 2)
        println("Type in the value and operation desired, separated by spaces ' '.\nYou can do:\nLogical operations between two values (&& or ||) or negate a single one (!);\nSimple mathematical operations (+, -, *, /);\nBitshifting by 1 bit (shl, shr).")
}
private fun mathOp(input: List<String>){
    if(input.size < 3) return
    try{
        val num1: Int = getIntValueOf(input[0])
        val num2: Int = getIntValueOf(input[2])
        val op: String = input[1]
        when(op){
            "*" -> println(String.format(mathTemplate, num1, "times", num2, getVariedResult(num1*num2)))
            "/" -> println(String.format(mathTemplate, num1, "divided by", num2, getVariedResult(num1 / num2)))
            "+" -> println(String.format(mathTemplate, num1, "plus", num2, getVariedResult(num1+num2)))
            "-" -> println(String.format(mathTemplate, num1, "minus", num2, getVariedResult(num1-num2)))
        }
    } catch (e: NumberFormatException){
        val num1: Float = getNumValueOf(input[0])
        val num2: Float = getNumValueOf(input[2])
        val op: String = input[1]
        when(op){
            "*" -> println(String.format(mathTemplate, num1, "times", num2, getVariedResult(num1*num2)))
            "/" -> println(String.format(mathTemplate, num1, "divided by", num2, getVariedResult(num1 / num2)))
            "+" -> println(String.format(mathTemplate, num1, "plus", num2, getVariedResult(num1+num2)))
            "-" -> println(String.format(mathTemplate, num1, "minus", num2, getVariedResult(num1-num2)))
        }
    } catch(e: ArithmeticException){
        println("Invalid operation: Division by 0.")
    }
}
private fun logicOp(input: List<String>){
    if(input.size < 2) return
    if(input.size < 3){
        val in1: Boolean = !(input[1] == "0" || input[1].lowercase() == "false")
        println(String.format(logicNotTemplate, input[1], getVariedResult(!in1)))
        return
    }
    val in1: Boolean = !(input[0] == "0" || input[0].lowercase() == "false")
    val op: String = input[1]
    val in2: Boolean = !(input[2] == "0" || input[2].lowercase() == "false")
    when(op){
        "&&" -> println(String.format(logicTemplate, input[0], op, input[2], getVariedResult(in1 && in2)))
        "||" -> println(String.format(logicTemplate, input[0], op, input[2], getVariedResult(in1 || in2)))
        else -> println("Invalid prompt format. When comparing two values, use either && for AND or || for OR. When negating a value, use ! before the value. Always separate the values with a space")
    }
}

private fun bitshiftOp(input: List<String>){
    if(input.size < 2) return
    val op: String = input[0]
    val num: Int = getIntValueOf(input[1])
    when(op){
        "shl" -> println(String.format(bitshiftTemplate, num, "left", getVariedResult(num.shl(1))))
        "shr" -> println(String.format(bitshiftTemplate, num, "right", getVariedResult(num.shr(1))))
        else -> println("Invalid prompt format. When bitshifting a value, use either shl to shift to the left or shr to shift to the right. Always separate the values with a space")
    }

}

private fun getIntValueOf(arg: String): Int{
    var num: Int = 0
    num = if(arg == "true") 1
    else if (arg == "false") 0
    else arg.toInt()
    return num
}

private fun getNumValueOf(arg: String): Float{
    var num: Float = 0f
    num = if(arg == "true") 1f
    else if (arg == "false") 0f
    else arg.toFloat()
    return num
}

private fun getVariedResult(value: Int): String{
    val hex = Integer.toHexString(value)
    val boolean  = if(value == 0) "false" else "true"
    return "Decimal: $value\nHexadecimal: 0x$hex\nBoolean: $boolean"
}

private fun getVariedResult(value: Float): String{
    val boolean  = if(value == 0f) "false" else "true"
    return "Decimal: $value\nBoolean: $boolean"
}

private fun getVariedResult(value: Boolean): String{
    val dec: Int = if(value) 1 else 0
    val hex = Integer.toHexString(dec)
    return "Decimal: $dec\nHexadecimal: 0x$hex\nBoolean: $value"
}


