package com.example.mail_server.Model.DataStructures;

public class linkedlist<E> {

    private doubleNode head;
    private doubleNode tail;
    private int size;
    public linkedlist() {
        size = 0;
        head = new doubleNode(null, null, null);
        tail = new doubleNode(null, null, head);
        head.next=tail;
    }


    private class doubleNode {
        E element;
        doubleNode next;
        doubleNode prev;

        public doubleNode(E element, doubleNode next, doubleNode prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
        public doubleNode() {
        }
    }


    public void add(int index, E element) {
        doubleNode current = head.next;
        if (index>size || index <0)
            throw new RuntimeException("Invalid Index !");
        else if (size == index)
            add(element);
        else {
            for (int i = 0; i < index; i++)
                current = current.next;
            doubleNode addedNode = new doubleNode(element, current, current.prev);
            current.prev.next = addedNode;
            current.prev = addedNode;//it was current.next.prev >> error
            size++;
        }
    }

    public void add(E element) {
        doubleNode addedNode = new doubleNode(element, tail, tail.prev); // three steps
        tail.prev.next = addedNode;
        tail.prev = addedNode;//You forgot this step..
        size++;
    }

    public E get(int index) {
        if (index>=size || index <0)
            throw new RuntimeException("Invalid Index !");
        doubleNode current;
        if(index<size/2) {
            current = head.next;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        }
        else{
            current = tail.prev;
            for (int i = size-1; i > index; i--) {
                current = current.prev;
            }
        }
        return current.element;
    }


    public void set(int index, E element) {
        if (index>=size || index <0)
            throw new RuntimeException("Invalid Index !");
        doubleNode current ;
        if(index<size/2) {
            current = head.next;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        }
        else{
            current = tail.prev;
            for (int i = size-1; i > index; i--) {
                current = current.prev;
            }
        }
        current.element = element;

    }


    public void clear() {
        head.next= tail;
        tail.prev = head;
        size = 0;
    }


    public boolean isEmpty() {

        return (size==0);
    }


    public void remove(int index) { // revise if you want to decide from where to start the loop depending of the index (nearer to tail or head)
        if (index>=size || index <0)
            throw new RuntimeException("Invalid Index !");
        doubleNode deletedItem;
        if(index<size/2) {
            deletedItem = head.next;
            for (int i = 0; i < index; i++) {
                deletedItem = deletedItem.next;
            }
        }
        else{
            deletedItem = tail.prev;
            for (int i = size-1; i > index; i--) {
                deletedItem = deletedItem.prev;
            }
        }
        deletedItem.prev.next=deletedItem.next;
        deletedItem.next.prev = deletedItem.prev;
        deletedItem.next=null;
        deletedItem.prev=null;
        deletedItem.element = null;
        size--;
    }


    public int size() {
        return size;
    }


    public linkedlist<E> sublist(int fromIndex, int toIndex) {
        doubleNode current = head.next;
        linkedlist<E> sublist = new linkedlist<>();
        if (fromIndex>=size || fromIndex <0 || toIndex >= size || toIndex < fromIndex )
            throw new RuntimeException("Invalid Index !");
        for ( int i = 0; i < fromIndex ; i++) {
            current = current.next;
        }
        for ( int i = fromIndex; i <= toIndex ; i++) {
            sublist.add(current.element);
            current = current.next;
        }

        return sublist;
    }


    public boolean contains(E o) {
        doubleNode current = head.next;
        for ( int i = 0; i < size; i++) {
            if(current.element==o)
                return true;
            else
                current = current.next;
        }
        return false;
    }
    /*    public void print (){
            doubleNode current = head.next;
            System.out.println("PRINTING STARTS");
            System.out.println("size is " + size());
            for (int i = 0; i < size ; i++) {
                System.out.println(current.element);
                current = current.next;
            }
            System.out.println("PRINTING ENDSSSSSSSSSS !!!");
    }
        */
/////////////////////////////////////////////////////////////////////////////
    public linkedlist<E> Arraytolist (E[][] arr)
    {
        linkedlist<E> mylist = new linkedlist<>();
        for (E[] ints : arr) {
            for (int j = 0; j < arr[0].length; j++) {
                mylist.add(ints[j]);
            }
        }
        return mylist;
    }
    public linkedlist<E> Arraytolist (E[] arr)
    {
        linkedlist<E> mylist = new linkedlist<>();
        for (E ints : arr) {
            mylist.add(ints);
        }
        return mylist;
    }
    //enhanced listtoarray
//    public E[][] listTo2DArray (){
//        E[][] myarray = new E [size/2][2];
//        doubleNode current = head.next;
//        for (int i = 0; i < size/2 ; i++) {
//            for (int j = 0; j < 2 ; j++) {
//                myarray[i][j] = current.element;
//                current = current.next;
//            }
//        }
//        return myarray;
//    }
//    public E[] listTo1DArray() {
//        E[] myArray = new E[size];
//        doubleNode current = head.next;
//        for (int i = 0; i < size ; i++) {
//            myArray[i] = current.element;
//            current = current.next;
//        }
//        return myArray;
//    }
    public void ReverseList(){
        linkedlist<E> reversedList = new linkedlist<>();
        doubleNode current = tail.prev;
        while (current!=head){
            reversedList.add(current.element);
            current = current.prev;
        }
        head = reversedList.head;
    }

	/*public static void main(String[] args) {
		LinkedList trial = new LinkedList();
		for (int i = 0; i <10 ; i++) {
			trial.add(i);
		}
		 trial.ReverseList();
		trial.print();
	}
*/
}
