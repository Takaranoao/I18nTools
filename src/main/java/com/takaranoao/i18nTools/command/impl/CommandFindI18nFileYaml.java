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
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommandFindI18nFileYaml extends Command {
    @Override
    public void exec(String name, CommandSender sender, ArrayList<String> args) throws CommandException {
        needArgs(args, 3);
        Path inFile = FileSystems.getDefault().getPath(args.get(0));
        Path i18nFile = FileSystems.getDefault().getPath(args.get(1));
        Path outFile = FileSystems.getDefault().getPath(args.get(2));
        if (I18nFileUtils.notRWFile(inFile)) {
            return;
        }
        if (I18nFileUtils.notRWFile(i18nFile)) {
            return;
        }
        if (I18nFileUtils.canNotCreateRWFile(outFile)) {
            return;
        }
        List<String> inStr;
        Map<String, Object> i18nYml;
        Yaml yaml = new Yaml();

        try {
            inStr = Files.readAllLines(inFile);
            i18nYml = yaml.load(Files.newBufferedReader(i18nFile));
            //i18nYml = Files.readAllLines(i18nFile);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        inStr = inStr.stream().distinct().collect(Collectors.toList());
        List<String> i18nStr = I18nFileUtils.getI18nKeyYaml(i18nYml);

        for (String s : inStr) {
            if (!findInI18nStr(s, i18nStr)) {
                try {
                    Files.writeString(outFile, s + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean findInI18nStr(String inStr, List<String> i18nStrList) {
        for (String s : i18nStrList) {
            if (s.equals(inStr)) return true;
        }
        return false;
    }
}
