import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class EvenlyDistributeValueTest {

    final double epsilon = 1E-10;

    @Test
    public void callTest() {
        final int blocksCount = 6;
        final int maxn = (int)1E7;

        for (int i = 0; i <= maxn; i++) {
            BigDecimal total = new BigDecimal(String.valueOf(i));
            BigDecimal sum = BigDecimal.ZERO;
            List<BigDecimal> decimal = Main.call(total, blocksCount);
//            System.out.println(decimal);

            for (BigDecimal b : decimal) {
                sum = sum.add(b);
            }

            Assert.assertEquals(sum.doubleValue(), total.doubleValue(), epsilon);
        }

//        double[] ans = list.stream().mapToDouble(value -> value.doubleValue()).toArray();
//        double[] result = Main.call(total, blocksCount).stream().mapToDouble(value -> value.doubleValue()).toArray();
//
//        Assert.assertArrayEquals(ans, result, epsilon);
    }
}
