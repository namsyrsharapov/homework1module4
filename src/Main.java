class Counter {
    private int count;
    public void increment() {
        count++;
    }
    public int getValue() {
        return count;
    }
}
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread thread1 = new Thread(new CounterThread(counter));
        Thread thread2 = new Thread(new CounterThread(counter));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("count: " + counter.getValue());

    }static class CounterThread implements Runnable {
        private final Counter counter;
        public CounterThread(Counter counter) {
            this.counter = counter;
        }

        public void run() {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        }
    }
}
// Результат может меняться потому что два потока конкурируют друг с другом без порядка выполнения.
//Оба потока пытаются обновить значение count одновременно и могут получать разные результаты.