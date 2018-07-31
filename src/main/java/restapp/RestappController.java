package restapp;


import model.Expence;
import model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class RestappController {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ExpenceRepository expenceRepository;

// rooutes for Expences
    @RequestMapping(method=RequestMethod.POST,value="/expence")
    public String postexpence(@RequestBody Expence expence) {
        System.out.println("postExpence="+expence);
        expenceRepository.save(expence);
        return expence.getId();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/expence")
    public List<Expence> listExpenceByReport(@RequestParam(value="reportId") String reportId) {
        System.out.println("ReportId="+reportId);
        return expenceRepository.findByReportId(reportId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/expence/{id}")
    public Expence expencebyId(@PathVariable String id) {
        System.out.println("expence/{id} ExpenceId="+id);
        return expenceRepository.findByid(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/expence/{id}")
    public String deleteExpence(@PathVariable String id){
        Expence expence = expenceRepository.findByid(id);
        expenceRepository.delete(expence);
        return "Expence with id="+id+" deleted";
    }

//Routes for reports

    @RequestMapping(method=RequestMethod.POST,value="/report")
    public String postReport(@RequestBody Report report) {
        System.out.println("postReport="+report);
        reportRepository.save(report);
        return report.getId();
    }

    @RequestMapping(method=RequestMethod.GET,value="/report")
    public List<Report> getAllReports() {
        System.out.println("getAllReports");
        return reportRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/report/{id}")
    public Report reportbyId(@PathVariable String id) {
        System.out.println("report/{id} reportId="+id);
        return reportRepository.findByid(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/report/{id}")
    public String deleteReport(@PathVariable String id){
        Report report = reportRepository.findByid(id);
        reportRepository.delete(report);
        return "Report with id="+id+" deleted";
    }

//TODO support update for expence and reports

}
