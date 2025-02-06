import java.util.*;

class Process {
    int processID;
    int arrivalTime;
    int burstTime;
    int remainingBurstTime;
    int waitingTime;
    int turnaroundTime;
}

public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of processes:");
        int n = sc.nextInt();

        Process[] processes = new Process[n];

        for (int i = 0; i < n; i++) {
            processes[i] = new Process();
            processes[i].processID = i + 1;
            System.out.println("Enter arrival time for process " + (i + 1) + ":");
            processes[i].arrivalTime = sc.nextInt();
            System.out.println("Enter burst time for process " + (i + 1) + ":");
            processes[i].burstTime = sc.nextInt();
            processes[i].remainingBurstTime = processes[i].burstTime;
        }

        int currentTime = 0;
        int completed = 0;
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;

        while (completed < n) {
            boolean allCompleted = true;

            for (int i = 0; i < n; i++) {
                Process p = processes[i];
                if (p.remainingBurstTime > 0) {
                    allCompleted = false;
                    int timeToProcess = Math.min(p.remainingBurstTime, 4);
                    p.remainingBurstTime -= timeToProcess;
                    currentTime += timeToProcess;

                    if (p.remainingBurstTime == 0) {
                        p.turnaroundTime = currentTime - p.arrivalTime;
                        p.waitingTime = p.turnaroundTime - p.burstTime;
                        totalWaitingTime += p.waitingTime;
                        totalTurnaroundTime += p.turnaroundTime;
                        completed++;
                    }
                }
            }

            if (allCompleted) break;
        }

        System.out.println("\nProcess   Arrival Time   Burst Time   Waiting Time   Turnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println(processes[i].processID + "\t\t" + processes[i].arrivalTime + "\t\t" + processes[i].burstTime + "\t\t" + processes[i].waitingTime + "\t\t" + processes[i].turnaroundTime);
        }

        System.out.println("\nAverage Waiting Time: " + (totalWaitingTime / n));
        System.out.println("Average Turnaround Time: " + (totalTurnaroundTime / n));

        sc.close();
    }
}
