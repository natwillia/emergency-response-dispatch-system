package emergencydispatch;

    // Definition for a node in the sorted linked list
    class PQNode {
        String value;    // String value
        int key; // Key
        PQNode next;

        // Constructor to create a new node
        public PQNode(String reportedIncident, int urgencyLvl) {
            this.value = reportedIncident;
            this.key = urgencyLvl;
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
        public void insert(String reportedIncident, int urgencyLvl) {
            PQNode newNode = new PQNode(reportedIncident, urgencyLvl);

            // If the queue is empty or the new node has a higher priority than the head
            if (head == null || head.key > urgencyLvl) {
                newNode.next = head;
                head = newNode;
            } else {
                // Traverse the list to find the correct position for the new node
                PQNode current = head;
                while (current.next != null && current.next.key <= urgencyLvl) {
                    current = current.next;
                }
                // Insert the new node at the correct position
                newNode.next = current.next;
                current.next = newNode;
            }
            size++;
        }


        // Natasha: Method to dispatch the highest priority incident by removing and returning it
        public String dispatch() {
            if (head == null) {
                throw new IllegalStateException("Priority Queue is empty!");
            }
            String highestPriorityIncident = head.value;
            head = head.next;  // Remove the head node
            size--;
            return highestPriorityIncident;
        }

        // Link: Method to retrieve the element with the highest priority without removing it
        public int min() {
            if (head == null) {
                throw new IllegalStateException("Priority Queue is empty!");
            }
            return head.value;
        }

        // Biraj: Method to display all pending incidents sorted by urgency level
        public void pendingIncidents() {
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

            // Natasha: Insert incidents into the priority queue
            priorityQueue.insert("Fire in cafeteria", 3);
            priorityQueue.insert("Active shooter", 1);
            priorityQueue.insert("Stampede at concert", 2);
            priorityQueue.insert("Nose bleed", 4);
            priorityQueue.insert("Noise complaint", 5);

            System.out.println("Number of entries in the priority queue: " + priorityQueue.size());

            // Peek at the highest-priority element
            System.out.println("Element with highest priority (peek): " + priorityQueue.min());

            // Dequeue element based on priority
            System.out.println("Element removed with highest priority: " + priorityQueue.dispatch());

            System.out.println("Number of entries in the priority queue: " + priorityQueue.size());

            System.out.println("Element removed with highest priority: " + priorityQueue.dispatch());
        }
    }

