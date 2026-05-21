package hust.soict.hedspi.garbage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NoGarbage {
    public static void main(String[] args) throws IOException {
        String filename = "test.exe";
        byte[] inputBytes = Files.readAllBytes(Paths.get(filename));
        long startTime = System.currentTimeMillis();

        StringBuilder outputStringBuilder = new StringBuilder();
        for (byte b : inputBytes) {
            outputStringBuilder.append((char) b);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Time taken with StringBuilder: " + (endTime - startTime) + " ms");
    }
}