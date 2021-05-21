import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class TextToAikenUtil {
    public static void main(String[] args) {
        List<String> lines = Collections.emptyList();
        try
        {
            lines = Files.readAllLines(Paths.get("/Users/sumanth/repo/util/src/sample.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Unable to process");
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        AtomicBoolean lastWordWasAnswer = new AtomicBoolean(false);

        lines.forEach(word -> {
            if (Pattern.matches("^[A-G]([).].*)", word) ||
                Pattern.matches("^ANSWER:.*", word) ||
                Pattern.matches("\\d([).].*)", word)
            ) {
                sb.append("\n");
            }
            sb.append(word);
            if (Pattern.matches("^ANSWER:.*", word)) lastWordWasAnswer.set(true);
            if (lastWordWasAnswer.get()) {
                sb.append("\n");
                lastWordWasAnswer.set(false);
            }
        });
        System.out.println(sb.toString());
    }
}
