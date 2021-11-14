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
                "D:\\nyaaplugins\\RPGItems-reloaded\\lang_diff_en.txt"
        });
    }
    //sort_yaml
    @Test void testCommandSortYamlFile(){
        Main.main(new String[]{"sort_yaml",
                "D:\\nyaaplugins\\RPGItems-reloaded\\src\\main\\resources\\lang\\en_US.yml",
                "D:\\nyaaplugins\\RPGItems-reloaded\\sort_en.txt"
        });
    }
    //diff_key_i18n_yaml
    @Test void testCommandDiffKey(){
        Main.main(new String[]{"diff_key_i18n_yaml",
                "D:\\nyaaplugins\\RPGItems-reloaded\\src\\main\\resources\\lang\\en_US.yml",
                "D:\\nyaaplugins\\RPGItems-reloaded\\src\\main\\resources\\lang\\zh_CN.yml"
        });
    }
}
