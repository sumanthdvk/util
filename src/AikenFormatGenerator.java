import java.io.*;

public class AikenFormatGenerator {
    public AikenFormatGenerator() throws FileNotFoundException {
    }

    private void parseFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        int mainCount = 0;
        int subCount = 0;

        File file = new File("/Users/aparajith/Desktop/Linear Algebra/Matrices_Assign1 (with stars).txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;

        while ((st = br.readLine()) != null) {
            sb.append(st);
            sb.append("\n");
        }
        String fullText = sb.toString().replaceAll("\n", " ");
        String[] words = fullText.split(" ");

        StringBuilder aikenText = new StringBuilder();
        boolean isChoices = false;
        boolean isSection = false;
        int answer = 0;
        for (String word : words) {
            if (word.equalsIgnoreCase("\\begin{enumerate}") && !isSection && !isChoices) {
                mainCount = 1;
                isSection = true;
                word = "";
            }
            if (word.equalsIgnoreCase("\\begin{enumerate}") && isSection && !isChoices) {
                subCount = 1;
                isChoices = true;
                word = "";
            }
            if (word.equalsIgnoreCase("\\item") && isSection && !isChoices) {
                aikenText.append("\n");
                word = mainCount++ + ".";
            }
            if (word.equalsIgnoreCase("\\item") && isSection && isChoices) {
                aikenText.append("\n");
                if (subCount == 1)
                    word = "A)";
                else if (subCount == 2)
                    word = "B)";
                else if (subCount == 3)
                    word = "C)";
                else if (subCount == 4)
                    word = "D)";

                subCount++;

            }
            if (word.equalsIgnoreCase("{*}") && isSection && isChoices) {
                answer = subCount;
                continue;
            }
            if (word.equalsIgnoreCase("\\end{enumerate}") && isChoices) {
                isChoices = false;
                if (answer == 2)
                    word = "\nANSWER: A\n";
                else if (answer == 3)
                    word = "\nANSWER: B\n";
                else if (answer == 4)
                    word = "\nANSWER: C\n";
                else if (answer == 5)
                    word = "\nANSWER: D\n";
            }

            if (word.equalsIgnoreCase("\\end{enumerate}") && !isChoices) {
                isSection = false;
                word = "";
            }
            if (!word.isEmpty())
                aikenText.append(word).append(" ");
        }
        System.out.println("Text " + aikenText);
    }

    public static void main(String[] args) {
        try {
            AikenFormatGenerator aikenFormatGenerator = new AikenFormatGenerator();
            aikenFormatGenerator.parseFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
