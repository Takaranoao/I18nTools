package com.takaranoao.i18nTools.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18n {
    public static ResourceBundle I18nResourceBundle;
    public static void load() {
        Locale defaultLocale = Locale.getDefault();
        ResourceBundle resourceBundle;
        resourceBundle = ResourceBundle.getBundle("lang.lang", defaultLocale);
        I18nResourceBundle = resourceBundle;
    }
    public static String format(String key,Object... args){
        return get(key).formatted(args);
    }
    public static String get(String key){
        if(I18nResourceBundle == null)load();
        if(I18nResourceBundle == null)return "<CAN NOT LOAD I18N>";
        if(!I18nResourceBundle.containsKey(key)){
            return "<MISSING LANG>";
        }
        return  I18nResourceBundle.getString(key);
    }
}
