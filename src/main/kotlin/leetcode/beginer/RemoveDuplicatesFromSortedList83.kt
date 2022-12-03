package leetcode.beginer

object RemoveDuplicatesFromSortedList83 {
    private fun ListNode.findOtherValueOrNull(valInt: Int = this.`val`): ListNode?{
        return if (valInt == this.next?.`val`){
            this.next?.findOtherValueOrNull(valInt)
        } else {
            this.next
        }
    }
    fun deleteDuplicates(head: ListNode?): ListNode? {
        if (head?.`val` == head?.next?.`val`){
            head?.next = head?.findOtherValueOrNull()
        }
        if (head?.next != null) deleteDuplicates(head.next)
        return head
    }
}