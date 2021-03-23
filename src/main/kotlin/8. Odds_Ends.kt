package oddSamples

import java.lang.NumberFormatException

typealias FloatSet = Set<Float>

typealias MapWithStringKeys<T> = Map<String, T>

typealias Predicate<T> = (T) -> Boolean

fun <T> where(items:Sequence<T>, p:Predicate<T>) : Sequence<T> { return items.filter { x -> p(x) } }
typealias PropertyChangedHandler = (Object, String) -> Unit
class Bike {
    inner class Wheel {

    }
}
typealias BikeWheel = Bike.Wheel

/////////////////////////////////////////////////////////////////

enum class Direction { North, South, East, West }
enum class Color(val rgb:Int){
    Red(0xff0000) {
        override fun example(): String {
            return "blood"
        }
    },
    Green(0x00ff00) {
        override fun example(): String {
            return "grass"
        }
    },
    Blue(0x00ff00) {
        override fun example(): String {
            return "Sky"
        }
    };

    abstract fun example():String
}

/////////////////////////////////////////////////////////////////

data class Vector(var x:Int, var y:Int) {
    operator fun plus(other:Vector) : Vector {
        return Vector(x + other.x, y + other.y)
    }
}

/////////////////////////////////////////////////////////////////

fun main(args: Array<String>)
{
    var f:FloatSet = setOf(1.2f, 2.3f)

    var m:MapWithStringKeys<Double> = mapOf("hello".to(2.3))
    println(m) // -> {hello=2.3}

    /////////////////////////////////////////////////////////////

    var dir = Direction.East;
    println(dir)

    var b = Color.Blue
    println("b has the name ${b.name}, value = ${b.rgb}, pos = ${b.ordinal}") // -> b has the name Blue, value = 255, pos = 2

    println("$b is the color of ${b.example()}") // -> Blue is the color of Sky

    for (c in Color.values())
        println(c)

    println("value of red is ${Color.valueOf("Red").rgb}") // -> value of red is 16711680

    /////////////////////////////////////////////////////////////////

    val v = arrayOf(1, 2, 3)

    try
    {
        println(v[10])
    }
    catch (e:ArrayIndexOutOfBoundsException)
    {
        println(e.toString())
    }
    finally
    {
        println("반드시 실행되는 구간")
    }

    // try 구문을 변수에 담아서 사용 가능하다.
    val text = "123"
    val number:Int? = try {text.toInt()} catch (e:NumberFormatException) { null }
    println(number)

    /////////////////////////////////////////////////////////////////

    var v1 = Vector(3,4)
    var v2 = Vector(11, 3)
    println(v1 + v2)
}