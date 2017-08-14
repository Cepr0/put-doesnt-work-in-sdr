package io.github.cepr0.putissue.standalone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Cepro, 2017-08-13
 */
@Entity
public class Work {
    
    @Id @GeneratedValue
    private Integer id;
    
    private String position;
    
    public Work() {
    }
    
    public Work(String position) {
        this.position = position;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getPosition() {
        return position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    
    @Override
    public String toString() {
        return "Work{" +
                "id=" + id +
                ", position='" + position + '\'' +
                '}';
    }
}
