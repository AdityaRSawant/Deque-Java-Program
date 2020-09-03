/**
* This class deque implements APIs like pushleft, pushleft, popleft, popright
* Deque would behave as stack and queue whill adding and removing accordingly
* @author  Aditya Raghunath Sawant
* @version 1.0
* @since   08-31-2020 
*/

import java.util.*;

public class Deque<Item> implements Iterable<Item> {

    /**
    * Inner class NODE that holds declaration of the item, previous and next items
    */
    private class Node {
        Item item;
        Node previous;
        Node next;
    }

    // Declaration of class variables
    private Node first;
    private Node last;
    private int size;

    /**
     * Constructor to set null and 0 to first,last and size
     */
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Function to check if deque is empty
     * @return boolean of size equals 0
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Function to get size of deque
     * @return the size of deque
     */
    public int size() {
        return size;
    }

    /**
    * Function to add new item to the left i.e. start of the deque
    * This function behaves like push of stack as it adds element at the front of deque
    * @param item to be added to the deque
    */
    public void pushLeft(Item item) {
        Node oldFirst = first;

        first = new Node();
        // Replace the current first element with new item
        first.item = item;
        first.next = oldFirst;

        if (oldFirst != null) 
        {
            //Set the previous first to current item
            oldFirst.previous = first;
        } 
        else 
        {
            //If only one element deque exists in the deque
            last = first;
        }

        //Increase size of deque
        size++;
    }

    /**
    * Function to add new item to the right i.e. end of the deque
    * This function behaves like enqueue of queue as it adds element at the end of deque
    * @param item to be added to the deque
    */
    public void pushRight(Item item) {
        Node oldLast = last;

        last = new Node();
        // Replace the current last element with new item
        last.item = item;
        last.previous = oldLast;

        if (oldLast != null) 
        {
            //Set the next of old last to current item
            oldLast.next = last;
        } 
        else 
        {
            //Set the first element to last in case of only one item
            first = last;
        }

        //Increase size of deque
        size++;
    }

    /**
    * Function to remove item from the left i.e. start of the deque
    * This function behaves like pop of stack as it removes element from the start of deque
    * @return item popped from left of deque
    */
    public Item popLeft() {
        //Check if deque is empty
        if (isEmpty()) 
        {
            //Throw exception if deque is empty
            throw new RuntimeException("Deque is empty");
        }

        //Get first item from deque
        Item item = first.item;

        //Set current first item to its next item
        first = first.next;

        if (first != null) 
        {
            //If first item is not null, set its previous to null
            first.previous = null;
        } 
        else 
        {
            //Set last as null in case only one element in deque
            last = null;
        }

        //Reduce size of deque
        size--;

        return item;
    }

    /**
    * Function to remove item from the right i.e. end of the deque
    * @return item popped from right of deque
    */
    public Item popRight() {
        //Check if deque is empty
        if (isEmpty()) 
        {
            //Throw exception if deque is empty
            throw new RuntimeException("Deque underflow");
        }

        //Get last item from deque
        Item item = last.item;

        //Set current first item to its next item
        last = last.previous;

        //Check if last is null
        if (last != null) 
        {
            //If next of last item is not null, set it to null
            last.next = null;
        } 
        else 
        {
            //If the popped element is first element, then set first to null
            first = null;
        }
        //Decrease size of deque
        size--;

        return item;
    }

    /**
     * @return object of Iterator
     */
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        int index = 0;
        Node current = first;

        /**
         * function to check if next element exists in the Deque
         * @return boolean of index less than size
         */
        @Override
        public boolean hasNext() {
            return index < size();
        }

        /**
         * Override next function that will return the next item in deque
         * @return item in the steque
         */
        @Override
        public Item next() {
            index++;

            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        Deque<String> deque = new Deque<>();

        deque.testPushLeft();
        System.out.println("-----------");
        deque.testPushRight();
        System.out.println("-----------");
        deque.testPopLeft();
        System.out.println("-----------");
        deque.testPopRight();
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Time Elapsed is:"+(elapsedTime/1000000)+"ms");
    }

    private void testPushLeft() {
        System.out.println("Testing Push Left operation of deque");

        Deque<String> deque1 = new Deque<>();
        deque1.pushLeft("A");
        deque1.pushLeft("B");
        deque1.pushLeft("C");


        System.out.print("Deque items generated : ");

        Iterator itr1 = deque1.iterator();
        while(itr1.hasNext())
        {
            System.out.print(itr1.next().toString()+" ");
        }

        System.out.println();
        
        System.out.println("Expected: C B A");
    }

    private void testPushRight() {
        System.out.println("Testing Push Right operation of deque");

        Deque<String> deque2 = new Deque<>();
        deque2.pushRight("A");
        deque2.pushRight("B");
        deque2.pushRight("C");

        System.out.print("Deque items generated : ");

        Iterator itr1 = deque2.iterator();
        while(itr1.hasNext())
        {
            System.out.print(itr1.next().toString()+" ");
        }

        System.out.println();
        System.out.println("Expected: A B C");
    }

    private void testPopLeft() {
        System.out.println("Testing Pop Left operation of deque");

        Deque<String> deque3 = new Deque<>();
        deque3.pushRight("A");
        deque3.pushRight("B");
        deque3.pushRight("C");

        deque3.popLeft();
        deque3.popLeft();

        System.out.print("Deque items generated : ");

        Iterator itr1 = deque3.iterator();
        while(itr1.hasNext())
        {
            System.out.print(itr1.next().toString()+" ");
        }

        System.out.println();

        System.out.println("Expected: C");
    }

    private void testPopRight() {
        System.out.println("Testing Pop Right operation of deque");

        Deque<String> deque4 = new Deque<>();
        deque4.pushRight("A");
        deque4.pushRight("B");
        deque4.pushRight("C");

        deque4.popRight();
        deque4.popRight();

        System.out.print("Deque items generated : ");

        Iterator itr1 = deque4.iterator();
        while(itr1.hasNext())
        {
            System.out.print(itr1.next().toString()+" ");
        }

        System.out.println();     
        System.out.println("Expected: A");
    }
}
