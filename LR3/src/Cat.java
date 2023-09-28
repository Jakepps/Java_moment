class Cat extends Animal implements AnimalAction, AnimalInfo{
    private String eyeColor;

    public Cat(String name, String type, String sound, String eyeColor) {
        super(name, type, sound);
        this.eyeColor = eyeColor;
    }

    @Override
    public void say() {
        System.out.println(getName() + " говорит " + getSound());
    }

    @Override
    public String toString() {
        return super.toString() + "\nЦвет глаз: " + eyeColor;
    }

}
