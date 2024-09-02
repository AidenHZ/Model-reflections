package com.example.kotlintext
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotlintext.Models.register
import com.example.kotlintext.ui.theme.MyApplicationTheme
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import java.io.File
import java.io.FileInputStream
import java.lang.IllegalArgumentException
import java.lang.StringBuilder
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.net.URL
import java.sql.Ref
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Properties
import java.util.concurrent.ConcurrentHashMap
import kotlin.properties.Delegates
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.typeOf
import kotlin.reflect.KFunction
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.jvmErasure


class MainActivity : ComponentActivity() {
    private val nameText: TextView by lazy {
         findViewById(androidx.core.R.id.text)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")

                }
            }

            
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}
// region+ 折叠之前demo
/*fun main1(){
    val b = IntArray(5){it}
    val a = intArrayOf(1,2,3,4,5)
    val c=a[0]
    println("这是c的内容：$c")
    println("直接打印出数组的内容为：${a.contentToString()}")
    a.forEach {element->
        println(element)
    }
    for (element in a){
        println(element)
    }
    b.forEach {
        println("这是b的元素:$it")
    }
    if (1 in a){
        println("1 exists in variable a")
    }
    val e=1..10
    println(e.joinToString())
    val a: List<Int> = listOf(1,2,3,4)
    val b:MutableList<Int> = mutableListOf(5,6,7,8)
    b.remove(8)
   // println("a的内容为：$a b的内容为：$b")

    val c:Map<String,Any> = mapOf("age" to "18" ,"gender" to "man" )

    c.forEach {
        println(it)
    }
    for (element in a){
        println("a中for循环遍历的内容为：${element}")
    }
    for (element in c.entries){
        println("C中for循环遍历的内容为：${element.key},${element.value}")
    }
    val d = "student" to "Z"
    println("pair的内容为：${d}")
    if("student" == d.first){
        println("在d中存在：${d.first}")
    }
    val y:MutableList<String> = mutableListOf("s")
    y.add("t")
    y.remove("s")
    val p:Int=0
}
fun text(a:Array<Int>):String{
    val c=a

    println(c)
    return a.toString()
}
val l =::text
fun t1(i1:(TextClass)->String){
    i1(TextClass())
    i1.invoke(TextClass())
}

class  TextClass{
    fun t():String{
        return "1"
    }
}
val o :Function1<TextClass,Any> = TextClass::t

fun o (vararg a:Int){
    print((a.contentToString()))
}
val o1=o(1,2,3)
fun uio(){
    println(o1)
    fun b1():Pair<Int,String>{
        return Pair(1,"2")
    }
    val (a,b)=b1()
    fun b2(x:Int=5,y:String="",z:Long=1L):Triple<Int,String,Long>{
        return Triple(x,y,z)

    }
    val c=b2(y="1")
    println(c)
    var e = when (val a = readLine()) {
        null -> 0
        else -> a.length

    }


}
fun pp(){
    val a:Int = 0
    val b:Int = 5
    var c:Int
    if(5==2){
        c = 9
    }else{
        c = 15
    }
    try {
        c = a/b
    }catch (e:Exception){
        e.printStackTrace()
        c = 0
    }
}*/
//endregion

//region+折叠
operator fun String.minus(right:Any?):String{
    return this.replaceFirst(right.toString(),"")
}

operator fun String.times(right: Int):String{
    return (1..right).joinToString(separator = "", transform = {this})
}
operator fun String.div(right: Any?):Int{
    val tRight = right.toString()
    return this.windowed(tRight.length,1){it == right

    }.count(){it}
}
class Teach (var t:String, var u:Int){
    override fun hashCode(): Int {
        var resultU = u
        return 31*resultU+t.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        val compare = (other as? Teach)?:return false
        return this.t == other.t && this.u == other.u
        return true
    }
}
class Compare(var real:Int,var image:Int ){
    override fun toString() = "real:${real}    image:${image}"   // 如果不复写toString方法的话，返回的是对象类名+哈希码
}
operator fun Compare.minus(value: Int): Compare {
    return Compare(this.real-value,this.image)
}
val funt: (Int) -> Unit = fun(p:Int){
    println(p.toString())   //匿名函数的写法
}
val textL:(Int)->Int = {p:Int->
    println("")
    123      //Lambda表达式,表达式的返回值跟最后一行有关。
}
val textp = {
    println("")
    123
}
val text:Function1<Int,Unit> = {p->
        println(p)
}
val text3:(o:String)->Unit = {
    println(it)
}
//endregion

