import ExampleModule.Hello.*

fun main(args: Array<String>) {
    HelloModule()
    println(square(10))
    println(triple(3))

    var (x, y) = solve_quadratic_equation(1.0, 10.0, 16.0)
    println("$x, $y")

    InfixFunctionExample()

    LambdaExample()

    ExtensionFuncExample()
}

fun square(x:Int) : Int
{
    return x * x
}

fun triple(x:Int) = x*3

fun solve_quadratic_equation(a:Double, b:Double, c:Double) : Pair<Double, Double>
{
    fun calc_discriminant(a:Double, b:Double, c:Double = 5.0): Double
    // fun calc_discriminant(): Double // 인자를 생략해도 내부적으로 캡쳐되어 사용 가능
    {
        return b*b-4*a*c
    }

    var root_disc = Math.sqrt(calc_discriminant(a,b,c))

    return Pair((-b+root_disc)/(2*a),(-b-root_disc)/(2*a))
}

fun InfixFunctionExample()
{
    val alphabat = 'z' downTo 'a' // downTo is Infix Function
    println(alphabat.toList())
}

fun LambdaExample()
{
    var product:(Int, Int) -> Int = {x, y -> x*y}

    var result = product(2, 3)

    println(result)
}

fun <T> ArrayList<T>.swap(index1:Int, index2:Int)
{
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}

fun ExtensionFuncExample()
{
    var MyIntFunc = fun Int.(value:Int) = this + value
    var x = 1

    print(x.MyIntFunc(10)) // Int 객체에 MyIntFunc가 포함된 것처럼 사용 가능하다.

    val myList = arrayListOf(1, 2, 3)
    myList.swap(0, 2) // arrayList에 swap을 추가하여 사용 가능
}