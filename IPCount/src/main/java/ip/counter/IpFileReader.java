package ip.counter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;

public class IpFileReader {

    private IpConverter converter;
    private IpCounter counter;

    private static final long MAX_NUMBER_IP_ADDRESSES = 1L << 32;

    public IpFileReader(IpConverter converter, IpCounter counter) {
        this.converter = converter;
        this.counter = counter;
    }

    public void readFile(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("Input is null");
        }
        long start = System.currentTimeMillis();
        long countLines = 0;
        int minute = 1;
        String line = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            System.out.println("Started file read!");
            while ((line = reader.readLine()) != null && counter.getCount() < MAX_NUMBER_IP_ADDRESSES) {
                countLines++;
                counter.countUniqueIp(converter.convertToLong(line));
                long time = System.currentTimeMillis();
                if ((((time - start) / 1000) / minute) == 60) {
                    minute++;
                    System.out.println("Reading is " + (minute - 1) + " minutes, readed - " + countLines
                            + " addresses, it's containts " + counter.getCount() + " unique addresses");
                }
            }
        } catch (UnknownHostException e) {
            throw new FileException("Data in \"" + fileName + "\" has invalid format!");
        } catch (IOException e) {
            throw new FileException("File \"" + fileName + "\" not found!");
        }
    }

}
