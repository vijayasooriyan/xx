import java.util.Scanner;
class process {
    int processID;
    int arrivalTime;
    int burstTime;
    int waitingTime;
    int turnaroundTime;
}
public class FCFS{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);

        System.out.println("enter the Number of processes :");
        int n=sc.nextInt();

        process[] processes=new process[n];

        for(int i=0;i<n;i++){
            processes[i]=new process();
            processes[i].processID=i+1;
            System.out.println("Enter arrival time for process :");
            processes[i].arrivalTime=sc.nextInt();
            System.out.println("Enter the Burst time for process :");
            processes[i].burstTime=sc.nextInt();
        }

        for(int i=0;i<n-1;i++){
            for(int j=0;j<n-i-1;j++){
                if(processes[j].arrivalTime>processes[j+1].arrivalTime){
                    process temp=processes[j];
                    processes[j]=processes[j+1];
                    processes[j+1]=temp;
                }
            }
        }

        int currentTime=0;
        double totalWaitingTime=0;
        double totalTurnaroundTime=0;

        for(int i=0;i<n;i++){
            if(currentTime<processes[i].arrivalTime){
                currentTime=processes[i].arrivalTime;
            } 
            processes[i].waitingTime=currentTime-processes[i].arrivalTime;
            processes[i].turnaroundTime=processes[i].waitingTime-processes[i].burstTime;

            totalWaitingTime+=processes[i].waitingTime;
            totalTurnaroundTime+=processes[i].turnaroundTime;

            currentTime+=processes[i].burstTime;

        }

        System.out.println("\nProcess   Arrival Time    Burst Time  Waiting Time    Turnaround Time");
        for(int i=0;i<n;i++){
            System.out.println(processes[i].processID +"\t\t"+processes[i].arrivalTime+"\t\t"+processes[i].burstTime+"\t\t"+processes[i].waitingTime+"\t\t"+processes[i].turnaroundTime);

        }

        System.out.println("\nAverage Waiting Time : "+totalWaitingTime/n);
        System.out.println("\nAverage Turn Around Time : "+totalTurnaroundTime/n);
     
        sc.close();
    }
}