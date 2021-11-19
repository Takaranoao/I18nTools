package com.takaranoao.i18nTools.utils;

import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class I18nFileUtils {
    public static List<String> getI18nKeyYaml(Map<?, ?> obj) {
        return yamlObjectToMap(obj).keySet().stream().toList();
    }

    public static Map<String, String> yamlObjectToMap(Map<?, ?> obj) {
        Map<String, String> result = new LinkedHashMap<>();
        obj.forEach(
                (k, v) -> {
                    if (!(k instanceof String)) return;
                    if (v instanceof String) result.put((String) k, (String) v);
                    if (v instanceof Map) {
                        yamlObjectToMap((Map<?, ?>) v).forEach((k1, v1) -> result.put(k + "." + k1, v1));
                    }
                }
        );
        return result;
    }

    public static Map<String, Object> i18nMapToYamlObjectRaw(Map<?, ?> i18nMap) {
        Map<Object, Object> mapIn = new LinkedHashMap<>(i18nMap);
        Map<String, String> stringMap = new LinkedHashMap<>();
        Iterator<? extends Map.Entry<?, ?>> iterator = mapIn.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<?, ?> next = iterator.next();
            if (next.getKey() instanceof String && next.getValue() instanceof String) {
                stringMap.put((String) next.getKey(), (String) next.getValue());
                iterator.remove();
            }
        }

        for (Map.Entry<String, Object> entry : i18nMapToYamlObject(stringMap).entrySet()) {
            try {
                mapIn.put(entry.getKey(), entry.getValue());
            } catch (Exception ignored) {
            }
        }

        Map<String, Object> result = new LinkedHashMap<>();
        mapIn.forEach((k, v) -> {
            if (k instanceof String) {
                result.put((String) k, v);
            }
        });
        return result;
    }

    public static Map<String, Object> i18nMapToYamlObject(Map<String, String> i18nMap) {
        Map<String, Object> result = new LinkedHashMap<>();
        i18nMap.forEach((k, v) -> addToI18nYaml(result, k, v));
        return result;
    }

    public static Map<String, Object> addToI18nYaml(Map<String, Object> map, String key, String value) {
        List<String> keys = new ArrayList<>(Arrays.asList(key.split("\\.")));
        Map<String, Object> subMap = map;
        StringBuilder key0_ = null;
        while (keys.size() > 1) {
            String subKey = keys.remove(0);
            if (key0_ == null) {
                key0_ = new StringBuilder(subKey);
            } else {
                key0_.append(".").append(subKey);
            }

            if (!subMap.containsKey(subKey)) {
                subMap.put(subKey, new LinkedHashMap<String, Object>());
            }
            Object obj = subMap.get(subKey);
            if (obj instanceof Map) {
                subMap = (Map<String, Object>) obj;
            } else {
                map.put(key0_.toString(), obj);
                Map<String, Object> map0_ = new LinkedHashMap<>();
                subMap.put(subKey, map0_);
                subMap = map0_;
            }
        }
        String subKey = keys.remove(0);
        subMap.put(subKey, value);
        return map;
    }


    public static boolean notRWFile(Path path) {
        if (Files.isDirectory(path)) {
            return true;
        }
        return !Files.isWritable(path) || !Files.isReadable(path);
    }

    public static boolean canNotCreateRWFile(Path path) {
        if (Files.isDirectory(path)) {
            return true;
        }
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {

                e.printStackTrace();
                return true;
            }
        }
        return !Files.isWritable(path) || !Files.isReadable(path);
    }

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
        while (matcher.find()) {
            result.add(matcher.group(1));
        }
        return result;
    }

}
