package ip.counter;

public class Launcher {

    public static void main(String[] args) {
        IPCounter counter = new IPCounter();
//        System.out.println("Unique IP adresses in this file is " + counter.countUnique("D:\\Downloads\\ip_addresses"));
        System.out.println(java.lang.Runtime.getRuntime().maxMemory());
//        System.out.println("Unique IP adresses in this file is " + counter.countUniqueBySet("D:\\Downloads\\ip_addresses"));
//        System.out.println("Unique IP adresses in this file is " + counter.countUniqueByMap("D:\\Downloads\\ip_addresses"));
        System.out.println("Unique IP adresses in this file is " + counter.countUnique("D:\\Downloads\\ip_addresses"));
//        System.out.println("Unique IP adresses in this file is " + counter.countUnique("C:\\Repositories\\IPUniqueCount\\IPCount\\src\\main\\resources\\testipfile.txt"));        
    }
}