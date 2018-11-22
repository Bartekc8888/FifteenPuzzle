package puzzleutils.PuzzleHandling;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.*;

import lombok.extern.slf4j.Slf4j;
import puzzleutils.PuzzleContainers.Puzzle;

@Slf4j
public class Generator {
    private int maxDepth;
    private int size;
    private static final int[][] HELP_ARRAY_SIZE_2 = new int[][]{{1, 2}, {0, 3}, {0, 3}, {1, 2}};
    private static final int[][] HELP_ARRAY_SIZE_3 = new int[][]{{1, 3}, {0, 2, 4}, {1, 5}, {0, 4, 6}, {1, 3, 5, 7}, {2, 4, 8}, {3, 7}, {4, 6, 8}, {5, 7}};
    private static final int[][] HELP_ARRAY_SIZE_4 = new int[][]{{1, 4}, {0, 2, 5}, {1, 3, 6}, {2, 7}, {0, 5, 8}, {1, 4, 6, 9}, {2, 5, 7, 10}, {3, 6, 11}, {4, 9, 12},
                                                                 {5, 8, 10, 13}, {6, 9, 11, 14}, {7, 10, 15}, {8, 13}, {9, 12, 14}, {10, 13, 15}, {11, 14}};
    private static final byte[] START_ARRAY_SIZE_2 = new byte[]{18, 48};
    private static final byte[] START_ARRAY_SIZE_3 = new byte[]{18, 52, 86, 120, 0};
    private static final byte[] START_ARRAY_SIZE_4 = new byte[]{18, 52, 86, 120, -102, -68, -34, -16};
    private byte[] valuesArray;
    private int[][] helpArray;

    public Generator(int size, int maxDepth) {
        this.maxDepth = maxDepth;
        this.size = size;
        switch (this.size) {
            case 2:
                this.helpArray = HELP_ARRAY_SIZE_2;
                this.valuesArray = START_ARRAY_SIZE_2;
                break;
            case 3:
                this.helpArray = HELP_ARRAY_SIZE_3;
                this.valuesArray = START_ARRAY_SIZE_3;
                break;
            case 4:
                this.helpArray = HELP_ARRAY_SIZE_4;
                this.valuesArray = START_ARRAY_SIZE_4;
                break;
            default:
                throw new IllegalArgumentException();
        }

        log.trace(this.toString());
    }

    public void generateToFile(String path) {
        Puzzle puzzle = generate();

        PuzzleSerializer.printToFile(path, puzzle);
    }

    private Puzzle generate() {
        List<byte[]> visitedNodes = new ArrayList<>();
        Queue<byte[]> queue = new LinkedList<>();
        int depth = 0;
        queue.add(this.valuesArray);


        while (depth != this.maxDepth) {
            Queue<byte[]> tempQueue = new LinkedList<>();
            ++depth;

            while (!queue.isEmpty()) {
                byte[] element = queue.remove();
                List<byte[]> children = this.generateChildren(element);

                for (byte[] child : children) {
                    if (!this.isAlreadyVisited(visitedNodes, child)) {
                        tempQueue.add(child);
                        visitedNodes.add(child);
                    }
                }
            }

            queue = tempQueue;
        }

        Random random = new Random();
        byte[] nodeValues = visitedNodes.get(random.nextInt(visitedNodes.size()));
        int[] puzzleValues = convertToFullWidthByte(nodeValues);
        return Puzzle.createPuzzle(size, size, puzzleValues);
    }

    private boolean isAlreadyVisited(List<byte[]> visitedNodes, byte[] child) {
        for (byte[] visited : visitedNodes) {
            if (Arrays.equals(child, visited)) {
                return true;
            }
        }

        return false;
    }

    private int findEmptyBlock(byte[] array) {
        int emptyBlockNumber = 0;

        for (int i = 0; i < array.length; ++i) {
            int first = (array[i] & 240) >> 4;
            int second = array[i] & 15;
            if (first == 0) {
                emptyBlockNumber = i * 2;
                break;
            }

            if (second == 0) {
                emptyBlockNumber = i * 2 + 1;
                break;
            }
        }

        return emptyBlockNumber;
    }

    private List<byte[]> generateChildren(byte[] array) {
        List<byte[]> children = new ArrayList<>();
        int emptyBlockPosition = this.findEmptyBlock(array);
        int EmptyBlockByteNumber = (int) Math.floor((double) emptyBlockPosition / 2.0D);
        int EmptyBlockRemainder = emptyBlockPosition % 2;
        int[] possibleMoves = this.helpArray[emptyBlockPosition];

        for (int possibleMove : possibleMoves) {
            byte[] tempArray = array.clone();
            int changedBlockByteNumber = (int) Math.floor((double) possibleMove / 2.0D);
            int changedBlockRemainder = possibleMove % 2;
            byte value;

            if (changedBlockRemainder == 0) {
                value = (byte) ((tempArray[changedBlockByteNumber] & 240) >> 4);
                tempArray[changedBlockByteNumber] = (byte) (tempArray[changedBlockByteNumber] & 15);
            } else {
                value = (byte) (tempArray[changedBlockByteNumber] & 15);
                tempArray[changedBlockByteNumber] = (byte) (tempArray[changedBlockByteNumber] & 240);
            }

            if (EmptyBlockRemainder == 0) {
                tempArray[EmptyBlockByteNumber] = (byte) (tempArray[EmptyBlockByteNumber] | value << 4);
            } else {
                tempArray[EmptyBlockByteNumber] |= value;
            }

            if (!Arrays.equals(tempArray, this.valuesArray)) {
                children.add(tempArray);
            }
        }

        return children;
    }

    private int[] convertToFullWidthByte(byte[] halfWidthBytes) {
        int[] fullWidthBytes = new int[size*size];

        for (int i = 0; i < fullWidthBytes.length; i++) {
            if (i % 2 == 0) {
                fullWidthBytes[i] = (halfWidthBytes[i / 2] & 240) >> 4;
            } else {
                fullWidthBytes[i] = halfWidthBytes[i / 2] & 15;
            }
        }

        return fullWidthBytes;
    }
}
