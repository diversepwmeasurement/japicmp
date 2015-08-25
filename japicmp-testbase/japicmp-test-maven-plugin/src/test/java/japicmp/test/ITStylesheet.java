package japicmp.test;

import org.hamcrest.Matcher;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class ITStylesheet {

	@Test
	public void testStylesheetIsUsed() throws IOException {
		Path htmlPath = Paths.get(System.getProperty("user.dir"), "target", "japicmp", "japicmp.html");
		assertThat(Files.exists(htmlPath), is(true));
		List<String> htmlLines = Files.readAllLines(htmlPath);
		List<String> cssLines = Files.readAllLines(Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "css", "stylesheet.css"));
		assertThat(htmlLines.size(), not(is(0)));
		assertThat(cssLines.size(), not(is(0)));
		for (String cssLine : cssLines) {
			assertThat(htmlLines, hasItem(cssLine));
		}
	}

	private String readFileAsString(Path htmlPath) throws IOException {
		byte[] htmlBytes = Files.readAllBytes(htmlPath);
		return new String(htmlBytes, "UTF-8");
	}
}
