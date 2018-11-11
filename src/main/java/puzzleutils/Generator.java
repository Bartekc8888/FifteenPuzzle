package puzzleutils;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.*;

public class Generator {
    private String path;
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
    private List<byte[]> visitedNodes = new ArrayList<>();
    private Queue<byte[]> queue = new LinkedList<>();

    public Generator(String path, int size, int maxDepth) {
        this.path = path;
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
        }

        System.out.println(this);
    }

    public void generate() {
        int depth = 0;
        this.queue.add(this.valuesArray);
        Serializer serializer = new Serializer(this.path, this.size);

        while (depth != this.maxDepth) {
            Queue<byte[]> tempQueue = new LinkedList<>();
            ++depth;

            while (!this.queue.isEmpty()) {
                byte[] element = this.queue.remove();
                List<byte[]> children = this.generateChildren(element);

                for (byte[] child : children) {
                    if (!this.isAlreadyVisited(child)) {
                        tempQueue.add(child);
                        this.visitedNodes.add(child);
                    }
                }
            }

            this.queue = tempQueue;
            serializer.printToFile(this.visitedNodes, depth);
        }

    }

    private boolean isAlreadyVisited(byte[] child) {

        for (byte[] visited : this.visitedNodes) {
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
}
