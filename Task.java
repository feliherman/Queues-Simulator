public class Task {
    private int arrivalTime;
    private int processingTime;
    private int finishTime;
    private int id;

    public Task() {
        this.arrivalTime = 0;
        this.processingTime = 0;
        this.finishTime = 0;
        this.id = 0;
    }

    public Task(int id,int aTime, int pTime) {
        this.arrivalTime = aTime;
        this.processingTime = pTime;
        this.id = id;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }
    public int getArrivalTime() {
        return arrivalTime;
    }
    public int getId() {
        return id;
    }
    public int getProcessingTime() {
        return processingTime;
    }
    public String toString(){
        String s="";
        s="("+getId()+","+getArrivalTime()+","+getProcessingTime()+")";
        return s;
    }
}
