import java.util.concurrent.*;

    class A implements Runnable{
        @Override
        public void run(){
            System.out.println("hello from class A");
        }
    }
         class B implements Runnable{
        @Override
        public void run(){
            System.out.println("hello from class B");
        }
}

public class threads {
    public static void main(String[] args) {

     Runnable o1 = new A();
     Runnable o2 = new B();

     Thread t1 = new Thread(o1);
     Thread t2 = new Thread(o2);

     t1.start();
     t2.start();
    }
}
  

