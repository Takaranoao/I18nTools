package com.takaranoao.i18nTools.command;

import com.takaranoao.i18nTools.Main;

public class CommandSender {
    public void sendMessage(String msg){
        Main.LOGGER.info(msg);
    }
}
