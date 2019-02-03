
public class NoVisiabilityTest extends Thread {

    private boolean ready = false;

    private int number;

    public void run() {
        while (number!=-1) {
            int a = 0;
            number++;
//			Thread.yield();
        }
        System.out.println("\n"+number+"\n"+ready);
    }

    public void readyOn() {
        this.ready = true;
        number = -1;
    }

    public static void main(String[] args) throws InterruptedException {
        NoVisiabilityTest readThread = new NoVisiabilityTest();
        readThread.start();
        while(readThread.number!=-1){
            System.out.println(readThread.number);
            readThread.readyOn();
        }

    }
}