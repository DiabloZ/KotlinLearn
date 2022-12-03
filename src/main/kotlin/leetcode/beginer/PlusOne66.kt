package leetcode.beginer

import java.util.LinkedList

object PlusOne66 {
    fun plusOne(digits: IntArray): IntArray {
        var carry = 1
        val sumArray = LinkedList<Int>()
        for (index in digits.size - 1 downTo 0 ){
            val sum = digits[index] + carry
            carry = if (sum > 9) 1 else 0
            sumArray.add(0, sum % 10)
        }
        if (carry != 0) sumArray.add(0, carry)
        return sumArray.toIntArray()
    }

}