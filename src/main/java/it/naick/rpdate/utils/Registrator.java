package it.naick.rpdate.utils;

import it.naick.rpdate.rpDate;
import it.naick.rpdate.tasks.TimeTask;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Registrator {

    public void registerAll() {

        new TimeTask().runTaskTimer(rpDate.getInstance(), 0, 20L);


    }
}
