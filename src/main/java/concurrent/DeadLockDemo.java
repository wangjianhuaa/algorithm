package concurrent;

/**
 * @author wangjianhua
 * @Description 死锁
 * @date 2022/1/25/025 22:10
 */
public class DeadLockDemo {

    private static String resource_a = "A";

    private static String resource_b = "B";

    public static void main(String[] args) {
        deadLock();
    }

    /*
    main方法跑起来后 通过java的jps命令找到所有java进程的pid
    jps
    10752 DeadLockDemo
    3968 RemoteMavenServer36
    8384
    6936 Launcher
    9576 Jps
    jstack [pid] 可以快速定位死锁
    jstack 10752
2022-01-25 22:18:53
Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.161-b12 mixed mode):

"DestroyJavaVM" #15 prio=5 os_prio=0 tid=0x0000000002eb3800 nid=0x2910 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Thread-1" #14 prio=5 os_prio=0 tid=0x00000000191bf800 nid=0x1d60 waiting for monitor entry [0x0000000019e6e000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at concurrent.DeadLockDemo$2.run(DeadLockDemo.java:44)
        - waiting to lock <0x00000000d60ad4b0> (a java.lang.String)
        - locked <0x00000000d60ad4e0> (a java.lang.String)
        at java.lang.Thread.run(Thread.java:748)

"Thread-0" #13 prio=5 os_prio=0 tid=0x00000000191df000 nid=0x28c4 waiting for monitor entry [0x0000000019d6f000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at concurrent.DeadLockDemo$1.run(DeadLockDemo.java:27)
        - waiting to lock <0x00000000d60ad4e0> (a java.lang.String)
        - locked <0x00000000d60ad4b0> (a java.lang.String)
        at java.lang.Thread.run(Thread.java:748)

"Service Thread" #12 daemon prio=9 os_prio=0 tid=0x0000000019085800 nid=0x1f14 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C1 CompilerThread2" #11 daemon prio=9 os_prio=2 tid=0x000000001900f000 nid=0x2764 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread1" #10 daemon prio=9 os_prio=2 tid=0x000000001900a000 nid=0x2b10 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread0" #9 daemon prio=9 os_prio=2 tid=0x0000000018fd4000 nid=0x15d0 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"JDWP Command Reader" #8 daemon prio=10 os_prio=0 tid=0x0000000018e8f000 nid=0x1478 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"JDWP Event Helper Thread" #7 daemon prio=10 os_prio=0 tid=0x0000000018e8b800 nid=0x1be0 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"JDWP Transport Listener: dt_socket" #6 daemon prio=10 os_prio=0 tid=0x0000000018e89800 nid=0xa64 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Attach Listener" #5 daemon prio=5 os_prio=2 tid=0x0000000017b54800 nid=0xedc waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Signal Dispatcher" #4 daemon prio=9 os_prio=2 tid=0x0000000017b11800 nid=0x2960 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Finalizer" #3 daemon prio=8 os_prio=1 tid=0x0000000017af0800 nid=0x2860 in Object.wait() [0x0000000018e6f000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(Native Method)
        - waiting on <0x00000000d5d88ec0> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:143)
        - locked <0x00000000d5d88ec0> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:164)
        at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:209)

"Reference Handler" #2 daemon prio=10 os_prio=2 tid=0x0000000002fa3000 nid=0x5c0 in Object.wait() [0x0000000018d6f000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(Native Method)
        - waiting on <0x00000000d5d86b68> (a java.lang.ref.Reference$Lock)
        at java.lang.Object.wait(Object.java:502)
        at java.lang.ref.Reference.tryHandlePending(Reference.java:191)
        - locked <0x00000000d5d86b68> (a java.lang.ref.Reference$Lock)
        at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:153)

"VM Thread" os_prio=2 tid=0x0000000017ac8000 nid=0x1460 runnable

"GC task thread#0 (ParallelGC)" os_prio=0 tid=0x0000000002ec9000 nid=0x6e4 runnable

"GC task thread#1 (ParallelGC)" os_prio=0 tid=0x0000000002eca800 nid=0x289c runnable

"GC task thread#2 (ParallelGC)" os_prio=0 tid=0x0000000002ecc000 nid=0xfc0 runnable

"GC task thread#3 (ParallelGC)" os_prio=0 tid=0x0000000002ece000 nid=0xc9c runnable

"VM Periodic Task Thread" os_prio=2 tid=0x00000000191bc000 nid=0x29b8 waiting on condition

JNI global references: 1679


Found one Java-level deadlock:
=============================
"Thread-1":
  waiting to lock monitor 0x0000000017acbfe8 (object 0x00000000d60ad4b0, a java.lang.String),
  which is held by "Thread-0"
"Thread-0":
  waiting to lock monitor 0x0000000017ace928 (object 0x00000000d60ad4e0, a java.lang.String),
  which is held by "Thread-1"

Java stack information for the threads listed above:
===================================================
"Thread-1":
        at concurrent.DeadLockDemo$2.run(DeadLockDemo.java:44)
        - waiting to lock <0x00000000d60ad4b0> (a java.lang.String)
        - locked <0x00000000d60ad4e0> (a java.lang.String)
        at java.lang.Thread.run(Thread.java:748)
"Thread-0":
        at concurrent.DeadLockDemo$1.run(DeadLockDemo.java:27)
        - waiting to lock <0x00000000d60ad4e0> (a java.lang.String)
        - locked <0x00000000d60ad4b0> (a java.lang.String)
        at java.lang.Thread.run(Thread.java:748)

Found 1 deadlock.

==================================================华丽的分割线==================================================================
在上面的打印中 jstack从jvm中帮我们快速定位死锁的原因 以及死锁个数.
     */

    public static void deadLock(){
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resource_a){
                    System.out.println("get resource_a");
                    try {
                        Thread.sleep(3000);
                        synchronized (resource_b){
                            System.out.println("get resource_b");
                        }
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resource_b){
                    System.out.println("get resource_b");
                    try {
                        Thread.sleep(3000);
                        synchronized (resource_a){
                            System.out.println("get resource_a");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        threadA.start();
        threadB.start();
    }
}
