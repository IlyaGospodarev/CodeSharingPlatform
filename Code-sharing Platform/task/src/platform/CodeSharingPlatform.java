package platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Map;

@SpringBootApplication
@RestController
public class CodeSharingPlatform {

    private static final String TEXT = """
            public static void main(String[] args) {
                SpringApplication.run(CodeSharingPlatform.class, args);
            }""";

    public static void main(String[] args) {
        SpringApplication.run(CodeSharingPlatform.class, args);
    }

    @GetMapping("/code")
    public ModelAndView getHttpResponse() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("text.html");
        modelAndView.addObject("text", "\n" + TEXT);

        return modelAndView;
    }

    @GetMapping("/api/code")
    public Map<String, String> getJsonResponse() {
        return Collections.singletonMap("code", TEXT);
    }

}
