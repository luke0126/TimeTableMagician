import java.awt.Window;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

class TimeTableMain{

	public static SelectLecture sL;
	
	public static void main(String[] args) {

		String filepath = "";
		
		JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jFileChooser.setCurrentDirectory(new File("/"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xls", "xlsx");
		jFileChooser.setFileFilter(filter);
		jFileChooser.setDialogTitle("Select file");
		jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		int returnVal = jFileChooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			filepath = jFileChooser.getSelectedFile().toString();
		} else if (returnVal == jFileChooser.CANCEL_OPTION) {
			filepath = "";
		}
		 sL = new SelectLecture(filepath);
	}
}
