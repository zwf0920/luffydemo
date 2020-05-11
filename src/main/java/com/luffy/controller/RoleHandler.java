package com.luffy.controller;

import com.alibaba.fastjson.JSONObject;
import com.luffy.Reposity.RoleReposity;
import com.luffy.Reposity.UserReposity;
import com.luffy.entity.Role;
import com.luffy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;

@RestController
@RequestMapping("/role")
public class RoleHandler {
    @Autowired
    private RoleReposity roleRepository;

    @Autowired
    private UserReposity userRepository;

    @GetMapping("/findAll/{page}/{size}")
    public Page<Role> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        PageRequest request = PageRequest.of(page,size);
        return roleRepository.findAll(request);
    }

    @PostMapping("/save")
    public String save(@RequestBody Role role){
        Role result = roleRepository.save(role);
        if(result != null){
            return "success";
        }else{
            return "error";
        }
    }

    @GetMapping("/findById/{id}")
    public Role findById(@PathVariable("id") Long id){
        return roleRepository.findById(id).get();
    }

    @PutMapping("/update")
    public String update(@RequestBody Role role){
        role.setPicpath(role.getPicpath().replace("src/main/resources/static","static"));
        Role result = roleRepository.save(role);
        if(result != null){
            return "success";
        }else{
            return "error";
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Long id){
        roleRepository.deleteById(id);
    }

    @RequestMapping("/addImage")
    @ResponseBody
    public String addImage(@RequestParam(name = "image_data", required = false) MultipartFile file) {
        //文件上传
        //图片命名
        //String newCompanyImageName = DateUtil.getDateFromDate(new Date(), "yyyyMMdd").toString()+".jpg";
        String newCompanyImageName = (new Date()).getTime()+".jpg";
        String newCompanyImagepath = "src/main/resources/static/upload/images/"+newCompanyImageName;
        if (!file.isEmpty()) {
            try {

                File newFile = new File(newCompanyImagepath);
                if (!newFile.exists()) {
                    newFile.createNewFile();
                }
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(newFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "图片上传失败！";
            } catch (IOException e) {
                e.printStackTrace();
                return "图片上传失败！";
            }
        }
        return newCompanyImagepath;
    }

    @GetMapping("/vote/{roleid}/{userid}")
    public String vote(@PathVariable("roleid") Long roleid, @PathVariable("userid") Long userid){
        JSONObject json = new JSONObject();
        json.put("flag",false);
        json.put("msg","投票失败");
        Role role = roleRepository.findByRoleid(roleid);
        User user = userRepository.findByUserid(userid);
        if(user.getVotes()>0){
            role.setAllvotes(role.getAllvotes()+1);
            roleRepository.save(role);
            user.setVotes(user.getVotes()-1);
            userRepository.save(user);
            json.put("flag",true);
            json.put("msg","投票成功");
        }
        return json.toString();
    }

}
