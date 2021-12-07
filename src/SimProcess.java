import java.util.Random;

public class SimProcess {

    int pid, totalInstructions;
    String procName;

    public SimProcess(int pid, String procName, int totalInstructions) {
        this.pid = pid;
        this.procName = procName;
        this.totalInstructions = totalInstructions;
    }

    public ProcessState execute(int i) {
        System.out.println("PID: " + pid + " -- Name: " + procName + " -- Executing Instruction: " + (i + 1));
        if (i >= totalInstructions)
            return ProcessState.FINISHED;
        else if (new Random().nextInt(20)<3)
            return ProcessState.BLOCKED;
        else
            return ProcessState.READY;
    }

    private boolean BlockedProbability(){
        return new Random().nextInt(15)==0;
    }
}
