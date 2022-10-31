package main.leetcode.beginer

object LengthOfLastWord {
    fun lengthOfLastWord(s: String): Int {
        return s.trim().substringAfterLast(" ").length
    }
}