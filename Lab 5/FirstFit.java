import java.util.Scanner;

public class FirstFit {
  
  // Code for Lab 5 - First Fit
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
 
        // input the number of free memory blocks to run programs
        System.out.print("Enter number of free memory blocks to run programs: ");
        int block = sc.nextInt();
 
        // input the number of programs waiting to run (job)
        System.out.print("Enter number of programs/jobs waiting to run: ");
        int job = sc.nextInt();
 
        // create array according to input sizes
        int blockSize[] = new int[block];
        int jobSize[] = new int[job];
 
        // array to store job size in blocks
        int currentBlock[] = new int[block];
 
        // array to update memory block status that has been occupied
        int blockStatus[] = new int[block];
 
        // input size of each memory blocks
        System.out.print("Enter the size of each memory blocks: ");
        for (int i = 0; i < block; i++) {
            blockSize[i] = sc.nextInt();
        }
 
        // input the size of program/job
        System.out.print("Enter the size of each job: ");
        for (int i = 0; i < job; i++) {
            jobSize[i] = sc.nextInt();
        }
 
        // to print Job List table
        System.out.println("\nJob List: \nJob Number\tMemory Requested/Job size");
        for (int i = 0; i < job; i++) {
            System.out.println(" " + (i + 1) + "\t\t" + jobSize[i]);
        }
        System.out.println();
 
        // first fit algorithm
        System.out.println("Executing first fit algorithm...");
        for (int i = 0; i < job; i++) {
            for (int j = 0; j < block; j++) {
                if (blockStatus[j] != -1) {   
                    if (jobSize[i] <= blockSize[j]) {
                        currentBlock[j] = jobSize[i];
                        blockStatus[j] = -1;
                        break;
                    }
                }
            }
 
        }
 
        int k = 1;  //to print job number, initialize k=1
 
        // Print output
        System.out.println("\nOutput: \nJob Number\tBlock Number\tInitial Block Size\tOccupied Block Size\tInternal Fragmentation");
        while(k < job) {
            for (int j=0; j<block; j++) {
                if (blockStatus[j] == -1) {
                    System.out.println(" " + (k) + "\t\t\t" + (j + 1) + "\t\t\t" + blockSize[j] + "\t\t\t" + currentBlock[j] + "\t\t\t" + (blockSize[j] - currentBlock[j]));
                    k++;
                }
            }
            break;

  }
}
}
