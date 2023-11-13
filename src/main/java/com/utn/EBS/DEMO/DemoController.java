package com.utn.EBS.DEMO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/demo")
public class DemoController {  //El DemoController en teoria simula al usuarioController, seguir con esto

    @PostMapping(value = "demoUser")
    public String welcome()
    {
        return "Bienvenido desde el endpoint seguro";
    }

    @PostMapping(value = "demoAdmin")
    public String welcomeAdmin()
    {
        return "Bienvenido desde el endpoint seguro ADMIN";
    }

}
