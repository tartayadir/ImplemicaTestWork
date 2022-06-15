package Task2;

public class Road {

    /*
     * this class is an edge of a weighted graph
     */

    private final City target;
    private final int weight;

    public Road(City target, int weight){
        this.target = target;
        this.weight = weight;
    }

    public City getTarget() {return target;}
    public int getWeight() {return weight;}
}
