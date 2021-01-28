package someTrain.CountDownLatchAndCyclicBarrier;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        for(int i=0;i<cyclicBarrier.getParties();i++){
            new Thread(new MyRunnable(cyclicBarrier),"队友"+i).start();
        }

        System.out.println("main function is finished");
    }

    private static class MyRunnable implements Runnable{

        private CyclicBarrier barrier;

        public MyRunnable(CyclicBarrier barrier){
            this.barrier = barrier;
        }

        @Override
        public void run() {
            for(int i=0;i<3;i++){
                Random rand = new Random();
                int randomNum = rand.nextInt((3000-1000)+1)+1000;//产生1000到3000之间的随机整数
                try {
                    Thread.sleep(randomNum);
                    System.out.println(Thread.currentThread().getName()+"，通过了第"+i+"个障碍物，使用了"+((double)randomNum/1000)+"s");
                    this.barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

