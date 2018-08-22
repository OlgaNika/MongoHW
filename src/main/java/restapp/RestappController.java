package restapp;


import model.Expence;
import model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@PermitAll
public class RestappController {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ExpenceRepository expenceRepository;

// rooutes for Expences
    @CrossOrigin(origins = "*")
    @RequestMapping(method=RequestMethod.POST,value="/expence")
    public Expence postexpence(@RequestBody Expence expence) {
        System.out.println("postExpence="+expence);
        if(expence.getDate()==null) {
            expence.setDate(LocalDateTime.now());
        }
        expence.setCreated(LocalDateTime.now());
        expenceRepository.save(expence);
        return expence;
    }

    //get all expenceies

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET, value = "/expence")
    public List<Expence> listAllExpence() {
        System.out.println("listAllExpence");
        return expenceRepository.findAll();
    }

    //TODO get all expenceies for partucular month
    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET, value = "/expenceForThisMonth")
    public List<Expence> expenceForThisMonth() {
        System.out.println("expenceForThisMonth");
        LocalDateTime date = LocalDateTime.parse("2018-08-01");
        return expenceRepository.findAll();
    }
    //TODO get all expenceies by Type for month

    // get all expenceies for partucular type
    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET, value = "/expenceByType/{type}")
    public List<Expence> expenceByType(@PathVariable String type) {
        System.out.println("expenceByType");
        return expenceRepository.findByType(type);
    }
    //TODO get total for mounth
    //TODO get total by Type for mounth

    //get all expenceies for the Report by ReportId
    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET, value = "/expence/byReportid/{id}")
    public List<Expence> listExpenceByReport(@PathVariable String id) {
        System.out.println("Reportid="+id);
        return expenceRepository.findByReportid(id);
    }

//modify expence's data
    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.PUT, value = "/expence/{id}")
    public String updateExpence(@PathVariable String id, @RequestBody Expence expenceFromClient) {
        System.out.println("Update expence/{id} expenceId="+id);
        Expence expenceFromDb = expenceRepository.findByid(id);
        if(expenceFromClient.getAmount()!=null) {
            expenceFromDb.setAmount(expenceFromClient.getAmount());
        }
        if(expenceFromClient.getType()!=null) {
            expenceFromDb.setType(expenceFromClient.getType());
        }
        if(expenceFromClient.getDate()!=null) {
            expenceFromDb.setDate(expenceFromClient.getDate());
        }
        if(expenceFromClient.getCreated()!=null) {
            expenceFromDb.setCreated(expenceFromClient.getCreated());
        }

        expenceFromDb.setModified(LocalDateTime.now());

        if(expenceFromClient.getReportid()!=null) {
            expenceFromDb.setReportid(expenceFromClient.getReportid());
        }
            expenceRepository.save(expenceFromDb);
            return expenceFromDb.toString();
    }


    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET, value = "/expence/{id}")
    public Expence expencebyId(@PathVariable String id) {
        System.out.println("expence/{id} ExpenceId="+id);
        return expenceRepository.findByid(id);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.DELETE, value = "/expence/{id}")
    public String deleteExpence(@PathVariable String id){
        Expence expence = expenceRepository.findByid(id);
        expenceRepository.delete(expence);
        System.out.println("Expence with id="+id+" deleted");
        return null;
    }

//Routes for reports
    @CrossOrigin(origins = "*")
    @RequestMapping(method=RequestMethod.POST,value="/report")
    public Report postReport(@RequestBody Report report) {
        System.out.println("postReport="+report);
        report.setCreated(LocalDateTime.now());
        reportRepository.save(report);
        return report;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.PUT, value = "/report/{id}")
    public String updateReport(@PathVariable String id, @RequestBody Report reportFromClient) {
        System.out.println("Update report/{id} reportId="+id);
        Report reportFromDb = reportRepository.findByid(id);
        if(reportFromClient.getName()==null) {
            System.out.println("Report name is empty");
            return "Nothing to Update";
        } else {
            reportFromDb.setName(reportFromClient.getName());
            reportRepository.save(reportFromDb);
            System.out.println("report/{id} reportId="+id+" is updated with name "+reportFromClient.getName());
            return reportFromDb.toString();
        }

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method=RequestMethod.GET,value="/report")
    public List<Report> getAllReports() {
        System.out.println("getAllReports");
        return reportRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET, value = "/report/{id}")
    public Report reportbyId(@PathVariable String id) {
        System.out.println("report/{id} reportId="+id);
        return reportRepository.findByid(id);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.DELETE, value = "/report/{id}")
    public String deleteReport(@PathVariable String id){
        Report report = reportRepository.findByid(id);
        reportRepository.delete(report);
        System.out.println("Report "+id+" is deleted");
        return null;
    }

}
