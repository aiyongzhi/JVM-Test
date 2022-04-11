public class Text {
    public static class Animal {
        public void play() {

        }
    }

    public static class cat extends Animal {
        public void play() {
            System.out.println("我是猫，我会喵喵叫！");
        }
    }

    public static class dog extends Animal {
        public void play() {
            System.out.println("我是狗，我会汪汪叫！");
        }
    }

    public static class girl {
        public void play(Animal animal) {
            animal.play();
        }
    }

    public static void main(String[] args) {
        girl girl = new girl();
        girl.play(new cat());
        girl.play(new dog());
    }
}
