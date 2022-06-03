package spicyVonNeumannFilletWithExtraShifts;

import java.math.BigInteger;
import java.util.LinkedList;

public class MemoryArchitecture {

	String[] memory;
	String[] instructions;
	Register[] registerFile;
	int clockCycle = 1;
	int noOfInstructions, noOfCompletedInstructions, totalNumberOfInstructions, loadedWord, pc, rs, rt, rd, shamt, imm,
			address, valueRD, valueRS, valueRT;
	long opcode;
	String currentInstruction;
	int ClockCyclesTotal;
	OpcodeValue OPCODE;
	LinkedList<Instruction> fetchQueue = new LinkedList<Instruction>();
	LinkedList<Instruction> decodeQueue1 = new LinkedList<Instruction>();
	LinkedList<Instruction> decodeQueue2 = new LinkedList<Instruction>();
	LinkedList<Instruction> executeQueue1 = new LinkedList<Instruction>();
	LinkedList<Instruction> executeQueue2 = new LinkedList<Instruction>();
	LinkedList<Instruction> memoryQueue = new LinkedList<Instruction>();
	LinkedList<Instruction> writeBackQueue = new LinkedList<Instruction>();
	LinkedList<Instruction> finishQueue = new LinkedList<Instruction>();

	public int getClockCyclesTotal() {
		return ClockCyclesTotal;
	}

	public void setClockCyclesTotal(int clockCyclesTotal) {
		ClockCyclesTotal = clockCyclesTotal;
	}

	public MemoryArchitecture() {
		this.memory = new String[2048];
		this.registerFile = new Register[33];
		this.pc = 0;
		for (int i = 0; i < this.registerFile.length; i++) {
			if (i == 32) {
				this.registerFile[i] = new Register("PC", 0);
			} else {
				this.registerFile[i] = new Register("R" + i, 0);
			}
		}
	}

	public long fetch() {
		this.currentInstruction = this.getInstruction(pc);
		long instruction = (this.parseLong(this.currentInstruction, 2)) & 0xffffffffL;
		this.pc++;
		this.getRegisterFile()[32].setValueInRegister(this.pc);
		return instruction;
	}

	public void decode(Instruction i) {
		long instruction = i.getInstVal();
		long temp = instruction & 0b11110000000000000000000000000000;
		opcode = temp >> 28;
		temp = instruction & 0b00001111100000000000000000000000;
		rd = (int) temp >> 23;
		temp = instruction & 0b00000000011111000000000000000000;
		rs = (int) temp >> 18;
		temp = instruction & 0b00000000000000111110000000000000;
		rt = (int) temp >> 13;
		temp = instruction & 0b00000000000000000001111111111111;
		shamt = (int) temp;
		if (currentInstruction.charAt(14) == '0') {
			imm = (int) instruction & 0b00000000000000111111111111111111;
		} else {
			imm = this.negative2sComplement(currentInstruction.substring(14));
		}
		address = (int) instruction & 0b00001111111111111111111111111111;
		valueRD = this.getRegisterFile()[rd].getValueInRegister();
		valueRS = this.getRegisterFile()[rs].getValueInRegister();
		valueRT = this.getRegisterFile()[rt].getValueInRegister();
		i.setOpcode(opcode);
		i.setRd(rd);
		i.setRs(rs);
		i.setRt(rt);
		i.setShamt(shamt);
		i.setImm(imm);
		i.setValueRD(valueRD);
		i.setValueRS(valueRS);
		i.setValueRT(valueRT);
		i.setAddress(address);
	}

