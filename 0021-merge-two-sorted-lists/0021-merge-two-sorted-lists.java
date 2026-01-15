/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        
        ListNode answerNode = new ListNode();
        ListNode curr = answerNode;

        if(list1 == null && list2 == null) {
            return null;
        }

        while(list1 != null && list2 != null) {

            if(list1.val >= list2.val) {
                curr.val = list2.val;
                list2 = list2.next;
            } else {
                curr.val = list1.val;
                list1 = list1.next;
            }
            curr.next = new ListNode();
            curr = curr.next;
        }

        if(list1 == null && list2 != null) {
            curr.val = list2.val;
            curr.next = list2.next;
        } else if(list1 != null && list2 == null) {
            curr.val = list1.val;
            curr.next = list1.next;
        }

        return answerNode;
    }
}