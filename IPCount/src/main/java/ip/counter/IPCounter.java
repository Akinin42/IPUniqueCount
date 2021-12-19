package ip.counter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPCounter {

    int[] depository = new int[1 << 27];
    long count = 0;

    public long countUnique(String fileName) {
        long start = System.currentTimeMillis();
        long countLines = 0;
        int minute = 1;
        String line = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
            System.out.println("Started file read!");
            while ((line = reader.readLine()) != null) {
                countLines++;
                registerLongValue(toLongValue(line));

                long time = System.currentTimeMillis();
                if ((((time - start) / 1000) / minute) == 60) {
                    minute++;
                    System.out.println("Reading is " + (minute - 1) + " minutes, readed - " + countLines
                            + " addresses, it's containts " + count + " unique addresses");
                }
            }

        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new IllegalArgumentException(fileName + " containts invalid data format: " + line);
        } catch (IOException e) {
            throw new NoFileException("File \"" + fileName + "\" not found!");
        }

        long finish = System.currentTimeMillis();
        long analisisTime = (finish - start) / 1000;
        System.out.println("Size this file is " + countLines + " lines");
        System.out.println("Counting unique IPv4 addresses in " + fileName + " lasted " + analisisTime / 60
                + " minutes " + analisisTime % 60 + " seconds!");
        return count;
    }

    private void registerLongValue(long longValue) {
        int index = (int) (longValue >> 5);
        int bit = 1 << (longValue & 31);
        if ((depository[index] & bit) == 0) {
            count++;
            depository[index] |= bit;
        }
    }

    private long toLongValue(String ipString) throws UnknownHostException {
        long result = 0;
        for (byte b : InetAddress.getByName(ipString).getAddress())
            result = (result << 8) | (b & 255);
        return result;
    }
}
