package Tools;

import PuzzleSolvers.AlgorithmType;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SolverArgumentsTest {

    @Test
    void populateCommands() {
        String[] args = {"bfs", "RDUL", "01_0001.txt", "4x4_01_0001_bfs_rdul_sol.txt", "4x4_01_0001_bfs_rdul_stats.txt"};

        SolverArguments solverArguments = new SolverArguments();
        new CommandLine(solverArguments).setCaseInsensitiveEnumValuesAllowed(true).parse(args);

        assertEquals(AlgorithmType.BFS, solverArguments.getAlgorithmType());
        assertEquals("RDUL", solverArguments.getStrategy());
        assertNotNull(solverArguments.getSourceFile());
        assertNotNull(solverArguments.getResultFile());
        assertNotNull(solverArguments.getMetadataFile());
    }
}