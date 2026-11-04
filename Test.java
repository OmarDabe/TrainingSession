public class Test implements Runnable{
    @Override
    public void run(){
        for(int i=0; i<10; i++){
            System.out.println("-------from run method-------");
        }
    } 



    public static void main(String []args){
            Runnable task = new Test();
            Thread worker = new Thread(task);
            worker.start();
            

            for(int i=0; i<10; i++){
            System.out.println("from main method");
        }
    }
}