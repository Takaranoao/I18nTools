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
}
