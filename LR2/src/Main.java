
abstract class Animal {
    private String name;
    private String type;
    private String sound;

    public Animal(String name, String type, String sound) {
        this.name = name;
        this.type = type;
        this.sound = sound;
    }

    abstract void say();

    void run() {
        System.out.println(name + " бежит.");
    }

    void doCommand() {
        System.out.println(name + " выполняет команду.");
    }

    void eat() {
        System.out.println(name + " ест.");
    }

    void sleep() {
        System.out.println(name + " спит.");
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getSound(){
        return sound;
    }
}

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
}

class Cat extends Animal {
    private String eyeColor;

    public Cat(String name, String type, String sound, String eyeColor) {
        super(name, type, sound);
        this.eyeColor = eyeColor;
    }

    @Override
    void say() {
        System.out.println(getName() + " говорит " + getSound());
    }

}

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

    void run() {
        System.out.println(getName() + " бежит со скоростью " + speed + " км/ч.");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal[] animals = new Animal[9];
        animals[0] = new Dog("Бублик", "Собака", "Гав", 22.0);
        animals[1] = new Cat("Вася", "Кошка", "Мяу", "Зеленый");
        animals[2] = new Horse("Билибердыч", "Лошадь", "Иго-го", 500, 60);
        animals[3] = new Dog("Рекс", "Собака", "Вуф", 20.0);
        animals[4] = new Cat("Мурка", "Кошка", "Мур", "Желтый");
        animals[5] = new Horse("Плотва", "Лошадь", "Фу-фу", 600, 50);
        animals[6] = new Dog("Барк", "Собака", "Ррр", 15.5);
        animals[7] = new Cat("Муся", "Кошка", "Мур-мяу", "Зеленый");
        animals[8] = new Horse("Спринтер", "Лошадь", "Фуууу", 500, 70);

        for (Animal animal : animals) {
            System.out.println(animal.getType() + " по имени " + animal.getName());
            animal.say();
            animal.run();
            animal.doCommand();
            animal.eat();
            animal.sleep();
            System.out.println();
        }
    }
}
