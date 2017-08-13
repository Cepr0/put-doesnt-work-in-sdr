package io.github.cepr0.putissue.standalone;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author Cepro, 2017-08-13
 */
@RequiredArgsConstructor
@Component
public class TestDemoData implements ApplicationRunner {
    
    private final ManRepo manRepo;
    private final WorkRepo workRepo;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Work> works = workRepo.save(asList(
                new Work("position1"),
                new Work("position2")
        ));
        
        // initial values - Man name is 'man1', and his work (position) is 'position1'
        manRepo.save(new Man("man1", works.get(0)));
    }
}
