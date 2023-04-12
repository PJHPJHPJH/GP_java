package com.Controller;

import com.Entity.User;
import com.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    //测试接口，可删除
    @RequestMapping("/BRP")
    public String BRP(Map<String, Object> map,
                      HttpServletResponse response) {
        //业务处理
        // 设置跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        return "BRP";
    }


    /**
     * 前台业务
     * @param params
     * @return
     */

    //登录功能实现
    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestBody Map<String,Object> params) {

        String userName = (String) params.get("name");
        String inputPassword = (String) params.get("password");


        System.out.println(userName);
        System.out.println(inputPassword);

        String userPassword = userService.getUserPasswordByName(userName);
        if (userPassword != null && userPassword.equals(inputPassword)) {
            Integer userId = userService.getUserIdByName(userName);
            return Integer.toString(userId);
        }
        return "false";
    }


    //注册功能实现
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
        String repeatName = userService.getUserNameByName(userName);
        String repeatEmail = null;
        String repeatPhone = null;
        if (!userEmail.equals("")) {
            repeatEmail = userService.getUserEmailByEmail(userEmail);
        }
        if (!userPhone.equals("")) {
            repeatPhone = userService.getUserPhoneByPhone(userPhone);
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
    public String repeatName(@RequestBody Map<String, Object> params) {

        String userName = (String) params.get("name");
        //进行合法性检查
        //检查是否重名
        String repeatName = userService.getUserNameByName(userName);
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
        String repeatEmail = userService.getUserEmailByEmail(userEmail);
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
        String repeatPhone = userService.getUserPhoneByPhone(userPhone);
        //如果不重复手机号,则返回成功
        if (userPhone.equals(repeatPhone)) {
            return "true";
        }
        else {
            return "false";
        }
    }

    //忘记密码：邮箱验证接口
    @ResponseBody
    @PostMapping("/emailValidation")
    public String emailValidation(@RequestBody Map<String,Object> params) {
        String userName = (String) params.get("name");
        String userEmail = (String) params.get("email");

        String validationEmail = userService.getUserEmailByName(userName);

        if(userEmail.equals(validationEmail)) {
            return "true";
        }
        else return "false";

    }
    //忘记密码：手机号验证接口
    @ResponseBody
    @PostMapping("/phoneValidation")
    public String phoneValidation(@RequestBody Map<String,Object> params) {
        String userName = (String) params.get("name");
        String userPhone = (String) params.get("phone");
        String validationPhone = userService.getUserPhoneByName(userName);
        if(userPhone.equals(validationPhone)) {
            return "true";
        }
        else return "false";
    }


    //忘记密码：修改用户密码
    @ResponseBody
    @PostMapping("/forget")
    public String forget(@RequestBody Map<String,Object> params) {
        String userName = (String) params.get("name");
        String newPassword = (String) params.get("password");

        //修改密码
        if(userService.updateUserPasswordByName(userName, newPassword) != 0){
            return "true";
        }

        return "false";

    }

    @ResponseBody
    @PostMapping("/getUserInformation")
    public String getUserInformation(@RequestBody Map<String, Object> map) {
        //获得用户的id
        Integer userId = Integer.parseInt((String) map.get("id"));
        User user = userService.getUserByUserId(userId);
        user.setUserId(userId);
        //将密码处理成密文
        String password = user.getUserPassword();
        String newPassword = "";
        for (int i = 0; i < password.length(); i++){
            newPassword += "*";
        }
        user.setUserPassword(newPassword);
        //将数据进行JSON封装
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //测试数据输出
//        System.out.println(json);
        return json;
    }



    //修改用户名
    @ResponseBody
    @PostMapping("/updateName")
    public String updateName(@RequestBody Map<String, Object> map) {
        Integer userId = Integer.parseInt((String) map.get("id"));
        String userName = (String) map.get("name");
        String userPassword = (String) map.get("password");
        //将输入密码与数据库中的密码进行验证
        if(userPassword.equals(userService.getUserPasswordById(userId))){
            if(userService.updateUserNameById(userId, userName) != 0){
                return "true";
            }
        }
        return "false";
    }




    //修改用户邮箱
    @ResponseBody
    @PostMapping("/updateEmail")
    public String updateEmail(@RequestBody Map<String, Object> map) {
        Integer userId = Integer.parseInt((String) map.get("id"));
        String userPassword = (String) map.get("password");
        String userEmail = (String) map.get("email");
        //将输入密码与数据库中的密码进行验证
        if(userPassword.equals(userService.getUserPasswordById(userId))){
            if(userService.updateUserEmailById(userId, userEmail) != 0){
                return "true";
            }
        }
        return "false";
    }

    //修改用户手机号
    @ResponseBody
    @PostMapping("/updatePhone")
    public String updatePhone(@RequestBody Map<String, Object> map) {
        Integer userId = Integer.parseInt((String) map.get("id"));
        String userPassword = (String) map.get("password");
        String userPhone = (String) map.get("phone");
        //将输入密码与数据库中的密码进行验证
        if(userPassword.equals(userService.getUserPasswordById(userId))){
            if(userService.updateUserPhoneById(userId, userPhone) != 0){
                return "true";
            }
        }
        return "false";
    }









    /**
     * 后台业务
     */

    //返回所有用户的信息
    @ResponseBody
    @GetMapping("/getUsers")
    public String getUsers() {
        User[] users = userService.getUsers();
        //将数据进行JSON封装
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(users);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(json);
        return json;
    }

}
