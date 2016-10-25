abstract class AbstractFruit {

    private float weight;

    AbstractFruit(float weight) {
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " - weight = " + getWeight();
    }
}
