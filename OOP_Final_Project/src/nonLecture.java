import java.util.Vector;

class nonLecture extends scheduleBlock{
	private float frontDelay;

	public nonLecture(Vector<timeBlock> time, String name, float frontDelay) {
		super(time, name);
		this.frontDelay = frontDelay;
	}

	public float getFrontDelay() {
		return frontDelay;
	}

	public void setFrontDelay(float frontDelay) {
		this.frontDelay = frontDelay;
	}
	
	public void pushTime(timeBlock tB) {
		Vector<timeBlock> temp = super.getTime();
		temp.add(tB);
		super.setTime(temp);
	}
}
