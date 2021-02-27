import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class Person
{
    lateinit var name:String // lateinit을 붙이면 객체 생성후 초기화 가능
    var age:Int = 0

    val canVote:Boolean
        get() = age >= 16 // Custom getter

    var ssn = "0000"
        get() = field // field는 해당 멤버 변수를 의미함
        set(value) {
            println("SSN is changed")
            field = value
        }
}

class ConstructorAndInitializationExample(var name:String, var age:Int)
{
    init {
        println("this is init")
    }
}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String{
        return "$thisRef, thank you for delegating, ${property.name} to me"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String): String{
        return "$value, has been assigend to ${property.name} in $thisRef"
    }
}

class NicePerson
{
    var name: String by Delegate()

    val lazyValue:String by lazy {
        println("init!!!")
        "this Msg Will Inserted"
    }

    var otherName: String by Delegates.observable("king"){
        prop, old, new -> println("$old -> $new")
    }
}

data class Human(var name:String, val age:Int) { }

open class A(var name:String)
{
    open fun hello()
    {
        print("A hello")
    }
}

class B(name:String) : A(name)
{
    override fun hello() {
        super.hello()
        println("B hello")
    }
}

fun main(args: Array<String>) {
    var a = A("A")
    a.hello()
    var b = B("B")
    b.hello()
    a = B("B2")
    a.hello()

    var h = Human("man", 123)
    println(h)

    var h2 = Human("man", 123)
    println(h == h2) // true

    var h3 = h.copy()
    println(h == h3) // false

    var np = NicePerson()
    np.name = "kkman"
    var s = np.name
    println(s)
    println(np.lazyValue)
    println(np.lazyValue)
    println(np.lazyValue)
    np.otherName = "kk222"
    np.otherName = "hoho"


    var me = Person()
    me.name = "verking"
    me.age = 20

    println(me.canVote);

    me.ssn = "11232323"

    var obj = ConstructorAndInitializationExample("verking", 1010)
    println(obj.name)
    println(obj.age)
}

