package hello;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class test {

    private static  String[] roles = new String [2];

    public static void main(String[] args) {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println("ldt.getMonthValue()="+ldt.getMonthValue());
        System.out.println(DateTimeFormatter.ofPattern("MM-dd-yyyy").format(ldt));
        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").format(LocalDateTime.now()));
        System.out.println(ldt);

        roles[0]="ADMIN";
        roles[1]="USER";

        System.out.println("roles="+roles[0]+";"+roles[1]);

        for (int i=0;i<roles.length;i++)
            System.out.println("roles["+i+"]="+roles[i]);

    }
}
