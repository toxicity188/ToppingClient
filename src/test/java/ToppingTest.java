import kor.toxicity.topping.ToppingClient;
import org.junit.jupiter.api.Test;

public class ToppingTest {
    @Test
    public void testTopping() {
        System.out.println(ToppingClient.getProductInfo(72,42));
    }
}
