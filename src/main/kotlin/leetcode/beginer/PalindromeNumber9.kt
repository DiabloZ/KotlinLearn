package leetcode.beginer

class PalindromeNumber9 {
    fun isPalindrome(x: Int = 1000021): Boolean {
        val value = x.toString()
        val reversed = value.reversed()
        val size = value.length / 2
        val first = value.subSequence(0, size)
        val last = reversed.subSequence(0, size)
        return first == last
    }
}