package model;

import org.springframework.data.annotation.Id;

public class Report {

    @Id
    private String id;

    private String name;

    public Report(){}

    public Report( String name ){
        this.name=name;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
