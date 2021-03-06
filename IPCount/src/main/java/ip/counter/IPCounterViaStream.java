package ip.counter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class IPCounterViaStream {

    private long result = 0;

    public long countUniqueViaStream(String fileName) {
        long start = System.currentTimeMillis();
        Map<String, PartsIP> keepers = createNumberKeepersWithArrayInteger();
        if (fileName == null) {
            throw new IllegalArgumentException("File name can't be null!");
        }
        try (Stream<String> fileLines = Files.lines(new File(fileName).toPath())) {
            fileLines.map(line -> line.split("\\.")).forEach(s -> {
                PartsIP keeper = keepers.get(s[0]);
                int[] first = keeper.getFirstNumber();
                int[] second = keeper.getSecondNumber();
                int[] third = keeper.getThirdNumber();
                int firstNumber = Integer.parseInt(s[1]);
                int secondNumber = Integer.parseInt(s[2]);
                int thirdNumber = Integer.parseInt(s[3]);
                if (first[firstNumber] != 1 || second[secondNumber] != 1 || third[thirdNumber] != 1) {
                    result++;
                    first[firstNumber] = 1;
                    second[secondNumber] = 1;
                    third[thirdNumber] = 1;
                }
            });
        } catch (IOException e) {
            throw new NoFileException("File \"" + fileName + "\" not found!", e);
        }
        long finish = System.currentTimeMillis();
        long analisisTime = (finish - start) / 1000;
        System.out.println("Analysis " + fileName + " lasted " + analisisTime / 60 + " minutes " + analisisTime % 60 + " seconds!");
        return result;
    }    

    private Map<String, PartsIP> createNumberKeepersWithArrayInteger() {
        Map<String, PartsIP> keepers = new LinkedHashMap<>();
        for (int i = 0; i < 256; i++) {
            keepers.put(Integer.toString(i), new PartsIP());
        }
        return keepers;
    }    
}