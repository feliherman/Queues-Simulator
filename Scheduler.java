

import java.util.ArrayList;
import java.util.List;

public class Scheduler {

    public Scheduler(int m) {
        this.maxNoServers = m;
        this.strategy = new ConcreteStrategyTime();
        this.servers=new ArrayList<>();
        for(int i =0 ; i < maxNoServers; i++){
            this.servers.add(new Server());
        }


    }

    public enum SelectionPolicy{
        SHORTEST_QUEUE,SHORTEST_TIME
    }

    private int maxNoServers;
    private int maxTasksPerServer;
    private List<Server> servers = new ArrayList<>(maxNoServers);
    private Strategy strategy = new Strategy() {
        @Override
        public void addTask(List<Server> servers, Task t) {

        }
    };



    public void changeStrategy(SelectionPolicy policy){

        if(policy == SelectionPolicy.SHORTEST_QUEUE)
            strategy=new ConcreteStrategyQueue();
        if(policy == SelectionPolicy.SHORTEST_TIME)
            strategy = new ConcreteStrategyTime();

    }

    public void dispatchTask(Task t){
        strategy.addTask(servers,t);

    }
    public List<Server> getServers(){
        return servers;
    }


}
