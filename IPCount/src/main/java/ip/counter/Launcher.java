package ip.counter;

public class Launcher {

    public static void main(String[] args) {
        IpConverter converter = new IpConverter();
        IpCounter counter = new IpCounter();
        IpFileReader reader = new IpFileReader(converter, counter);
        reader.readFile("D:\\Downloads\\ip_addresses");
        System.out.println(counter.getCount());
    }
}