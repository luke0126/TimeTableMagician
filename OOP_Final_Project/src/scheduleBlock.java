import java.util.Vector;

class scheduleBlock {
	private Vector<timeBlock> time;//When schedule is going on
	private String name;//Schedule's name
	public scheduleBlock(Vector<timeBlock> time, String name) {
		super();
		this.time = time;
		this.name = name;
	}
	public scheduleBlock() {
		this.time = new Vector<timeBlock>();
		this.name = "";
	}
	public Vector<timeBlock> getTime() {
		return time;
	}
	public void setTime(Vector<timeBlock> time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isIntersected(scheduleBlock other) {
		for(int i=0;i<other.time.size();i++) {
			for(int j=0;j<this.time.size();j++) {
				if(other.time.elementAt(i).getDay()==this.time.elementAt(j).getDay()&&
						((other.time.elementAt(i).getStartTime()>this.time.elementAt(j).getStartTime()&&other.time.elementAt(i).getStartTime()<this.time.elementAt(j).getEndTime())||
								(other.time.elementAt(i).getEndTime()>this.time.elementAt(j).getStartTime()&&other.time.elementAt(i).getEndTime()<this.time.elementAt(j).getEndTime())||
								(other.time.elementAt(i).getStartTime()>this.time.elementAt(j).getStartTime()&&other.time.elementAt(i).getEndTime()<this.time.elementAt(j).getEndTime()))) {
					return true;
				}
			}
		}
		return false;
	}
}
