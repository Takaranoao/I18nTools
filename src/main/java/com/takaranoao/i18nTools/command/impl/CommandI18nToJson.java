package com.takaranoao.i18nTools.command.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.takaranoao.i18nTools.command.Command;
import com.takaranoao.i18nTools.command.CommandSender;
import com.takaranoao.i18nTools.command.exception.CommandException;
import com.takaranoao.i18nTools.utils.I18nFileUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Map;

public class CommandI18nToJson extends Command {
    private final boolean deep;

    public CommandI18nToJson(boolean deep) {
        this.deep = deep;
    }

    @Override
    public void exec(String name, CommandSender sender, ArrayList<String> args) throws CommandException {
        needArgs(args, 2);
        Path i18nFile = FileSystems.getDefault().getPath(args.get(0));
        Path outFile = FileSystems.getDefault().getPath(args.get(1));
        if (I18nFileUtils.notRWFile(i18nFile)) return;
        if (I18nFileUtils.canNotCreateRWFile(outFile)) return;
        Yaml yaml = new Yaml();
        Map<String, Object> i18nYml;
        try {
            i18nYml = yaml.load(Files.newBufferedReader(i18nFile));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
        try {
            Files.writeString(outFile, gson.toJson(deep ? i18nYml : I18nFileUtils.yamlObjectToMap(i18nYml)), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
