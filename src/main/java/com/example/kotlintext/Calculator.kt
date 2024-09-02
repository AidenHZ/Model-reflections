package com.example.kotlintext
/*
* 这是一个Calculator
*input：3 * 4
* output：
*
*
*
* */
class Calculator {


}
fun main(vararg args:String) {
    if (args.size<3){
        return error()
    }
    val operators= mapOf(
        "+" to ::plus,
        "-" to ::minus,
        "*" to ::mul,
        "/" to ::division
    )
    val element1=args[1]
    val result = operators[element1]?:return error()

    try {
        println("input:${args.joinToString (" ")}")
        println("output:${result(args[0].toInt(),args[2].toInt())}")
    } catch (e: Exception) {
        error()
    }
}
fun division(arg0: Int,arg1: Int):Int{
    return arg0/arg1
}
fun mul(arg0: Int,arg1: Int):Int{

    return arg0*arg1
}
fun minus(arg0:Int,arg1:Int):Int{

    return arg0-arg1

}

fun plus(arg0:Int,arg1:Int):Int{

    return arg0+arg1

}
fun error(){
    println("""
        Calculator Example:
            input:3 * 5
            output:15
        
    """.trimIndent())
}