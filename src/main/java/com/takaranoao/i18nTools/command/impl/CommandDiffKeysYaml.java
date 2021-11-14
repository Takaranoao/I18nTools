package com.takaranoao.i18nTools.command.impl;

import com.takaranoao.i18nTools.command.Command;
import com.takaranoao.i18nTools.command.CommandSender;
import com.takaranoao.i18nTools.command.exception.CommandException;
import com.takaranoao.i18nTools.utils.I18nFileUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class CommandDiffKeysYaml extends Command {
    @Override
    public void exec(String name, CommandSender sender, ArrayList<String> args) throws CommandException {
        needArgs(args, 2);
        Path inFile = FileSystems.getDefault().getPath(args.get(0));
        Path in2File = FileSystems.getDefault().getPath(args.get(1));
        if (I18nFileUtils.notRWFile(inFile)) return;
        if (I18nFileUtils.notRWFile(in2File)) return;
        Yaml yaml = new Yaml();
        Map<String, Object> inYaml;
        Map<String, Object> in2Yaml;
        try {
            inYaml = yaml.load(Files.newBufferedReader(inFile));
            in2Yaml = yaml.load(Files.newBufferedReader(in2File));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        HashSet<String> set1 = new HashSet<>(I18nFileUtils.getI18nKeyYaml(inYaml));
        HashSet<String> set2 = new HashSet<>(I18nFileUtils.getI18nKeyYaml(in2Yaml));
        HashSet<String> result = new HashSet<>();
        for (String s : set1) {
            if (!set2.remove(s)) {
                result.add(s);
            }
        }

        result.addAll(set2);
        result.forEach(sender::sendMessage);
    }
}
