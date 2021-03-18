package ip.counter;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class IPAddress {
    
    private AtomicIntegerArray firstNumber = new AtomicIntegerArray(256);
    private AtomicIntegerArray secondNumber = new AtomicIntegerArray(256);
    private AtomicIntegerArray thirdNumber = new AtomicIntegerArray(256);
    
    public int getFirstNumber(int index) {
        return firstNumber.get(index);
    }
    public void setFirstNumber(int index) {
        this.firstNumber.setRelease(index, 1);
    }
    public int getSecondNumber(int index) {
        return secondNumber.get(index);
    }
    public void setSecondNumber(int index) {
        this.secondNumber.setRelease(index, 1);
    }
    public int getThirdNumber(int index) {
        return thirdNumber.get(index);
    }
    public void setThirdNumber(int index) {
        this.thirdNumber.setRelease(index, 1);
    }
}