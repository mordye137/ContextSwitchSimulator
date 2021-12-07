import java.util.Random;

public class SimProcessor {

    SimProcess currProcess;
    int currInstruction;
    int reg1, reg2, reg3, reg4;

    public SimProcess getCurrProcess() { return currProcess; }
    public void setCurrProcess(SimProcess currProcess) {
        this.currProcess = currProcess;
    }

    public int getCurrInstruction() { return currInstruction; }
    public void setCurrInstruction(int currInstruction) {
        this.currInstruction = currInstruction;
    }

    public void setReg1(int reg1) { this.reg1 = reg1;}
    public void setReg2(int reg2) { this.reg2 = reg2;}
    public void setReg3(int reg3) { this.reg3 = reg3;}
    public void setReg4(int reg4) { this.reg4 = reg4;}

    public int getReg1() { return reg1; }
    public int getReg2() { return reg2; }
    public int getReg3() { return reg3; }
    public int getReg4() { return reg4; }

    public ProcessState executeNextInstruction() {

        ProcessState processStateResult = currProcess.execute(currInstruction);

        currInstruction++;

        setReg1(new Random().nextInt(500));
        setReg2(new Random().nextInt(500));
        setReg3(new Random().nextInt(500));
        setReg4(new Random().nextInt(500));

        return processStateResult;
    }
}