fun costTime(cost:()-> Unit){
    val sTime = System.currentTimeMillis()
    cost()
    println (System.currentTimeMillis()-sTime)
}
fun fibonacci():()->Long{
    var first = 0L
    var second = 1L
    return {
        val next = first+second
        val current = first
        first = second
        second = next
        current
    }
}
/*fun main(){

    val teach = HashSet<Teach>()
    *//*val c = Compare(3,5)
    println((c - 5))    //打印出来的结果是     real：-2    image：5
    funt.invoke(5)*//*
    (0..5).forEach{
        teach.add(Teach("math",5))
    }
    println(teach.size)
    val ex = "Hello"
    println(ex-"H")
    println(ex * 5)
    println(ex / "o")

    costTime {
        val fib = ::fibonacci
        val fibc = fib()            //上面两行代码等价于   val  fib = fibonacci()  访问函数实例
        (0..10).forEach {
            println("forEach循环的结果为：${fibc()}+${it}")
        }

    }
    val fib1:()->Long = fibonacci()
    for (i in 0..10){
        println("for循环的结果为:${fib1()}")
    }
    val c = cTime {
                println("inline的调用")

            }
    println(c.toString())


    var poc:Int

    val bi = Bi()
    bi.let {
        println(it.pocket)
    }
    bi.run {
        println(this)
    }
    val bb =    bi.also {
                it.pocket = 5
                println("also打印的结果是${it.pocket}")
            }
    println(bb.pocket)
    bi.apply {
       this.pocket = 7
        println("apply打印的结果是${this.pocket}")
    }
    val name: String? = "Kotlin"

    // 使用 let 在非空检查后执行代码
     name?.let {
        println("The name is $it")
    }

    // 链式调用 let
    val result = name?.let {
        println("Length of the name is ${it.length}")
        it.length
    }
    println("Result is $result")
    val    lo =  listOf<Int>(1,2,3)
    lo.filter {
        println("饿汉式会立即执行${it}")
         it%2 ==0
    }

    lo.asSequence().filter {
        println("懒汉式不会立即执行${it}")
        it%2 ==0
    }
    lo.map {
        println("饿汉式执行map的结果为${it}")
    }
    lo.asSequence().map(){
        println("懒汉式执行map的结果为${it}")
    }.toList()

    val fm = lo.flatMap {
             0 until  it
             }
    println(fm)

    val  fma = lo.asSequence().flatMap {
        println(it)
        (0 until it).asSequence()

    }.joinToString()
    println(fma.toString())
    val  fl =lo.fold(StringBuilder()){
                a,it->a.append(it)
             }
    println("fold的值为${fl}")
    val numbers = listOf(1, 2, 3, 4, 5)
    val sum = numbers.fold(0) { acc, number ->
        acc + number
    }
    println(sum) // 输出: 15


}*/




inline fun cTime(cost:()->Unit):Long{
    val cS = System.currentTimeMillis()
    cost()
    return  System.currentTimeMillis()-cS
}
class   Bi(){
    var pocket = 0
    var money: Int
        inline    get() {
                return 0
            }
        inline    set(value) {
               pocket = value
            }
}


interface  A{
    fun a(){}
}

val aS = object : A{
                      override fun a() {
                      println("")
                      }
                    }
val c= {
            println("")
        }

