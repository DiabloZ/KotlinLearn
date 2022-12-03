package leetcode.beginer

class RomanToInteger13 {
    private val mapRoman:Map<Char, Int> = mapOf(
        Pair('I', 1),
        Pair('V', 5),
        Pair('X', 10),
        Pair('L', 50),
        Pair('C', 100),
        Pair('D', 500),
        Pair('M', 1000),
    )

    private val mapRomanWithMinus: Map<String, Int> = mapOf(
        Pair("IV", 4),
        Pair("IX", 9),
        Pair("XL", 40),
        Pair("XC", 90),
        Pair("CD", 400),
        Pair("CM", 900),
    )
    private val mapOtherRomanWithMinus: Map<String, Int> = mapOf(
        Pair("IV", -1),
        Pair("IX", -1),
        Pair("XL", -10),
        Pair("XC", -10),
        Pair("CD", -100),
        Pair("CM", -100),
    )

    fun calculate(input: String){
        var value = 0
        val lastIndex = input.lastIndex
        input.forEachIndexed { index, romanChar ->
            val calculated = handleValue(index, lastIndex, input)
            value += calculated
        }
        println(value)
    }

    private fun handleValue(index: Int, lastIndex: Int, listChar: String): Int = when {
        listChar.get(index) == null -> 0
        index == lastIndex -> mapRoman[listChar[index]] ?: 0
        index != lastIndex -> handleNotLastValue(index, listChar)
        else -> 0
    }

    private fun handleNotLastValue(index: Int, listChar: String):Int{
        val nextIndex = index + 1
        val charPair = "${listChar[index]}${listChar[nextIndex]}"
        return mapOtherRomanWithMinus[charPair] ?: mapRoman[listChar[index]] ?: 0
    }



}