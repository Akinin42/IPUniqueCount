package ip.counter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IpCounterTest {

    private IpCounter counter;

    @BeforeEach
    void init() {
        counter = new IpCounter();
    }

    @Test
    void getCountShouldReturnNumberUniqueNumberWhenNotRepeatNumber() {
        long[] uniqueNumbers = { 413543613L, 4135451213L, 4143613L, 41313L, 4135433L };
        for (int i = 0; i < uniqueNumbers.length; i++) {
            counter.countUniqueIp(uniqueNumbers[i]);
        }
        assertEquals(uniqueNumbers.length, counter.getCount());
    }

    @Test
    void getCountShouldReturnOneWhenOnlyOneUnique() {
        long one = 413543613L;
        for (int i = 0; i < 10; i++) {
            counter.countUniqueIp(one);
        }
        assertEquals(1L, counter.getCount());
    }

    @Test
    void getCountShouldReturnOneWhenInputZero() {
        long zero = 0;
        counter.countUniqueIp(zero);
        assertEquals(1L, counter.getCount());
    }

    @Test
    void getCountShouldReturnOneWhenInputMaxIpNumber() {
        long maxIp = 4294967295L;
        counter.countUniqueIp(maxIp);
        assertEquals(1L, counter.getCount());
    }
}