interface Node{
    fun render():String
}
class StringNode(val cotent:String) : Node{
    override fun render():String {
        return  cotent
    }
}
class BlockNode(val name:String):Node{
    val children =ArrayList<Node>()
    val properties = HashMap<String,Any>()
    override fun render(): String {
        TODO("Not yet implemented")
    }

    operator fun String.invoke(block: BlockNode.() -> Unit):BlockNode{
        val node = BlockNode(this)
        node.block()
        this@BlockNode.children += node
        return node
    }
    operator fun String.unaryPlus(){
        this@BlockNode.children+= StringNode(this)
    }
}
fun html(block:BlockNode.()->Unit):BlockNode{
    val html = BlockNode("html")
    html.block()
    return html

}
fun BlockNode.head(block:BlockNode.()->Unit):BlockNode{
    val head = BlockNode("head")
    head.block()
    this.children+= head
    return head

}
fun BlockNode.body(block:BlockNode.()->Unit):BlockNode{
    val body = BlockNode("body")
    body.block()
    this.children+= body
    return body
}
class LoadT(var inf:Any,image:String){
    constructor(gender:String) : this(1, image = ""){

    }
    constructor():this(""){
    }
    init {
        inf= ""
    }
    init {
        inf = image
    }
}
class  LoadT1{
    var inf:Int = 5
    var image:String
    @JvmOverloads       //实际上是定义在constructor的前面。
    constructor(gender:String):super(){ //如果父类的主构造器没有，可以省略super()
        image  = ""    //这里省略了this，但是效果是一样的。

    }
    constructor():this(""){

    }
}
val  loadT = HashMap<String,LoadT>()
fun LoadTCatch(image: String):LoadT{
    return loadT[image]?: LoadT(1,image).also {  loadT[image] = it }

}
class    Osajdoi(){
       var i :Int= 5
           get() {
               return field
           }
           set(value) {
               field=value
           }
    private  val c= ""
    @JvmName("!daojcs")    internal fun ia(){

    }


}
private    fun oo(){
        val  os = Osajdoi()
        os.i


    }

fun ui(){
    val  os = Osajdoi()

}


class StateManager {
    var state: Int by Delegates.observable(0) {
            property, oldValue, newValue ->
        println("State changed from $oldValue -> $newValue")
    }
}


class SuperArray<E>(
    private val list:MutableList<E?> = ArrayList(),
    private val map:MutableMap<Any,E> = HashMap()
):MutableList<E?> by list, MutableMap<Any,E> by map {
    override fun isEmpty(): Boolean =
        list.isEmpty()&& map.isEmpty()


    override val size: Int
        get() =  list.size+map.size


    override fun clear() {
        list.clear()
        map.clear()
    }

    override fun toString(): String {
        return "List{$list};Map:{$map}"
    }

    override  operator  fun set(index: Int, element: E?): E? {
        if (list.size <=  index){
            repeat(index-list.size + 1){
                list.add(null)
            }
        }
        return list.set(index,element)
    }

}
class User {

    var age: Int by Delegates.observable(0) { property, oldValue, newValue ->
        println("Property ${property.name} changed from $oldValue to $newValue")
    }
}

class   Xi{

    val x:Int by Agent()
    var y:Int by Agent()

}
class   Agent{
    operator fun getValue(thisRef: Any?,property:KProperty <*>):Int{
        return   3
    }
    operator fun setValue(xi: Xi,property: KProperty<*>,i:Int){
        //如果确定要代理的类，可以把thisRef：Any？换成代理的类

    }

}


