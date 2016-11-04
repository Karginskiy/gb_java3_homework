package lesson1;

abstract class AbstractFruit {

    private float weight;

    AbstractFruit(float weight) {
        this.weight = weight;
    }

    float getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " - weight = " + getWeight();
    }
}
