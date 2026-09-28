package Project;

public class HSRecord{
	private int noTurns;
	private String name;
	
	public HSRecord() {}
	
	public HSRecord(int noTurns, String name) {
		this.setName(name);
		this.setNoTurns(noTurns);
	}
	
	public int getnoTurns() {
		return noTurns;
	}
	public void setNoTurns(int noTurns) {
		this.noTurns = noTurns;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
