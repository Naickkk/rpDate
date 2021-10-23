package it.naick.rpdate;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.plugin.java.JavaPlugin;
import it.naick.rpdate.license.AdvancedLicense;
import it.naick.rpdate.utils.Registrator;

public final class rpDate extends JavaPlugin {

    @Getter
    private static rpDate instance;
    @Getter
    private static World world;
    @Getter
    private static BossBar bossBar;

    @Override
    public void onEnable() {
        // Plugin startup logic


        instance = this;

        //config
        saveDefaultConfig();
        if (!new AdvancedLicense(getConfig().getString("config.license-key"), "https://unfathomed-foods.000webhostapp.com/verify.php", this).register())
            return;
        if (Bukkit.getWorld(getConfig().getString("config.world")) == null) {
            System.out.println("§4| §cNon hai impostato un mondo valido");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        world = Bukkit.getWorld(getConfig().getString("config.world"));
        System.out.println("§2| §aIl mondo che hai impostato è valido");

        //bossbar
        bossBar = Bukkit.createBossBar("Date", BarColor.valueOf(getConfig().getString("bossbar.color")), BarStyle.valueOf(getConfig().getString("bossbar.style")));

        new Registrator().registerAll();

        System.out.println("§2| §arpDate abilitato con successo");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (bossBar != null) bossBar.removeAll();

        System.out.println("§4| §crpDate disabilitato con successo");

    }
}
