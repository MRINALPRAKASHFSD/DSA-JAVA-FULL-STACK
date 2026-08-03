import java.util.concurrent.Semaphore;

class FooBar {
    private int n;
    
    // foo gets 1 permit initially so it can run first
    private Semaphore fooSem = new Semaphore(1);
    // bar gets 0 permits initially so it must wait
    private Semaphore barSem = new Semaphore(0);

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            // Wait for permission to print "foo"
            fooSem.acquire();
            
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            
            // Give permission to print "bar"
            barSem.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            // Wait for permission to print "bar"
            barSem.acquire();
            
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            
            // Give permission to print "foo" for the next iteration
            fooSem.release();
        }
    }
}
