package ip.counter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.UnknownHostException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IpConverterTest {

    private IpConverter converter;

    @BeforeEach
    void init() {
        converter = new IpConverter();
    }

    @Test
    void convertToLongShouldReternExpectedLong() throws UnknownHostException {
        long expected = 2517603855L;
        long actual = converter.convertToLong("150.15.150.15");
        assertEquals(expected, actual);
    }

    @Test
    void convertToLongShouldReternExpectedLongWhenInputZeroIp() throws UnknownHostException {
        long expected = 0;
        long actual = converter.convertToLong("0.0.0.0");
        assertEquals(expected, actual);
    }

    @Test
    void convertToLongShouldReternExpectedLongWhenInputLastIp() throws UnknownHostException {
        long expected = 4294967295L;
        long actual = converter.convertToLong("255.255.255.255");
        assertEquals(expected, actual);
    }
}
