import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ParsingLecture {
	
	private Vector<Vector<Lecture>> lectures = new Vector<Vector<Lecture>>();
	
	public ParsingLecture(String route) {
		try {
			FileInputStream xls = new FileInputStream(route);
			HSSFWorkbook workbook = new HSSFWorkbook(xls);
			int rowIndex = 0;
			int columnIndex = 0;
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			int rows = sheet.getPhysicalNumberOfRows();
			for(rowIndex=1;rowIndex<rows;rowIndex++) {
				
				HSSFRow row = sheet.getRow(rowIndex);
				if(row != null) {
					int cells = row.getPhysicalNumberOfCells();
					String lectureType="";
					String lectureName="";
					String professor="";
					String code="";
					Vector<TimeBlock> time=new Vector<TimeBlock>();
					int level=0;
					int credit=0;
					boolean isMajor=false;
					int lectureTypeInt=0;
					for(columnIndex=0; columnIndex<=cells;columnIndex++) {
						HSSFCell cell = row.getCell(columnIndex);
						String value = "";
						if(cell!=null) {
							value=cell.getStringCellValue()+"";
							switch (columnIndex) {
							case 2:
								level = Integer.parseInt(value);
								break;
								
							case 3:
								lectureType = value;
								break;
								
							case 4:
								code = value;
								break;
							case 5:
								lectureName = value;
								break;
								
							case 6:
								credit = (int)(value.charAt(0)-'0');
								break;
							case 7:
								professor = value;
								break;
							case 9:
								for(int i=0;i<value.length();i++) {
									if(value.charAt(i)=='월') {
										TimeBlock temp = new TimeBlock(0, 0, 0);
										time.add(temp);
									}
									else if(value.charAt(i)=='화') {
										TimeBlock temp = new TimeBlock(0, 0, 1);
										time.add(temp);
									}
									else if(value.charAt(i)=='수') {
										TimeBlock temp = new TimeBlock(0, 0, 2);
										time.add(temp);
									}
									else if(value.charAt(i)=='목') {
										TimeBlock temp = new TimeBlock(0, 0, 3);
										time.add(temp);
									}
									else if(value.charAt(i)=='금') {
										TimeBlock temp = new TimeBlock(0, 0, 4);
										time.add(temp);
									}
									else if(value.charAt(i)=='토') {
										TimeBlock temp = new TimeBlock(0, 0, 5);
										time.add(temp);
									}
									else if(value.charAt(i)=='일') {
										TimeBlock temp = new TimeBlock(0, 0, 6);
										time.add(temp);
									}
									else if(value.charAt(i)>='0'&&value.charAt(i)<='9') {
										int sT=value.charAt(i)-'0'+8;
										time.lastElement().setStartTime(sT);
										int comma = 0;
										while(i!=value.length()&&value.charAt(i)!=' ') {
											if(value.charAt(i)==',') {
												comma++;
											}
											i++;
										}
										if(i==value.length()) {
											time.lastElement().setEndTime(sT+comma+1);
										}
										else {
											time.lastElement().setEndTime(sT+comma);
										}
									}
								}
								
								break;
							default:
								break;
							}
						}
					}
					if(lectureType.substring(0, 2).compareTo("전공")==0) {
						isMajor = true;
						if(lectureType.length()==2) {
							lectureTypeInt=2;
						}
						else if(lectureType.substring(2, 4).compareTo("기초")==0) {
							lectureTypeInt = 1;
						}
					}
					else {
						if(lectureType.length()==2) {
							lectureTypeInt=0;
						}
					}
					if(rowIndex!=1&&lectures.lastElement().lastElement().getCode().substring(0, 5).compareTo(code.substring(0, 5))==0) {
						lectures.lastElement().add(new Lecture(time, lectureName, professor, credit, isMajor, lectureTypeInt, level, code));
					}
					else {
						lectures.add(new Vector<Lecture>());
						lectures.lastElement().add(new Lecture(time, lectureName, professor, credit, isMajor, lectureTypeInt, level, code));	
					}
					
				}
			}
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	Vector<Vector<Lecture>> getLectures() {
		return lectures;
	}
	
}
