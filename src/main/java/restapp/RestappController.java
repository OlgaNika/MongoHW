package restapp;


import model.Expence;
import model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class RestappController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    @Autowired
    private ReportRepository reportRepository;


    @Autowired
    private ExpenceRepository expenceRepository;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name
    ) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping("/expence")
    public Expence expence(@RequestParam(value="amount", defaultValue="0") String amount) {
        Expence expence = new Expence(amount);
        expenceRepository.insert(expence);
        return expence;
    }

    @RequestMapping("/report")
    public Report report(@RequestParam(value="name", defaultValue = "noname") String name){
        Report report = new Report(name);
        reportRepository.insert(report);
        return report;

    }
}
