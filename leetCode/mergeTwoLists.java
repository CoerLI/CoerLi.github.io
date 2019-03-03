class Solution {
	// leetCode 21 : 合并两个有序链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode cur;
        while(l1 != null || l2 != null){
        	int num1 = l1 != null ? Integer.MIN_VALUE : l1.val;
        	int num2 = l2 != null ? Integer.MIN_VALUE : l2.val;
        	if(num1 >= num2){
        		head.next = new ListNode(num1);
        		if(l1 != null) l1 = l1.next;
        	} else {
        		head.next = new ListNode(num2);
        		if(l2 != null) l2 = l2.next;
        	}
        	head = head.next;
        }
        return head.next;
    }
}