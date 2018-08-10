package model;

import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;

public class Expence {

    @Id
    private String id;

    private Double amount;
    private String type;
    private String notes;
    private LocalDateTime date;
    private LocalDateTime created;
    private LocalDateTime modified;
    private String reportid;

    @Override
    public String toString() {
        return String.format(
                "Expence[id=%s, amount='%s', category='%s', date='%s', created='%s', modified='%s',notes='%s', reportId='%s']",
                id, amount, type, date, created, modified, notes,reportid);
    }

    public Expence(){

    }

    public Expence(Double amount, String type, LocalDateTime date, LocalDateTime created, LocalDateTime modified,String notes ,String report_id) {
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.created = created;
        this.modified = modified;
        this.notes = notes;
        this.reportid = report_id;
    }

    public String getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String category) {
        this.type = category;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getReportid() {
        return reportid;
    }

    public void setReportid(String reportid) {
        this.reportid = reportid;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
