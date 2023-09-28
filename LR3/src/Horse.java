class Horse extends Animal implements AnimalInfo, AnimalAction{
    private double weight;
    private double speed;

    public Horse(String name, String type, String sound, double weight, double speed) {
        super(name, type, sound);
        this.weight = weight;
        this.speed = speed;
    }

    @Override
    public void say() {
        System.out.println(getName() + " говорит: " + getSound());
    }

    @Override
    public void run() {
        System.out.println(getName() + " бежит со скоростью " + speed + " км/ч.");
    }

    public String toString() {
        return super.toString() + "\nВес: " + weight + " кг\nСкорость: " + speed + " км/ч";
    }
}
