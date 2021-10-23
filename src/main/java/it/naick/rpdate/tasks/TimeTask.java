package it.naick.rpdate.tasks;

import it.naick.rpdate.rpDate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import it.naick.rpdate.enums.Time;

public class TimeTask extends BukkitRunnable {

    final rpDate instance = rpDate.getInstance();

    @Override
    public void run() {

        long time = rpDate.getWorld().getTime();
        int ticksSpawn = (int) rpDate.getWorld().getTime();
        int hoursSpawn = (int) ((Math.floor(ticksSpawn / 1000.0) + 6.0) % 24.0);
        int minutesSpawn = (int) Math.floor(ticksSpawn % 1000 / 1000.0 * 60.0);

        String strMinutesSpawn;
        if (minutesSpawn < 10) strMinutesSpawn = "0" + minutesSpawn;
        else strMinutesSpawn = String.valueOf(minutesSpawn);

        String strHoursSpawn;
        if (hoursSpawn < 10) strHoursSpawn = "0" + hoursSpawn;
        else strHoursSpawn = String.valueOf(hoursSpawn);


        String orario = strHoursSpawn + ":" + strMinutesSpawn;
        int week = instance.getConfig().getInt("time.week");
        int month = instance.getConfig().getInt("time.month");
        int day = instance.getConfig().getInt("time.day");
        int year = instance.getConfig().getInt("time.year");

        if (orario.equals("00:00")) {
            rpDate.getWorld().setTime(time + 20);
            if (week == 7) instance.getConfig().set("time.week", 1);
            else instance.getConfig().set("time.week", week + 1);

            if (getMonth(month).equals("Dicembre")) {
                if (day == 31) {
                    instance.getConfig().set("time.month", 1);
                    instance.getConfig().set("time.year", year + 1);
                    instance.getConfig().set("time.day", 1);
                } else {
                    instance.getConfig().set("time.day", day + 1);
                }
            } else {
                for (Time times : Time.values()) {
                    if (times.getNome().equals(getMonth(month))) {
                        if (times.getGiorni() == day) {
                            instance.getConfig().set("time.month", month + 1);
                            instance.getConfig().set("time.day", 1);
                        } else {
                            instance.getConfig().set("time.day", day + 1);
                        }
                        break;
                    }
                }
            }
            instance.saveConfig();
        }

        final String title = ChatColor.translateAlternateColorCodes('&', instance.getConfig().getString("bossbar.title")
                .replaceAll("%hours%", strHoursSpawn)
                .replaceAll("%minutes%", strMinutesSpawn)
                .replaceAll("%day%", getWeek(week))
                .replaceAll("%number%", String.valueOf(day))
                .replaceAll("%month%", getMonth(month))
                .replaceAll("%year%", instance.getConfig().getString("time.year")));

        Bukkit.getOnlinePlayers().forEach(player -> rpDate.getBossBar().addPlayer(player));
        rpDate.getBossBar().setTitle(title);
        rpDate.getBossBar().setProgress(1.0);
    }

    private String getMonth(int month) {
        String name = null;
        for (Time time : Time.values()) {
            if (time.getWeight() == month) {
                name = time.getNome();
                break;
            }
        }
        return name;
    }

    private String getWeek(int week) {
        String name = null;
        switch (week) {
            case 1:
                name = "Lunedì";
                break;
            case 2:
                name = "Martedì";
                break;
            case 3:
                name = "Mercoledì";
                break;
            case 4:
                name = "Giovedì";
                break;
            case 5:
                name = "Venerdì";
                break;
            case 6:
                name = "Sabato";
                break;
            case 7:
                name = "Domenica";
                break;
        }
        return name;
    }
}

