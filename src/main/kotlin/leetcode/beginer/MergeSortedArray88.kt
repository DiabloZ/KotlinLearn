package leetcode.beginer

val arrayForCheckMergeSortedArray88 = listOf(
    MSortedArray(intArrayOf(1,2,3,3,0,0,0,0), 4, intArrayOf(2,5,5,6), 4), //[1, 2, 2, 3, 3, 5, 5, 6]
    MSortedArray(intArrayOf(1,2,3,0,0,0), 3, intArrayOf(2,5,6), 3), //[1, 2, 2, 3, 5, 6]
    MSortedArray(intArrayOf(1), 1, intArrayOf(), 0), //[1]
    MSortedArray(intArrayOf(0), 0, intArrayOf(1), 1), //[1]
    MSortedArray(intArrayOf(4,5,6,0,0,0), 3, intArrayOf(1,2,3), 3), //[1,2,3,4,5,6]
    MSortedArray(intArrayOf(-1,0,0,3,3,3,0,0,0), 6, intArrayOf(1,2,2), 3), //[-1,1,2,2,2,3,2,3,3]
)

object MergeSortedArray88 {

    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        (nums1.copyOfRange(0, m) + nums2.copyOfRange(0, n)).apply {
            sort()
            forEachIndexed { index, i ->
                nums1[index] = i
            }
        }

        println(nums1.toList().toString())
    }

}

class MSortedArray(
    val nums1: IntArray,
    val m: Int,
    val nums2: IntArray,
    val n:Int
)

//first
/*
fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
    var carry: Int? = null
    val zero = 0
    var i1 = zero
    var i2 = zero


    while (carry != null || i1 != nums1.size || i2 != nums2.size){
        val num1 =  nums1.getOrNull(i1) ?: zero
        val num2 =  nums2.getOrNull(i2) ?: zero
        when{

            carry != null -> {
                when{
                    carry < num1 -> {
                        nums1[i1] = carry
                        carry = num1
                    }
                    num1 == zero -> {
                        nums1[i1] = carry
                        carry = null
                    }
                }
            }

            num1 == 0 && num2 != 0 -> {
                nums1[i1] = num2
                ++i2
            }

            num1 > num2 && num2 != 0 -> {
                carry = num1
                nums1[i1] = num2
                ++i2
            }

            num1 < num2 -> {
                carry = num2
                ++i2
            }

            num1 == num2 -> {
                carry = num1
            }

            num1 == 0 && num2 == 0 -> {

            }
        }
        ++i1
    }
    println(nums1.toList().toString())
}*/
//second
/*fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
    var carry: Int? = null
    val q:Queue<Int> = LinkedList<Int>()
    val zero = 0
    var i1 = zero
    var i2 = zero
    val test = (m + n) / Math.max(m, n)

    while (carry != null || i1 != nums1.size || i2 != nums2.size){
        val num1 =  nums1.getOrNull(i1) ?: zero
        val num2 =  nums2.getOrNull(i2) ?: zero
        when{

            q.isNotEmpty() && (q.peek() < num1 || num1 == zero) -> {
                when{
                    q.peek() < num1 -> {
                        nums1[i1] = q.poll()
                        q.add(num1)
                        i1 -= test
                    }
                    num1 == zero -> {
                        nums1[i1] = q.poll()
                        i1 -= test
                    }
                }
            }

            num1 == 0 && num2 != 0 -> {
                nums1[i1] = num2
                ++i2
            }

            num1 > num2 && num2 != 0 -> {
                q.add(num1)
                nums1[i1] = num2
                ++i2
            }

            num1 < num2 -> {
                q.add(num2)
                ++i2
            }

            num1 == num2 -> {
                q.add(num1)
            }

        }
        ++i1
    }
    println(nums1.toList().toString())
}*/
//third
/*fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
    (nums1.copyOfRange(0, m) + nums2.copyOfRange(0, n)).apply {
        sort()
        forEachIndexed { index, i ->
            nums1[index] = i
        }
    }

    println(nums1.toList().toString())
}*/
