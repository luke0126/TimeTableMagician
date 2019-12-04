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
	
	public boolean isIntersected(lecture l) {
		Vector<timeBlock> temp = new Vector<timeBlock>(super.getTime().size());
		
		for(int i=0;i<super.getTime().size();i++) {
			temp.add(new timeBlock(super.getTime().elementAt(i).getStartTime()-frontDelay,
					super.getTime().elementAt(i).getEndTime(), super.getTime().elementAt(i).getDay()));
		}
		for(int i=0;i<temp.size();i++) {
			if(l.isIntersected(temp)) {
				return true;
			}
		}
		return false;
	}
}
