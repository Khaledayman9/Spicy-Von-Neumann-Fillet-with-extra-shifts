package spicyVonNeumannFilletWithExtraShifts;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		Interpreter interpreter = new Interpreter();
		String instructions[] = interpreter.readLines("Program");
		InstructionSetArchitecture ISA = new InstructionSetArchitecture(instructions);
		ISA.addInstructionsToMemory(ISA.getInstructions());
		ISA.memoryArchitecture.pipeline();

	}

}