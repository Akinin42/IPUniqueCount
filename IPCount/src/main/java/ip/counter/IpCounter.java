package ip.counter;

public class IpCounter {

    private int[] depository = new int[1 << 27];
    private long count = 0;

    public long getCount() {
        return count;
    }

    public void countUniqueIp(long ip) {
        int index = (int) (ip >> 5);
        int bit = 1 << (ip & 31);
        if ((depository[index] & bit) == 0) {
            count++;
            depository[index] |= bit;
        }
    }
}
