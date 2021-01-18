# FCFS-Operating-System
  Batch processing is a technique in which an Operating System collects similar jobs together in a batch and then starts executing the batches sequentially, one job at a time. In a multi-programmed batch system, the CPU will never be idle. The Operating System keeps multiple jobs in the main memory, these are the jobs waiting to be executed. The Operating System will choose one of these jobs and begins execution. Once this job needs an I/O operation, the Operating System switches to another job. If several jobs are ready to run at the same time, then the Operating System chooses which one to run through the process of CPU Scheduling.

## System Calls
  A system call is the programmatic way in which a process requests a service from
the kernel of the operating system it is executed on.

### Types of system calls :
  1. Read the data of any file from the disk.
  2. Write text input to a file in the disk.
  3. Print data on the screen.
  4. Take text input from the user.
  
## Processes
OS can create different processes and switch between them in the
memory. In order to switch between processes, the OS must have information
about each process such as its state (running, ready ,etc..), and ID. 

### 1- Process 1:
- It should take input from the user: a filename. Then print the content of this file
on the screen.
### 2- Process 2:
- It should take two inputs from the user: a filename, and some data. Then write
the data to the file.
### 3- Process 3:
- It should count and display to the user the numbers from 0 to 300.
### 4- Process 4:
- It should count and display to the user the numbers from 500 to 1000.
### 5- Process 5:
- It should take two inputs from the user: a lower number and a larger number.
Then count from the lower number to the upper number and write the count to a
new file.
