import java.util.Scanner;
class Process{
    int processID;
    int arrivalTime;
    int burstTime;
    int turnaroundTime;
    int waitingTime;
    int remainingTime;
}
public class SJFPree{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the num of processes:");
        int n=sc.nextInt();

        Process [] processes=new Process[n];

        for(int i=0;i<n;i++){
            processes[i]=new Process();
            processes[i].processID=i+1;
            System.out.println("Enter the arrival time : ");
            processes[i].arrivalTime=sc.nextInt();
            System.out.println("Enter the burst Time:");
            processes[i].burstTime=sc.nextInt();
            processes[i].remainingTime=processes[i].burstTime;

        }
        int currentTime=0;
        int completedProcesses=0;
        double totalWaitingTime=0;
        double totalTurnaroundTime=0;

        while(completedProcesses<n){
            int shortestIndex=-1;
            int minRemainingTime=Integer.MAX_VALUE;

            for(int i=0;i<n;i++){
                if(processes[i].remainingTime > 0  && processes[i].arrivalTime<=currentTime && processes[i].remainingTime<minRemainingTime){
                    minRemainingTime=processes[i].remainingTime;
                    shortestIndex=i;
                }
            }
            if(shortestIndex==-1){
                currentTime++;
            }else{
                processes[shortestIndex].remainingTime--;
                currentTime++;
                
                if(processes[shortestIndex].remainingTime==0){
                    completedProcesses++;
                    processes[shortestIndex].turnaroundTime=currentTime-processes[shortestIndex].arrivalTime;
                    processes[shortestIndex].waitingTime=processes[shortestIndex].turnaroundTime-processes[shortestIndex].burstTime;

                    totalTurnaroundTime+=processes[shortestIndex].turnaroundTime;
                    totalWaitingTime+=processes[shortestIndex].waitingTime;
                    
                }
            }
        }
        System.out.println("\nProcess   Arrival Time    Burst Time  Waiting Time    Turnaround Time");
        for(int i=0;i<n;i++){
            System.out.println(processes[i].processID+"\t\t"+processes[i].arrivalTime+"\t\t"+processes[i].burstTime+"\t\t"+processes[i].waitingTime+"\t\t"+processes[i].turnaroundTime);
        }

        System.out.println("\nAverage Waiting time : "+totalWaitingTime/n);
        System.out.println("\nAverage Turnaround Time : "+totalTurnaroundTime/n);
     sc.close();
    }
}