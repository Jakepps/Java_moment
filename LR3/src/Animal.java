abstract class Animal implements AnimalInfo {
    private String name;
    private String type;
    private String sound;

    public Animal(String name, String type, String sound) {
        this.name = name;
        this.type = type;
        this.sound = sound;
    }

    abstract void say();

    public void run() {
        System.out.println(name + " бежит.");
    }

    public void doCommand() {
        System.out.println(name + " выполняет команду.");
    }

    public void eat() {
        System.out.println(name + " ест.");
    }

    public void sleep() {
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

    @Override
    public boolean equals(Object o)
    {
        boolean result = false;
        if(o != null && o instanceof Animal)
        {
            Animal a = (Animal)o;
            if ((name.equals(a.name)) && (type.equals(a.type)) && (sound.equals(a.sound)))
                result=true;
        }
        return result;
    }

    @Override
    public String toString() {
        return "Имя: " + name + "\nТип: " + type + "\nЗвук: " + sound;
    }
}