/*class PropertiesDelegate(private val path: String, private val defaultValue: String = ""){

    private lateinit var url: URL

    private val properties: Properties by lazy {
        val prop = Properties()
        url = try {
            javaClass.getResourceAsStream(path).use {
                prop.load(it)
            }
            javaClass.getResource(path)
        } catch (e: Exception) {
            try {
                ClassLoader.getSystemClassLoader().getResourceAsStream(path).use {
                    prop.load(it)
                }
                ClassLoader.getSystemClassLoader().getResource(path)!!
            } catch (e: Exception) {
                FileInputStream(path).use {
                    prop.load(it)
                }
                URL("file://${File(path).canonicalPath}")
            }
        }

        prop
    }
    operator fun getValue(thisRef: Any?,property: KProperty<*>):String{
        return properties.getProperty(property.name,defaultValue)
    }
    operator fun setValue(thisRef: Any?,property: KProperty<*>,value:String){
        properties.setProperty(property.name,value)
        File(url.toURI()).outputStream().use {
            properties.store(it,"这是一个提醒")
        }
    }

}*/
/*abstract class  AbsProperties(path: String){
    protected  val absProperties = PropertiesDelegate(path)
}*/
/*
class Config :AbsProperties("Config.properties"){
    var author by  absProperties
    var version by absProperties
    var desc by absProperties
}
*/

/*fun main(){
    val config =Config()
    println(config)
    config.author = "me"
    println(config.author)
}*/
class SharedPreDelegates<T>(private val name: String,
                         private val defaultValue: T,
                        private val sharedPreferences: SharedPreferences){
    operator fun getValue(thisRef: Any?,property: KProperty<*>):T{
        return  when  (defaultValue){
            is Boolean -> sharedPreferences.getBoolean(name,defaultValue) as T
            is Float -> sharedPreferences.getFloat(name,defaultValue) as T
            is Int -> sharedPreferences.getInt(name,defaultValue) as T
            is Long -> sharedPreferences.getLong(name,defaultValue) as T
            is String -> sharedPreferences.getString(name,defaultValue) as T
            else -> throw  IllegalArgumentException("This type cannot be save")
        }
    }
    operator fun setValue(thisRef: Any?,property: KProperty<*>,i: T){
        with(sharedPreferences.edit()){
            when(i){
                is Boolean -> putBoolean(name,i)
                is Float -> putFloat(name,i)
                is Int -> putInt(name,i)
                is Long -> putLong(name,i)
                is String -> putString(name,i)
                else -> throw  IllegalArgumentException("")
            }.apply()
        }
    }

}
abstract  class  AbsSharePreferences(context: Context){

    private val shred: SharedPreferences =context.getSharedPreferences("MyPreferences",Context.MODE_PRIVATE)
    protected  fun <T> delegate(name: String,defaultValue: T):SharedPreDelegates<T>{
     return  SharedPreDelegates<T> (name,defaultValue,shred)
    }

}
class  SetShareP(context: Context):AbsSharePreferences(context){
    var userName by  delegate("userName","")
    var isLoggedIn by delegate("isLoggenIn",false)
    var userAge by delegate("passWord",0)

}
object  Singleton{
    var i :Int = 3
    fun ig(){
    }

    object StaticInnerObject {

    }


}
fun oyy(){
    Singleton.i
    Singleton.ig()
    Room.o()
    var visit = Singleton.StaticInnerObject
}
class   Room{    //无论是age还是male，转java后都只能访问其Filed
    @JvmField var male = ""  //转java后不会有static
    companion object{
        @JvmField var age= 5    //转java后会有static
        @JvmStatic  var grade = ""
        @JvmStatic fun o(){

        }
    }
}
class Outer{

    inner class Inner{
        //这里是非静态内部类，，访问需要持有外部实例的引用
    }
    class StaticInner {
        //这里相当于是静态类内部类，在访问的时候可以直接通过类目加点
    }
}
fun visit(){
    var  inner = Outer().Inner()
    var staticInner = Outer.StaticInner()
}
fun     implements(){
    object:Runnable,Cloneable {
        override fun run() {
            TODO("Not yet implemented")//继承Runnable方法
        }
        override fun clone(): Any {
            return super.clone()    //继承Cloneable方法
        }
    }
    class LocalClass:Cloneable,Runnable{
        override fun run() {
            TODO("Not yet implemented")
        }
    }
    val runnableCloneable = object:Cloneable,Runnable{
        override fun run() {
            TODO("Not yet implemented")
        }
    }
}
data class Book(
    val id:Long,
    val name :String
){}
fun function8(){
    val book = Book(1L,"")
    val id = book.component1()
    val name = book.component2()
    val pair = "" to ""
    val c1 = pair.component1()
    val c2 = pair.component2()
}
/*fun main(){
    println("??/")
    val r = State.Index1
    println(r)
    val o = State.Index1.ordinal
    println(o)
    val all = State.entries
    println(all)
    val question = State.values().toMutableList()
    val oi = question[1]

    println(question)
    val state = State.Index1
    if (state<= State.Index2){
        println("Index1<=Index2")
    }
}*/


