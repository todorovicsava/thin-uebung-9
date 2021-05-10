import java.util.Arrays;
import java.util.List;

public class Procedure {

    private final String    SYMBOL_DELIMITER = "1";
    public        int       oldState;
    public        Character acceptedInput;
    public        int       newState;
    public        Character output;
    public        Direction direction;

    public Procedure(String args) {
        if (args == null) {
            throw new IllegalArgumentException();
        }
        List<String> items = Arrays.asList(args.split(SYMBOL_DELIMITER));
        this.oldState = items.get(0).length();
        String bandInput = items.get(1);
        if (bandInput.equals("0")) {
            this.acceptedInput = '0';
        }
        else if (bandInput.equals("00")) {
            this.acceptedInput = '1';
        }
        else if (bandInput.equals("000")) {
            this.acceptedInput = ' ';
        }
        else if (bandInput.equals("0000")) {
            this.acceptedInput = '$';
        }
        else if (bandInput.equals("00000")) {
            this.acceptedInput = '#';
        }
        else {
            throw new IllegalArgumentException();
        }
        this.newState = items.get(2).length();
        String bandOutput = items.get(3);
        if (bandOutput.equals("0")) {
            this.output = '0';
        }
        else if (bandOutput.equals("00")) {
            this.output = '1';
        }
        else if (bandOutput.equals("000")) {
            this.output = ' ';
        }
        else if (bandOutput.equals("0000")) {
            this.output = '$';
        }
        else if (bandOutput.equals("00000")) {
            this.output = '#';
        }
        else {
            throw new IllegalArgumentException();
        }
        this.direction = items.get(4).equals("0") ? Direction.Left : Direction.Right; //TODO
    }

    @Override
    public String toString() {
        return "Procedure{" + "oldState=" + oldState + ", acceptedInput=" + acceptedInput + ", newState=" + newState + ", output=" + output + ", direction=" + direction + '}';
    }

}
