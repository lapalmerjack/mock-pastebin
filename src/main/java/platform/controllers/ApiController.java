package platform.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.model.Code;
import platform.services.CodeService;
import java.util.List;
import java.util.UUID;


@RestController
public class ApiController {


    private final CodeService codeService;

    @Autowired
    public ApiController(CodeService codeService) {
        this.codeService = codeService;
    }


    @PostMapping("/api/code/new")
    public ResponseEntity<?> sendCode(@RequestBody Code theCode) {
        codeService.addNewSnippet(theCode);
        IdReturn idReturn = new IdReturn(theCode.getId());
        return new ResponseEntity<>(idReturn, HttpStatus.OK);

    }


    @GetMapping("/api/code/{UUID}")
    public ResponseEntity<?> returnCodeSnippets(@PathVariable String UUID) {
        Code code = codeService.getById(UUID);
        if(code == null) {
            return new ResponseEntity<>("404 Not Found", HttpStatus.NOT_FOUND);
        }

        System.out.println("PRINTING " + (UUID));

     return new ResponseEntity<>(code, HttpStatus.OK);

    }

    @GetMapping("api/code/latest")
    public ResponseEntity<List<Code>> returnLatestSnippets () {
        System.out.println("getting the latest code....");
        List<Code> latestCode = codeService.getLatestCode();
        return new ResponseEntity<>(latestCode, HttpStatus.OK);
    }



}

@AllArgsConstructor
@Data
class IdReturn {

    private String id;

}