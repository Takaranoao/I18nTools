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
import java.util.IllegalFormatCodePointException;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.takaranoao.i18nTools.utils.I18nFileUtils.i18nMapToYamlObjectRaw;

public class CommandI18nJsonToYaml extends Command {
    private final boolean deep;

    public CommandI18nJsonToYaml(boolean deep) {
        this.deep = deep;
    }

    @Override
    public void exec(String name, CommandSender sender, ArrayList<String> args) throws CommandException {
        needArgs(args, 2);
        Path jsonFile = FileSystems.getDefault().getPath(args.get(0));
        Path outFile = FileSystems.getDefault().getPath(args.get(1));
        if (I18nFileUtils.notRWFile(jsonFile)) return;
        if (I18nFileUtils.canNotCreateRWFile(outFile)) return;
        Gson gson = new GsonBuilder().create();
        Map<?,?> jsonMap;
        try {
            jsonMap = gson.fromJson(Files.newBufferedReader(jsonFile), LinkedHashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Yaml yaml = new Yaml();
        if(!deep) {
            jsonMap = i18nMapToYamlObjectRaw(jsonMap);
        }
        try {
            Files.writeString(outFile, yaml.dumpAsMap(jsonMap), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
