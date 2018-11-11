package puzzleutils;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class Serializer {
    private String path;
    private int numberOfPreviousResults = 0;
    private String newline = System.getProperty("line.separator");
    private int size;

    public Serializer(String path, int size) {
        this.path = path;
        this.size = size;
    }

    public void printToFile(List<byte[]> visitedNodes, int depth) {
        for (int i = this.numberOfPreviousResults; i < visitedNodes.size(); ++i) {
            int number = i - this.numberOfPreviousResults + 1;

            try (PrintWriter out = new PrintWriter(this.path + "/" +
                                                   String.format("%dx%d_%02d_%05d.txt", this.size, this.size, depth, number))) {
                int iterator = 0;
                out.print(this.size + " " + this.size);
                out.print(this.newline);

                for (int j = 0; j < visitedNodes.get(i).length; ++j) {
                    out.print((visitedNodes.get(i)[j] & 240) >> 4);
                    ++iterator;
                    if (iterator == this.size) {
                        out.print(this.newline);
                        iterator = 0;
                    } else {
                        out.print(" ");
                    }

                    if (this.size == 3 && j == 4) {
                        break;
                    }

                    out.print(visitedNodes.get(i)[j] & 15);
                    ++iterator;
                    if (iterator == this.size) {
                        out.print(this.newline);
                        iterator = 0;
                    } else {
                        out.print(" ");
                    }
                }

            } catch (FileNotFoundException var18) {
                var18.printStackTrace();
            }
        }

        this.numberOfPreviousResults = visitedNodes.size();
    }
}
