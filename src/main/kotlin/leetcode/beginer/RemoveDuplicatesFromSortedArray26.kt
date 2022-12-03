package leetcode.beginer

class RemoveDuplicatesFromSortedArray26 {
    fun removeDuplicates(nums: IntArray = intArrayOf(0,0,1,1,1,2,2,3,3,4)): Int {
        if (nums.isEmpty()) return 0
        var preValue:Int = nums.first()
        var index = 0
        for (i in 1 until nums.size){
            if (nums[i] == preValue){
                nums[i] = 0
            } else {
                nums[++index] = nums[i]
                preValue = nums[i]
            }
        }
        return ++index
    }
}