public class ProcessControlBlock {

    SimProcess currProcess;
    int currentInstruction;
    int reg1, reg2, reg3, reg4;

    public ProcessControlBlock(SimProcess process) {
        this.currProcess = process;
    }
    public SimProcess getCurrProcess() { return currProcess; }

    public int getCurrentInstruction() { return currentInstruction; }
    public void setCurrentInstruction(int currentInstruction) { this.currentInstruction = currentInstruction; }

    public void setReg1(int reg1) { this.reg1 = reg1;}
    public void setReg2(int reg2) { this.reg2 = reg2;}
    public void setReg3(int reg3) { this.reg3 = reg3;}
    public void setReg4(int reg4) { this.reg4 = reg4;}

    public int getReg1() { return reg1; }
    public int getReg2() { return reg2; }
    public int getReg3() { return reg3; }
    public int getReg4() { return reg4; }

}