	public void execute(Instruction i) {
		if (i.getOpcode() == 0) {
			OPCODE = OpcodeValue.ZERO;
		} else if (i.getOpcode() == 1) {
			OPCODE = OpcodeValue.ONE;
		} else if (i.getOpcode() == 2) {
			OPCODE = OpcodeValue.TWO;
		} else if (i.getOpcode() == 3) {
			OPCODE = OpcodeValue.THREE;
		} else if (i.getOpcode() == 4) {
			OPCODE = OpcodeValue.FOUR;
		} else if (i.getOpcode() == 5) {
			OPCODE = OpcodeValue.FIVE;
		} else if (i.getOpcode() == 6) {
			OPCODE = OpcodeValue.SIX;
		} else if (i.getOpcode() == 7) {
			OPCODE = OpcodeValue.SEVEN;
		} else if (i.getOpcode() == 8) {
			OPCODE = OpcodeValue.EIGHT;
		} else if (i.getOpcode() == 9) {
			OPCODE = OpcodeValue.NINE;
		} else if (i.getOpcode() == 10) {
			OPCODE = OpcodeValue.TEN;
		} else if (i.getOpcode() == 11) {
			OPCODE = OpcodeValue.ELEVEN;
		}
		switch (OPCODE) {
		case ZERO:
			i.setResult(i.getValueRS() + i.getValueRT());
			i.setDataflagForR1(true);
			break;
		case ONE:
			i.setResult(i.getValueRS() - i.getValueRT());
			i.setDataflagForR1(true);
			break;
		case TWO:
			i.setResult(i.getValueRS() * i.getImm());
			i.setDataflagForR1(true);
			break;
		case THREE:
			i.setResult(i.getValueRS() + i.getImm());
			i.setDataflagForR1(true);
			break;
		case FOUR:
			if (i.getValueRD() != i.getValueRS()) {
				i.setResult(pc + i.getImm());
				i.setDataflagForPC(true);
			}
			break;
		case FIVE:
			i.setResult(i.getValueRS() & i.getImm());
			i.setDataflagForR1(true);
			break;
		case SIX:
			i.setResult(i.getValueRS() | i.getImm());
			i.setDataflagForR1(true);
			break;
		case SEVEN:
			int temp = registerFile[32].getValueInRegister() & 0b11110000000000000000000000000000;
			String tempStr = this.makeBinaryString(temp);
			String addressResult = this.toBinary(i.getAddress(), 28);
			String resultStr = tempStr + addressResult;
			i.setResult(Integer.parseInt(resultStr, 2));
			i.setDataflagForR1(true);
			break;
		case EIGHT:
			i.setResult(i.getValueRS() << i.getShamt());
			i.setDataflagForR1(true);
			break;
		case NINE:
			i.setResult(i.getValueRS() >>> i.getShamt());
			i.setDataflagForR1(true);
			break;
		case TEN:
			i.setResult(i.getValueRS() + i.getImm());
			i.setDataflagForLW(true);
			i.setDataflagForR1(true);
			break;
		case ELEVEN:
			i.setResult(i.getValueRS() + i.getImm());
			i.setDataflagForSW(true);
			break;
		default:
			i.setResult(0);
			break;
		}
		if (i.getResult() == 0) {
			i.setZeroFlag(1);
		}
	}

	public void removeALLInQueue(LinkedList<Instruction> q) {
		while (!q.isEmpty()) {
			q.remove();
		}
	}

	public void memory(Instruction i) {
		if (i.isDataflagForPC()) {
			String x;
			x = this.getInstruction(i.getResult());
			if (x == null) {
				System.out.println("\n--------> Cannot perform jump!" + "\n" + "\n");
			} else {
				pc = i.getResult();
				this.getRegisterFile()[32].setValueInRegister(i.getResult());
				i.setDataflagForPC(false);
			}
		}
		if (i.isDataflagForLW()) {
			String str = getData(i.getResult());
			if (str.equals("")) {
				System.out.println("\n--------> The location is unaccessessble!" + "\n" + "\n");
			} else {
				i.setLoadedWord(Integer.parseInt(str, 2));
			}
		}
		if (i.isDataflagForSW()) {
			this.setData(toBinary(i.getValueRD(), 32), i.getResult());
			System.out.println("\n--------> Memory data element at address " + i.getResult() + " was populated by "
					+ toBinary(i.getValueRD(), 32));
			i.setDataflagForSW(false);
		}
	}

	public void writeback(Instruction i) {
		if (i.isDataflagForR1() && !(i.isDataflagForLW())) {
			if (i.getRd() == 0) {
				System.out.print("\n--------> You cannot overwrite R0!" + "\n" + "\n");
			} else {
				System.out.print("\n--------> Value of register " + this.getRegisterFile()[i.getRd()].getName()
						+ " changed from " + this.getRegisterFile()[i.getRd()].getValueInRegister());
				this.getRegisterFile()[i.getRd()].setValueInRegister(i.getResult());
				i.setDataflagForR1(false);
				System.out.println(" to " + this.getRegisterFile()[i.getRd()].getValueInRegister());
			}
		}
		if (i.isDataflagForR1() && i.isDataflagForLW()) {
			System.out.print("\n--------> Value of register " + this.getRegisterFile()[i.getRd()].getName()
					+ " changed from " + this.getRegisterFile()[i.getRd()].getValueInRegister());
			this.getRegisterFile()[i.getRd()].setValueInRegister(i.getLoadedWord());
			i.setDataflagForLW(false);
			System.out.println(" to " + this.getRegisterFile()[i.getRd()].getValueInRegister());
		}
	}

