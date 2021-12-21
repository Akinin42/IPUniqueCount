package ip.counter;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpConverter {

    public long convertToLong(String ip) throws UnknownHostException {
        long result = 0;
        for (byte b : InetAddress.getByName(ip).getAddress())
            result = (result << 8) | (b & 255);
        return result;
    }
}
