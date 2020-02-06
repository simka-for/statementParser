public class Movement {

    private String operation;
    private double coming;
    private double consumption;

    public Movement(String operation, double coming, double consumption){
        this.operation = operation;
        this.coming = coming;
        this.consumption = consumption;
    }

    public String getOperation() {
        return operation;
    }

    public double getComing() {
        return coming;
    }

    public double getConsumption() {
        return consumption;
    }


    @Override
    public String toString() {
        return  operation + "\t" + coming + "\t" + consumption;
    }
}
