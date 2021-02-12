import java.util.List;

public class ConcreteStrategyTime implements Strategy {
    public void addTask(List<Server> servers, Task t) {
            int aux =999999, poz = 0;
            for (int i = 0; i < servers.size(); i++) {
                if (servers.get(i).getWaitingPeriod().intValue() < aux) {
                    aux = servers.get(i).getWaitingPeriod().intValue();
                    poz = i;
                }
            }
            servers.get(poz).addTask(t);

    }
}
