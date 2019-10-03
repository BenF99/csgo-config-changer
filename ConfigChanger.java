import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigChanger {

	private Path path;
	private String user_input;
	private String command;
	private String value;

	public ConfigChanger(Path path, String user_input) {
		this.path = path;
		setComVal(user_input);
	}
	
	public void setComVal(String user_input) {
		String[] parts = user_input.split("\\s+");
		String command = parts[0];
		String value = parts[1];

		this.command = command;
		this.value = value;
	
	}

	public void changeValue() throws IOException {
		Charset charset = StandardCharsets.UTF_8;
		String content = new String(Files.readAllBytes(this.path), charset);
		content = content.replaceAll("(?m)^" + this.command + "\\s\\d*\\.?\\d*$", this.command + " " + this.value);
		Files.write(this.path, content.getBytes(charset));
	}
}
