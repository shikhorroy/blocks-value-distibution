import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Main {
    /**
     * Return i'th block value after evenly distribute 'total' value into 'blocksCount' blocks.
     *
     * @param total       is the total amount needs to be distributed and is a positive integer
     * @param blocksCount is total number of blocks for distribution
     * @param ithBlock    is the the block number for which value is to be calculated
     */
    static BigDecimal getDistributedValueForIthBlock(BigDecimal total, int blocksCount, int ithBlock) {
        // if we need to distribute 'total' values among 'blocksCount' blocks then the value for each block
        // will be, blockValue = ceil(total / blocksCount) = v .......................... (i)
        BigDecimal blockValue = total.divide(BigDecimal.valueOf(blocksCount), 0, BigDecimal.ROUND_CEILING); //-> v

        // if value for each block is zero then distribution is not required:
        if (blockValue.compareTo(BigDecimal.ZERO) == 0) return blockValue;

        // as we have modified ceiling value 'blockValue' for each block, we need 'requiredBlocks'
        // for 'total' value. From (i) we can get,
        //          blocksCount = floor(total / blockValue) => requiredBlocks = b ........ (ii)
        BigDecimal requiredBlocks = total.divide(blockValue, 0, RoundingMode.FLOOR); //-> b

        // Now, From (ii) we can get,
        //          total - (blockValue * requiredBlocks) => fractionValue = r ............(iii)
        BigDecimal fractionValue = total.subtract(blockValue.multiply(requiredBlocks)); //-> r
        boolean isFractional = false;
        if (fractionValue.compareTo(BigDecimal.ZERO) > 0) isFractional = true;

        // based on calculated value we can distribute total value into n blocks as followed:
    /*--------------------------------------------------------------
     | 1 | 2 | 3 | . . . . . . . | b | b + 1 | . . . . | n - 1 | n | <-- blocks
     |--------------------------------------------------------------
     | v | v | v | . . . . . . . | v |   r   | . . . . |   0   | 0 | <-- distributed value
     |--------------------------------------------------------------*/
        if (BigDecimal.valueOf(ithBlock).compareTo(requiredBlocks) < 0) return blockValue;
        else if (isFractional && BigDecimal.valueOf(ithBlock).compareTo(requiredBlocks) == 0) return fractionValue;
        return BigDecimal.ZERO;
    }

    public static void main(String[] args) {

    }

    public static List<BigDecimal> call(BigDecimal total, int n) {
        List<BigDecimal> list = new ArrayList<>();
        for (int i = 0; i < n; i++)
            list.add(getDistributedValueForIthBlock(total, n, i));

        return list;
    }
}
