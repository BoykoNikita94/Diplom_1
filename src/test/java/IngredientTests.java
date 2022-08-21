import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

@RunWith(Parameterized.class)
public class IngredientTests {

    private final IngredientType type;
    private final String name;
    private final float price;

    public IngredientTests(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[] data() {
        return new Object[][]{
                {IngredientType.FILLING, "meat", Float.MAX_VALUE},
                {IngredientType.SAUCE, "брусн@#!%ичный", Float.MIN_VALUE},
                {IngredientType.FILLING, "kotле та", 200.4f},
                {IngredientType.SAUCE, "1527", 0f},
                {IngredientType.FILLING, "fresh and flesh", 500f},
        };
    }

    @Test
    public void checkGetType() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals(type, ingredient.getType());
    }

    @Test
    public void checkGetName() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals(name, ingredient.getName());
    }

    @Test
    public void checkGetPrice() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals(price, ingredient.getPrice(), 0);
    }
}
