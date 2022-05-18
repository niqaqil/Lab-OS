import java.util.Scanner;

public class Paging {
  public static void main(String[] args) {
    Scanner scan =  new Scanner(System.in);

    int  n, pageSize, frame, pagesNo, offset;
    int[] page = new int[50];
    System.out.print("Enter no of pages in memory: ");
    n = scan.nextInt();
    System.out.print("Enter page size: ");
    pageSize = scan.nextInt();
    System.out.print("Enter no of frame: ");
    frame = scan.nextInt();
    
    System.out.println("\nEnter the page table:");
    System.out.println("Enter frame no -1 if the page is not presented in any frame\n");
    System.out.println("Page No. \t Frame No. \n----------\t----------");

    for(int i = 0; i < n; i++) {
      System.out.printf("%d\t\t", i);
      page[i] = scan.nextInt();
    }

    int x = 0;
    do {
      System.out.println("\nEnter logical address(i.e,page no & offset): ");
      System.out.print("Page no: ");
      pagesNo = scan.nextInt();
      System.out.print("offset: ");
      offset = scan.nextInt();
      
      if(page[pagesNo] == -1){
        System.out.println("Page is not available in any frame.");
      }
      else{
        int phyAddress = (page[pagesNo] * pageSize) + offset;
        System.out.printf("Physical address is: %d", phyAddress);
        System.out.printf("\nFrame no and offset is: %d %d", page[pagesNo], offset);
      }
      System.out.print("Do you want to continue? (0/1): ");
      x = scan.nextInt(2);
    } while(x==1);
  }

  
}
