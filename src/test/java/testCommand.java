import com.takaranoao.i18nTools.Main;
import org.junit.jupiter.api.Test;


public class testCommand {

    @Test
    public void testCommandHelp() {
        Main.main(new String[]{"help"});
    }

    @Test
    public void testCommandDef() {
        Main.main(new String[]{"2333"});
    }
    @Test
    public void testCommandListJava() {
        Main.main(new String[]{"list_java","D:\\git\\i18ntools"});
    }
    @Test void testCommandGetFileStr(){
        Main.main(new String[]{"file_str","D:\\git\\i18ntools\\src\\main\\java\\com\\takaranoao\\i18nTools\\command\\CommandManager.java"});
    }
    @Test void testCommandSearchLang(){
        Main.main(new String[]{"search_lang","D:\\nyaaplugins\\RPGItems-reloaded\\src","D:\\nyaaplugins\\RPGItems-reloaded\\lang.txt"});
    }
    //find_i18n_file
    @Test void testCommandFindI18nFile(){
        Main.main(new String[]{"find_i18n_file_yaml",
                "D:\\nyaaplugins\\RPGItems-reloaded\\lang.txt",
                "D:\\nyaaplugins\\RPGItems-reloaded\\src\\main\\resources\\lang\\en_US.yml",
                "D:\\nyaaplugins\\RPGItems-reloaded\\lang_diff_en2.txt"
        });
    }
    //sort_yaml
    @Test void testCommandSortYamlFile(){
        Main.main(new String[]{"sort_yaml",
                "D:\\nyaaplugins\\RPGItems-reloaded\\src\\main\\resources\\lang\\zh_CN.yml",
                "D:\\nyaaplugins\\RPGItems-reloaded\\src\\main\\resources\\lang\\sort_cn.yaml"
        });
    }
    //diff_key_i18n_yaml
    @Test void testCommandDiffKey(){
        Main.main(new String[]{"diff_key_i18n_yaml",
                "D:\\nyaaplugins\\RPGItems-reloaded\\src\\main\\resources\\lang\\en_US.yml",
                "D:\\nyaaplugins\\RPGItems-reloaded\\src\\main\\resources\\lang\\zh_CN.yml"
        });
    }

    //merge_i18n_yaml
    @Test void testCommandMergeYaml(){
        Main.main(new String[]{"merge_i18n_yaml",
                "D:\\nyaaplugins\\RPGItems-reloaded\\src\\main\\resources\\lang\\en_US.yml",
                "D:\\nyaaplugins\\RPGItems-reloaded\\src\\main\\resources\\lang\\zh_CN.yml",
                "D:\\nyaaplugins\\RPGItems-reloaded\\src\\main\\resources\\lang\\en_US_merge.yml"
        });
    }
    //i18n_yaml2JsonProperties
    @Test void testCommandYaml2JsonProperties(){
        Main.main(new String[]{"i18n_yaml2json",
                "D:\\nyaaplugins\\RPGItems-reloaded\\src\\main\\resources\\lang\\en_US.yml",
                "D:\\nyaaplugins\\RPGItems-reloaded\\src\\main\\resources\\lang\\en_US.json"
        });
    }
    @Test void testCommandYaml2Deep(){
        Main.main(new String[]{"i18n_yaml2json_deep",
                "D:\\nyaaplugins\\RPGItems-reloaded\\src\\main\\resources\\lang\\en_US.yml",
                "D:\\nyaaplugins\\RPGItems-reloaded\\src\\main\\resources\\lang\\en_US_deep.json"
        });
    }
    @Test void testCommandJson2YamlProperties(){
        Main.main(new String[]{"i18n_json2yaml",
                "D:\\nyaaplugins\\RPGItems-reloaded\\src\\main\\resources\\lang\\en_US.json",
                "D:\\nyaaplugins\\RPGItems-reloaded\\src\\main\\resources\\lang\\en_US_2.yml"
        });
    }
}
