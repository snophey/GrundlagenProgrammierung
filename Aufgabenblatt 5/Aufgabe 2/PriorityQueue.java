public class PriorityQueue {
        static final int SIZE = 32;
        Element[] q = new Element[SIZE];
        int len = 0;
        
        // Insert an element into the queue according to its priority
        void put(Element x) { /* ... */ }
        
        // Return the element with the highest priority from the queue
        Element get()       { /* ... */ }
        
        // Return the queue length
        int length()        { /* ... */ }

        // Print the queue contents
        public String toString() { /* ... */ }
}
