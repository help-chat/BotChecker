package me.piggypiglet.botchecker.core.utils.discord;

import me.piggypiglet.botchecker.core.enums.Roles;
import me.piggypiglet.botchecker.core.objects.Constants;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import java.util.List;
import java.util.function.Predicate;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class RoleUtils {
    public static boolean isHelpful(Member member) {
        return containsRoles(member, r -> r == Constants.HELPFUL || isTrusted(member) || isAdmin(member));
    }

    public static boolean isTrusted(Member member) {
        return containsRoles(member, r -> r == Constants.TRUSTED || isAdmin(member));
    }

    public static boolean isAdmin(Member member) {
        return containsRoles(member, r -> r == Constants.ADMIN);
    }

    public static Roles getRole(Member member) {
        Roles role = Roles.EVERYBODY;
        Guild guild = member.getGuild();
        List<Role> roles = member.getRoles();

        if (roles.contains(guild.getRoleById(Constants.HELPFUL))) role = Roles.HELPFUL;
        if (roles.contains(guild.getRoleById(Constants.TRUSTED))) role = Roles.TRUSTED;
        if (roles.contains(guild.getRoleById(Constants.ADMIN))) role = Roles.ADMIN;

        return role;
    }

    private static boolean containsRoles(Member member, Predicate<? super Long> predicate) {
        return member.getRoles().stream().map(Role::getIdLong).anyMatch(predicate);
    }
}
