package leetcode.beginer

class LongestCommonPrefix14 {
    private val arr = arrayOf("flower","flow","flight")
    fun longestCommonPrefix(strs: Array<String> = arr): String {
        var sameString = ""
        strs.first().forEach { char ->
            val isThis = strs.all { it.startsWith(sameString+char) }
            if (!isThis) return sameString
            sameString += char.toString()
        }
        return sameString
    }
}