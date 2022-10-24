package main.leetcode.beginer

class TwoSum1 {
    fun calculate(nums: IntArray, target: Int):IntArray {
        val array:MutableList<Int> = mutableListOf()
        nums.forEachIndexed { numIndex, value ->
            nums.forEachIndexed { otherIndex, otherValue ->
                if (otherIndex != numIndex && (value + otherValue) == target) {
                    array.add(numIndex)
                }
            }
        }
        println(array)
        return array.toIntArray()
    }
    //less memory and less fast
    /*fun calculate(nums: IntArray, target: Int):IntArray { //
        val array = arrayListOf<Int>()
        nums.forEachIndexed { numIndex, value ->
            nums.forEachIndexed { otherIndex, otherValue ->
                if (otherIndex != numIndex && (value + otherValue) == target) {
                    array.add(numIndex)
                }
            }
        }
        println(array)
        return array.toIntArray()
    }*/
}