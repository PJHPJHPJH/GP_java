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


        //进行合法性检查

        //检查是否重名
        String repeatName = userService.getUserName(userName);
        //如果不重名,则进行添加
        if (repeatName == null && inputPassword.equals(repeatPassword)) {
            Integer returnId = userService.addUser(userName, inputPassword);
            // 返回值为1为成功，返回值为0为失败
            if (returnId == 1) {
                return "true";
            }
            else return "false";
        }
        return "false";

    }


}
