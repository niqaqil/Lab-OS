import java.util.Scanner;

public class FCFS {

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    System.out.print("Enter number of process: ");
    int n = s.nextInt(); 
    int[] burstTime = new int[n];
    int[] waitTime = new int[n];
    int[] turnAroundTime = new int[n];
    System.out.println("Enter burst time for each processes:");
    for (int i = 0; i < n; i++) {
      System.out.printf("Job %d = ", i + 1); // save each burst time in array
      burstTime[i] = s.nextInt();
    }
    waitingTime(n, burstTime, waitTime);
    turnAroundTime(n, burstTime, waitTime, turnAroundTime);
    System.out.println("Waiting time and Turnaround time for each job:");
    System.out.println("Job \tWaiting time \tTurnaround time");
    //printing out the table
    for (int i = 0; i < n; i++) {
      System.out.printf("%d: \t%d, \t\t%d\n", i + 1, waitTime[i], turnAroundTime[i]); 
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
