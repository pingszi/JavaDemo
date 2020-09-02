package cn.pings.module2;

import cn.pings.module1.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloController {

    @RequestMapping("/getUser")
    public String getUser(){
        User rst = User.getInstance();
        System.out.println(rst);
        return rst.toString();
    }
}
