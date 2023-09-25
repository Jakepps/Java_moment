public class Zoo {

    public static void main(String[] args) {
        Animal[] animals = new Animal[9];
        animals[0] = new Dog("Бублик", "Собака", "Гав", 22.0);
        animals[1] = new Cat("Вася", "Кошка", "Мяу", "Зеленый");
        animals[2] = new Horse("Билибердыч", "Лошадь", "Иго-го", 500, 60);
        animals[3] = new Dog("Рекс", "Собака", "Вуф", 20.0);
        animals[4] = new Cat("Мурка", "Кошка", "Мур", "Зеленый");
        animals[5] = new Horse("Плотва", "Лошадь", "Фу-фу", 600, 50);
        animals[6] = new Dog("Рекс", "Собака", "Ррр", 15.5);
        animals[7] = new Cat("Мурка", "Кошка", "Мур", "Зеленый");
        animals[8] = new Horse("Спринтер", "Лошадь", "Фуууу", 500, 70);

//        for (Animal animal : animals) {
//            System.out.println(animal.getType() + " по имени " + animal.getName());
//            animal.say();
//            animal.run();
//            animal.doCommand();
//            animal.eat();
//            animal.sleep();
//            System.out.println();
//        }

        System.out.println(animals[0].toString() + "\n");
        System.out.println(animals[1].toString()+ "\n");
        System.out.println(animals[2].toString()+ "\n");

        if(animals[4].equals(animals[7])){
            System.out.println("animal4 equal animal7");
        }
        else System.out.println("animal4 no equal animal7");

        if (animals[0].equals(animals[6])){
            System.out.println("animal0 equal animal6");
        }
        else System.out.println("animal0 no equal animal6");
    }
}
