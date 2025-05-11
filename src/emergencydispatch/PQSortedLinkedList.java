package emergencydispatch;
import java.util.Scanner;

    // Definition for a node in the sorted linked list
    class IncidentNode {
        String reportedIncident;    // String value
        int priority; // Key
        IncidentNode next;

        // Constructor to create a new node
        public IncidentNode(String reportedIncident, int priority) {
            this.reportedIncident = reportedIncident;
            this.priority = priority;
            this.next = null;
        }
    }

    // Priority Queue implementation using a sorted linked list
    public class PQSortedLinkedList {
        private IncidentNode head;
        private int size;

        // Constructor to initialize the priority queue
        public PQSortedLinkedList() {
            head = null;
            size = 0;
        }

        // Method to insert an element into the priority queue
        public void insert(String reportedIncident, int urgencyLvl) {
            IncidentNode newNode = new IncidentNode(reportedIncident, urgencyLvl);

            // If the queue is empty or the new node has a higher priority than the head
            if (head == null || head.priority > urgencyLvl) {
                newNode.next = head;
                head = newNode;
            } else {
                // Traverse the list to find the correct position for the new node
                IncidentNode current = head;
                while (current.next != null && current.next.priority <= urgencyLvl) {
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
            String highestPriorityIncident = head.reportedIncident;
            head = head.next;  // Remove the head node
            size--;
            return highestPriorityIncident;
        }

        // Link: Method to retrieve the element with the highest priority without removing it
        public int nextDispatch() {
            if (head == null) {
                throw new IllegalStateException("Priority Queue is empty!");
            }
            return head.value;  // Add your code here to fix this
        }

        public void pendingIncidents() {
            if(head == null){
                throw new IllegalStateException("No pending incidents");
            }
            // start at the head of the priority queue (element with the highest priority: 1)

            }


        // Method to return the number of entries
        public int size(){
            return size;
        }

        // Method to check whether the priority queue is empty
        public boolean isEmpty(){
            return (size==0);
        }

        // Main method to demonstrate the priority queue
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            PQSortedLinkedList priorityQueue = new PQSortedLinkedList();

            System.out.println("\n\t\t\t\t\t\t\t\t\t------ Emergency Response Dispatch System ------");

            // Natasha: Insert incidents into the priority queue
            priorityQueue.insert("Fire in cafeteria", 3);
            priorityQueue.insert("Active shooter", 1);  // Highest Priority
            priorityQueue.insert("Stampede at concert", 2);
            priorityQueue.insert("Nose bleed", 4);
            priorityQueue.insert("Noise complaint", 5); // Lowest Priority

            System.out.println("\nInitial number of entries in the priority queue: " + priorityQueue.size());

            // Give the user an option to input more incidents to the priority queue
            System.out.println("\nDo you want to add more incidents? (y/n)");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("y")) {
                System.out.println("Enter the number of incidents to add: ");
                int additionalIncidents = scanner.nextInt();
                scanner.nextLine();

                for (int i = 0; i < additionalIncidents; i++) {
                    System.out.println("Describe the incident to report: ");
                    String reportedIncident = scanner.nextLine();

                    System.out.println("Enter the priority on a scale of (1 = highest, 5 = lowest: ");
                    int priority = scanner.nextInt();
                    scanner.nextLine();

                    priorityQueue.insert(reportedIncident, priority);
                }
            }

            System.out.println();

            // Natasha: Display the initial contents of the PQSortedLinkedList (including user additions if any)
            System.out.println("Contents:");
            IncidentNode temp = priorityQueue.head;
            while (temp != null) {
                System.out.print("[" + temp.reportedIncident + " | " + temp.priority + "]");
                if (temp.next != null) {
                    System.out.print(" -> ");
                }
                temp = temp.next;
            }

            System.out.println();
            System.out.println();

            // Biraj: Display all pending incidents sorted by priority
            System.out.println("Pending Incidents: ");
            priorityQueue.pendingIncidents();

            // Link: Peek at the current highest-priority incident
            System.out.println("Incident with highest priority (peek): " + priorityQueue.nextDispatch());

            System.out.println();

            // Natasha: Dequeue (remove) incidents based on priority
            System.out.println("Dispatching Incidents: ");
            System.out.println("Current incident dispatched with highest priority: " + priorityQueue.dispatch());
            System.out.println("Current incident dispatched with highest priority: " + priorityQueue.dispatch());
            System.out.println("Current incident dispatched with highest priority: " + priorityQueue.dispatch());
            System.out.println("Current incident dispatched with highest priority: " + priorityQueue.dispatch());
            System.out.println("Current incident dispatched with highest priority: " + priorityQueue.dispatch());

            System.out.println();

            // Natasha: Check if the priority queue is empty
            if (priorityQueue.isEmpty()) {
                System.out.println("All incidents have been dispatched, Priority Queue is now empty" );
            } else {
                System.out.println("There are still pending incidents");
                priorityQueue.pendingIncidents();
            }
        }
    }

