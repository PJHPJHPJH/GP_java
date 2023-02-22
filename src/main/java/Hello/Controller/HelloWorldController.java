package Hello.Controller;

import org.apache.tomcat.util.http.parser.Cookie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

//解决跨域问题
@CrossOrigin(origins = "*")
@Controller
public class HelloWorldController {


    //测试1
    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }


    //测试2
    //查出一些数据，在页面展示
    @RequestMapping("/success")
    public String success(Map<String, Object> map) {
        map.put("hello", "你好");
        return "success";
    }


    //首页
    @RequestMapping("/index")
    public String index(Map<String, Object> map) {
        //业务处理
        return "index";
    }


    //访问BRP算法接口


    @RequestMapping("/BRP")
    public String BRP(Map<String, Object> map,
                      HttpServletResponse response) {
        //业务处理
        // 设置跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        return "BRP";
    }


}