	public void pipeline() {
		while (noOfCompletedInstructions != noOfInstructions) {
			if (clockCycle == 1) {
				System.out.println("_________________SIMULATION STARTED_________________");
			}
			if (clockCycle == ClockCyclesTotal + 1) {
				System.out.println("----------AT THE END OF THE SIMULATION----------\n");
			} else {
				System.out.println("----------AT CLOCK CYCLE " + clockCycle + "----------" + "\n");
			}
			if (clockCycle % 2 == 1) {
				if (memory[pc] != null) {
					long inst = this.fetch();
					Instruction i = new Instruction();
					i.setInstVal(inst);
					i.setInstID(pc);
					fetchQueue.add(i);
					System.out.println("FETCHING STAGE INSTRUCTIONS: " + "\n");
					printQueue(fetchQueue);
					System.out.println(
							"___________________________________________________________________________________");

				} else {
					System.out.println("NO MORE INSTRUCTIONS TO FETCH!" + "\n");
					System.out.println(
							"___________________________________________________________________________________");
				}
				if (!(decodeQueue1.isEmpty())) {
					Instruction i = decodeQueue1.remove();
					decodeQueue2.add(i);
					System.out.println("DECODING STAGE INSTRUCTIONS: " + "\n");
					System.out.println("**Instruction " + i.getInstID() + " is still decoding**" + "\n");
					printQueue(decodeQueue2);
					System.out.println(
							"___________________________________________________________________________________");
				}
				if (!(executeQueue1.isEmpty())) {
					Instruction i = executeQueue1.remove();
					executeQueue2.add(i);
					System.out.println("EXECUTING STAGE INSTRUCTIONS: " + "\n");
					System.out.println("**Instruction " + i.getInstID() + " is still executing**" + "\n");
					printQueue(executeQueue2);
					System.out.println(
							"___________________________________________________________________________________");
				}
				if (!(memoryQueue.isEmpty())) {
					Instruction i = memoryQueue.remove();
					writeBackQueue.add(i);
					System.out.println("WRITE-BACK STAGE INSTRUCTIONS: " + "\n");
					printQueue(writeBackQueue);
					this.writeback(i);
					System.out.println(
							"___________________________________________________________________________________");
				}
			} else if (clockCycle % 2 == 0) {
				if (!executeQueue2.isEmpty() && executeQueue2.peek().opcode == 7) {
					removeALLInQueue(fetchQueue);
					removeALLInQueue(decodeQueue1);
					removeALLInQueue(decodeQueue2);
				}
				if (!executeQueue2.isEmpty() && executeQueue2.peek().opcode == 4) {
					removeALLInQueue(fetchQueue);
					removeALLInQueue(decodeQueue1);
					removeALLInQueue(decodeQueue2);
				}
				if (!(fetchQueue).isEmpty()) {
					Instruction i = fetchQueue.remove();
					decodeQueue1.add(i);
					System.out.println("DECODING STAGE INSTRUCTIONS: " + "\n");
					printQueue(decodeQueue1);
					this.decode(i);
					System.out.println(
							"___________________________________________________________________________________");
				}
				if (!(decodeQueue2.isEmpty())) {
					Instruction i = decodeQueue2.remove();
					executeQueue1.add(i);
					System.out.println("EXECUTING STAGE INSTRUCTIONS: " + "\n");
					printQueue(executeQueue1);
					this.execute(i);
					System.out.println(
							"___________________________________________________________________________________");
				}
				if (!(executeQueue2.isEmpty())) {
					Instruction i = executeQueue2.remove();
					memoryQueue.add(i);
					System.out.println("MEMORY STAGE INSTRUCTIONS: " + "\n");
					printQueue(memoryQueue);
					this.memory(i);
					System.out.println(
							"___________________________________________________________________________________");
				}
				if (!(writeBackQueue.isEmpty())) {
					finishQueue.add(writeBackQueue.remove());
					this.setNoOfCompletedInstructions(this.getNoOfCompletedInstructions() + 1);
					System.out.println("FINISHED INSTRUCTIONS: " + "\n");
					printQueue(finishQueue);
					System.out.println(
							"___________________________________________________________________________________");
				}
			}
			if (clockCycle == ClockCyclesTotal) {
				System.out.println("REGISTER FILE CONTENT: " + "\n");
				this.registerFilePrint();
				System.out
						.println("___________________________________________________________________________________");
				System.out.println("MEMORY CONTENT: " + "\n");
				this.printMemory();
				System.out
						.println("___________________________________________________________________________________");
				return;
			}
			clockCycle++;
		}
		System.out.println("_________________SIMULATION ENDED_________________");
	}

