package com.takaranoao.i18nTools;

import com.takaranoao.i18nTools.i18n.I18n;

import java.util.logging.Logger;

public class Main {
    public static Logger LOGGER = Logger.getLogger("i18nTools");

    public static void main(String[] args) {
        if (args.length <= 0)
            LOGGER.info(I18n.get("info"));
    }
}
