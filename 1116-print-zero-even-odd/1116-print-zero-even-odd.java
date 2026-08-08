import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class ZeroEvenOdd {
    private int n;
    
    // Semaphores to control execution flow
    private Semaphore zeroSem = new Semaphore(1);
    private Semaphore oddSem = new Semaphore(0);
    private Semaphore evenSem = new Semaphore(0);
    
    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            zeroSem.acquire();
            printNumber.accept(0);
            
            // Hand off control to either odd or even based on the current step
            if (i % 2 != 0) {
                oddSem.release();
            } else {
                evenSem.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            evenSem.acquire();
            printNumber.accept(i);
            
            // Hand control back to zero
            zeroSem.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            oddSem.acquire();
            printNumber.accept(i);
            
            // Hand control back to zero
            zeroSem.release();
        }
    }
}
