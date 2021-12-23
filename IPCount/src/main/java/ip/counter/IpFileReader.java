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
        String line = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((line = reader.readLine()) != null && counter.getCount() < MAX_NUMBER_IP_ADDRESSES) {
                counter.countUniqueIp(converter.convertToLong(line));
            }
        } catch (UnknownHostException e) {
            throw new FileException("Data in \"" + fileName + "\" has invalid format!");
        } catch (IOException e) {
            throw new FileException("File \"" + fileName + "\" not found!");
        }
    }
}
