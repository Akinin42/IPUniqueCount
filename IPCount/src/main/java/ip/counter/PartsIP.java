package ip.counter;

public class PartsIP {
    
    private int[]firstNumber = new int[256];
    private int[]secondNumber = new int[256];
    private int[]thirdNumber = new int[256];
    
    public int[] getFirstNumber() {
        return firstNumber;
    }
    public void setFirstNumber(int[] firstNumber) {
        this.firstNumber = firstNumber;
    }
    public int[] getSecondNumber() {
        return secondNumber;
    }
    public void setSecondNumber(int[] secondNumber) {
        this.secondNumber = secondNumber;
    }
    public int[] getThirdNumber() {
        return thirdNumber;
    }
    public void setThirdNumber(int[] thirdNumber) {
        this.thirdNumber = thirdNumber;
    }
}