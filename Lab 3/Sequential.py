def recurse(files):
  flag = 0
  temp = 0
  startBlock, len = input("Enter the starting block and length of files: ").split()
 
  #convert string to integer
  startBlock = int(startBlock)
  len = int(len)
 
  # check if the blocks are occupied or not
  for i in range(startBlock, startBlock + len):
    if files[i] == 0:
      flag+=1
 
  #if all requested blocks are free(empty)
  if len == flag:
    for i in range(startBlock, startBlock + len):
 
      #allocate the free blocks
      if files[i] == 0:
        files[i] = 1
        print(i, "\t", files[i])
       
    print("File successfully allocated to the disk\n")    
     
  #else if a requested block is occupied
  else:
    print("\nBlock is occupied. File failed to be allocated to the disk\n")
 
  print("Do you want to enter more files?")
  if int(input("Press 1 for YES, 0 for NO")) == 1:
 
    #recurse this function if user wants to add more files
    recurse(files)
  else:
    return
 
files = [0] * 15
print(files)
print("Performing file allocation...")
recurse(files)
print(files)