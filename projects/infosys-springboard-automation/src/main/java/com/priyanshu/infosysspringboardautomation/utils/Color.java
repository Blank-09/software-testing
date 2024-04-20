package com.priyanshu.infosysspringboardautomation.utils;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class Color {

    public static String info(String message) {
        return ansi().a("[").fg(CYAN).a("INFO").reset().a("] " + message).toString();
    }

    public static String success(String message) {
        return ansi().fg(BLACK).bgBright(GREEN).a(" SUCCESS ").reset().a(" " + message).toString();
    }

    public static String warning(String message) {
        return ansi().a("[").fg(YELLOW).a("WARN").reset().a("] " + message).toString();
    }

    public static String completed(String message) {
        return ansi().a("[").fg(GREEN).a("COMPLETED").reset().a("] " + message).toString();
    }

    public static String question(String message) {
        return ansi().bold().fg(BLUE).a("?").reset().a(" " + message).toString();
    }
}
