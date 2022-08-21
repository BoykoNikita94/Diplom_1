import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

@RunWith(Parameterized.class)
public class BunTests {

    private final String name;
    private final float price;

    public BunTests(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[] buns() {
        return new Object[][]{
                {"b", Float.MAX_VALUE},
                {"white bun", Float.MIN_VALUE},
                {"white bun bun", 200.4f},
                {"red*&bun", 0f},
                {"red123bun", 500f},
        };
    }

    @Test
    public void checkGetName() {
        Bun bun = new Bun(name, price);
        Assert.assertEquals(name, bun.getName());
    }

    @Test
    public void checkGetPrice() {
        Bun bun = new Bun(name, price);
        System.out.println(bun.getPrice());
        Assert.assertEquals(price, bun.getPrice(), 0);
    }
}