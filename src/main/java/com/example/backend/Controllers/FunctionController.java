package com.example.backend.Controllers;

import com.example.backend.entities.Function;
import com.example.backend.entities.RepRapport;
import com.example.backend.services.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/function")
public class FunctionController {

    @Autowired
    private FunctionService funcService;

    @RequestMapping(value="/add",method= RequestMethod.POST,consumes =  MediaType.APPLICATION_JSON_VALUE)
    public void addFunction(@RequestBody Function function) {
        funcService.addFunction(function);
    }

    @RequestMapping(value = "/list")
    public List<Function> allFunctions(){
        return funcService.getListFunction();
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        funcService.deleteFunc(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Function> updateFunction(@PathVariable("id") long id, @RequestBody Function function) {

        Function updatedFunction=funcService.updateFunction(id, function);

        return ResponseEntity.ok(updatedFunction);
    }

    @PutMapping("/{functionId}/reprapports/{repRapportId}")
    public ResponseEntity<?> assignRepRapportToFunction(@PathVariable Long functionId, @PathVariable Long repRapportId) {
        funcService.assignRepRapportToFunction(functionId, repRapportId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/rapports")
    public List<RepRapport> getListRapport(){
        return funcService.getListRapport();
    }

    @GetMapping("/{functionId}/reprapports")
    public ResponseEntity<List<RepRapport>> getRepRapportsByFunctionId(@PathVariable Long functionId) {
        List<RepRapport> repRapports = funcService.getRepRapportsByFunctionId(functionId);
        return ResponseEntity.ok(repRapports);
    }

    @DeleteMapping("/{functionId}/reprapports/{repRapportId}")
    public ResponseEntity<?> removeRepRapportFromFunction(@PathVariable Long functionId, @PathVariable Long repRapportId) {
        funcService.removeRepRapportFromFunction(functionId, repRapportId);
        return ResponseEntity.ok().build();
    }

}
