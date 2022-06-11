# Spicy-Von-Neumann-Fillet-with-extra-shifts
Simulating a fictional processor design and architecture using Java, 


# Memory Architecture 
Using Von Neumann Memory Architecture which is a digital computer architecture whose design is based on the concept of stored program computers where program data and instruction data are stored in the same memory.
* Memory Size = 2048 * 32
* The main memory addresses are from 0 to 211 − 1 (0 to 2047).
* The memory is word addressable
* Addresses from 0 to 1023 contain the program instructions.
• Addresses from 1024 to 2048 contain the data.

# Regisers
* Size: 32 bits
* 31 General-Purpose Registers (GPRS)
  – Names: R1 to R31
* 1 Zero Register
  – Name: R0
  – Hard-wired value “0” (cannot be overwritten by any instruction).
* 1 Program Counter 
  
  – Name: PC
  
  – A program counter is a register in a computer processor that contains the address (location) of the instruction being executed at the current time.
  
  – As each instruction gets fetched, the program counter is incremented to point to the next instruction to be executed.

# Instruction Set Architecture
* We have 3 format (R-Format, I-Format, J-Format)
* R-Format:

![R-Format](https://user-images.githubusercontent.com/105018459/173196144-5aed43ad-4abc-4d3b-a443-f80148c0922b.PNG)

* I-Format:

![I-Format](https://user-images.githubusercontent.com/105018459/173196165-3d0e95d0-244c-4acf-b3bd-2c78ed3fa9c0.PNG)

* J-Format:

![J-Format](https://user-images.githubusercontent.com/105018459/173196224-08534674-3640-4f1b-98d9-711fc638e6c2.PNG)


# Total Instructions
* The opcodes are from 0 to 11 according to the instructions order in the following table:
![Operations](https://user-images.githubusercontent.com/105018459/173196280-f6849279-7ff8-4f32-bcdb-17b4b4231da7.PNG)


# Datapath

All instructions regardless of their type must pass through all 5 stages even if they do not need
to access a particular stage.
* Instruction Fetch (IF): Fetches the next instruction from the main memory using the address in the PC (Program Counter), and increments the PC.
* Instruction Decode (ID): Decodes the instruction and reads any operands required from the register file.
* Execute (EX): Executes the instruction. In fact, all ALU operations are done in this stage.
* Memory (MEM): Performs any memory access required by the current instruction. For loads, it would load an operand from the main memory, while for stores, it would store an operand into the main memory.
* Write Back (WB): For instructions that have a result (a destination register), the Write Back writes this result back to the register file.

# Pipeline
* 4 instructions (maximum) running in parallel
* Instruction Fetch (IF) and Memory (MEM) can not be done in parallel since they access the same physical memory.
• At a given clock cycle, you can either have the IF, ID, EX, WB stages active, or the ID, EX, MEM, WB stages active.
• Number of clock cycles: 7 + ((n − 1) ∗ 2), where n = number of instructions

– Imagine a program with 7 instructions: 7 + (6 ∗ 2) = 19 clock cycles
- Example for the Pipleline pattern:
![Pipeline 2](https://user-images.githubusercontent.com/105018459/173196466-5c106e76-cb17-4d38-b45c-813868e67488.PNG)
- The pattern is as follows:
  *You fetch an instruction every 2 clock cycles starting from clock cycle 1.
  * An instruction stays in the Decode (ID) stage for 2 clock cycles.
  * An instruction stays in the Execute (EX) stage for 2 clock cycles.
  * An instruction stays in the Memory (MEM) stage for 1 clock cycle.
  * An instruction stays in the Write Back (WB) stage for 1 clock cycle.
  * We cannot have the Instruction Fetch (IF) and Memory (MEM) stages working in parallel. Only one of them is active at a given clock cycle.



# Technologies
Project is created with:

* Java SE-15
* Eclipse IDE


# Set up

* To run this project, just import the project as an existing project in Eclipse