	public int getTotalNumberOfInstructions() {
		return totalNumberOfInstructions;
	}

	public void setTotalNumberOfInstructions(int totalNumberOfInstructions) {
		this.totalNumberOfInstructions = totalNumberOfInstructions;
	}

	public String[] getInstructions() {
		return instructions;
	}

	public void setInstructions(String[] instructions) {
		this.instructions = instructions;
	}

	public void setRegisterFile(Register[] registerFile) {
		this.registerFile = registerFile;
	}

	public Register[] getRegisterFile() {
		return registerFile;
	}

	public int getNoOfInstructions() {
		return noOfInstructions;
	}

	public void setNoOfInstructions(int noOfInstructions) {
		this.noOfInstructions = noOfInstructions;
	}

	public int getNoOfCompletedInstructions() {
		return noOfCompletedInstructions;
	}

	public void setNoOfCompletedInstructions(int noOfCompletedInstructions) {
		this.noOfCompletedInstructions = noOfCompletedInstructions;
	}

	public String[] getMemory() {
		return memory;
	}

	public void setMemory(String[] memory) {
		this.memory = memory;
	}

	public int getPC() {
		return pc;
	}

	public void setPC(int pc) {
		this.pc = pc;
	}

	public String getInstruction(int address) {
		if (address >= 0 && address <= 1023) {
			if (memory[address].equals(null)) {
				System.out.println("The element is null" + "\n" + "\n");
				return "";
			} else {
				return memory[address];
			}
		} else {
			return "";
		}
	}

	public void setInstruction(String instructionValue, int address) {
		if (address >= 0 && address <= 1023) {
			memory[address] = instructionValue;
		} else {
			System.out.println("Instruction Overflow!" + "\n" + "\n");
		}
	}

	public String getData(int address) {
		if (address >= 1024 && address <= 2047) {
			if (memory[address] == null) {
				System.out.println("The element is null" + "\n" + "\n");
				return "";
			} else {
				return memory[address];
			}
		} else {
			return "";
		}
	}

	public void setData(String dataValue, int address) {
		if (address >= 1024 && address <= 2047) {
			memory[address] = dataValue;
		} else {
			System.out.println("Data Overflow!" + "\n" + "\n");
		}
	}

	public void registerFilePrint() {
		for (int i = 0; i < this.getRegisterFile().length; i++) {
			System.out.println("Content of " + this.getRegisterFile()[i].getName() + " is "
					+ this.getRegisterFile()[i].getValueInRegister());
		}
	}

	public void printMemory() {
		for (int i = 0; i < this.memory.length; i++) {
			if (i >= 0 && i <= 1023 && this.memory[i] != null) {
				System.out.println("#" + i + " Instruction: " + this.memory[i]);
			}
			if (i >= 1024 && i <= 2047 && this.memory[i] != null) {
				System.out.println("#" + i + " Data: " + this.memory[i]);
			}
		}
	}

	public void printQueue(LinkedList<Instruction> q) {
		for (Instruction i : q) {
			System.out.println(i.toString());
		}
	}

	public long parseLong(String s, int base) {
		return new BigInteger(s, base).longValue();
	}

	public String makeBinaryString(int n) {
		StringBuilder sb = new StringBuilder();
		while (n > 0) {
			sb.append(n % 2);
			n /= 2;
		}
		sb.reverse();
		return sb.toString();
	}

	public String toBinary(int x, int len) {
		StringBuilder result = new StringBuilder();
		for (int i = len - 1; i >= 0; i--) {
			int mask = 1 << i;
			result.append((x & mask) != 0 ? 1 : 0);
		}
		return result.toString();
	}

	public int negative2sComplement(String a) {
		String x = "";
		String y = "111111111111111111";
		for (int i = 0; i < 18; i++) {
			if (a.charAt(i) == y.charAt(i))
				x += "0";
			else
				x += "1";
		}
		int res = Integer.parseInt(x, 2);
		res = (res + 1) * -1;
		return res;
	}

}