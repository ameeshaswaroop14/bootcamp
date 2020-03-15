package com.BootAssignment.BootAssignment1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
@GetMapping(path="/welcome")
    public String show()
{
    return"Welcome to spring boot";
}
}
