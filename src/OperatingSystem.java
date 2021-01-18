import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class OperatingSystem {
	public static ArrayList<Process> processTable = new ArrayList<>();
	public static Queue<Process> readyQueue = new LinkedList<Process>();
	public static MySemaphore readFileSemaphore = new MySemaphore("read");
	public static MySemaphore writeFileSemaphore = new MySemaphore("write");
	public static MySemaphore inputSemaphore = new MySemaphore("input");
	public static MySemaphore printSemaphore = new MySemaphore("print");
	public static FCFS scheduler = new FCFS();
	
	//system calls:
	// 1- Read from File
	public static String readFile(String name) {
		String Data="";
		File file = new File(name);
	 try {
		Scanner scan = new Scanner(file);
		while (scan.hasNextLine())
		{
			Data+= scan.nextLine()+"\n";
		}
		scan.close();
	} catch (FileNotFoundException e) {
		System.out.println(e.getMessage());
	}
		return Data;
	}
	
	// 2- Write into file
	@SuppressWarnings("unused")
	public static void writefile(String name, String data) {
		try
		{
			BufferedWriter BW = new BufferedWriter(new FileWriter(name));
			BW.write(data);
			BW.close();
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}

	}
	//3- print to console
	@SuppressWarnings("unused")
	public static void printText(String text) {

		System.out.println(text);
		
	}
	
	//4- take input
	
	@SuppressWarnings("resource")
	public static String TakeInput() {
		Scanner in= new Scanner(System.in);
		String data = in.nextLine();
		return data;
		
	}
	
	
	private static void createProcess(int processID){		
		Process p = new Process(processID);
		processTable.add(p);
		Process.setProcessState(p,ProcessState.Ready);
		System.out.println(readyQueue);
		scheduler.addProcess(p);
	}
	
// NOTE: remove the comments and comment the createProcess method, if you want to test the OS using semaphores ONLY (the scheduler won't be involved).
	
//	private static void createProcess(int processID){
//		Process p = new Process(processID);
//		processTable.add(p);
//		Process.setProcessState(p,ProcessState.Ready);
//		p.start();
//	}
	
	public static void printProcesses() {
		for (Process process : processTable) {
			System.out.println(process.getName() + " : " + Process.getProcessState(process));
		}
		System.out.println();
	}
	
	public static void main(String[] args) {	
		createProcess(1);
		createProcess(2);
		createProcess(3);
		createProcess(3);
		createProcess(3);
		createProcess(4);
		createProcess(5);
		createProcess(3);
	}
}



