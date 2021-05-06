import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Procedure {

    private final String    SYMBOL_DELIMITER = "1";
    public        int       oldState;
    public        int       acceptedInput;
    public        int       newState;
    public        int       output;
    public        Direction direction;

    public Procedure(String args) {
        if (args == null) {
            throw new IllegalArgumentException();
        }

        List<Integer> nums    = new ArrayList<>();
        List<String> items = Arrays.asList(args.split(SYMBOL_DELIMITER));
        for(var item :items) {
            nums.add(item.length());
        }
        this.oldState      = nums.get(0);
        this.acceptedInput = nums.get(1);
        this.newState      = nums.get(2);
        this.output        = nums.get(3);
        this.direction     = nums.get(4) == 1 ? Direction.Left : Direction.Right;
    }

    @Override
    public String toString() {
        return "Procedure{" + "oldState=" + oldState + ", acceptedInput=" + acceptedInput + ", newState=" + newState + ", output=" + output + ", direction=" + direction + '}';
    }

}
