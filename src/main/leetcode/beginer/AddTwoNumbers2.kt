package main.leetcode.beginer


data class ListNode(var `val`: Int) {
     var next: ListNode? = null
}

/*
fun ListNode.reverseValue(): Long? {
    val mutaListOfLong = mutableListOf<String>()
    getValueFromNode(mutaListOfLong::add)
    var value = ""
    mutaListOfLong.forEach { value += it }
    return value.toLongOrNull()
}

fun ListNode.getValueFromNode(valueListener: (value: String) -> Unit) {
    next?.getValueFromNode(valueListener)
    valueListener(`val`.toString())
}

fun Long.toNodesList(): ListNode{
    val charArray = this.toString().reversed().toCharArray()
    val mapNodes = charArray.map { char ->
        ListNode(char.digitToInt())
    }
    return mapNodes.createList()
}

fun List<ListNode>.createList(): ListNode {
    val lastIndex = lastIndex
    this.forEachIndexed { indext, listNode ->
        if (indext == lastIndex) return@forEachIndexed
        listNode.next = this[indext + 1]
    }
    return first()
}

class AddTwoNumbers2 {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val nodesListFirst = 9123L.toNodesList()
        val nodesListSecond = 99999991L.toNodesList()
        return calculate(nodesListFirst, nodesListSecond)
    }

    private fun calculate(l1: ListNode?, l2: ListNode?): ListNode? {
        val firstList = l1?.reverseValue() ?: return l1
        val secondList = l2?.reverseValue() ?: return l2
        val sumOfNodes = firstList.plus(secondList)
        println(sumOfNodes)
        return sumOfNodes.toNodesList()
    }


}*/



fun Long.toNodesList(): ListNode{
    val charArray = this.toString().reversed().toCharArray()
    val mapNodes = charArray.map { char ->
        ListNode(char.digitToInt())
    }
    return mapNodes.createList()
}

fun List<ListNode>.createList(): ListNode {
    val lastIndex = lastIndex
    this.forEachIndexed { indext, listNode ->
        if (indext == lastIndex) return@forEachIndexed
        listNode.next = this[indext + 1]
    }
    return first()
}

class AddTwoNumbers2 {

    fun addTwoNumbers(l1: ListNode? = 1L.toNodesList(), l2: ListNode? = 9L.toNodesList()): ListNode? = calculate(2L.toNodesList(), 9L.toNodesList())

    private fun calculate(l1: ListNode?, l2: ListNode?): ListNode?{
        var n1: ListNode? = l1
        var n2: ListNode? = l2
        var head:ListNode? = null
        var cur:ListNode? = null
        var carry = 0
        var isFirst = true
        fun sum(firstVal: Int?, secondVal: Int?, carry: Int): Int = (firstVal ?: 0) + (secondVal ?: 0) + carry

        while(n1 != null || n2 != null){
            val sum = sum(n1?.`val`, n2?.`val`, carry)
            if (isFirst){
                isFirst = false
                head = ListNode(sum % 10)
                cur = head
            } else {
                cur?.next = ListNode(sum % 10)
                cur = cur?.next
            }

            carry = if(sum > 9) 1 else 0
            if(n1 != null) n1 = n1.next
            if(n2 != null) n2 = n2.next
        }

        if(carry > 0){
            cur?.next = ListNode(carry)
            cur = cur?.next
        }

        return head
    }


}