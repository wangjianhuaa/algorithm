package thread;

import java.util.concurrent.*;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/20 15:59
 */
public class Test {



    public static void main(String[] args) {

//        Executors.newFixedThreadPool(1);
//
//        Executors.newCachedThreadPool();
//
//        Executors.newScheduledThreadPool();
//
//        Executors.newSingleThreadExecutor();


        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(),Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardOldestPolicy());


        System.out.println(executor.getThreadFactory().getClass());
    }
}
