class Horse extends Animal {
    private double weight;
    private double speed;

    public Horse(String name, String type, String sound, double weight, double speed) {
        super(name, type, sound);
        this.weight = weight;
        this.speed = speed;
    }

    @Override
    void say() {
        System.out.println(getName() + " говорит: " + getSound());
    }

    @Override
    void run() {
        System.out.println(getName() + " бежит со скоростью " + speed + " км/ч.");
    }

    public String toString() {
        return super.toString() + "\nВес: " + weight + " кг\nСкорость: " + speed + " км/ч";
    }
}
