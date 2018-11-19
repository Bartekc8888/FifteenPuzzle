package Tools;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GeneratorArgumentsTest {

    @Test
    void pupulateCommand() {
        String[] args = {"generate", "-w", "4", "-h", "4", "-d", "15", "generated/puzzle.txt"};

        GeneratorArguments generatorArguments = CommandLine.populateCommand(new GeneratorArguments(), args);

        assertEquals(4, generatorArguments.getWidth());
        assertEquals(4, generatorArguments.getHeight());
        assertEquals(15, generatorArguments.getMaxDepth());
        assertNotNull(generatorArguments.getOutputFile());
    }
}