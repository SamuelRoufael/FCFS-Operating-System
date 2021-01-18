
public class FCFS {
	boolean busy;
	Process runningProcess = null;

	public FCFS() {
		busy = false;
	}

	public void addProcess(Process process) {
		if (busy == false) {
			run(process);
		}
	}

	void run(Process process) {		
		busy = true;
		process.start();
		runningProcess = process;
		Looper looper = new Looper();
		looper.start();
	}

}
class Looper extends Thread{

	@Override
	public void run() {

		while (Process.getProcessState(OperatingSystem.scheduler.runningProcess) != ProcessState.Terminated) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (OperatingSystem.readyQueue.isEmpty())
			OperatingSystem.scheduler.busy = true;
		else {
			OperatingSystem.scheduler.run(OperatingSystem.readyQueue.poll());
		}

	}

}
