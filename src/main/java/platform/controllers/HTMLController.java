package platform.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import platform.model.Code;
import platform.services.CodeService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@org.springframework.stereotype.Controller
@Component
public class HTMLController {

    @ExceptionHandler(ResourceNotFound.class)
    public String handleResourceNotFoundException() {

        return "not-found";
    }



    private final CodeService codeService;


    @Autowired
    public HTMLController(CodeService codeService) {
        this.codeService = codeService;
    }


    @RequestMapping(method= RequestMethod.GET, path="code/new")
    public String showNewTemplate () {

        return "new-code";
    }


    @GetMapping("code/{id}")
    public String getCode(@PathVariable String id, Model model) {
        Code code = codeService.getById(id);
        if(code == null) {
            throw new ResponseStatusException (HttpStatus.NOT_FOUND, "Not found!");
        } else {
            model.addAttribute("code", code);

            return "get-code-snippet";

        }

    }

    @GetMapping("/code/latest")
    public String getLatestCode(Model model) {
        List<Code> newList = codeService.getLatestCode();
        model.addAttribute("codes", newList);

        return "code-list";

    }


}



