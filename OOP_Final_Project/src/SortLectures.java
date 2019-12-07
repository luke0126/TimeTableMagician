import java.util.Comparator;
import java.util.Vector;

public class SortLectures implements Comparator<Vector<Lecture>> {

	int grade;
	
	public SortLectures(int grade) {
		super();
		this.grade = grade;
	}
	@Override
	public int compare(Vector<Lecture> a, Vector<Lecture> b) {
		Lecture l1 = a.elementAt(0);
		Lecture l2 = b.elementAt(0);
		if(l1.getLevel()!=l2.getLevel()) { //두 강의가 학년이 다르다면
			if(l1.getLevel()==grade) { //l1이 사용자의 학년이라면
				return -1;
			}
			else if(l2.getLevel()==grade) { //l2가 사용자의 학년이라면
				return 1;
			}
			else { //두 강의 모두 사용자의 학년과 다르다면
				if(l1.isMajor()!=l2.isMajor()) { //하나는 교양, 하나는 전공이라면
					if(l1.isMajor()) { //l1이 전공이라면
						return -1;
					}
					else { //l2가 전공이라면
						return 1;
					}
				}
				else { //두 강의 모두 같은 타입이라면
					if(l1.isMajor()) { //두 강의 모두 전공이라면
						if(l1.getLecture_type()<l2.getLecture_type()) { //l1의 우선순위가 l2보다 높다면
							return -1;
						}
						else if(l1.getLecture_type()>l2.getLecture_type()) { //l2의 우선순위가 l1보다 높다면
							return 1;
						}
						else { //두 강의의 우선순위가 같다면
							return 0;
						}
					}
					else { //둘 다 교양이라면
						return 0;
					}
				}
			}
		}
		else {
			if(l1.isMajor()!=l2.isMajor()) { //하나는 교양, 하나는 전공이라면
				if(l1.isMajor()) { //l1이 전공이라면
					return -1;
				}
				else { //l2가 전공이라면
					return 1;
				}
			}
			else { //두 강의 모두 같은 타입이라면
				if(l1.isMajor()) { //두 강의 모두 전공이라면
					if(l1.getLecture_type()<l2.getLecture_type()) { //l1의 우선순위가 l2보다 높다면
						return -1;
					}
					else if(l1.getLecture_type()>l2.getLecture_type()) { //l2의 우선순위가 l1보다 높다면
						return 1;
					}
					else { //두 강의의 우선순위가 같다면
						return 0;
					}
				}
				else { //둘 다 교양이라면
					return 0;
				}
			}
		}
	}
}
