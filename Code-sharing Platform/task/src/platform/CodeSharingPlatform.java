package platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class CodeSharingPlatform {

    private String codeSnippet = "";
    private String loadDate = "";

    public static void main(String[] args) {
        SpringApplication.run(CodeSharingPlatform.class, args);
    }

    @GetMapping("/code")
    public ModelAndView getCodePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("text.html");
        modelAndView.addObject("text", codeSnippet);
        modelAndView.addObject("date", loadDate);

        return modelAndView;
    }

    @GetMapping("/api/code")
    public Map<String, String> getCode() {
        Map<String, String> mem = new LinkedHashMap<>();
        mem.put("code", codeSnippet);
        mem.put("date", loadDate);

        return mem;
    }

    @PostMapping("/api/code/new")
    public Map<String, Object> updateCode(@RequestBody CodeRequest codeRequest) {
        codeSnippet = codeRequest.getCode();
        loadDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));

        return Collections.emptyMap();
    }

    @GetMapping("/code/new")
    public ModelAndView getNewCodePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("create.html");

        return modelAndView;
    }
}

class CodeRequest {
    private String code;

    public String getCode() {
        return code;
    }
}
