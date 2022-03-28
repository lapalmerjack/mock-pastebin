package platform.bootstrap;

import freemarker.core.CommandLine;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import platform.model.Code;
import platform.repositories.CodeRepository;

@Component
public class CodeConfig implements CommandLineRunner {

    public final CodeRepository codeRepository;

    public CodeConfig(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("loading");


    }

}
