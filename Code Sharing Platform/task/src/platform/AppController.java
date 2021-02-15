package platform;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;


@Controller
public class AppController {
    private final CodeService codeService;

    public AppController(CodeService codeService){
        this.codeService = codeService;
    }

    @GetMapping(path = "/code/new", produces = "text/html")
    public String newCode(){
        return "new";
    }

    @GetMapping(path = "/code/{UUID}", produces = "text/html")
    public ModelAndView getCode(@PathVariable String UUID){
        Optional<Code> tmp = codeService.getCode(UUID);
        if(tmp.isPresent()){
            Code code = tmp.get();
            codeService.checkRestrictions(code);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("code",code);
            modelAndView.setViewName("id");
            return modelAndView;
        }else{
            throw new CodeNotFoundException();
        }
    }

    @GetMapping(path = "/code/latest", produces = "text/html")
    public ModelAndView getLatest(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("codeList", codeService.getLatest());
        modelAndView.setViewName("latest");
        return modelAndView;
    }

    @PostMapping(path = "/api/code/new", produces = "application/json")
    @ResponseBody
    public Map<String,String> newApiCode(@RequestBody Code code){
        codeService.save(code);
        return Collections.singletonMap("id",code.getId().toString());
    }

    @GetMapping(path = "/api/code/{UUID}", produces = "application/json")
    @ResponseBody
    public Code getApiCodeById(@PathVariable String UUID){
        Optional<Code> tmp = codeService.getCode(UUID);
        if (tmp.isPresent()) {
            Code code = tmp.get();
            codeService.checkRestrictions(code);
            return code;
        } else {
            throw new CodeNotFoundException();
        }
    }

    @GetMapping(path = "/api/code/latest", produces = "application/json")
    @ResponseBody
    public List<Code> getApiLatestCode() {
        return codeService.getLatest();
    }

}
