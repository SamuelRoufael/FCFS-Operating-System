import java.util.LinkedList;
import java.util.Queue;

public class MySemaphore {
	int value;
	Queue<Process> blockedProcesses;
	String MySemaphoreName;

	public MySemaphore(String name) {
		value = 1; 
		blockedProcesses = new LinkedList<Process>();
		MySemaphoreName = name;
	}

	public void down(Process process) {
		while (value == 0 || blockedProcesses.contains(Thread.currentThread())){
			try {
				if(!blockedProcesses.contains(Thread.currentThread())) {
					blockedProcesses.add((Process)Thread.currentThread());
					Process.setProcessState(process, ProcessState.Waiting);
				}
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		value = 0;
	}

	public void up() {
		value = 1;
		Process process = blockedProcesses.poll();
		if (process!= null)
			Process.setProcessState(process, ProcessState.Ready);
	}

}
