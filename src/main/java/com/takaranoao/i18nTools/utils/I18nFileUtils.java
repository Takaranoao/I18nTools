package com.takaranoao.i18nTools.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class I18nFileUtils {
    public static List<String> listJavaFile(String path) {
        return listFile(path).stream().
                filter(p-> FilenameUtils.getExtension(p).equalsIgnoreCase("java")).
                collect(Collectors.toList());
    }

    public static List<String> listFile(String path) {
        ArrayList<String> result = new ArrayList<>();
        Path dir = FileSystems.getDefault().getPath(path);
        try {
            Files.walkFileTree(dir,new SimpleFileVisitor<>(){
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    result.add(file.toString());
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
