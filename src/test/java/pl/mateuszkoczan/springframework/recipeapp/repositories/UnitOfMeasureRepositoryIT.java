package pl.mateuszkoczan.springframework.recipeapp.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.mateuszkoczan.springframework.recipeapp.domains.UnitOfMeasure;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {
    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Test
    public void findByDescription() {
        Optional<UnitOfMeasure> teaspoonUOMOptional = unitOfMeasureRepository.findByDescription("teaspoon");

        assertEquals("teaspoon", teaspoonUOMOptional.get().getDescription());
    }

    @Test
    public void findByDescription2() {
        Optional<UnitOfMeasure> teaspoonUOMOptional = unitOfMeasureRepository.findByDescription("cup");

        assertEquals("cup", teaspoonUOMOptional.get().getDescription());
    }
}