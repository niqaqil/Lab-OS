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
      System.out.printf("job %d = ", i + 1); // save each burst time in array
      burstTime[i] = s.nextInt();
    }
    waitingTime(n, burstTime, waitTime);
    turnAroundTime(n, burstTime, waitTime, turnAroundTime);
    System.out.println("Waiting time and Turnaround time for each job:");
    System.out.println("Job: \tWaiting time, \tTurnaround time");
    for (int i = 0; i < n; i++) {
      System.out.printf("job %d: \t%d, \t%d\n", i + 1, waitTime[i], turnAroundTime[i]);
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