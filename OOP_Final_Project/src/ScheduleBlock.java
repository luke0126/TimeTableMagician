import java.util.Vector;

class ScheduleBlock {
	private Vector<TimeBlock> time = new Vector<TimeBlock>();//When schedule is going on
	private String name;//Schedule's name
	public ScheduleBlock(Vector<TimeBlock> time, String name) {
		super();
		this.time = time;
		this.name = name;
	}
	public ScheduleBlock() {
		this.time = new Vector<TimeBlock>();
		this.name = "";
	}
	public Vector<TimeBlock> getTime() {
		return time;
	}
	public void setTime(Vector<TimeBlock> time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isIntersected(Vector<TimeBlock> other) { //Is intersected with other schedule's timeblock
		for(int i=0;i<other.size();i++) {
			for(int j=0;j<this.time.size();j++) {
				if(other.elementAt(i).getDay()==this.time.elementAt(j).getDay()&&
						((other.elementAt(i).getStartTime()>=this.time.elementAt(j).getStartTime()&&other.elementAt(i).getStartTime()<this.time.elementAt(j).getEndTime())||
								(other.elementAt(i).getEndTime()>this.time.elementAt(j).getStartTime()&&other.elementAt(i).getEndTime()<=this.time.elementAt(j).getEndTime())||
									(other.elementAt(i).getStartTime()>=this.time.elementAt(j).getStartTime()&&other.elementAt(i).getEndTime()<=this.time.elementAt(j).getEndTime())||
									(other.elementAt(i).getStartTime()<=this.time.elementAt(j).getStartTime()&&other.elementAt(i).getEndTime()>=this.time.elementAt(j).getEndTime()))) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean isInStartToEnd(float startTime, float endTime) {
		for(int i=0;i<time.size();i++) {
			if(time.elementAt(i).getStartTime()<startTime||time.elementAt(i).getEndTime()>endTime) {
				return false;
			}
		}
		return true;
	}
}
