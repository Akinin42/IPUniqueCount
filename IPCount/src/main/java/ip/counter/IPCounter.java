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
        Map<String, IPAddress> setIPAddress = createSet();
        if (fileName == null) {
            throw new IllegalArgumentException("File name can't be null!");
        }
        String line = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
            while ((line = reader.readLine()) != null) {
                String[] inputIP = line.split("\\.");
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
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new IllegalArgumentException(fileName + " containts invalid data format: " + line);
        } catch (IOException e) {
            throw new NoFileException("File \"" + fileName + "\" not found!");
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