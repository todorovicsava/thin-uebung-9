import java.util.Arrays;
import java.util.List;

public class UniversalTM {

    private final String TM_DELIMITER = "111";

    public UniversalTM() { }

    public void run(String input, Mode mode) {
        int     currentState = 0;
        boolean eof          = false;
        boolean stuck        = false;
        int     currentPos   = 0;

        //read configuration first
        String        configValue = Arrays.asList(input.split(TM_DELIMITER, 2)).get(0);
        Configuration config      = new Configuration(configValue);
        currentPos = configValue.length() + TM_DELIMITER.length();

        StringBuilder band    = new StringBuilder(input);
        int           counter = 0;

        //System.out.println("Band " + band);

        if (mode == Mode.Step) {
            var p1 = "State:" + currentState + "; ";
            var p2 = "Band: " + band.substring(currentPos > 15 ? currentPos - 15 : 0, currentPos);
            var p3 = "|";
            var p4 = band.substring(currentPos, currentPos + 15 < band.length() ? currentPos + 15 : band.length()) + "; ";
            var p5 = "Counter: " + counter + ";";
            System.out.println(p1 + p2 + p3 + p4 + p5);
        }

        //process input
        while (!eof && !stuck) {
            char            c           = band.charAt(currentPos);
            List<Procedure> procs       = config.get(currentState);
            boolean         noProcValid = true;
            if (procs != null) {
                for (var proc : procs) {
                    if (proc.acceptedInput.equals(c)) {
                        band.replace(currentPos, currentPos, proc.output.toString());
                        currentState = proc.newState;
                        counter++;
                        noProcValid = false;
                        if (proc.direction == Direction.Right) {
                            currentPos++;
                        }
                        else {
                            currentPos--;
                        }

                        if (mode == Mode.Step) {
                            var p1 = "State:" + currentState + "; ";
                            var p2 = "Band: " + band.substring(currentPos > 15 ? currentPos - 15 : 0, currentPos);
                            var p3 = "|";
                            var p4 = band.substring(currentPos, currentPos + 15 < band.length() ? currentPos + 15 : band.length() - 1) + "; ";
                            var p5 = "Counter: " + counter + ";";
                            System.out.println(p1 + p2 + p3 + p4 + p5);
                        }

                        break;
                    }
                }
            }
            if (noProcValid) {
                stuck = true;
            }
            else if (currentPos >= band.length() - 1) {
                eof = true;
            }

            if (eof || stuck && mode == Mode.Run) {
                var p1 = "State:" + currentState + "; ";
                var p2 = "Band: " + band.substring(currentPos > 15 ? currentPos - 15 : 0, currentPos);
                var p3 = "|";
                var p4 = band.substring(currentPos, currentPos + 15 < band.length() ? currentPos + 15 : band.length() - 1) + "; ";
                var p5 = "Counter: " + counter + ";";
                System.out.println(p1 + p2 + p3 + p4 + p5);
            }
        }
    }

    public enum Mode {Run, Step}

}
