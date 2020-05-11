package com.luffy.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.luffy.Reposity.AdminReposity;
import com.luffy.Reposity.RoleReposity;
import com.luffy.entity.Admin;
import com.luffy.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminHandler {
    @Autowired
    private AdminReposity adminRepository;

    @GetMapping("/findAll/{page}/{size}")
    public Page<Admin> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        PageRequest request = PageRequest.of(page,size);
        return adminRepository.findAll(request);
    }

    @PostMapping("/save")
    public String save(@RequestBody Admin admin){

        Admin result = adminRepository.save(admin);
        if(result != null){
            return "success";
        }else{
            return "error";
        }
    }

    @GetMapping("/findById/{id}")
    public Admin findById(@PathVariable("id") Long id){
        return adminRepository.findById(id).get();
    }

    @PostMapping("/login")
    public String login(@RequestBody Admin admin){
        JSONObject json = new JSONObject();
        json.put("flag",false);
        json.put("msg","系统错误");
        json.put("errorcode",333);
        System.out.println(admin);
        Admin adminReal =adminRepository.findByUsername(admin.getUsername());
        if(adminReal==null){
            json.put("flag",false);
            json.put("msg","您的账号尚未注册");
            json.put("errorcode",111);
        }else{
            System.out.println(admin.getPassword());
            System.out.println(adminReal.getPassword());
            if(admin.getPassword().equals(adminReal.getPassword())){
                json.put("flag",true);
                json.put("msg","登陆成功");
                json.put("errorcode",0);
            }else{
                json.put("flag",false);
                json.put("msg","密码错误");
                json.put("errorcode",222);
            }

        }




        return json.toString();
    }

    @PostMapping("/register")
    public String register(@RequestBody Admin admin){
        JSONObject json = new JSONObject();
        json.put("flag",false);
        json.put("msg","系统错误");
        json.put("errorcode",333);
        Admin adminReal =adminRepository.findByUsername(admin.getUsername());
        if(adminReal!=null){
                json.put("flag",false);
                json.put("msg","您的账号已经注册，请使用新的账号注册");
                json.put("errorcode",111);


        }else{
            System.out.println(admin);
            Admin result = adminRepository.save(admin);
            if(result != null){
                json.put("flag",true);
                json.put("msg","账号注册成功");
                json.put("errorcode",0);
            }

        }


        return json.toString();
    }
    @PutMapping("/update")
    public String update(@RequestBody Admin admin){
        Admin result = adminRepository.save(admin);
        if(result != null){
            return "success";
        }else{
            return "error";
        }
    }


    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Long id){
        adminRepository.deleteById(id);
    }
}
