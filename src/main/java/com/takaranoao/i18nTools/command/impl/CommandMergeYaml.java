package com.takaranoao.i18nTools.command.impl;

import com.takaranoao.i18nTools.command.Command;
import com.takaranoao.i18nTools.command.CommandSender;
import com.takaranoao.i18nTools.command.exception.CommandException;
import com.takaranoao.i18nTools.i18n.I18n;
import com.takaranoao.i18nTools.utils.I18nFileUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class CommandMergeYaml extends Command {

    @Override
    public void exec(String name, CommandSender sender, ArrayList<String> args) throws CommandException {
        needArgs(args, 3);
        Path i18nFile1 = FileSystems.getDefault().getPath(args.get(0));
        Path i18nFile2 = FileSystems.getDefault().getPath(args.get(1));
        Path outFile = FileSystems.getDefault().getPath(args.get(2));
        if (I18nFileUtils.notRWFile(i18nFile1)) return;
        if (I18nFileUtils.notRWFile(i18nFile2)) return;
        if (I18nFileUtils.canNotCreateRWFile(outFile)) return;
        Yaml yaml = new Yaml();
        Map<String, Object> yamlObject1, yamlObject2;

        try {
            yamlObject1 = yaml.load(Files.newBufferedReader(i18nFile1));
            yamlObject2 = yaml.load(Files.newBufferedReader(i18nFile2));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        TreeMap<String, String> i18nMapResult = new TreeMap<>(I18nFileUtils.yamlObjectToMap(yamlObject1));
        for (Map.Entry<String, String> entry : I18nFileUtils.yamlObjectToMap(yamlObject2).entrySet()) {
            if(!i18nMapResult.containsKey(entry.getKey())){
                i18nMapResult.put(entry.getKey(),entry.getValue());
                sender.sendMessage(I18n.format("command.merge.merge_key",entry.getKey()));
            }
        }

        try {
            Files.writeString(outFile, yaml.dumpAsMap(I18nFileUtils.i18nMapToYamlObject(i18nMapResult)), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
