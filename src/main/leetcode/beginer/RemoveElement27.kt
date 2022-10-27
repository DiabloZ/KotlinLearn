package main.leetcode.beginer

class RemoveElement27 {
    val arrTest = intArrayOf(2,2,3)
    val arrTest2 = intArrayOf(2,0,1,2,2,3,0,4,2)
    fun removeElement(nums: IntArray = arrTest2, `val`: Int = 2): Int {
        var count = 0
        nums.forEach {
            if (it != `val`) nums[count++] = it
        }

        return count
    }
}

/*
fun removeElementFirst(nums: IntArray = arrTest, `val`: Int = 2): Int {
    if (nums.isEmpty()) return 0
    if (nums.all { it == `val` }) return 0
    var indexAbowe = if (nums.first() == `val`) 1 else 0
    nums.forEachIndexed{ index, num ->
        if (num != `val`){
            if (indexAbowe != 0) {
                nums[indexAbowe] = nums[index]
            }
            ++indexAbowe
        }
        if (num == `val` && indexAbowe != 0 && index == 0){
            if (nums[indexAbowe] == `val`){
                indexAbowe = nums.indexOfFirst { it != `val` } ?: indexAbowe
                nums[index] = nums[indexAbowe]
                --indexAbowe
            } else {
                nums[index] = nums[indexAbowe]
            }
            --indexAbowe
        }
    }

    return indexAbowe

}*/
