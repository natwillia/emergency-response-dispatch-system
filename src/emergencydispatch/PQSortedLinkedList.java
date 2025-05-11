package emergencydispatch;
import java.util.Scanner;

    // Definition for a node in the sorted linked list
    class IncidentNode {
        String reportedIncident;    // String value: type of  incident
        int priority; // Key
        String serviceType;
        IncidentNode next;

        // Constructor to create a new node
        public IncidentNode(String serviceType, String reportedIncident, int priority) {
            this.serviceType = serviceType;
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
        public void insert(String serviceType, String reportedIncident, int urgencyLvl) {
            IncidentNode newNode = new IncidentNode(serviceType, reportedIncident, urgencyLvl);

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
            int priority = head.priority;
            String serviceType = head.serviceType;

            head = head.next;  // Remove the head node
            size--;

            // Print dispatch information with priority level and type of service needed
            System.out.println("Dispatching Incident: " + highestPriorityIncident +
                    " | Priority Level: " + priority +
                    " | Service Type: " + serviceType);

            return highestPriorityIncident;
        }

        // Link: Method to retrieve the incident with the highest priority without removing it
        public int nextDispatch() {
            if (head == null) {
                throw new IllegalStateException("Priority Queue is empty!");
        }
                return head.value;  // Add your code here to fix this
        }

        // Biraj: Method to display all pending incidents sorted by priority
        public void pendingIncidents() {
            if (head == null) {
                throw new IllegalStateException("No pending incidents");
            }
            // Add code here to display pending incidents sorted by priority: start at the head of the priority queue
        }
        }

        // Method to return the number of entries
        public int size() {
            return size;
        }

        // Method to check whether the priority queue is empty
        public boolean isEmpty() {
            return (size == 0);
        }

        // Main method to demonstrate the priority queue
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            PQSortedLinkedList priorityQueue = new PQSortedLinkedList();

            System.out.println("\n\t\t\t\t\t\t\t\t\t------ Emergency Response Dispatch System ------");

            // Insert incidents into the priority queue
            priorityQueue.insert("Fire", "Fire in cafeteria", 3);   // Service: Fire
            priorityQueue.insert("Police", "Active shooter", 1);      // Highest Priority; Service: Police
            priorityQueue.insert("Medical", "Stampede at concert", 2);
            priorityQueue.insert("Medical","Nose bleed", 4);          // Service: Medical
            priorityQueue.insert("Police", "Noise complaint", 5);     // Lowest Priority

            while (true) {
                System.out.println("\n1. Report new incident");
                System.out.println("2. Dispatch Current Highest Priority Incident");
                System.out.println("3. Peek");
                System.out.println("4. View Pending Incidents");
                System.out.println("5. Display Queue Contents");
                System.out.println("6. Exit");
                System.out.println("\nEnter a numbered option: ");

                int option = scanner.nextInt();
                scanner.nextLine();

                // Natasha: Give the user an option to input more incidents to the priority queue
                if (option == 1) {
                    System.out.println("Enter service type (Fire, Medical, Police): ");
                    String serviceType = scanner.nextLine();

                    System.out.println("Incident Description: ");
                    String reportedIncident = scanner.nextLine();

                    System.out.println("Enter the priority on a scale of (1 = highest, 5 = lowest): ");
                    int priority = scanner.nextInt();
                    scanner.nextLine();

                    priorityQueue.insert(serviceType, reportedIncident, priority);
                    System.out.println("Incident Report Complete!");
                } else if (option == 2) {
                    if (priorityQueue.isEmpty()) {
                        System.out.println("No incidents in the priority queue");
                    } else {
                        priorityQueue.dispatch();
                    }
                } else if (option == 3) {
                    if (priorityQueue.isEmpty()) {
                        System.out.println("No pending incidents");
                    } else {
                        System.out.println("Next Incident: " + priorityQueue.head.reportedIncident +
                                " | Priority Level: " + priorityQueue.head.priority);
                    }
                } else if (option == 4) {
                        priorityQueue.pendingIncidents();
                } else if (option == 5) {
                    if (priorityQueue.isEmpty()) {
                        System.out.println("\nThere are currently no incidents in the priority queue.");
                    } else {
                        System.out.println("\nCurrent number of entries in the priority queue: " + priorityQueue.size());
                        System.out.println("\nContents:");

                        IncidentNode temp = priorityQueue.head;
                        while (temp != null) {
                            System.out.print("[" + temp.reportedIncident + " | Priority: " + temp.priority + "]");
                            if (temp.next != null) {
                                System.out.print(" -> ");
                            }

                            temp = temp.next;
                        }

                        System.out.println();
                    }
                } else if (option == 6) {
                    System.out.println("System Exited");
                    break;
                }
            }
        }
    }