class Solution {
    // leetCode 2 : 两数相加
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            // 声明一个节点，两个指针保存它，head用于保留头部，cur用于遍历
        	ListNode head = new ListNode(0);   // 此节点是哑节点，否则循环体不好写
        	ListNode cur = head;
            // carry用于保存进位
        	int carry = 0;
            // 重点：一个循环遍历两个链表的方法，两者有一个没有为空时，都可以继续
            // 内部控制为空的不走，不为空的继续遍历
        	while(l1 != null || l2 != null){
        		int num1 = l1 == null ? 0 : l1.val;
        		int num2 = l2 == null ? 0 : l2.val;
        		int sum = num1 + num2 + carry;
        		cur.next = new ListNode(sum % 10);
        		carry = sum / 10;
        		cur = cur.next;
        		if(l1 != null)	l1 = l1.next;
        		if(l2 != null)  l2 = l2.next;
        	}
        	if(carry != 0)
        		cur.next = new ListNode(carry);
        	return head.next;
    }
}