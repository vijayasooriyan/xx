import java.util.Scanner;

class Process {
    int processID;
    int arrivalTime;
    int burstTime;
    int waitingTime;
    int turnaroundTime;
    boolean completed;
}
public class sj{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter the no of processes :");
        int n=sc.nextInt();
        Process [] processes=new Process[n];

        for(int i=0;i<n;i++){
            processes[i]=new Process();
            processes[i].processID=i+1;
            System.out.println("Enter the arrival time of processes :"+(i+1)+" : ");
            processes[i].arrivalTime=sc.nextInt();
            System.out.println("Enter the burst time of processes :"+(i+1)+" : ");
            processes[i].burstTime=sc.nextInt();
            processes[i].completed=false;
        }

        int currentTime=0;
        int completedProcess=0;
        double totalWaitingTime=0;
        double totalTurnaroundTime=0;

        while(completedProcess<n){
            int shortestIndex=-1;
            int minBurstTime=Integer.MAX_VALUE;

            for(int i=0;i<n;i++){
                if(!processes[i].completed && processes[i].arrivalTime <= currentTime && processes[i].burstTime < minBurstTime){
                    shortestIndex=i;
                    minBurstTime=processes[i].burstTime;
                }
            }

            if(shortestIndex==-1){
                 currentTime++;
                 continue;
            }

            Process curProcess=processes[shortestIndex];
            curProcess.waitingTime=currentTime-curProcess.arrivalTime;
            curProcess.turnaroundTime=curProcess.waitingTime-curProcess.burstTime;

            totalTurnaroundTime+=curProcess.turnaroundTime;
            totalWaitingTime+=curProcess.waitingTime;

            currentTime+=curProcess.burstTime;
            curProcess.completed=true;
            completedProcess++;

        }

        System.out.println("\nProcess   Arrival Time    BurstTime   waiting Time    Turnaround Time");
        for(int i=0;i<n;i++){
            System.out.println(processes[i].processID+"\t\t"+processes[i].arrivalTime+"\t\t"+processes[i].burstTime+"\t\t"+processes[i].waitingTime+"\t\t"+processes[i].turnaroundTime);
    }

    System.out.println("\tAverage waiting time"+totalWaitingTime/n);
    System.out.println("\tAverage turnAround Time"+totalTurnaroundTime/n);

    sc.close();
    }
}