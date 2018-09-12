package restapp;

import model.Expence;
import model.Report;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class RestappController {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ExpenceRepository expenceRepository;

    @Autowired
    private UserRepository userRepository;


   @RequestMapping(method=RequestMethod.POST,value="/user")
   public User postUser(@RequestBody User user) {
       User userFromDb=userRepository.findByUsername(user.getUsername());
       String[] roles=user.getRoles();
       if ((Arrays.asList(roles).contains("ADMIN"))&&(!user.getUsername().equals("admin"))){
           System.out.println("ADMIN role can't be assigned to your user..");
           return null;
       }
       if (userFromDb==null) {
           System.out.println("ADD user=" + user);
           user.setCreated(LocalDateTime.now());
           userRepository.save(user);
           return user;
       }
       System.out.println("User "+user.getUsername()+" is already created..");
       return null;
    }
///for migration from v1 to v1.2 only - to be removed later
    @RequestMapping(method = RequestMethod.GET, value = "/upgrade/setOwner")
    public List<Expence> setOwner(){
        List<Expence> expencesSetOwner =  expenceRepository.findByNullableOwner();
        for (Expence expence:expencesSetOwner){
            expence.setOwner("admin");
            expenceRepository.save(expence);
            System.out.println("[Updated]expence="+expence);
        }
        return expencesSetOwner;
    }

    @RequestMapping(method=RequestMethod.POST,value="/expence")
    public Expence postexpence(@RequestBody Expence expence) {
        System.out.println("postExpence="+expence);
        if(expence.getDate()==null) {
            expence.setDate(LocalDateTime.now());
        } else {
            expence.setDate(expence.getDate().plusHours(4));
        }
        expence.setCreated(LocalDateTime.now());
        UserDetails user =
                (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        expence.setOwner(user.getUsername());
        expenceRepository.save(expence);
        return expence;
    }

    //get all expenses for ADMIN only
    @RequestMapping(method = RequestMethod.GET, value = "/expence")
    public List<Expence> listAllExpence() {
        System.out.println("listAllExpence");
        UserDetails user =
                (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Your user's roles=" +user.getAuthorities().toString());
        //TODO fix it to work for multiple roles
        if (user.getAuthorities().toString().equals("[ROLE_ADMIN]"))
            return expenceRepository.findAll();
        System.out.println("You user is not authorised for this call");
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/expenceForThisMonth/{month}")
    public List<Expence> expenceForParticularMonth(@PathVariable int month) {
        if (month<1 || month>12) month = LocalDateTime.now().getMonthValue();
        System.out.println("expenceForThisMonth for month="+month);
        UserDetails user =
                (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("UserDetails getUsername="+user.getUsername()+";Roles="+user.getAuthorities());

        return expenceRepository.findforMonth(user.getUsername(),month);
    }


    @RequestMapping(method = RequestMethod.GET, value="/logoutPage")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("logout procedure started");
        if (auth != null){
            System.out.println("logout procedure");
            new SecurityContextLogoutHandler().logout(request, response, auth);
        } else {
            System.out.println("Authentication is null");

        }
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/userDetails")
    public UserDetails getUserDeatils(){
        UserDetails userDetails =
                (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("userDetails getUsername="+userDetails.getUsername());
        System.out.println("userDetails getAuthorities="+userDetails.getAuthorities());
        return userDetails;
    }



    // get all expenceies for partucular type for this month
    @RequestMapping(method = RequestMethod.GET, value = "/expenceByType/{type}/{month}")
    public List<Expence> expenceByTypeForMonth(@PathVariable String type,@PathVariable int month) {
        if (month<1 || month>12) month = LocalDateTime.now().getMonthValue();
        System.out.println("expenceByTypeForMonth for month="+month);
        UserDetails user =
                (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return expenceRepository.findByTypeForMonth(user.getUsername(),type,month);
    }

    //get all expenceies for the Report by ReportId
    @RequestMapping(method = RequestMethod.GET, value = "/expence/byReportid/{id}")
    public List<Expence> listExpenceByReport(@PathVariable String id) {
        System.out.println("Reportid="+id);
        return expenceRepository.findByReportid(id);
    }

//modify expence's data
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


    @RequestMapping(method = RequestMethod.GET, value = "/expence/{id}")
    public Expence expencebyId(@PathVariable String id) {
        System.out.println("expence/{id} ExpenceId="+id);
        UserDetails user =
                (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return expenceRepository.findByidForUser(user.getUsername(),id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/expence/{id}")
    public String deleteExpence(@PathVariable String id){
        UserDetails user =
                (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Expence expence = expenceRepository.findByidForUser(user.getUsername(),id);
        expenceRepository.delete(expence);
        System.out.println("Expence with id="+id+" deleted");
        return null;
    }

//Routes for reports
    @RequestMapping(method=RequestMethod.POST,value="/report")
    public Report postReport(@RequestBody Report report) {
        System.out.println("postReport="+report);
        report.setCreated(LocalDateTime.now());
        reportRepository.save(report);
        return report;
    }

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
        System.out.println("Report "+id+" is deleted");
        return null;
    }

}
