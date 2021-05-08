import java.util.Arrays;
import java.util.List;

public class UniversalTM {

    private final String TM_DELIMITER = "111";

    public UniversalTM() { }

    public void run(String args, Mode mode) {
        int     currentState = 1;
        boolean eof          = false;
        boolean stuck        = false;
        int     index        = 0;
        int     counter      = 0;

        List<String>  splitConfigAndInput = Arrays.asList(args.split(TM_DELIMITER, 2));
        Configuration config              = new Configuration(splitConfigAndInput.get(0));
        StringBuilder band                = new StringBuilder(splitConfigAndInput.get(1));

        if (mode == Mode.Step) {
            print(currentState, band, index, counter);
        }

        //process input
        while (!eof && !stuck) {
            char c = ' ';
            try {
                c = band.charAt(index);
            }
            catch (IndexOutOfBoundsException e) {
                //ignore
            }
            List<Procedure> procs       = config.get(currentState);
            boolean         noProcValid = true;
            if (procs != null) {
                for (var proc : procs) {
                    if (proc.acceptedInput.equals(c)) {

                        if (proc.output.equals(' ') && !(index < 0) && !band.toString().isEmpty()) {
                            band.deleteCharAt(index);
                        }
                        else if (proc.output.equals(' ')) {
                            band = new StringBuilder(' ' + band.toString());
                            if (proc.direction == Direction.Right) {
                                index += 2;
                            }
                            else {
                                index -= 2;
                            }
                        }
                        else {
                            if (index == band.length()) {
                                band.append(proc.output);
                            }
                            else {
                                band.setCharAt(index, proc.output);
                            }
                            if (proc.direction == Direction.Right) {
                                index++;
                            }
                            else {
                                index--;
                            }
                        }

                        currentState = proc.newState;
                        counter++;
                        noProcValid = false;

                        if (mode == Mode.Step) {
                            print(currentState, band, index, counter);
                        }

                        break;
                    }
                }
            }
            if (noProcValid) {
                stuck = true;
            }

            if (stuck && mode == Mode.Run) {
                print(currentState, band, index, counter);
            }
        }
    }

    private void print(int state, StringBuilder band, int index, int counter) {
        var p1 = "State: " + (state < 10 ? (" " + state) : state) + "; ";
        var p2 = "Band: ";
        Integer charsBeforeHeader = index - 15 > 0 ? index - 15 : index < 0 ? 0 : index;
        for(int i=0; i < 15 - charsBeforeHeader; i++) {
            p2 += "_";
        }
        p2 += band.substring(Math.max(index, 0)- charsBeforeHeader, index < 0 ? 0: index);
        p2 += "|";
        Integer charsAfterHeader = band.length() - index > 15 ? 15 : band.length() - index;
        p2 += band.substring(Math.min(index, 0), index + charsAfterHeader);
        for(int i=0; i < 15 - charsAfterHeader; i++) {
            p2 += "_";
        }

        var p3 = " Counter: " + counter + ";";
        System.out.println(p1 + p2 + p3);
    }

    public enum Mode {Run, Step}

}