enum class State(val id:Int):Runnable{
    Index1(1){
        override fun run() {
            super.run()
        }
    },Index2(2);

    override fun run() {
        TODO("Not yet implemented")
    }
}
fun State.next():State{
    return State.values().let {
        val nextOrdinal = (ordinal+1)% it.size
        it[nextOrdinal]
    }
}
fun query(s:String):Any {
    val state = State.valueOf(s)
    return    when (state) {
            State.Index1 -> { "" }

            State.Index2 -> { 8 }
        }
}
enum class Color(){
    White,Black,Red,Blue,Yellow
}
fun quColor(){
    val colorRange = Color.White..Color.Black
    val color = Color.Red
    println(color in colorRange)
}
inline class BoxString (val value: String){

}

fun ca(){
    val box = BoxString("")      //编译优先转换成String类型
    val newBox = box.value * 200     //   使用String类型进行运算
    println(newBox)            //这里会转换成包装类型
}

data class UserData(val count: String,val password:String){

}


/*fun main(){
    val gson = Gson()
    val gNull = GsonBuilder().serializeNulls().create()    //创建一个包含Null的Gson；‘；
    val userData = UserData("An","asd791125")
    val serialization = gson.toJson(userData)     //把对象转换成序列化
    println(serialization)    //转换成序列化
    val json = """{"count":"An","password":"asd791125"}"""
    val Deserialization = gson.fromJson(json,UserData::class.java) //把json转换成对象
    println(Deserialization)
    //moshi的序列化和反序列化
    val  moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    val userD= UserData("Aiden","asd790831")
    val jsonAdapter = moshi.adapter(UserData::class.java)
    println(jsonAdapter.toJson(userD))   //moshi的序列化
    println(jsonAdapter.fromJson(json)) //moshi的反序列化

    val cGson = GsonBuilder() //创建一个GsonBuilder对象，用于自定义Gson
        .registerTypeAdapter(
            Date::class.java,     //这行告诉Gson，只有遇到Date的时候需要使用自定义序列化
            Serializers.DateSerializer
        )
        .registerTypeAdapter(
            Date::class.java,
            Serializers.DateDeserializer
        )
        .create()
    val cPersonDate = PersonDate("Aiden","female",Date())
    println(cGson.toJson(cPersonDate))    //自定义序列化
    println(cGson.fromJson("""{"name":"An","gender":"male","brithDay":"2024-08-15  22:45"}""",PersonDate::class.java))

    //kotlinX .serialization  的序列化和反序列化
    val count = Count("",5)
    println(Json.encodeToString(count))
    val dCount = """{"name":"","age":}"""
    println(Json.decodeFromString<Count>(dCount))
}*/
  //定义一个PersonData数据类，储存个人数据信息。birthDay信息是Date类
data class PersonDate(val name :String,val gender:String,val brithDay:Date)

//自定义序列化Date类型的数据
object  Serializers{
    //定义df常量，用于格式化日期
    private val df:DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
    object DateDeserializer:JsonDeserializer<Date>{    //采用单例模式，存放序列化和反序列化

        override fun deserialize(  // 复写反序列化
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): Date {
            return df.parse(json?.asString
                ?:throw Exception("Json element is null"))   //空类型安全，用elvis表达式，如果会空就throw Exception

        }
    }
    object DateSerializer: JsonSerializer<Date>{
        override fun serialize(       //复写序列化
            src: Date?,
            typeOfSrc: Type?,
            context: JsonSerializationContext?
        ): JsonElement {
            return JsonPrimitive(df.format(src))
        }
    }
}

