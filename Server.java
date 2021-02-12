import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private int id;
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    boolean empty = false;
    private volatile boolean flag = false;

    public Server(){
        this.tasks=new ArrayBlockingQueue<Task>(100);
        this.waitingPeriod= new AtomicInteger(0);
        this.id=0;
    }

    public void addTask(Task newTask){
        getTasks().add(newTask);
        getWaitingPeriod().addAndGet(newTask.getProcessingTime());
        //start
        Thread t = new Thread(this);
        t.start();
        if(flag == false)
            flag = true;
    }


    public void run() {
        while(flag){
            try{
                Thread.sleep(1000);
            }
            catch(Exception e){
                e.getMessage();
            }
            if(tasks.isEmpty()){
                flag = false;
            }
            else{

                waitingPeriod.getAndDecrement();
                Task t = getTasks().peek();
                t.setProcessingTime(t.getProcessingTime()-1);

                if(t.getProcessingTime() == 0)
                    getTasks().remove(t);
                else {//System.out.println(t.toString());
                }
            }
        }
    }

    public BlockingQueue<Task> getTasks(){
        return this.tasks;
    }
    public AtomicInteger getWaitingPeriod(){
        return this.waitingPeriod;
    }

    public int getId() {
        return id;
    }
    public String toString()
    {
        String s = "";
        for(Task i : getTasks())
            s+=i.toString();

        return s;
    }
}
