package io.github.cepr0.putissue.standalone;

import javax.persistence.*;

/**
 * @author Cepro, 2017-08-13
 */
@Entity
public class Man {
    
    @Id @GeneratedValue
    private Integer id;
    
    private String name;
    
    @ManyToOne
    private Work work;
    
    public Man() {
    }
    
    public Man(String name, Work work) {
        this.name = name;
        this.work = work;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Work getWork() {
        return work;
    }
    
    public void setWork(Work work) {
        this.work = work;
    }
    
    @Override
    public String toString() {
        return "Man{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", work=" + work +
                '}';
    }
}
