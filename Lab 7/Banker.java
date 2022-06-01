import java.util.*;

public class Banker {
  public static void main(String[] args) {
    System.out.println("Bankers Algorithm - Deadlock Avoidance");
    // opt for input
    Scanner scan = new Scanner(System.in);
    System.out.print("No. of Jobs:");
    int size = scan.nextInt();
    // initialize array with its size equals to no. of jobs
    // to store the number of device requested
    int[] devReq = new int[size];
    System.out.print("Devices required for each job: ");
    for (int i = 0; i < size; i++) {
      devReq[i] = scan.nextInt();
    }
    System.out.print("Maximum devices available: ");
    int maxDevice = scan.nextInt();
    deadlock(size, devReq, maxDevice);
  }

  static void deadlock(int size, int[] devReq, int maxDevice) {
    int initialDev = maxDevice;
    int[] remainingDev = new int[size];
    int[] deviceCon = new int[size];
    printline();
    Random r = new Random();
    System.out.println("Job No.\t\tDevice Required\t\tDevices Allocated\tRemaining Devices Required");
    // Allocation of devices to each job
    for (int i = 0; i < size; i++) {
      if (devReq[i] < maxDevice) {
        deviceCon[i] = r.nextInt(devReq[i]);
        remainingDev[i] = devReq[i] - deviceCon[i];
        printJobs(i + 1, devReq[i], deviceCon[i],
            remainingDev[i]);
      } else {
        deviceCon[i] = r.nextInt(maxDevice);
        remainingDev[i] = devReq[i] - deviceCon[i];
        printJobs(i + 1, devReq[i], deviceCon[i],
            remainingDev[i]);
      }
      maxDevice -= deviceCon[i];
    }
    while (true) {
      int temp = 0;
      int location = 0;
      int counter = 0;
      // find the job that needs the least remaining device required
      for (int i = 0; i < size; i++) {
        if (temp == 0 && remainingDev[i] != 0) {
          temp = remainingDev[i];
          location = i;
        } else if (remainingDev[i] < temp && remainingDev[i] != 0) {
          temp = remainingDev[i];
          location = i;
        }
      }
      // if in unsafe state
      if (!printDetails((initialDev - maxDevice), maxDevice,
          temp)) {
        printline();
        System.out
            .println("Remaining Available Devices are now allocate to Job " + (location + 1) + " to finish the job");
        System.out.println("All jobs still need extra devices to finish execution.");
        printline();
        // move all available devices to the job with minimum remaining devices required
        remainingDev[location] -= maxDevice;
        deviceCon[location] += maxDevice;
        maxDevice = 0;
        System.out.println("Job No.\t\t Max Devices Required\tDevices Allocated\tRemaining Devices Required");
        for (int i = 0; i < size; i++) {
          printJobs(i + 1, devReq[i], deviceCon[i],
              remainingDev[i]);
        }
        printDetails((initialDev - maxDevice), maxDevice, temp);
        // break the function and return deadlock
        break;
      }
      // if in safe state
      printline();
      System.out
          .println("Remaining Available Devices are now allocated to Job " + (location + 1) + " to finish the job.");
      System.out.println("Job " + (location + 1) + " is done. Returning devices to Available list.");
      printline();
      // move all available devices to job with minimum remaining devices required;
      // set everything to 0
      maxDevice += deviceCon[location];
      remainingDev[location] = 0;
      deviceCon[location] = 0;
      System.out.println("Job No. \t\tMax Devices Required\t Devices Allocated" + "\tRemaining Devices Required");
      for (int i = 0; i < size; i++) {
        printJobs(i + 1, devReq[i], deviceCon[i],
            remainingDev[i]);
        if (remainingDev[i] == 0) {
          counter += 1; // counter when the device is returned back to available device list once the
          // job is finished
        }
      }
      if (counter == size) { // counter to check whether all job have been completed
        printDetails((initialDev - maxDevice), maxDevice, temp);
        break;
      }
    }
  }

  // print job details
  static void printJobs(int i, int j, int k, int l) {
    System.out.println(i + "\t\t\t" + j + "\t\t\t" + k + "\t\t\t" +
        l);
  }

  // print execution details
  static boolean printDetails(int i, int j, int k) {
    System.out.println("Total Devices Allocated to Jobs\t:" + i);
    System.out.println("Remaining Available Devices\t:" + j);
    if (j < k && j != 0) {
      System.out.println("Current Condition:: Unsafe");
      return false;
    } else if (j >= k && j != 0) {
      System.out.println("Current Condition: Safe");
      return true;
    } else {
      System.out.println("Uh-Oh, DEADLOCK OCCURS");
    }
    return true;
  }

  static void printline() {
    System.out.println("---------------------" +
        "--------------------");
  }
}
