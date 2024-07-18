package com.example.RegistrationForm;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/Form" )
public class Controller {
    Map<Integer,Form> hp = new HashMap<>();

    @PostMapping("/")
    public Form postMethodName(@RequestBody Form form) {
       
        hp.put(form.getId(), form);
        return hp.get(form.getId());
    }
    
    @GetMapping("/{id}")
    public Form getMethodName(@PathVariable Integer id) {
        return hp.get(id);
    }
    
    @PutMapping("/{id}")
    public Form putMethodName(@PathVariable Integer id, @RequestBody Form entity) throws NotFoundException {
        
        Form form  = hp.get(id);
        if(form!=null) {
        	form.setEmailId(entity.getEmailId());
            form.setMobileNumber(entity.getMobileNumber());
            form.setName(entity.getName());
            form.setPassword(entity.getPassword());
            form.setUserName(entity.getUserName());
            return form;
        }else {
            // Handle not found scenario, e.g., throw NotFoundException or return null with proper response status
            throw new NotFoundException();
        }
   
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id)
    {
        hp.remove(id);
        return "User Deleted Successfully";
    }

}
