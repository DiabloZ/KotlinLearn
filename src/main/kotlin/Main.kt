package main.kotlin

import kotlinx.coroutines.*
import main.kotlin.my_study.coroutines.CoroutinesSandBox
import leetcode.beginer.arrayForCheckMergeSortedArray88
import main.kotlin.utils.CoroutineTimer
import main.kotlin.utils.Logger
import java.net.InetAddress
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.cast

suspend fun main (args: Array<String>) {
    println("""////////////////START\\\\\\\\\\\\\\\\""")
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

    Logger.printText("","!!!!!TimerIsStarted !!!!!")
    val timer = CoroutineTimer()
    val time = Date().time
    val timerTime = 100L
    timer.createTimer(timerTime, 16, { }){
        Logger.printText("","!!!!!TimerIsCompleted !!!!! overTime - ${Date().time - timerTime - time}")
    }
    timer.start()

    arrayForCheckMergeSortedArray88.forEach { value ->

        /*println(
            MergeSortedArray88.merge(value.nums1, value.m, value.nums2, value.n)
        )*/
    }

    CoroutinesSandBox().makeSandBox()
    delay(1000)
    println("""\\\\\\\\\\\\\\\\END////////////////""")
}

