package opp.flow;

public class ImgCompare implements Comparable<ImgCompare>{
	private String name;
	private int percentage;
	
	public ImgCompare(String name, int percentage) {
		super();
		this.name = name;
		this.percentage = percentage;
	}
	
	public ImgCompare() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	
	@Override
	public int compareTo(ImgCompare o) {
		if(this.percentage==o.getPercentage()) {
			return 0;
		}else if(this.percentage>o.getPercentage()) {
			return -1;
		}else {
			return 1;
		}
	}
		
}
