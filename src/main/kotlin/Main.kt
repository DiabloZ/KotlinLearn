package main.kotlin

import main.leetcode.beginer.*

fun main (args: Array<String>){
    /*a_1_1_ElvisNull()
    a_2_1_2_FunVariableInClass()
    a_2_3_1__2_3_4_ExampleWhen()
    a_2_3_4__2_3_7_OverlappingCheckAndTypeCast()
    a_2_4_2_SequenceIterations()
    a_2_4_3_IteratingOverElementsDictionaries()
    a_2_4_4_InForCheckEntriesInCollections()
    a_2_5_1_TryCatchFinally()
    a_2_5_2_TryAsAnExpression()
    a_3_3_2_LaunchExtensionFromJava.launch()
    a_3_3_5_PropertyExtension()
    a_3_4_2_UnpackCollectionsFromArray()
    a_3_4_3_IndexesCall()
    a_3_5_1_StringDivider()
    a_3_5_2_RegularExpressionsAndStringsInTriplyQuotes()
    a_3_5_3_MultiLineLiteralsInTripleQuotes()
    a_3_6_Base()
    a_4_1_1_interfaces_in_kotlin()*/

    //TwoSum1().calculate(intArrayOf(2,7,11,15), 9)
    //AddTwoNumbers2().addTwoNumbers(null, null)
    //LongestSubstringWithoutRepeatingCharacters3().lengthOfLongestSubstring()
    //PalindromeNumber9().isPalindrome()
    //RomanToInteger13().calculate("MCMXCIV")
    //LongestCommonPrefix14().longestCommonPrefix()
    //println( ValidParentheses20().isValid())
    //MergeTwoSortedLists21().mergeTwoLists()
    //RemoveDuplicatesFromSortedArray26().removeDuplicates()
    //RemoveElement27().removeElement()
    //SearchInsertPosition35.searchInsert(intArrayOf(1,5,7), 4)
    //LengthOfLastWord.lengthOfLastWord("Hello World")
    //PlusOne66.plusOne(intArrayOf(4,3,2,1)).toList().toString()
    //AddBinary67.addBinary("100", "110010")
    //SqrtX69.mySqrt(8)
    //RemoveDuplicatesFromSortedList83.deleteDuplicates(11233L.toNodesList())?.reverseValue()
    arrayForCheck.forEach { value ->
        println(
            MergeSortedArray88.merge(value.nums1, value.m, value.nums2, value.n)
        )
    }
}
val arrayForCheck = listOf(
    MSortedArray(intArrayOf(1,2,3,3,0,0,0,0), 4, intArrayOf(2,5,5,6), 4), //[1, 2, 2, 3, 3, 5, 5, 6]
    MSortedArray(intArrayOf(1,2,3,0,0,0), 3, intArrayOf(2,5,6), 3), //[1, 2, 2, 3, 5, 6]
    MSortedArray(intArrayOf(1), 1, intArrayOf(), 0), //[1]
    MSortedArray(intArrayOf(0), 0, intArrayOf(1), 1), //[1]
    MSortedArray(intArrayOf(4,5,6,0,0,0), 3, intArrayOf(1,2,3), 3), //[1,2,3,4,5,6]
    MSortedArray(intArrayOf(-1,0,0,3,3,3,0,0,0), 6, intArrayOf(1,2,2), 3), //[-1,1,2,2,2,3,2,3,3]
)

