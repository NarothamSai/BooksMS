package com.example.books;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {
  public String template = "Hello, %s";

  @GetMapping("/greeting")
  public Greetings greeting(
    @RequestParam(value = "name", defaultValue = "World") String name
  ) {
    return new Greetings(String.format(template, name));
  }
}
