package model;

import org.springframework.data.annotation.Id;

public class Expence {

    @Id
    private String id;

    private String amount;
    private String category;
    private String date;
    private String created;
    private String modified;
    private String reportId;

    @Override
    public String toString() {
        return String.format(
                "Expence[id=%s, amount='%s', category='%s', date='%s', created='%s', modified='%s', reportId='%s']",
                id, amount, category, date, created, modified, reportId);
    }

    public Expence(){

    }

    public Expence(String amount, String category, String date, String created, String modified, String reportId) {
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.created = created;
        this.modified = modified;
        this.reportId = reportId;
    }

    public String getId() {
        return id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public String getReportId() {
        return reportId;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }


}
