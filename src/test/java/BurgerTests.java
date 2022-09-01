import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient1;

    @Mock
    Ingredient ingredient2;

    @Test
    public void SetBuns() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        Assert.assertEquals(bun, burger.bun);
    }

    @Test
    public void AddIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient1);
        Assert.assertEquals(ingredient1, burger.ingredients.get(0));
        Assert.assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void removeIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient1);
        burger.removeIngredient(0);
        Assert.assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void moveIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(1, 0);
        Assert.assertEquals(ingredient2, burger.ingredients.get(0));
        Assert.assertEquals(2, burger.ingredients.size());
    }

    @Test
    public void getPriceWithoutIngredients() {
        Mockito.when(bun.getPrice()).thenReturn(4.20F);
        Burger burger = new Burger();
        burger.setBuns(bun);
        Assert.assertEquals(8.40F, burger.getPrice(), 0);
    }

    @Test
    public void getPriceWithIngredients() {
        Mockito.when(bun.getPrice()).thenReturn(4.20F);
        Burger burger = new Burger();
        burger.setBuns(bun);
        Mockito.when(ingredient1.getPrice()).thenReturn(315F);
        Mockito.when(ingredient2.getPrice()).thenReturn(23F);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        Assert.assertEquals(346.40F, burger.getPrice(), 0);
    }

    @Test
    public void getReceipt() {
        Mockito.when(bun.getPrice()).thenReturn(4.20F);
        Mockito.when(bun.getName()).thenReturn("булка");
        Burger burger = new Burger();
        burger.setBuns(bun);
        Mockito.when(ingredient1.getPrice()).thenReturn(315F);
        Mockito.when(ingredient2.getPrice()).thenReturn(23F);
        Mockito.when(ingredient1.getName()).thenReturn("соус");
        Mockito.when(ingredient2.getName()).thenReturn("мясо");
        Mockito.when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient2.getType()).thenReturn(IngredientType.FILLING);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        String actual = burger.getReceipt();
        String expected =
                "(==== " + bun.getName() + " ====)\r\n" +
                        "= " + ingredient1.getType().toString().toLowerCase() + " " + ingredient1.getName() + " =\r\n" +
                        "= " + ingredient2.getType().toString().toLowerCase() + " " + ingredient2.getName() + " =\r\n" +
                        "(==== " + bun.getName() + " ====)\r\n" +
                        String.format("%nPrice: %f%n", burger.getPrice());
        assertEquals(expected, actual);
    }
}
