import java.util.*

fun main(args: Array<String>) {
    ExampleSequence()
    QuantifiersExample()
    ProjectionExample()
    AggregationExample()
    FilteringExample()
    PartioningExample()
    GroupingExample()
    SortingExample()
    ElementOperationExample()
    SetOperationExample()
}

fun ExampleSequence()
{
    var gen = generateSequence(1, {
        println(it)
        it + 1
    })

    var numbers = gen.take(10) // 이 타이밍에서는 실제 값이 생성되지 않음
    numbers.toList() // 이 타이밍에 값 생성
}

fun QuantifiersExample()
{
    val numbers: Sequence<Int> = arrayOf(1, 2, 3, 4, 5).asSequence() // 시퀀스로 생성

    println("Are all numbers > 0 ? ${numbers.all{it > 0}}") // true

    println("Any numbers odd ? ${numbers.any{it and 1 == 0}}") // true

    println("Contains 6 ? ${numbers.contains(6)}") // false

    println("Total number of elements > 3 : ${numbers.count{ it > 3 }}") // 2
}

fun ProjectionExample()
{
    //////////// map 예제
    val seq: Sequence<Int> = generateSequence(1, {it+1})
    val numbers: Sequence<Int> = seq.take(4)

    var squares : Sequence<Int> = numbers.map { it*it }
    println(squares.toList());

    val sentence = "This is a nice sentence"
    val wordLengths = sentence.split(' ').map{ it.length }.asSequence()
    println(wordLengths.toList());
    ///////////////////////////////////////////////////////////////////////////////////

    ////////////////////// associate 예제
    val worldWithLength = sentence.split(' ').map{
        object {
            val length = it.length
            val word = it
        }
    }

    for (wl in worldWithLength)
    {
        println("${wl.word} / ${wl.length}")
    }

    val worldLengthPairs = sentence.split(' ').associate { it.to(it.length) }
    for(wl in worldLengthPairs)
        println(wl)
    ///////////////////////////////////////////////////////////////////////////////////

    ////////////////// flatMap 예제
    val sequence = listOf("red,green,blue", "orange", "white, pink")
    val allWords = sequence.map{ it.split(',')}
    println(allWords.toList()) // output : [[red, green, blue], [orange], [white, pink]]

    val objects = arrayOf("house", "car", "bicycle")
    val colors = arrayOf("red", "green", "blue")

    val pairs = objects.flatMap { o -> colors.map { c -> "$c $o" } }
    println(pairs.toList()) // 카티션 프로덕트
}

fun AggregationExample()
{
    val numbers = generateSequence(1, { it + 1 })
        .take(10).toList()
    println(numbers)

    println(numbers.joinToString("->"))

    // reduce : x[0] + x[1]
    // reduceRight : x[last] + x[last - 1]
    println("sum (reduce) = ${numbers.reduce{x,y -> x + y}}") // reduce : numbers에서 2개씩 pair 하게 꺼내어 처리
    println("product (reduce) = ${numbers.reduceRight{
            x,y ->
        println("($x * $y)")
        x * y
    }}")

    println("sum = ${numbers.sum()}, average = ${numbers.average()}")

    println("sum of squares = ${numbers.sumBy { it * it }}")
    println("sum of roots = ${numbers.sumByDouble { Math.sqrt(it.toDouble()) }}")

    // fold : seed + x[0]
    println("sum (fold) = ${numbers.fold(0, {x,y  -> x+y})}")
    println("product (fold) = ${numbers.fold(1, {x,y -> x*y})}")
}

fun FilteringExample()
{
    val seq = generateSequence(1, {it+1})
    val numbers = seq.take(10).toList()
    println(numbers)

    var evenNumbers = numbers.filter{ it % 2 == 0 }
    println(evenNumbers)

    val notDivBy3 = numbers.filterNot { it % 3 == 0 }
    println(notDivBy3)

    val oddSquares = numbers.map { it*it }.filterNot { it % 2 == 0 }
    println(oddSquares)

    val value = arrayOf<Any>(1, 2.5, 3, 4.56)
    val intNumbers = value.filter{ it is Int }
    println(intNumbers)
}

fun PartioningExample()
{
    var seq = arrayOf(-2, -1, 0, 1, 2)
    var (neg, others) = seq.partition { it < 0 }
    println(neg)
    println(others)

    var numbers = arrayOf(3,3,2,2,1,1,2,2,3,3)
    println(numbers.drop(2).take(6)) // drop은 skip 기능

    println(numbers.takeWhile { it > 1 })
    println(numbers.dropWhile { it == 3 }) // 3이 아닌것이 나올 때 까지 무시

    println(numbers.dropLast(4))
}

data class Man(var name:String, var age:Int)

fun GroupingExample()
{
    val people = listOf(
        Man("Adam", 36),
        Man("Boris", 18),
        Man("Claire", 2),
        Man("Adam", 20),
        Man("Jack", 20)
    )

    val byName: Map<String, List<Man>> = people.groupBy(Man::name)
    println(byName)

    val byAge: Map<Int, List<Man>> = people.groupBy(Man::age)
    println(byAge)
}

fun SortingExample()
{
    val rand = Random()
    val randomValues = generateSequence { rand.nextInt(10 - 5) }
        .take(10).toList()

    println(randomValues)
    println(randomValues.sorted())

    val people = listOf(
        Man("Adam", 36),
        Man("Boris", 18),
        Man("Claire", 2),
        Man("Adam", 20),
        Man("Jack", 20)
    )

    println(people)
    println(people.sortedBy { it.name })
    println(people.sortedWith(compareBy(Man::age, Man::name)))
    println(people.sortedWith(compareBy({it.name}, {it.age})))
    println(people.sortedWith(compareBy<Man>{it.age}.thenByDescending { it.name }))
}

fun ElementOperationExample()
{
    val numbers = listOf(1, 2, 3)
    println("first element is ${numbers.first()}")
    println("first element >10 ${numbers.first{it>10}}") // 크래시 발생
    println("first element >10 ${numbers.firstOrNull(){it>10}}") // OK

    println(numbers.last())
    println(numbers.last{it<3})

    val x = listOf(1)
    println(x.single())
    println(numbers.single())

    println(numbers.singleOrNull())

    println("element at pos : ${numbers.elementAtOrNull(4)}")
    println("element at pos 100 : ${numbers.elementAtOrElse(100, {-1})}") // 없으면 특정값 전달
}

fun SetOperationExample()
{
    val word1 = "helloooo".toCharArray().toList()
    val word2 = "help!".toCharArray().toList()

    println(word1.distinct()) // 중복값 제거
    println(word1.intersect(word2)) // 교집합
    println(word1.union(word2)) // 합집합
    println(word1.subtract(word2)) // 차집합

    val people = listOf(
        Man("Adam", 36),
        Man("Boris", 18),
        Man("Claire", 2),
        Man("Adam", 20),
        Man("Jack", 20)
    )

    println("distinct by name " + people.distinctBy { it.name })
}