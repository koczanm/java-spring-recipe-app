package pl.mateuszkoczan.springframework.recipeapp.services;

import pl.mateuszkoczan.springframework.recipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> getUnitOfMeasureList();
}
