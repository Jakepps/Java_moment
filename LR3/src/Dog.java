class Dog extends Animal implements AnimalInfo, AnimalAction {
    private double weight;

    public Dog(String name, String type, String sound, double weight) {
        super(name, type, sound);
        this.weight = weight;
    }

    @Override
    public void say() {
        System.out.println(getName() + " говорит " + getSound());
    }

    public String toString() {
        return super.toString() + "\nВес: " + weight + " кг";
    }
}
