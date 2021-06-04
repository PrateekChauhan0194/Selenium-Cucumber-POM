public abstract class Test {
    public void testMethod1() {
        System.out.println("testMethod1");
    }
}

class Test1 extends Test {
    public static void main(String[] args) {
        Test test = new Test1();
        test.testMethod1();
    }
}