@Serializable
data class Count (val name: String,val age :Int){

}

fun <T> type(a:T){
}
class Type<T>(){
}
fun <T:Comparable<T>>  compare(a:T,b:T):T{
    return if (a>b) a else b      //约束T实现ComparableT
}
//多个约束
fun <T> call (a:T,b:T)
    where T:Comparable<T>,T:() -> Unit{
        if (a>b) a()else b()
    }
fun <T,R> callMax(a:T,b:T):R   //传入泛型T和R   R还作为返回值
    where  T:Comparable<T>,T:()->R,  //实现Comparable 接口,并且必须是一个函数类型
           R:Number{      // R必须是Number的子类
               return if (a>b )a() else b()
           }
sealed class  List<out T>
class Box<out T> (val value:T){

}
fun useBox(box:Box<Number>){

}
fun iu(){
    val intBox = Box(1)     //进行类型推导intBox类型为Box<Int>
    useBox(intBox)     //会报错，需要的类型为Number，Int不可以。
}


interface Book1
interface FaceBook:Book1
class BookStore<out T:Book1>{
    fun getBook(): T{   //这里的T，就是协变点
        TODO()
    }
}
fun foundBook() {
    val fBook = BookStore<FaceBook>()
    val book1: BookStore<Book1> = fBook

    val book2 = book1.getBook()

}
open class Garbage
class DryGar: Garbage()
class DustBin<in T:Garbage>{
    fun putGar(t:T){

    }
}
fun findGarbage(){
    val dustBin = DustBin<Garbage>()   //逆变之后dustBin成为了子类
    val dryGar:DustBin<DryGar> = dustBin  //可以向下兼容
    //val d:DustBin<Garbage> =dryGar    //无法向上兼容

    val instanceG = Garbage()
    val instanceD = DryGar()

    dustBin.putGar(instanceG)   //子类可以替换所有的父类，所以没问题
    dustBin.putGar(instanceD)

    dryGar.putGar(instanceD)
    //dryGar.putGar(instanceG )   //父类的dryGar不能传递给子类，所以需要类型是DryGar().

}
inline    fun <reified T> t(t:T) {
        val jcalss = T::class.java   //调用的时候，T会变成实际的类
}

//利用泛型  模拟Scala中的Self Type
typealias OnConfirm = ()->Unit
typealias OnCancel = ()->Unit
private val EmptyFunction={}

open class Notification(
    val title:String,
    val content:String){
}
class ConfirmNotification(
    title: String,
    content:String,
    val onConfirm: OnConfirm,
    val onCancel: OnCancel
):Notification(title, content)

interface SelfType<Self>{
    val self:Self
        get() = this as Self
    
}
open  class NotificationBuilder<Self:NotificationBuilder<Self>>:SelfType<Self> {
    //这里把泛型Self的类型加以限制为NotificationBuilder<Self>，这样只能传入其子类，保证类型安全。
    protected var title: String = ""
    protected var content:String = ""
    fun title(title: String):Self{
        this.title = title
        return self
    }
    fun content(content: String):Self{
        this.content = content
        return self
    }
    open fun build() = Notification(this.title,this.content)
}
class ConfirmNotificationBuilder:NotificationBuilder<ConfirmNotificationBuilder>(){
    private var onConfirm:OnConfirm = EmptyFunction
    private var onCancel:OnCancel = EmptyFunction

    fun onConfirm(onConfirm: OnConfirm):ConfirmNotificationBuilder{
        this.onConfirm = onConfirm
        return this
    }
    fun onCancel(onCancel: OnCancel):ConfirmNotificationBuilder{
        this.onCancel = onCancel
        return this
    }

