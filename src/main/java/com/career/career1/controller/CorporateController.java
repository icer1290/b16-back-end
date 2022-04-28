package com.career.career1.controller;

// import java.util.List;


import com.career.career1.model.Corporation;
import com.career.career1.repo.CorporationRepo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CorporateController {
    @Autowired
    private CorporationRepo corporationRepo;

    // 注册新的企业
    @PostMapping(value="CorRegister")
    public int CorRegister(Corporation cor) {
        String email = cor.getEmail();
        int length = (int) corporationRepo.count();
        int b = 1;
        String e = "";
        for (int i = 1; i <= length; i++) {
            e = corporationRepo.findById(i).get().getEmail();
            if (email.equals(e)) return b = 0;
        }
        if (b == 1) {
            corporationRepo.save(cor);
        }
        return b;
    }

    // 企业登录
    @PostMapping(value="CorLogin")
    public int CorLogin(@RequestParam(name = "email") String email,
                     @RequestParam(name = "password") String password) {
        int length = (int) corporationRepo.count();
        int b = 0;
        String e = "";
        String p = "";
        for (int i = 1; i <= length; i++) {
            e = corporationRepo.findById(i).get().getEmail();
            if (email.equals(e)){
                p = corporationRepo.findById(i).get().getPassword();
                if (password.equals(p)) {
                    b = 1;
                }
            }
        }
        return b;
    }

    // 
}
