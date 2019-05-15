package wheeloffortune;

import java.util.Random;

public class Wheel {

    private Random _random;

    // wedges that will be selected randomly
    private final String _wedges[] =
            {
                    "5000", "600", "500", "300",
                    "500", "800", "550", "400", "300",
                    "900", "500", "300", "900", "BANKRUPT",
                    "600", "400", "300", "LOSE A TURN", "800",
                    "350", "450", "700", "300", "600"
            };

    public Wheel() {
        _random = new Random();
    }

    public static void main(String[] args) {
        Wheel wheel = new Wheel();
        System.out.println(wheel.spin().getTitle());
        System.out.println(wheel.spin().getTitle());
    }

    /**
     * it returns random instance of a wedge simulate a wheel spin
     */
    public Wedge spin() {
        int number = _random.nextInt(24);
        return new Wedge(_wedges[number]);
    }

}
