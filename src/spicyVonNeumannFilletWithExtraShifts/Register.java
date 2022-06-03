package spicyVonNeumannFilletWithExtraShifts;

public class Register {

	private String name;
	private int registerValue;

	public Register(String name, int registerValue) {
		this.name = name;
		this.registerValue = registerValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValueInRegister() {
		return registerValue;
	}

	public void setValueInRegister(int registerValue) {
		this.registerValue = registerValue;
	}

}