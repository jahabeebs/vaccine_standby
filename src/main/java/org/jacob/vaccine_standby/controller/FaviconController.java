package org.jacob.vaccine_standby.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Prevents Thymeleaf from throwing an error to the console when it can't find a favicon
 */
@Controller
public class FaviconController {
    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
    }
}
