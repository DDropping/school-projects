
package singlylinkedlist;


public class SinglyLinkedList {
    
    private ListNode head;
    
    private static class ListNode {
        private int data;
        private ListNode next;
        
        public ListNode(int data) {
            this.data = data;
            this.next = null;
        }   
    }
    


    public static void main(String[] args) {
        //create list
        ListNode head = new ListNode(10);
        ListNode second = new ListNode(9);
        ListNode third = new ListNode(8);
        ListNode fourth = new ListNode(7);

        //Link them together
        head.next = second;
        second.next = third;
        third.next = fourth;  
        
        SinglyLinkedList  singlyLinkedList = new SinglyLinkedList();
        singlyLinkedList.print(head);
    }
    
    
    
    
    
    
    //print method:    prints all elements in linked list
    public void print(ListNode head){
        if(head == null){
                return;
    }        
        
        ListNode current = head;
        while(current != null){
            System.out.println(current.data );
            current = current.next;
        }
        
    }
    
    
    
    
    //insert method:    Inserts node at a given position
    public ListNode insert(ListNode head, int data, int position){
        ListNode newNode = new ListNode(100);       //create new node
        
        if(position == 1) {             //if newNode goes at the front of list
            newNode.next = head;
        }
        else{                           // if newNode goes in middle of list
            ListNode previous = head;   //create temp node  set to head
            int count =1;               //  
            while(count < position-1){  //while loop to find the node in...
                previous = previous.next;//...position before desired position
                count++;
            }
            ListNode current = previous.next; //save the reference to next node (the node in position desired)
            newNode.next = current;     //make newNode point to current
            previous.next = newNode;    // makes previous node point to newNode
            return head;        //returns new linked list with newNode implemented
            
            
            
        }
    }

}
