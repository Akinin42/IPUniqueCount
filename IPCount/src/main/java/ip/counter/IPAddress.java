package ip.counter;

public class IPAddress {
    
    private int[]firstNumber = new int[256];
    private int[]secondNumber = new int[256];
    private int[]thirdNumber = new int[256];
    
    public int getFirstNumber(int index) {
        return firstNumber[index];
    }
    public void setFirstNumber(int index) {
        this.firstNumber[index] = 1;
    }
    public int getSecondNumber(int index) {
        return secondNumber[index];
    }
    public void setSecondNumber(int index) {
        this.secondNumber[index] = 1;
    }
    public int getThirdNumber(int index) {
        return thirdNumber[index];
    }
    public void setThirdNumber(int index) {
        this.thirdNumber[index] = 1;
    }
}