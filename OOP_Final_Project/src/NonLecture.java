import java.util.Vector;

class NonLecture extends ScheduleBlock{
	private float frontDelay;

	public NonLecture(Vector<TimeBlock> time, String name, float frontDelay) {
		super(time, name);
		this.frontDelay = frontDelay;
	}

	public float getFrontDelay() {
		return frontDelay;
	}

	public void setFrontDelay(float frontDelay) {
		this.frontDelay = frontDelay;
	}
	
	public void pushTime(TimeBlock tB) {
		Vector<TimeBlock> temp = super.getTime();
		temp.add(tB);
		super.setTime(temp);
	}
	
	public boolean isIntersected(Lecture l) { //Is intersected between lecture and nonLecture
		Vector<TimeBlock> temp = new Vector<TimeBlock>(super.getTime().size());
		
		for(int i=0;i<super.getTime().size();i++) {
			temp.add(new TimeBlock(super.getTime().elementAt(i).getStartTime()-frontDelay,
					super.getTime().elementAt(i).getEndTime(), super.getTime().elementAt(i).getDay()));
		}
		if(l.isIntersected(temp)) {
			return true;
		} else {
			return false;	
		}
		
	}
	
	public boolean isIntersected(NonLecture nl) { //Is intersected between lecture and nonLecture
		Vector<TimeBlock> temp1 = new Vector<TimeBlock>(super.getTime().size());
		
		for(int i=0;i<super.getTime().size();i++) {
			temp1.add(new TimeBlock(super.getTime().elementAt(i).getStartTime()-frontDelay,
					super.getTime().elementAt(i).getEndTime(), super.getTime().elementAt(i).getDay()));
		}
		Vector<TimeBlock> temp2 = new Vector<TimeBlock>(nl.getTime().size());
		
		for(int i=0;i<nl.getTime().size();i++) {
			temp2.add(new TimeBlock(nl.getTime().elementAt(i).getStartTime()-nl.getFrontDelay(),
					nl.getTime().elementAt(i).getEndTime(), nl.getTime().elementAt(i).getDay()));
		}
		ScheduleBlock sb = new ScheduleBlock(temp2, "");
		if(sb.isIntersected(temp1)) {
			return true;
		} else {
			return false;	
		}
		
	}
}
