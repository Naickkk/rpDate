package it.naick.rpdate.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Time {

    GENNAIO(31, "Gennaio", 1),
    FEBBRAIO(28, "Febbraio", 2),
    MARZO(31, "Marzo", 3),
    APRILE(30, "Aprile", 4),
    MAGGIO(31, "Maggio", 5),
    GIUGNO(30, "Giugno", 6),
    LUGLIO(31, "Luglio", 7),
    AGOSTO(31, "Agosto", 8),
    SETTEBRE(30, "Settembre", 9),
    OTTOBRE(31, "Ottobre", 10),
    NOVEMBRE(30, "Novembre", 11),
    DICEMBRE(31, "Dicembre", 12);

    public int giorni;
    public String nome;
    public int weight;
}