package spicyVonNeumannFilletWithExtraShifts;

public class InstructionSetArchitecture {

	String[] instructions;
	MemoryArchitecture memoryArchitecture;

	public InstructionSetArchitecture(String[] instructions) {
		this.instructions = instructions;
		memoryArchitecture = new MemoryArchitecture();
		memoryArchitecture.setNoOfInstructions(instructions.length);
		memoryArchitecture.setNoOfCompletedInstructions(0);
		memoryArchitecture.setInstructions(instructions);
		memoryArchitecture.setClockCyclesTotal(7 + ((instructions.length - 1) * 2));
	}

	public String getTotalFormat(String line) {
		String result = "";
		String[] isa = line.split(" ");
		String OPCODE, R1, R2, R3, SHAMT, IMM, ADDRESS;
		long temp;
		int num, y, z;
		switch (isa[0]) {
		case "ADD":
			OPCODE = "0000";
			if (isa[1].length() > 2) {
				num = Integer.parseInt(isa[1].substring(1, 3));
				R1 = this.toBinary(num, 5);
			} else {
				num = Character.getNumericValue(isa[1].charAt(1));
				R1 = this.toBinary(num, 5);
			}
			if (isa[2].length() > 2) {
				num = Integer.parseInt(isa[2].substring(1, 3));
				R2 = this.toBinary(num, 5);
			} else {
				num = Character.getNumericValue(isa[2].charAt(1));
				R2 = this.toBinary(num, 5);
			}
			if (isa[3].length() > 2) {
				num = Integer.parseInt(isa[3].substring(1, 3));
				R3 = this.toBinary(num, 5);
			} else {
				num = Character.getNumericValue(isa[3].charAt(1));
				R3 = this.toBinary(num, 5);
			}
			SHAMT = this.toBinary(0, 13);
			result = OPCODE + R1 + R2 + R3 + SHAMT;
			break;

		case "SUB":
			OPCODE = "0001";
			if (isa[1].length() > 2) {
				num = Integer.parseInt(isa[1].substring(1, 3));
				R1 = this.toBinary(num, 5);
			} else {
				num = Character.getNumericValue(isa[1].charAt(1));
				R1 = this.toBinary(num, 5);
			}
			if (isa[2].length() > 2) {
				num = Integer.parseInt(isa[2].substring(1, 3));
				R2 = this.toBinary(num, 5);
			} else {
				num = Character.getNumericValue(isa[2].charAt(1));
				R2 = this.toBinary(num, 5);
			}
			if (isa[3].length() > 2) {
				num = Integer.parseInt(isa[3].substring(1, 3));
				R3 = this.toBinary(num, 5);
			} else {
				num = Character.getNumericValue(isa[3].charAt(1));
				R3 = this.toBinary(num, 5);
			}
			SHAMT = this.toBinary(0, 13);
			result = OPCODE + R1 + R2 + R3 + SHAMT;
			break;

		case "MULI":
			OPCODE = "0010";
			if (isa[1].length() > 2) {
				num = Integer.parseInt(isa[1].substring(1, 3));
				R1 = this.toBinary(num, 5);
			} else {
				num = Character.getNumericValue(isa[1].charAt(1));
				R1 = this.toBinary(num, 5);
			}
			if (isa[2].length() > 2) {
				num = Integer.parseInt(isa[2].substring(1, 3));
				R2 = this.toBinary(num, 5);
			} else {
				num = Character.getNumericValue(isa[2].charAt(1));
				R2 = this.toBinary(num, 5);
			}
			temp = Long.parseLong(isa[3]);
			y = (int) temp;
			z = y & 0b00000000000000111111111111111111;
			IMM = this.toBinary(z, 18);
			result = OPCODE + R1 + R2 + IMM;
			break;

		case "ADDI":
			OPCODE = "0011";
			if (isa[1].length() > 2) {
				num = Integer.parseInt(isa[1].substring(1, 3));
				R1 = this.toBinary(num, 5);
			} else {
				num = Character.getNumericValue(isa[1].charAt(1));
				R1 = this.toBinary(num, 5);
			}
			if (isa[2].length() > 2) {
				num = Integer.parseInt(isa[2].substring(1, 3));
				R2 = this.toBinary(num, 5);
			} else {
				num = Character.getNumericValue(isa[2].charAt(1));
				R2 = this.toBinary(num, 5);
			}
			temp = Long.parseLong(isa[3]);
			y = (int) temp;
			z = y & 0b00000000000000111111111111111111;
			IMM = this.toBinary(z, 18);
			result = OPCODE + R1 + R2 + IMM;
			break;

		case "BNE":
			OPCODE = "0100";
			if (isa[1].length() > 2) {
				num = Integer.parseInt(isa[1].substring(1, 3));
				R1 = this.toBinary(num, 5);
			} else {
				num = Character.getNumericValue(isa[1].charAt(1));
				R1 = this.toBinary(num, 5);
			}
			if (isa[2].length() > 2) {
				num = Integer.parseInt(isa[2].substring(1, 3));
				R2 = this.toBinary(num, 5);
			} else {
				num = Character.getNumericValue(isa[2].charAt(1));
				R2 = this.toBinary(num, 5);
			}
			temp = Long.parseLong(isa[3]);
			y = (int) temp;
			z = y & 0b00000000000000111111111111111111;
			IMM = this.toBinary(z, 18);
			result = OPCODE + R1 + R2 + IMM;
			break;

		case "ANDI":
			OPCODE = "0101";
			if (isa[1].length() > 2) {
				num = Integer.parseInt(isa[1].substring(1, 3));
				R1 = this.toBinary(num, 5);
			} else {
				num = Character.getNumericValue(isa[1].charAt(1));
				R1 = this.toBinary(num, 5);
			}
			if (isa[2].length() > 2) {
				num = Integer.parseInt(isa[2].substring(1, 3));
				R2 = this.toBinary(num, 5);
			} else {
				num = Character.getNumericValue(isa[2].charAt(1));
				R2 = this.toBinary(num, 5);
			}
			temp = Long.parseLong(isa[3]);
			y = (int) temp;
			z = y & 0b00000000000000111111111111111111;
			IMM = this.toBinary(z, 18);
			result = OPCODE + R1 + R2 + IMM;
			break;

		case "ORI":
			OPCODE = "0110";
			if (isa[1].length() > 2) {
				num = Integer.parseInt(isa[1].substring(1, 3));
				R1 = this.toBinary(num, 5);
			} else {
				num = Character.getNumericValue(isa[1].charAt(1));
				R1 = this.toBinary(num, 5);
			}
			if (isa[2].length() > 2) {
				num = Integer.parseInt(isa[2].substring(1, 3));
				R2 = this.toBinary(num, 5);
			} else {
				num = Character.getNumericValue(isa[2].charAt(1));
				R2 = this.toBinary(num, 5);
			}
			temp = Long.parseLong(isa[3]);
			y = (int) temp;
			z = y & 0b00000000000000111111111111111111;
			IMM = this.toBinary(z, 18);
			result = OPCODE + R1 + R2 + IMM;
			break;

		case "J":
			OPCODE = "0111";
			temp = Long.parseLong(isa[1]);
			y = (int) temp;
			z = y & 0b00000000000000111111111111111111;
			ADDRESS = this.toBinary(z, 28);

			result = OPCODE + ADDRESS;
			break;

		case "SLL":
			OPCODE = "1000";
			if (isa[1].length() > 2) {
				num = Integer.parseInt(isa[1].substring(1, 3));
				R1 = this.toBinary(num, 5);
			} else {
				num = Character.getNumericValue(isa[1].charAt(1));
				R1 = this.toBinary(num, 5);
			}
			if (isa[2].length() > 2) {
				num = Integer.parseInt(isa[2].substring(1, 3));
				R2 = this.toBinary(num, 5);
			} else {
				num = Character.getNumericValue(isa[2].charAt(1));
				R2 = this.toBinary(num, 5);
			}
			R3 = this.toBinary(0, 5);
			temp = Long.parseLong(isa[3]);
			y = (int) temp;
			z = y & 0b00000000000000111111111111111111;
			SHAMT = this.toBinary(z, 13);
			result = OPCODE + R1 + R2 + R3 + SHAMT;
			break;

		case "SRL":
			OPCODE = "1001";
			if (isa[1].length() > 2) {
				num = Integer.parseInt(isa[1].substring(1, 3));
				R1 = this.toBinary(num, 5);
			} else {
				num = Character.getNumericValue(isa[1].charAt(1));
				R1 = this.toBinary(num, 5);
			}
			if (isa[2].length() > 2) {
				num = Integer.parseInt(isa[2].substring(1, 3));
				R2 = this.toBinary(num, 5);
			} else {
				num = Character.getNumericValue(isa[2].charAt(1));
				R2 = this.toBinary(num, 5);
			}
			R3 = this.toBinary(0, 5);
			temp = Long.parseLong(isa[3]);
			y = (int) temp;
			z = y & 0b00000000000000111111111111111111;
			SHAMT = this.toBinary(z, 13);
			result = OPCODE + R1 + R2 + R3 + SHAMT;
			break;

		case "LW":
			OPCODE = "1010";
			if (isa[1].length() > 2) {
				num = Integer.parseInt(isa[1].substring(1, 3));
				R1 = this.toBinary(num, 5);
			} else {
				num = Character.getNumericValue(isa[1].charAt(1));
				R1 = this.toBinary(num, 5);
			}
			if (isa[2].length() > 2) {
				num = Integer.parseInt(isa[2].substring(1, 3));
				R2 = this.toBinary(num, 5);
			} else {
				num = Character.getNumericValue(isa[2].charAt(1));
				R2 = this.toBinary(num, 5);
			}
			temp = Long.parseLong(isa[3]);
			y = (int) temp;
			z = y & 0b00000000000000111111111111111111;
			IMM = this.toBinary(z, 18);
			result = OPCODE + R1 + R2 + IMM;
			break;

		case "SW":
			OPCODE = "1011";
			if (isa[1].length() > 2) {
				num = Integer.parseInt(isa[1].substring(1, 3));
				R1 = this.toBinary(num, 5);
			} else {
				num = Character.getNumericValue(isa[1].charAt(1));
				R1 = this.toBinary(num, 5);
			}
			if (isa[2].length() > 2) {
				num = Integer.parseInt(isa[2].substring(1, 3));
				R2 = this.toBinary(num, 5);
			} else {
				num = Character.getNumericValue(isa[2].charAt(1));
				R2 = this.toBinary(num, 5);
			}
			temp = Long.parseLong(isa[3]);
			y = (int) temp;
			z = y & 0b00000000000000111111111111111111;
			IMM = this.toBinary(z, 18);
			result = OPCODE + R1 + R2 + IMM;
			break;
		}
		return result;
	}

	public String[] getInstructions() {
		return instructions;
	}

	public void setInstructions(String[] instructions) {
		this.instructions = instructions;
	}

	public String toBinary(int x, int len) {
		StringBuilder result = new StringBuilder();
		for (int i = len - 1; i >= 0; i--) {
			int mask = 1 << i;
			result.append((x & mask) != 0 ? 1 : 0);
		}
		return result.toString();
	}

	public void addInstructionsToMemory(String[] instructions) {
		for (int i = 0; i < instructions.length; i++) {
			String result = this.getTotalFormat(instructions[i]);
			this.memoryArchitecture.setInstruction(result, i);
		}
	}

}