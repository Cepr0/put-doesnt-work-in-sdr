package io.github.cepr0.putissue;

import io.github.cepr0.putissue.bi_onetomany.BiChild;
import io.github.cepr0.putissue.bi_onetomany.BiParent;
import io.github.cepr0.putissue.manytoone.Address;
import io.github.cepr0.putissue.manytoone.Person;
import io.github.cepr0.putissue.onetomany.Child;
import io.github.cepr0.putissue.onetomany.Parent;
import org.h2.tools.Server;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

import static java.util.Arrays.asList;

@SpringBootApplication
public class Application {
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }
    
    @Transactional
    @Bean
    public ApplicationRunner demoData(BaseRepo repo) {
        return (ApplicationArguments args) -> {
            
            // many-to-one
            
            List<Address> addresses = repo.save(asList(
                    new Address("address1"),
                    new Address("address2")
            ));
            
            repo.save(new Person("person1", addresses.get(0)));
            
            // one-to-many
    
            List<Child> children = repo.save(asList(
                    new Child("child1"),
                    new Child("child2"),
                    new Child("child3"),
                    new Child("child4")
            ));
            
            repo.save(new Parent("parent1", children.subList(0, 2)));

            // bidirectional one-to-many

            BiParent biParent = repo.save(new BiParent("parent1", null));
            repo.save(asList(
                    new BiChild(biParent, "child1"),
                    new BiChild(biParent, "child2"),
                    new BiChild(null, "child3"),
                    new BiChild(null, "child4")
            ));
        };
    }
}
