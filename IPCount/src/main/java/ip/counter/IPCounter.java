package ip.counter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class IPCounter {

    public long countUnique(String fileName) {
        long count = 0;
        long start = System.currentTimeMillis();
        Map<String, PartsIP> cacheIP = createCache();
        if (fileName == null) {
            throw new IllegalArgumentException("File name can't be null!");
        }
        String line = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
            while ((line = reader.readLine()) != null) {
                String[] inputIP = line.split("\\.");
                PartsIP partsIP = cacheIP.get(inputIP[0]);
                int[] firstPart = partsIP.getFirstNumber();
                int[] secondPart = partsIP.getSecondNumber();
                int[] thirdPart = partsIP.getThirdNumber();
                int firstNumber = Integer.parseInt(inputIP[1]);
                int secondNumber = Integer.parseInt(inputIP[2]);
                int thirdNumber = Integer.parseInt(inputIP[3]);
                if (firstPart[firstNumber] != 1 || secondPart[secondNumber] != 1 || thirdPart[thirdNumber] != 1) {
                    count++;
                    firstPart[firstNumber] = 1;
                    secondPart[secondNumber] = 1;
                    thirdPart[thirdNumber] = 1;
                }
            }
        } catch (RuntimeException e) {
            throw new IllegalArgumentException(fileName + " containts invalid data format: " + line);
        } catch (IOException e) {
            throw new NoFileException("File \"" + fileName + "\" not found!");
        }
        long finish = System.currentTimeMillis();
        long analisisTime = (finish - start) / 1000;
        System.out.println("Analysis " + fileName + " lasted " + analisisTime / 60 + " minutes " + analisisTime % 60
                + " seconds!");
        return count;
    }

    private Map<String, PartsIP> createCache() {
        Map<String, PartsIP> cache = new LinkedHashMap<>();
        for (int i = 0; i < 256; i++) {
            cache.put(Integer.toString(i), new PartsIP());
        }
        return cache;
    }
}