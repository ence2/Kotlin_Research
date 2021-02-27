fun main(args: Array<String>) {
    var_declarations()
    ranges()
    arrays()
    character_and_string()
}

fun var_declarations()
{
    val a:Int = 64 // val 불변(상수)
    //a = 10 // val은 상수 선언으로 변경 불가, error
    var b:Long = 123
    b = 14444 // OK

    var c:Float = 2.3f // var는 변수 선언을 의미
    var d:Double = 12.3e5

    println("$a, $b, $c, $d")

    val f:StringBuffer = StringBuffer("Hello~")
    f.replace(0, 1, "P")

    println(f)

    var g = 100 // 타입 생략 가능(type deduction)
}

fun ranges()
{
    val a: IntRange = 1..10 // 1, 2, 3, ..... , 10
    var b = 1.rangeTo(10) // 같은 표현 type deduction
    var c: IntProgression = 1.rangeTo(10).reversed() // sequence로 생성, 지연된 연산을 수행

    for (x in a) println(x)
    for (x in b) println(x)
    for (x in c) println(x) // c의 경우 이 시점에서 실제 IntRange가 생성됨

    var d = 10 downTo 1
    for (x in d) println(x)

    var e = 100 downTo 1 step 3
    for (x in e) println(x) // 3씩 건너 출력
}

fun arrays()
{
    var names: Array<String> = arrayOf("김", "나", "박", "이")
    names[0] = "송"
    println(names.toList())

    var ages = intArrayOf(44, 88, 11)
    println(ages.toList())

    var values = Array<Double>(10, {2.0})
    println(values.toList()) // 2.0을 10개 출력

    var squares = Array(10, {(it*it).toString()})
    println(squares.toList()) // 어떻게 나올까?
}

fun character_and_string()
{
    var a:Char = '\u0041'
    if (a.toInt() == 65)
        println("match")

    val b:String = "Hello"
    println(b)
    println(b[0])
    for (letter in b) println(letter)

    var raw = """hello
    "test"
    world~~~""" // """ 내용 """ 형태로 입력 할 경우 있는 그대로 대입 가능
    println(raw)

    var c = 123.0
    var d = "c = $c, price = ${c/10}" // $변수, ${표현식}으로 string formating 가능
    println(d)

    val e = "${(10 downTo 1).toList().map{it.toString().toCharArray()}.joinToString()}"
    println(e) // ?
}