    override fun build(): ConfirmNotification {
        return  ConfirmNotification(title,content,onConfirm,onCancel)
    }


}
/*fun main() {
    ConfirmNotificationBuilder()
        .title("Hello")
        .onCancel {
            println("onCancel")
        }.content("World")
        .onConfirm {
            println("onConfirmed")
        }
        .build()
        .onConfirm()
}*/
object Models{
    //ConcurrentHashMap建立键值对，键是class::，值是AbsModel.
    private val modelMap = ConcurrentHashMap<Class<out AbsModel>,AbsModel>()
    //this.java是KClass<T>的扩展属性，用于于KClass关联的java Class对象
    fun AbsModel.register(){
        modelMap[this.javaClass]= this
    }
    //this.javaClass是任何kotlin对象的扩展方法，用于获取java Class对象
    fun <T:AbsModel> KClass<T>.get():T{
        return modelMap[this.java] as T
    }


}
/*
定义所有Model的基类，每个子类实现的时候，都会执行init中的注册功能.
ConcurrentHashMap 是一种适用于高并发环境的线程安全映射数据结构。
它通过分段锁定和无锁操作等机制，实现了在多线程访问时的高效性和一致性。
*/
abstract class AbsModel{
    init {
        Models.run {
            register()
        }
    }
}
class DatabaseModel:AbsModel(){
    fun query(sql:String):Int=0
}
class NetworkModel:AbsModel(){
    fun get(url:String):String="""{"code":0}"""
}
/*
ReadOnlyProperty是用于val，只读属性。
<R,T>需要传入两个参数，第一个参数是持有者，第二个参数是输出类型。
*/
class  ModelDelegate<T:AbsModel>(val Kclass:KClass<T>):ReadOnlyProperty<Any,T>{
    //KClass是kotlin中的class，和java中的class相比，作用域不同。
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return Models.run { Kclass.get() }
    }
}
//初始化model
fun initModels(){
    DatabaseModel()
    NetworkModel()

}
/*
简化拿到类型反射。
使用内联特化防止类型擦除。
*/
inline fun <reified T:AbsModel> modelOf():ModelDelegate<T>{
    return ModelDelegate(T::class)
}
class MainViewModel{
    val databaseModel by modelOf<DatabaseModel>()
    val networkModel by modelOf<NetworkModel>()
}

/*fun main(){
    initModels()
    val mainActivity = MainViewModel()
    *//*这里的let(::println),相当于
    mainActivity.databaseModel.query("").let({
        println(it)
    })*//*
    mainActivity.databaseModel.query("select * from mysql.user").let(::println)
    mainActivity.networkModel.get("https://wwww...").let(::println)

    //获取的是KClass
    val cls:KClass<String> = String::class
    //如果要换成java的class
    cls.java.kotlin
    //获得Type类型
    typeOf<Map<String,Int>>()

    String::class.declaredFunctions
}*/
interface Api{
    fun getUsers():List<UserData>
}
abstract class SuperT<T>{
    //获取子类
    val typeParameter by lazy {
        this::class.supertypes.flatMap {
            it.arguments.mapNotNull {
                it.type?.classifier as? KClass<*>
            }.filter {
                it == String::class
            }
        }
    }

    //用javaClass获取子类的类型
    val typeParameterJava by lazy {
        this.javaClass.genericSuperclass.safeAs<ParameterizedType>()!!
            .actualTypeArguments.first()
    }
}
class SubType:SuperT<String>()

/*fun main(){
    *//*
    先拿到反射的类，再拿到函数，筛选出为"getUsers"
    因为可能有多个"getUsers"，所以存入map，再拿到函数里面的参数，打印出来
    *//*
    Api::class.declaredFunctions.filter { it.name =="getUsers" }
        .map {it.returnType.arguments.forEach(::println) }
    //用类直接反射到函数后拿到函数里的参数。
    println(Api::getUsers.returnType.arguments.forEach (::println))
    //用java的反射，拿到函数里面的参数。
    Api::class.java.getDeclaredMethod("getUsers")
        .genericReturnType.safeAs<ParameterizedType>()?.
        actualTypeArguments?.forEach(::println)

    val subT = SubType()
    subT.typeParameter.let (::println )
}*/
inline fun <reified T>Any.safeAs():T? {
    return this as? T
}


