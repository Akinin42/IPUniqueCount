package ip.counter;

public class Launcher {

    public static void main(String[] args) {
        IPCounter counter = new IPCounter();
        System.out.println("Unique IP adresses in this file is " + counter.countUnique("D:\\Downloads\\ip_addresses"));        
    }
}