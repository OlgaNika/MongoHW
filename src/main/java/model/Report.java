package model;

import org.springframework.data.annotation.Id;

public class Report {

    @Id
    private String id;
    private String name;
    private String created;

    public Report(){}

    public Report( String name, String created ){
        this.name=name;
        this.created=created;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getCreated(){return created;}

    public void setName(String name) {
        this.name = name;
    }
    public void setCreated(String created) {
        this.created = created;
    }
}