fun <T : Any> T.deepCopy(): T {
    // 如果类不是数据类，则直接返回原对象，因为只有数据类才需要进行深复制。
    if(!this::class.isData){
        return this
    }

    // 获取数据类的主构造函数并通过反射生成一个新的实例。
    return this::class.primaryConstructor!!.let { primaryConstructor ->
        // 遍历主构造函数的参数列表，为每个参数获取对应的属性值。
        primaryConstructor.parameters.map { parameter ->
            // 通过参数名称找到对应的属性，并获取其值。
            val value = (this::class as KClass<T> ).memberProperties.first { it.name == parameter.name }
                .get(this)

            // 如果参数的类型也是一个数据类，那么递归调用 deepCopy 进行深复制。
            if((parameter.type.classifier as? KClass<*>)?.isData == true){
                // 创建一个新的键值对，键是参数，值是深复制后的值。
                parameter to value?.deepCopy()
            } else {
                // 如果参数类型不是数据类，则直接使用原始值。
                parameter to value
            }
        }
            // 将参数和值映射成键值对（Map），并传递给主构造函数生成新的实例。
            .toMap()
            .let(primaryConstructor::callBy)
    }
}
data class Person(val id: Int, val name: String, val group: Group)

data class Group(val id: Int, val name: String, val location: String)

/*fun main() {
    val person = Person(
        0,
        "AidenAn",
        Group(
            0,
            "Kotliner.cn",
            "China"
        )
    )

    val copiedPerson = person.copy() // 浅复制，只复制顶层对象，不复制内部对象。
    val deepCopiedPerson = person.deepCopy() // 深复制，复制顶层对象及其内部对象。

    println(person === copiedPerson) // false：浅复制的对象与原对象不同。
    println(person === deepCopiedPerson) // false：深复制的对象与原对象不同。

    println(person.group === copiedPerson.group) // true：浅复制的对象内部引用仍然指向同一个对象。
    println(person.group === deepCopiedPerson.group) // false：深复制的对象内部引用指向不同的对象。

    println(deepCopiedPerson) // 打印深复制后的对象内容。
}*/


data class  UserVO(val login:String,val avatarUrl:String)


data class UserDTO(
    val id:Int,
    val login: String,
    val avatarUrl: String, 
    val url: String,
    val htmlURL: String
)


inline fun <reified T:Any>Map<String,Any?>.mapAs():T{
    return T::class.primaryConstructor!!.let{
        //拿到T类型中的所有属性
        it.parameters.map {paramenter->
            //检查属性的值是否在Map中存在，不存在返回空。如果有不可空类型又为空就报错
            paramenter to (this[paramenter.name]?:if (paramenter.type.isMarkedNullable) null
            else throw  IllegalArgumentException("${paramenter.name}is required but missing.")       )
        }.toMap()
            .let (it::callBy)
    }
}
inline  fun <reified T:Any,reified To:Any> T.mapAS():To{
    return T::class.memberProperties.map { it.name to it.get(this) }
        .toMap().mapAs()

}


fun main(){
    val userDTO = UserDTO(
        0,
        "Bennyhuo",
        "https://avatars2.githubusercontent.com/u/30511713?v=4",
        "https://api.github.com/users/bennyhuo",
        "https://github.com/bennyhuo"

    )
    //隐式推导mapAS<UserDTO,UserVO>
    val userVO:UserVO = userDTO.mapAS()
    println(userVO)

    val userMap = mapOf(
        "id" to 0,
        "login" to "AidenAn",
        "avatarUrl" to "https://api.github.com/users/bennyhuo",
        "url" to "https://api.github.com/users/bennyhuo"
    )
    val userVoFromMap:UserVO= userMap.mapAs()
    println(userVoFromMap)


}










