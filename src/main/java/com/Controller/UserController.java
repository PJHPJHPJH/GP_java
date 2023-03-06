package com.Controller;

import com.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

//解决跨域问题
@CrossOrigin(origins = "*")
@Controller
public class UserController {

    @Autowired
    public UserService userService;


    //测试1
//    @ResponseBody
//    @RequestMapping("/hello")
//    public String hello() {
//        return "Hello World!";
//    }


    //测试2
    //查出一些数据，在页面展示
//    @RequestMapping("/success")
//    public String success(Map<String, Object> map) {
//        map.put("hello", "你好");
//        return "success";
//    }


    //首页
//    @RequestMapping("/index")
//    public String index(Map<String, Object> map) {
//        //业务处理
//        return "index";
//    }


    //访问BRP算法接口
    @RequestMapping("/BRP")
    public String BRP(Map<String, Object> map,
                      HttpServletResponse response) {
        //业务处理
        // 设置跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        return "BRP";
    }

    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestBody Map<String,Object> params) {

        String userName = (String) params.get("name");
        String inputPassword = (String) params.get("password");


        System.out.println(userName);
        System.out.println(inputPassword);

        String userPassword = userService.getUserPassword(userName);
        if (userPassword.equals(inputPassword)) {
            return "true";
        }
        return "false";

    }


    @ResponseBody
    @PostMapping("/register")
    public String register(@RequestBody Map<String,Object> params) {

        String userName = (String) params.get("name");
        String inputPassword = (String) params.get("password");
        String repeatPassword = (String) params.get("repeatPassword");
        String userEmail = (String) params.get("email");
        String userPhone = (String) params.get("phone");


        //进行合法性检查
        //检查是否重名
        //待完成     重复邮箱和重复手机号
        String repeatName = userService.getUserName(userName);
        String repeatEmail = null;
        String repeatPhone = null;
        if (!userEmail.equals("")) {
            repeatEmail = userService.getUserEmail(userEmail);
        }
        if (!userPhone.equals("")) {
            repeatPhone = userService.getUserPhone(userPhone);
        }
        //如果不重名,则进行添加
        if (repeatName == null && repeatEmail == null && repeatPhone == null && inputPassword.equals(repeatPassword)) {
            Integer returnId = userService.addUser(userName, inputPassword, userEmail, userPhone);
            // 返回值为1为成功，返回值为0为失败
            if (returnId == 1) {
                return "true";
            }
            else return "false";
        }
        return "false";
    }


    //重名检查接口
    @ResponseBody
    @PostMapping("/repeatName")
    public String repeatName(@RequestBody Map<String,Object> params) {

        String userName = (String) params.get("name");
        //进行合法性检查
        //检查是否重名
        String repeatName = userService.getUserName(userName);
        //如果不重名,则返回成功
        if (userName.equals(repeatName)) {
            return "true";
        }
        else {
            return "false";
        }
    }


    //重复邮箱检查接口
    @ResponseBody
    @PostMapping("/repeatEmail")
    public String repeatEmail(@RequestBody Map<String,Object> params) {

        String userEmail = (String) params.get("email");
        //进行合法性检查
        //检查是否重复邮箱
        String repeatEmail = userService.getUserEmail(userEmail);
        //如果不重复邮箱,则返回成功
        if (userEmail.equals(repeatEmail)) {
            return "true";
        }
        else {
            return "false";
        }
    }




    //重复手机号检查接口
    @ResponseBody
    @PostMapping("/repeatPhone")
    public String repeatPhone(@RequestBody Map<String,Object> params) {

        String userPhone = (String) params.get("phone");
        //进行合法性检查
        //检查是否重复手机号
        String repeatPhone = userService.getUserPhone(userPhone);
        //如果不重复手机号,则返回成功
        if (userPhone.equals(repeatPhone)) {
            return "true";
        }
        else {
            return "false";
        }
    }

}
