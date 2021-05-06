public class Main {

    public static void main(String[] args) {
        args    = new String[1];
        args[0] = "010010001010011010010001010011100";
        new UniversalTM().run(args[0], UniversalTM.Mode.Step);
    }

}
