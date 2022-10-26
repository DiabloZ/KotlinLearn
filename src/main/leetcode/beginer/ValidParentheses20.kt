package main.leetcode.beginer

class ValidParentheses20 {

    //private val defaultParentheses = "()[]{}"
    private val defaultParenthesesMeme = "(([]){})"
    //private val defaultParenthesesMemea = "(([]]{})"
    //private val validePair = listOf("()", "[]", "{}")
    //private val valideReversePair = listOf(")(", "][", "}{")

    fun isValid(s: String = defaultParenthesesMeme): Boolean {
        if (s.length % 2 != 0) return false
        val case1 = "()"
        val case2 = "[]"
        val case3 = "{}"
        val voidString = ""

        var stringForHandle = s
        var resultAfterHandle = 0
        while (resultAfterHandle != stringForHandle.length){
            resultAfterHandle = stringForHandle.length
            stringForHandle = stringForHandle
                .replace(case1, voidString)
                .replace(case2, voidString)
                .replace(case3, voidString)

        }
        return stringForHandle.isEmpty()
    }


/*    fun handleChars(s: String): Boolean {
        var char:Char? = ' '
        var count = 0
        while (char != null){
            char = s.getOrNull(count)
            val otherChar = s.getOrNull(count + 1) ?: return false
            if (!validePair.contains("$char$otherChar")) return false
            count += 2
        }
        return true
    }*/



/*    fun isValid(s: String = "(([]){})"): Boolean {
        if (s.length % 2 != 0) return false
        val reverseString = s.reversed()
        var isNested = false
        var char:Char? = ' '
        var count = 0
        while (char != null){
            char = s.getOrNull(count) ?: break
            val otherChar = s.getOrNull(count + 1) ?: return false
            if (!validePair.contains("$char$otherChar")){
                val reversedChar = reverseString.getOrNull(count)
                if (!validePair.contains("$char$reversedChar")){
                    return false
                } else {
                    isNested = true
                }
            }
            if (isNested) count++ else count += 2
            if (isNested && s.length / 2 <= count){
                break
            }
        }
        return true
    }*/

    /*fun isValid(s: String = "({})"): Boolean {
        if (s.length % 2 != 0) return false
        var char:Char? = ' '
        var count = 0
        while (char != null){
            char = s.getOrNull(count) ?: break
            val otherChar = s.getOrNull(count + 1) ?: return false
            if (!validePair.contains("$char$otherChar")){
                if (!tryLookForSameParentheses(s, char, count)) {
                    return false
                }
            }
            count += 2
        }
        return true
    }

    private fun tryLookForSameParentheses(s: String, char: Char, count: Int): Boolean{
        val reverseString = s.reversed()
        return validePair.contains("$char${reverseString[count]}") || valideReversePair.contains("$char${reverseString[count]}")
    }*/

/*    fun isValid(s: String): Boolean {
        var char:Char? = ' '
        var count = 0
        while (char != null){
            char = s.getOrNull(count)
            val otherChar = s.getOrNull(count + 1) ?: return false
            if (!validePair.contains("$char$otherChar")) return false
            count += 2
        }
        return true
    }*/
}