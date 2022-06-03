package spicyVonNeumannFilletWithExtraShifts;

public class Instruction {

	long InstVal, opcode;
	int InstID, rd, rs, rt, shamt, imm, result, valueRD, valueRS, valueRT, loadedWord, zeroFlag, address;
	boolean R1DataFlag, PCDataFlag, SWDataFlag, LWDataFlag, PCDataFlagForJump;

	public Instruction() {
		this.R1DataFlag = false;
		this.PCDataFlag = false;
		this.SWDataFlag = false;
		this.LWDataFlag = false;
		this.PCDataFlagForJump = false;
	}

	public boolean isPCDataFlagForJump() {
		return PCDataFlagForJump;
	}

	public void setPCDataFlagForJump(boolean pCDataFlagForJump) {
		PCDataFlagForJump = pCDataFlagForJump;
	}

	public int getZeroFlag() {
		return zeroFlag;
	}

	public void setZeroFlag(int zeroFlag) {
		this.zeroFlag = zeroFlag;
	}

	public int getLoadedWord() {
		return loadedWord;
	}

	public void setLoadedWord(int loadedWord) {
		this.loadedWord = loadedWord;
	}

	public int getValueRD() {
		return valueRD;
	}

	public void setValueRD(int valueRD) {
		this.valueRD = valueRD;
	}

	public int getValueRS() {
		return valueRS;
	}

	public void setValueRS(int valueRS) {
		this.valueRS = valueRS;
	}

	public int getValueRT() {
		return valueRT;
	}

	public void setValueRT(int valueRT) {
		this.valueRT = valueRT;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public boolean isDataflagForR1() {
		return R1DataFlag;
	}

	public void setDataflagForR1(boolean dataflagForR1) {
		R1DataFlag = dataflagForR1;
	}

	public boolean isDataflagForPC() {
		return PCDataFlag;
	}

	public void setDataflagForPC(boolean dataflagForPC) {
		PCDataFlag = dataflagForPC;
	}

	public boolean isDataflagForSW() {
		return SWDataFlag;
	}

	public void setDataflagForSW(boolean dataflagForSW) {
		SWDataFlag = dataflagForSW;
	}

	public boolean isDataflagForLW() {
		return LWDataFlag;
	}

	public void setDataflagForLW(boolean dataflagForLW) {
		LWDataFlag = dataflagForLW;
	}

	public long getInstVal() {
		return InstVal;
	}

	public void setInstVal(long instVal) {
		InstVal = instVal;
	}

	public int getInstID() {
		return InstID;
	}

	public void setInstID(int instID) {
		InstID = instID;
	}

	public long getOpcode() {
		return opcode;
	}

	public void setOpcode(long opcode) {
		this.opcode = opcode;
	}

	public int getRd() {
		return rd;
	}

	public void setRd(int rd) {
		this.rd = rd;
	}

	public int getRs() {
		return rs;
	}

	public void setRs(int rs) {
		this.rs = rs;
	}

	public int getRt() {
		return rt;
	}

	public void setRt(int rt) {
		this.rt = rt;
	}

	public int getShamt() {
		return shamt;
	}

	public void setShamt(int shamt) {
		this.shamt = shamt;
	}

	public int getImm() {
		return imm;
	}

	public void setImm(int imm) {
		this.imm = imm;
	}

	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Instruction [InstVal=" + InstVal + ", opcode=" + opcode + ", InstID=" + InstID + ", rd=" + rd + ", rs="
				+ rs + ", rt=" + rt + ", shamt=" + shamt + ", imm=" + imm + ", result=" + result + ", valueRD="
				+ valueRD + ", valueRS=" + valueRS + ", valueRT=" + valueRT + ", loadedWord=" + loadedWord
				+ ", zeroFlag=" + zeroFlag + ", address=" + address + ", R1DataFlag=" + R1DataFlag + ", PCDataFlag="
				+ PCDataFlag + ", SWDataFlag=" + SWDataFlag + ", LWDataFlag=" + LWDataFlag + ", PCDataFlagForJump="
				+ PCDataFlagForJump + "]";
	}

}