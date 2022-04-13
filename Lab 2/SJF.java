import java.util.Scanner;

public class SJF {

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    System.out.print("Enter number of process: ");
    int n = s.nextInt(); 
    int[] burstTime = new int[n];
    int[] pid = new int[n]; // process id
    int[] waitTime = new int[n];
    int[] turnAroundTime = new int[n];
    System.out.println("Enter burst time for each processes:");
    for (int i = 0; i < n; i++) {
      System.out.printf("Job %d = ", i + 1); // save each burst time in array
      burstTime[i] = s.nextInt();
      pid[i] = i+1; // because i = 0, pid starts from 1
    }
    sortAccordingBurstTime(n, burstTime, pid);
    waitingTime(n, burstTime, waitTime);
    turnAroundTime(n, burstTime, waitTime, turnAroundTime);
    System.out.println("Process ID\tBurst Time\tWaiting Time\tTurnaround Time");
    for(int i=0;i<n;i++){
        System.out.println(pid[i]+"\t\t"+burstTime[i]+"\t\t"+waitTime[i]+"\t\t"+turnAroundTime[i]);
    }

    // calculate average waiting and turnaround time
    float totalWait = 0;
    float totalTurnAround = 0;
    for (int i = 0; i < n; i++) {
      totalWait += waitTime[i];
      totalTurnAround += turnAroundTime[i];
    }
    System.out.printf("\nAverage waiting time: %.2f", totalWait / n);
    System.out.printf("\nAverage Turnaround time: %.2f", totalTurnAround / n);
  }

   static void sortAccordingBurstTime(int n, int burst[], int pid[])
    {
        boolean swapped;
        int temp, temp2;
        for (int i = 0; i < n; i++) 
        {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) 
            {
                if (burst[j] > burst[j + 1]) 
                {
                    //swapping burst time
                    temp = burst[j];
                    burst[j] = burst[j + 1];
                    burst[j + 1] = temp;

                    //swapping process id
                    temp2 = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = temp2;

                    //enhanced bubble sort
                    swapped = true;
                }
            }
            if (swapped == false) 
            {
                break;
            }
        }
    }
   
  static void waitingTime(int n, int burst[], int wait[]) {
    // waiting time for first process is 0
    wait[0] = 0;

    // calculate wiating time for next process
    for (int i = 1; i < n; i++) {
      wait[i] = burst[i - 1] + wait[i - 1];
    }
  }

  static void turnAroundTime(int n, int burst[], int wait[], int turnAround[]) {
    // calculate turn around time by add
    // burst time[i] + waiting time[i]
    for (int i = 0; i < n; i++) {
      turnAround[i] = burst[i] + wait[i];
    }
  }
}
