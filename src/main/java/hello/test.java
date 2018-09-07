package hello;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class test {
    public static void main(String[] args) {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println("ldt.getMonthValue()="+ldt.getMonthValue());
        System.out.println(DateTimeFormatter.ofPattern("MM-dd-yyyy").format(ldt));
        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").format(LocalDateTime.now()));
        System.out.println(ldt);



    }
}
