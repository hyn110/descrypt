package com.fmi110.descrypt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fmi110.descrypt.util.DesCryptUtil;

@RestController
public class DesCryptController {

    @GetMapping("/decrypt")
    public Object decrypt(String key,String text) throws Exception {
        return DesCryptUtil.decode(key, text);
    }
}
