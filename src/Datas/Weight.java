/**
 * class Weight
 * @package Datas
 * @author Andrei Akhramchuk
 * @version 1.0;
 */
package Datas;

public class Weight {

    /**
     * space object's weight
     */
    private double weight;

    /**
     * contsructor without parameters for weight, weight set on 0
     * ( default constructor )
     */
    public Weight()
    {
        this.weight = 0;
    }

    public Weight(double v) {
        weight = v;
    }

    /**
     * getr fo Weight
     * @return weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * setr for Weight
     * @param weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

}
