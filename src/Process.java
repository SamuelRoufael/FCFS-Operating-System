//import java.util.concurrent.Semaphore;


public class Process extends Thread {

	public int processID;
	ProcessState status=ProcessState.New;	


	public Process(int m) {
		processID = m;
		switch(processID) {
		case 1 : setName("Process 1");break;
		case 2 : setName("Process 2");break;
		case 3 : setName("Process 3");break;
		case 4 : setName("Process 4");break;
		case 5 : setName("Process 5");break;
		}
	}
	
	@Override
	public void run() {

		switch(processID)
		{
		case 1:
			process1();
			break;
		case 2:
			process2();
			break;
		case 3:
			process3();
			break;
		case 4:
			process4();
			break;
		case 5:
			process5();
			break;
		}

	}

	private void process1() {
		
		OperatingSystem.printSemaphore.down(this);
		OperatingSystem.inputSemaphore.down(this);
		Process.setProcessState(this, ProcessState.Running);
		OperatingSystem.printText(this.getName() + " : Enter File Name: ");
		String fileName = OperatingSystem.TakeInput();
		OperatingSystem.printSemaphore.up();
		OperatingSystem.inputSemaphore.up();

		OperatingSystem.readFileSemaphore.down(this);
		Process.setProcessState(this, ProcessState.Running);
		String data = OperatingSystem.readFile(fileName);
		OperatingSystem.readFileSemaphore.up();
		
		OperatingSystem.printSemaphore.down(this);
		Process.setProcessState(this, ProcessState.Running);
		OperatingSystem.printText(data);
		OperatingSystem.printSemaphore.up();

		setProcessState(this,ProcessState.Terminated);

	}

	private void process2() {
		
		OperatingSystem.printSemaphore.down(this);
		OperatingSystem.inputSemaphore.down(this);
		Process.setProcessState(this, ProcessState.Running);
		OperatingSystem.printText(this.getName() + " : Enter File Name:");
		String filename= OperatingSystem.TakeInput();
		OperatingSystem.printText(this.getName() + " : Enter Data:");
		String data= OperatingSystem.TakeInput();
		OperatingSystem.printSemaphore.up();
		OperatingSystem.inputSemaphore.up();
	
		OperatingSystem.writeFileSemaphore.down(this);
		Process.setProcessState(this, ProcessState.Running);
		OperatingSystem.writefile(filename,data);
		OperatingSystem.writeFileSemaphore.up();

		setProcessState(this,ProcessState.Terminated);
	}

	private void process3() {
		int x=0;
		OperatingSystem.printSemaphore.down(this);
		Process.setProcessState(this, ProcessState.Running);
		while (x<11)
		{ 
			OperatingSystem.printText(this.getName() + " : "+x+"\n");
			x++;
		}
		OperatingSystem.printSemaphore.up();
		
		setProcessState(this,ProcessState.Terminated);
	}

	private void process4() {

		int x=500;
		OperatingSystem.printSemaphore.down(this);
		Process.setProcessState(this, ProcessState.Running);
		while (x<511)
		{
			OperatingSystem.printText(this.getName() + " : "+x+"\n");
			x++;
		}	
		OperatingSystem.printSemaphore.up();
		
		setProcessState(this,ProcessState.Terminated);
	}

	private void process5() {
		
		OperatingSystem.printSemaphore.down(this);
		OperatingSystem.inputSemaphore.down(this);
		Process.setProcessState(this, ProcessState.Running);
		OperatingSystem.printText(this.getName() + " : Enter LowerBound: ");
		String lower= OperatingSystem.TakeInput();
		OperatingSystem.printText(this.getName() + " : Enter UpperBound: ");
		String upper= OperatingSystem.TakeInput();
		OperatingSystem.printSemaphore.up();
		OperatingSystem.inputSemaphore.up();
		
		int lowernbr=Integer.parseInt(lower);
		int uppernbr=Integer.parseInt(upper);
		String data="";
		while (lowernbr<=uppernbr)
		{
			data+=lowernbr++ +"\n";
		}	
		
		OperatingSystem.writeFileSemaphore.down(this);
		Process.setProcessState(this, ProcessState.Running);
		OperatingSystem.writefile("P5.txt", data);
		OperatingSystem.writeFileSemaphore.up();
		
		setProcessState(this,ProcessState.Terminated);
	}

	public static void setProcessState(Process process, ProcessState state) {
		process.status=state;
		if (state == ProcessState.Terminated)
			OperatingSystem.processTable.remove(OperatingSystem.processTable.indexOf(process));
		
		else if (state == ProcessState.Ready) 
			OperatingSystem.readyQueue.add(process);
		
		else if (state == ProcessState.Running)
			OperatingSystem.readyQueue.remove(process);
		
		else if (state == ProcessState.Waiting && OperatingSystem.readyQueue.contains(process))
			OperatingSystem.readyQueue.remove(process);
	}

	public static ProcessState getProcessState(Process p) {
		return p.status;
	}
}
