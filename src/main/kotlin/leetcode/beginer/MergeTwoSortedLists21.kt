package leetcode.beginer

import java.lang.Exception

fun ListNode.getAllLevelsNodes(): MutableList<ListNode> {
    val mutaListOfLong = mutableListOf<ListNode>()
    getValueFromNode(mutaListOfLong::add)
    return mutaListOfLong
}

fun ListNode.getValueFromNode(valueListener: (value: ListNode) -> Unit) {
    next?.getValueFromNode(valueListener)
    this.next = null
    valueListener(this)
}

class MergeTwoSortedLists21 {
    fun mergeTwoLists(list1: ListNode? = 124L.toNodesList(), list2: ListNode? = 421L.toNodesList()): ListNode? {
        list1 ?: return list2
        list2 ?: return list1
        try {
            println("List 1 - " +list1.`val`.toString())
        }catch (e: Exception){}
        try {
            println("List 2 - " +list2.`val`.toString())
        }catch (e: Exception){}


        return when {
            list1.`val` < list2.`val` -> {
                list1.next = mergeTwoLists(list1.next, list2)
                println("merge 1 - $list1")
                list1
            }
            else -> {
                list2.next = mergeTwoLists(list2.next, list1)
                println("merge 2 - $list2")
                list2
            }
        }
    }
}

/*
fun mergeTwoListsFirstIteration(list1: ListNode? = 124L.toNodesList(), list2: ListNode? = 134L.toNodesList()): ListNode? {
    list1 ?: return list2
    list2 ?: return list1
    val listOfNodesFirst = list1.getAllLevelsNodes()
    val listOfNodesSecond = list2.getAllLevelsNodes()
    val lisOfNodes:MutableList<ListNode> = listOfNodesFirst.apply {
        addAll(listOfNodesSecond)
        sortBy { it.`val` }
    }

    val head:ListNode = lisOfNodes.first()
    var cursor:ListNode = head
    for (i in 1 until lisOfNodes.size){
        cursor.next = lisOfNodes[i]
        cursor = cursor.next ?: break
    }
    return head
}*/
