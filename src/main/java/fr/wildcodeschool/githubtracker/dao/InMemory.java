package fr.wildcodeschool.githubtracker.dao;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


// Cette classe qualifier sert juste à definir un qualifier pour "colorer" les implementations de GithuberDAO
@Qualifier
@Retention(RUNTIME)
@Target({TYPE, METHOD, FIELD, PARAMETER})
public @interface InMemory {

}

