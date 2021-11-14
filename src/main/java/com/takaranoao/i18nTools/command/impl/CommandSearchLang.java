package com.takaranoao.i18nTools.command.impl;

import com.takaranoao.i18nTools.command.Command;
import com.takaranoao.i18nTools.command.CommandSender;
import com.takaranoao.i18nTools.command.exception.BadCommandException;
import com.takaranoao.i18nTools.i18n.I18n;
import com.takaranoao.i18nTools.utils.I18nFileUtils;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class CommandSearchLang extends Command {
    @Override
    public void exec(String name, CommandSender sender, ArrayList<String> args) throws BadCommandException {
        needArgs(args, 1);
        Path path = FileSystems.getDefault().getPath(args.get(0));
        Path outputPath = null;
        List<String> result = new ArrayList<>();
        if (args.size() >= 2) {
            outputPath = FileSystems.getDefault().getPath(args.get(1));
            if(I18nFileUtils.notCreateRWFile(outputPath)){
                sender.sendMessage(I18n.format("command.search_lang.output.not_writable", outputPath.toString()));
                return;
            }
        }
        List<Path> javaFilePath = I18nFileUtils.listJavaFile(path);
        javaFilePath.forEach(
                path1 -> result.addAll(I18nFileUtils.getJavaFileString(path1))
        );
        Path finalOutputPath = outputPath;
        for (String outStr : result) {
            if (outStr.length() <= 1 || !outStr.contains(".") || outStr.startsWith(".")) continue;
            if (finalOutputPath == null) {
                sender.sendMessage(outStr);
            } else {
                try {
                    Files.writeString(finalOutputPath, outStr + System.lineSeparator(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }

    }
}
