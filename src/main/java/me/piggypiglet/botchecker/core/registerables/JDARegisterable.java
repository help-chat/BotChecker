package me.piggypiglet.botchecker.core.registerables;

import com.google.inject.Inject;
import me.piggypiglet.botchecker.core.enums.Registerables;
import me.piggypiglet.botchecker.core.framework.Registerable;
import me.piggypiglet.botchecker.core.handlers.EventHandler;
import me.piggypiglet.botchecker.core.storage.file.Config;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class JDARegisterable extends Registerable {
    @Inject private EventHandler eventHandler;

    public JDARegisterable() {
        super(Registerables.JDA);
    }

    @Override
    protected void execute() {
        try {
            addValue("jda", new JDABuilder(AccountType.BOT)
                    .setToken(Config.getString("token"))
                    .setStatus(OnlineStatus.ONLINE)
                    .setActivity(Activity.of(Activity.ActivityType.valueOf(Config.getString("game.type").toUpperCase()), Config.getString("game.game")))
                    .addEventListeners(eventHandler)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
