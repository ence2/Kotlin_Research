fun main(args: Array<String>) {
    nullability()
    when_exp()
}

fun nullability()
{
    var y:String? = null // nullable string

    // println(y.length) // error
    println(y?.length) // OK

    println(y!!.length) // OK but 런타임 에러
}

fun when_exp()
{
    var code = 44

    when (code)
    {
        44 -> println("UK")
        46 -> println("sweden")
        39, 379 -> println("vatican")
        in 1..999 -> println("i don't know")
        else ->
        {
            println("error")
        }
    }

    var z:Any = "Foo"

    var output = when (z) 
    {
        is Int -> print("this is int type") // is 키워드로 타입 검사 가능
        is String -> println("this is string type")
        else -> println("other type")
    }
    
    println(output) // 변수에 담아 출력도 OK
}

