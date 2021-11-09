package com.takaranoao.i18nTools.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {
    public static List<File> listJavaFile(String path) {
        return listFile(path).stream().
                map(File::new).
                filter(f-> getFileExtension(f).equalsIgnoreCase("java")).
                collect(Collectors.toList());
    }
    public static List<String> listJavaFileStr(String path) {
        return listJavaFile(path).stream().map(File::getPath).collect(Collectors.toList());
    }

    public static List<String> listFile(String path) {
        ArrayList<String> result = new ArrayList<>();
        File dir = new File(path);
        if (!dir.isDirectory()) {
            dir = dir.getParentFile();
        }
        if (dir == null) return result;
        File[] files = dir.listFiles();
        if (files == null) return result;
        for (File file : files) {
            if (!file.isDirectory()) {
                result.add(file.getPath());
            } else {
                result.addAll(listFile(file.getPath()));
            }
        }
        return result;
    }

    public static String getFileExtension(File file) {
        String extension = "";
        try {
            if (file != null && file.exists()) {
                String name = file.getName();
                extension = name.substring(name.lastIndexOf("."));
            }
        } catch (Exception e) {
            extension = "";
        }
        return extension;
    }
}
