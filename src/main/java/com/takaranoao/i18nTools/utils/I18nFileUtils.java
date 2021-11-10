package com.takaranoao.i18nTools.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class I18nFileUtils {
    public static List<Path> listJavaFile(Path path) {
        return listFile(path).stream().
                filter(p -> FilenameUtils.getExtension(p.toString()).equalsIgnoreCase("java")).
                collect(Collectors.toList());
    }

    public static List<Path> listFile(Path path) {
        ArrayList<Path> result = new ArrayList<>();
        try {
            if (!Files.isDirectory(path)) path = path.getParent();
            Files.walkFileTree(path, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    if (!attrs.isDirectory()) {
                        result.add(file);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<String> getJavaFileString(Path path) {
        List<String> result = new ArrayList<>();
        if (Files.isDirectory(path)) return result;
        String fileStr;
        try {
            fileStr = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
            return result;
        }

        Pattern pattern = Pattern.compile("\"(.*?)\"");
        Matcher matcher = pattern.matcher(fileStr);
        while (matcher.find()){
            result.add(matcher.group(1));
        }
        return result;
    }

}
