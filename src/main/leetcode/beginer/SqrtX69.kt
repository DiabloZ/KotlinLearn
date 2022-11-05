package main.leetcode.beginer

object SqrtX69 {
    fun mySqrt(x: Int): Int {
        if (x < 2) return x

        var left = 2
        var right = x / 2

        while (left <= right){
            val midd = left + (right - left) / 2
            val squaredMidd = midd.toLong() * midd.toLong()

            when{
                squaredMidd > x -> {
                    right = midd - 1
                }
                squaredMidd < x -> {
                    left = midd + 1
                }
                else -> {
                    return midd
                }
            }
        }

        return right
    }
}