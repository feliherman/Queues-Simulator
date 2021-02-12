import java.util.*;
import java.lang.*;
import java.io.*;

import static java.lang.System.out;
public class SimulationManager<isAlive> implements Runnable {

    public int timeLimit = 20;
    public int maxProcessingTime = 10;
    public int minProcessingTime = 2;
    public int numberOfServers = 3;
    public int numberOfClients = 100;
    public int minArriveTime=2;
    public int maxArriveTime=30;
    public  volatile boolean isAlive = true;
    public Scheduler.SelectionPolicy selectionPolicy = Scheduler.SelectionPolicy.SHORTEST_TIME;
    private Scheduler scheduler;
    private List<Task> generatedTasks = new ArrayList<>(getNumberOfClients());
    private  FileWriter myWriter ;
    public SimulationManager(int time, int maxT, int minT, int nrServ, int nrClients,File file,String outfile) throws IOException {

        Scanner sc = new Scanner(file);
        this.numberOfClients=sc.nextInt();
        this.numberOfServers=sc.nextInt();
        this.timeLimit=sc.nextInt();
        this.minArriveTime=sc.nextInt();
        this.maxArriveTime=sc.nextInt();
        this.minProcessingTime=sc.nextInt();
        this.maxProcessingTime=sc.nextInt();
        this.myWriter = new FileWriter(outfile);

        this.scheduler = new Scheduler(getNumberOfServers());
        generateNRandomTasks();

    }
    private void generateNRandomTasks(){
        int id=1;
        Random r = new Random();
        Task[] t = new Task[numberOfClients];
        for(int i=0;i<this.numberOfClients;i++)
        {
            t[i] = new Task();
            t[i].setId(i+1);
            t[i].setArrivalTime(r.nextInt((getMaxArriveTime()-getMinArriveTime())) + getMinArriveTime());
            t[i].setProcessingTime(r.nextInt((getMaxProcessingTime()-getMinProcessingTime()))+getMinProcessingTime());
            generatedTasks.add(t[i]);
            System.out.println(t[i].toString());

        }
        /*Task t1 = new Task(1,2,2);
        Task t2 = new Task(2, 3, 3);
        Task t3 = new Task(3, 4, 3);
        Task t4 = new Task(4, 10, 2);
        generatedTasks.add(t1);
        generatedTasks.add(t2);
        generatedTasks.add(t3);
        generatedTasks.add(t4);*/
    }

    public FileWriter getMyWriter() {
        return myWriter;
    }

    public boolean serversEmpty(){
        for(int i=0;i<scheduler.getServers().size() ; i++)
            if(!scheduler.getServers().get(i).empty)
                return false;
        return true;
    }
    private void afisare() throws IOException {
        //System.out.println("SUNT SERVERE " + scheduler.getServers().size());

        getMyWriter().write("Waiting clients: ");
        System.out.println("Waiting clients: ");
        for(Task i : generatedTasks)
        {
            getMyWriter().write(i.toString());
            System.out.print(i.toString());
        }
        int nr = 1;
        for(int i = 0 ; i<scheduler.getServers().size() ;i++)
        {
            getMyWriter().write("\nQueue "+ nr + ": " +scheduler.getServers().get(i).toString()+";");
            System.out.println("\nQueue "+ nr + ": " +scheduler.getServers().get(i).toString()+";");
            nr++;
        }

    }

    public void run() {
        int currenntTime=0,nrElim=0,nrTasks=generatedTasks.size();
        while(isAlive && currenntTime < timeLimit) {
            try { myWriter.write("\nTime : " + currenntTime + "\n"); } catch (IOException e) { e.printStackTrace(); }
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace();
            }int i=0;
            while(!generatedTasks.isEmpty() && i < nrTasks)
            {
                if(generatedTasks.get(i).getArrivalTime() == currenntTime) {
                    scheduler.dispatchTask(generatedTasks.get(i));
                    generatedTasks.remove(generatedTasks.get(i));
                    nrTasks--;
                }else {i++;}
            }
            try { this.afisare(); } catch (IOException e) { e.printStackTrace();}
            for(Server j : scheduler.getServers())
                j.toString();
            nrTasks=generatedTasks.size();currenntTime++;nrElim=0;
            List<Server> aux = new ArrayList<Server>(scheduler.getServers());
            if(generatedTasks.isEmpty()) {
                   for(Server k: aux)
                     if (k.getTasks().isEmpty())
                       nrElim++;
                   if(nrElim==scheduler.getServers().size())
                   {
                       isAlive = false;
                       try { getMyWriter().close(); } catch (IOException e) { e.printStackTrace(); }
                   }
            }
        }
    }



    public void setGeneratedTasks(List<Task> generatedTasks) {
        this.generatedTasks = generatedTasks;
    }

    public void setMaxProcessingTime(int maxProcessingTime) {
        this.maxProcessingTime = maxProcessingTime;
    }

    public void setMinProcessingTime(int minProcessingTime) {
        this.minProcessingTime = minProcessingTime;
    }

    public void setNumberOfClients(int numberOfClients) {
        this.numberOfClients = numberOfClients;
    }

    public void setNumberOfServers(int numberOfServers) {
        this.numberOfServers = numberOfServers;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void setSelectionPolicy(Scheduler.SelectionPolicy selectionPolicy) {
        this.selectionPolicy = selectionPolicy;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getMaxProcessingTime() {
        return maxProcessingTime;
    }

    public int getMinProcessingTime() {
        return minProcessingTime;
    }

    public int getNumberOfClients() {
        return numberOfClients;
    }

    public int getNumberOfServers() {
        return numberOfServers;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public int getMaxArriveTime() {
        return maxArriveTime;
    }

    public int getMinArriveTime() {
        return minArriveTime;
    }
}
