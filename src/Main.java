public class Main {

    public static void main(String[] args) {
        args = new String[1];
        args[0] = "01001000101001101001000101001110";
        UniversalTM tm = new UniversalTM(args);
        tm.parse(UniversalTM.Mode.Run);
        System.out.println(tm.getConfig().list);
    }

}
