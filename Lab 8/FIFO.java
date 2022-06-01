import java.util.Scanner;

public class FIFO {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    System.out.println("---Page Replacement Algorithm---");
    // get input from user
    int size = 0; 
    System.out.print("Enter number of pages: ");
    size = scan.nextInt();
    //int[] pages = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2};
    int[] pages = new int[size];
    System.out.print("Enter incoming pages: ");
    for (int i = 0; i < size; i++) {
      pages[i] = scan.nextInt();
    }
    int frame;
    System.out.print("Enter no of frame: ");
    frame = scan.nextInt();
    scan.close();
    pageFaults(frame, pages);

  }

  static void pageFaults(int frame, int[] pages) {
    int m, n ,s, fault = 0;
    System.out.println("\nSimulation of page replacement:");
    System.out.println("Ref String \t Frames");
    int[] temp = new int[frame];
    for (m = 0; m < frame; m++) {
      temp[m] = -1;
    }
    for (m = 0; m < pages.length; m++) {
      s = 0; 
      for (n = 0; n < frame; n++) {
        if (pages[m] == temp[n]) {
          s++;
          fault--;
        }
      }
      fault++;
      if (fault <= frame && s == 0) {
        temp[m] = pages[m];
      }
      else if (s == 0) {
        temp[(fault - 1) % frame] = pages[m];
      }
      System.out.println();
      System.out.printf("%d \t\t", pages[m]);
      for (n = 0; n < frame; n++) {
        if (temp[n] == -1) {
          System.out.print("-\t");
        }
        else {
          System.out.printf("%d\t", temp[n]);
        }
      }
    }
    System.out.printf("\n\nTotal Page Faults: %d\n\n", fault);
  }
}
