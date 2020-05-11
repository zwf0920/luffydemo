package com.luffy.controller;

import com.alibaba.fastjson.JSONObject;
import com.luffy.Reposity.UserReposity;
import com.luffy.entity.Admin;
import com.luffy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserHandler {
    @Autowired
    private UserReposity userRepository;

    @GetMapping("/findAll/{page}/{size}")
    public Page<User> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        PageRequest request = PageRequest.of(page,size);
        return userRepository.findAll(request);
    }

    @PostMapping("/save")
    public String save(@RequestBody User user){
        User result = userRepository.save(user);
        if(result != null){
            return "success";
        }else{
            return "error";
        }
    }

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") Long id){
        return userRepository.findById(id).get();
    }

    @PutMapping("/update")
    public String update(@RequestBody User user){
        User result = userRepository.save(user);
        if(result != null){
            return "success";
        }else{
            return "error";
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Long id){
        userRepository.deleteById(id);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        JSONObject json = new JSONObject();
        json.put("flag",false);
        json.put("msg","系统错误");
        json.put("errorcode",333);
        User userReal =userRepository.findByUsername(user.getUsername());
        if(userReal==null){
            json.put("flag",false);
            json.put("msg","您的账号尚未注册");
            json.put("errorcode",111);
        }else{
            System.out.println(user.getPassword());
            System.out.println(userReal.getPassword());
            if(user.getPassword().equals(userReal.getPassword())){
                json.put("flag",true);
                json.put("msg","登陆成功");
                json.put("userid",userReal.getUserid());
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
    public String register(@RequestBody User user){
        JSONObject json = new JSONObject();
        json.put("flag",false);
        json.put("msg","系统错误");
        json.put("errorcode",333);
        User userReal =userRepository.findByUsername(user.getUsername());
        if(userReal!=null){
            json.put("flag",false);
            json.put("msg","您的账号已经注册，请使用新的账号注册");
            json.put("errorcode",111);


        }else{
            System.out.println(user);
            User result = userRepository.save(user);
            if(result != null){
                json.put("flag",true);
                json.put("msg","账号注册成功");
                json.put("errorcode",0);
            }

        }


        return json.toString();
    }
}
