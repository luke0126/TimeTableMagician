import java.util.Comparator;
import java.util.Vector;

public class sortLectures implements Comparator<Vector<lecture>> {

	int grade;
	
	public sortLectures(int grade) {
		super();
		this.grade = grade;
	}
	@Override
	public int compare(Vector<lecture> a, Vector<lecture> b) {
		lecture l1 = a.elementAt(0);
		lecture l2 = b.elementAt(0);
		if(l1.getLevel()!=l2.getLevel()) { //�� ���ǰ� �г��� �ٸ��ٸ�
			if(l1.getLevel()==grade) { //l1�� ������� �г��̶��
				return -1;
			}
			else if(l2.getLevel()==grade) { //l2�� ������� �г��̶��
				return 1;
			}
			else { //�� ���� ��� ������� �г�� �ٸ��ٸ�
				if(l1.isMajor()!=l2.isMajor()) { //�ϳ��� ����, �ϳ��� �����̶��
					if(l1.isMajor()) { //l1�� �����̶��
						return -1;
					}
					else { //l2�� �����̶��
						return 1;
					}
				}
				else { //�� ���� ��� ���� Ÿ���̶��
					if(l1.isMajor()) { //�� ���� ��� �����̶��
						if(l1.getLecture_type()<l2.getLecture_type()) { //l1�� �켱������ l2���� ���ٸ�
							return -1;
						}
						else if(l1.getLecture_type()>l2.getLecture_type()) { //l2�� �켱������ l1���� ���ٸ�
							return 1;
						}
						else { //�� ������ �켱������ ���ٸ�
							return 0;
						}
					}
					else { //�� �� �����̶��
						return 0;
					}
				}
			}
		}
		else {
			if(l1.isMajor()!=l2.isMajor()) { //�ϳ��� ����, �ϳ��� �����̶��
				if(l1.isMajor()) { //l1�� �����̶��
					return -1;
				}
				else { //l2�� �����̶��
					return 1;
				}
			}
			else { //�� ���� ��� ���� Ÿ���̶��
				if(l1.isMajor()) { //�� ���� ��� �����̶��
					if(l1.getLecture_type()<l2.getLecture_type()) { //l1�� �켱������ l2���� ���ٸ�
						return -1;
					}
					else if(l1.getLecture_type()>l2.getLecture_type()) { //l2�� �켱������ l1���� ���ٸ�
						return 1;
					}
					else { //�� ������ �켱������ ���ٸ�
						return 0;
					}
				}
				else { //�� �� �����̶��
					return 0;
				}
			}
		}
	}
}
