import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class ConfigMain {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		Path path_;
		String user_input;
		Path sourcePath = Paths.get("./path.txt");
		
		if (!Files.exists(sourcePath)) {
			while (true) {
				String path = JOptionPane.showInputDialog("Enter path");

				if (path.contains("\"")) {
					path = path.replace("\"", "");
				}
				path = path.replaceAll("\\\\", "/");
				path_ = Paths.get(path);

				if (Files.exists(path_)) {
					Files.write(sourcePath, Arrays.asList(path), StandardOpenOption.CREATE);
					break;
				} else {
					JOptionPane.showMessageDialog(null, "The path does not exist, please try again.");
				}
			}
		} else {
			path_ = Paths.get(Files.readAllLines(Paths.get("./path.txt")).get(0));
		}
		

		while (true) {
			user_input = JOptionPane.showInputDialog("Enter Command e.g. sensitivity 2");
			if (user_input.matches("^[a-zA-Z0-9_]*\\s-?\\d+\\.?\\d*$")) {
				break;
			} else {
				JOptionPane.showMessageDialog(null, "Please enter the command in the correct format.");
			}
		}
		
		
		ConfigChanger main = new ConfigChanger(path_, user_input);
		main.changeValue();

	}
}