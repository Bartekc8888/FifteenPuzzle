import lombok.extern.slf4j.Slf4j;
import puzzleutils.PuzzleHandling.Generator;

@Slf4j
public class Main {

    public static void main(String[] args) {
        log.info("Program is starting...");
        Generator generator = new Generator(4, 8);

        String dir = System.getProperty("user.dir");
        generator.generateToFile(dir + "/generated");
    }
}
