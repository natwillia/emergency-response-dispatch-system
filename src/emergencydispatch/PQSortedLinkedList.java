package emergencydispatch;

    // Definition for a node in the sorted linked list
    class PQNode {
        String reportedIncident;    // String value;
        int urgencyLvl; // Key
        PQNode next;

        // Constructor to create a new node
        public PQNode(String reportedIncident, int urgencyLvl) {
            this.reportedIncident = reportedIncident;
            this.urgencyLvl = urgencyLvl;
            this.next = null;
        }
    }

    // Priority Queue implementation using a sorted linked list
    public class PQSortedLinkedList {
        private PQNode head;
        private int size;
        // Constructor to initialize the priority queue
        public PQSortedLinkedList() {
            head = null;
            size = 0;
        }

        // Method to insert an element into the priority queue
        public void insert(int data, int priority) {
            PQNode newNode = new PQNode(data, priority);

            // If the queue is empty or the new node has a higher priority than the head
            if (head == null || head.key > priority) {
                newNode.next = head;
                head = newNode;
            } else {
                // Traverse the list to find the correct position for the new node
                PQNode current = head;
                while (current.next != null && current.next.key <= priority) {
                    current = current.next;
                }
                // Insert the new node at the correct position
                newNode.next = current.next;
                current.next = newNode;
            }
            size++;
        }

        // Method to remove and return the element with the highest priority
        public int removeMin() {
            if (head == null) {
                throw new IllegalStateException("Priority Queue is empty!");
            }
            int highestPriorityData = head.value;
            head = head.next;  // Remove the head node
            size--;
            return highestPriorityData;
        }

        // Method to retrieve the element with the highest priority without removing it
        public int min() {
            if (head == null) {
                throw new IllegalStateException("Priority Queue is empty!");
            }
            return head.value;
        }
        //Method to return the number of entries
        public int size(){
            return size;
        }

        // Method to check whether the priority queue is empty
        public boolean isEmpty(){
            return (size==0);
        }

        // Main method to demonstrate the priority queue
        public static void main(String[] args) {
            PQSortedLinkedList priorityQueue = new PQSortedLinkedList();

            // Insert elements into the priority queue
            priorityQueue.insert(10, 3);
            priorityQueue.insert(15, 1);
            priorityQueue.insert(20, 2);
            priorityQueue.insert(30, 4);
            priorityQueue.insert(50, 5);

            System.out.println("Number of entries in the priority queue: " + priorityQueue.size());

            // Peek at the highest-priority element
            System.out.println("Element with highest priority (peek): " + priorityQueue.min());

            // Dequeue element based on priority
            System.out.println("Element removed with highest priority: " + priorityQueue.removeMin());

            System.out.println("Number of entries in the priority queue: " + priorityQueue.size());

            System.out.println("Element removed with highest priority: " + priorityQueue.removeMin());
        }
    }

