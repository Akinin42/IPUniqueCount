package ip.counter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class IPCounterStream {

    private long count = 0;

    public long countUnique(String fileName) {
        long start = System.currentTimeMillis();
        Map<String, IPAddress> setIPAddress = createSet();
        if (fileName == null) {
            throw new IllegalArgumentException("File name can't be null!");
        }
        try (Stream<String> fileLines = Files.lines(new File(fileName).toPath())) {
            fileLines.map(line -> line.split("\\.")).forEach(inputIP -> {
                IPAddress address = setIPAddress.get(inputIP[0]);
                int firstNumber = Integer.parseInt(inputIP[1]);
                int secondNumber = Integer.parseInt(inputIP[2]);
                int thirdNumber = Integer.parseInt(inputIP[3]);
                if (address.getFirstNumber(firstNumber) != 1 || address.getSecondNumber(secondNumber) != 1
                        || address.getThirdNumber(thirdNumber) != 1) {
                    count++;
                    address.setFirstNumber(firstNumber);
                    address.setSecondNumber(secondNumber);
                    address.setThirdNumber(thirdNumber);
                }
            });
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new IllegalArgumentException(fileName + " containts invalid data format: " + e);
        } catch (IOException e) {
            throw new NoFileException("File \"" + fileName + "\" not found!", e);
        }
        long finish = System.currentTimeMillis();
        long analisisTime = (finish - start) / 1000;
        System.out.println("Counting unique IPv4 addresses in " + fileName + " lasted " + analisisTime / 60
                + " minutes " + analisisTime % 60 + " seconds!");
        return count;
    }

    private Map<String, IPAddress> createSet() {
        Map<String, IPAddress> setIPAddress = new LinkedHashMap<>();
        for (int i = 0; i < 256; i++) {
            setIPAddress.put(Integer.toString(i), new IPAddress());
        }
        return setIPAddress;
    }
}