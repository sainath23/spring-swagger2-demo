package com.sainath.swaggerdemo.restcontroller;

import com.sainath.swaggerdemo.model.Contact;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api")
@Api(value = "Home", tags = {"Home"})
public class HomeController {

    ConcurrentHashMap<String, Contact> contacts = new ConcurrentHashMap<>();

    @GetMapping("/{id}")
    @ApiOperation(value = "Finds Contact by id",
                notes = "Provide an id to look up specific contact from the address book",
                response = Contact.class)
    public Contact getContact(@ApiParam(value = "ID value for the contact you need to retrieve",
                                        required = true)
                                  @PathVariable String id) {
        return contacts.get(id);
    }

    @GetMapping("/")
    public List<Contact> getAllContacts() {
        return new ArrayList<Contact>(contacts.values());
    }

    @PostMapping("/")
    public Contact addContact(@RequestBody Contact contact) {
        contacts.put(contact.getId(), contact);
        return contact;
    }
}
