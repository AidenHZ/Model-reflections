package com.example.kotlintext

class DeClass constructor(x:Int){
    val x1 =x
    fun function(){}
}
fun a(){
    val dClass = DeClass(5)
    dClass.x1
    dClass::x1
    dClass.function()
    dClass::function

}
interface Text{
    var simpleP:Int

    fun a(){
        println("")
    }
}
class Text1(val x:String):Text{
    var b:String=""
        set(value) {
            field=value
        }
        get() {
            return field
        }
    override var simpleP: Int=1



    override fun a() {
        super.a()
    }
}
abstract class AText(){
    var i:Int=0
   abstract fun a()
   open fun b(){

   }
}
class  T: AText() {
    override fun a() {
        TODO("Not yet implemented")
    }
    override fun  b(){

    }

}

class Utils(){
    var c:Boolean = true
}

fun Utils.isPass(passWord: String = ""): Boolean {
    return passWord == ""

}
var Utils.check:Boolean
    get() {
        return this.c
    }
    set(value) {
        this.c  = value
    }

var i:String? = ""
 fun iT(){
     i = null
     val length = i!!.length

 }


interface Teacher{
    val tec: String
}

class Student(override val tec: String = ""):Teacher{
    var teacher:Teacher = Student("")
        get() {
            return field
        }
        set(value) {
            field = value
        }
    fun t(){
        println((teacher as? Student)?.tec)
        if (teacher is Student){
            println(teacher.tec)
        }
    }

}


