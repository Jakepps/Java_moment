class Dog extends Animal {
    private double weight;

    public Dog(String name, String type, String sound, double weight) {
        super(name, type, sound);
        this.weight = weight;
    }

    @Override
    void say() {
        System.out.println(getName() + " говорит " + getSound());
    }

    public String toString() {
        return super.toString() + "\nВес: " + weight + " кг";
    }
}
