package ip.counter;

public class IpCounter {

    private int[] depository = new int[1 << 27];
    private long count = 0;

    public long getCount() {
        return count;
    }

    public void countUniqueIp(long ipLong) {
        int index = (int) (ipLong >> 5);
        int value = 1 << (ipLong & 31);
        if ((depository[index] & value) == 0) {
            count++;
            depository[index] |= value;
        }
    }
}
