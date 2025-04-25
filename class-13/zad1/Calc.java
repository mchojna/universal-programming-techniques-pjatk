/**
 *
 *  @author Chojna Michał S29758
 *
 */

package zad1;


import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;

public class Calc {
    private HashMap<String, Method> map = new HashMap<>();

    public String doCalc(String arg) {
        String result = "";

        try {
            arg = arg.replaceAll("(?![+*/-])\\s+", "");
            String[] args = arg.split("[+\\-*/]");

//            System.out.println(arg);

            String l1 = args[0].trim();
            String l2 = args[1].trim();
            String op = String.valueOf(arg.charAt(l1.length())).trim();

//            System.out.println(l1);
//            System.out.println(l2);
//            System.out.println(op);

            int precision = (l1.length() + l2.length()) * 100;

            map.putIfAbsent("+", BigDecimal.class.getMethod("add", BigDecimal.class, MathContext.class));
            map.putIfAbsent("-", BigDecimal.class.getMethod("subtract", BigDecimal.class, MathContext.class));
            map.putIfAbsent("*", BigDecimal.class.getMethod("multiply", BigDecimal.class, MathContext.class));
            map.putIfAbsent("/", BigDecimal.class.getMethod("divide", BigDecimal.class, MathContext.class));

            result = ((BigDecimal) (map.get(op).invoke(
                    new BigDecimal(l1, new MathContext(precision, RoundingMode.HALF_UP)),
                    new BigDecimal(l2, new MathContext(precision, RoundingMode.HALF_UP)),
                    new MathContext(precision, RoundingMode.HALF_UP)
            ))).toPlainString();

        } catch (Exception e) {
            result = "Invalid command to calc";
        }

        return result;
    }
}
