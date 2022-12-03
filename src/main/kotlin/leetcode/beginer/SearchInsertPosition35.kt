package leetcode.beginer

object SearchInsertPosition35 {
    fun searchInsert(nums: IntArray, target: Int): Int {
        var previousPosition = 0
        nums.forEachIndexed { index, integer ->
            if (integer == target) return index
            if (integer > target) return index
            previousPosition = index
        }

        return previousPosition + 1
    }
}