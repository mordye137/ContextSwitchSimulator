import java.util.*;

public class Main {

    public static void main(String[] args) {
        Random rand = new Random();
        final int QUANTUM = 5;
        List<String> processNameList = Arrays.asList("Chrome", "Word", "Zoom", "Spotify", "Discord",
                                                     "Whatsapp", "VS Code", "Excel", "Audacity", "Minesweeper");
        SimProcessor processor = new SimProcessor();
        Queue<ProcessControlBlock> readyProcesses = new LinkedList<>();
        ArrayList<ProcessControlBlock> blockedProcesses = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            SimProcess process = new SimProcess(i, processNameList.get(i), (rand.nextInt(31) + 10) * 10);
            ProcessControlBlock PCB = new ProcessControlBlock(process);
            readyProcesses.add(PCB);
        }

        for (int i = 0; i < 3001; i++) {
            int counter = 0;
            if (readyProcesses.isEmpty()) {
                blockedToReadyCheck(blockedProcesses, readyProcesses);
                continue;
            }
            ProcessControlBlock currentPCB = readyProcesses.remove();
            contextRestore(processor, currentPCB);

            while (counter < QUANTUM) {
                ProcessState state = processor.executeNextInstruction();
                if (state == ProcessState.READY) {
                    if (counter == 4) {
                        System.out.println("*** Quantum Expired ***");
                        contextSave(processor, currentPCB);
                        readyProcesses.add(currentPCB);
                    }
                    counter++;
                }
                if (state == ProcessState.BLOCKED) {
                    System.out.println("*** Process Blocked ***");
                    contextSave(processor, currentPCB);
                    blockedProcesses.add(currentPCB);
                    break;
                }
                if (state == ProcessState.FINISHED) {
                    System.out.println("*** Process Finished ***");
                    contextSave(processor, currentPCB);
                    break;
                }
            }

            blockedToReadyCheck(blockedProcesses, readyProcesses);
            System.out.println("Step " + (i + 1) + " complete");
        }
    }

    public static void blockedToReadyCheck(ArrayList blockedList, Queue readyList) {
        for (int i = 0; i < blockedList.size(); i++) {
            if (blockedToReadyChance()){
                readyList.add(blockedList.get(i));
                blockedList.remove(i);
            }
        }
    }

    public static void contextSave(SimProcessor processor, ProcessControlBlock pcb) {
        pcb.setCurrentInstruction(processor.getCurrInstruction());
        pcb.setReg1(processor.getReg1());
        pcb.setReg2(processor.getReg2());
        pcb.setReg3(processor.getReg3());
        pcb.setReg4(processor.getReg4());
        System.out.println("*** Context switch *** \nSaving process: " + processor.getCurrProcess().procName +
                 " -- Instruction #: " + processor.getCurrInstruction() + " -- R1: " + processor.getReg1() + "" +
                ", R2: " + processor.getReg2() + ", R3: " + processor.getReg3() + ", R4: " + processor.getReg4());
    }

    public static void contextRestore(SimProcessor processor, ProcessControlBlock pcb) {
        processor.setCurrProcess(pcb.getCurrProcess());
        processor.setCurrInstruction(pcb.getCurrentInstruction());
        processor.setReg1(pcb.getReg1());
        processor.setReg2(pcb.getReg2());
        processor.setReg3(pcb.getReg3());
        processor.setReg4(pcb.getReg4());
        System.out.println("Restoring process: " + pcb.getCurrProcess().procName +
                " -- Instruction #: " + pcb.getCurrentInstruction() + " -- R1: " + pcb.getReg1() + "" +
                ", R2: " + pcb.getReg2() + ", R3: " + pcb.getReg3() + ", R4: " + pcb.getReg4() + ".");
    }

    public static boolean blockedToReadyChance(){
        return new Random().nextInt(20)<3;
    }
}
