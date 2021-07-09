package it.heima.controller;

import it.heima.domain.Role;
import it.heima.domain.User;
import it.heima.service.RoleService;
import it.heima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/list")
    public ModelAndView list(ModelAndView modelAndView) {
        List<User> list = userService.list();
        modelAndView.addObject("userList", list);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    @RequestMapping("/saveUI")
    public ModelAndView saveUI() {
        List<Role> list = roleService.list();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", list);
        modelAndView.setViewName("user-add");
        return modelAndView;
    }

    @RequestMapping("/save")
    public String save(User user, Long roleIds[]) {
        Long userId = userService.saveUser(user);
        userService.saveRelation(userId, roleIds);
        return "redirect:/user/list";
    }

    @Transactional
    @RequestMapping("/delUser/{userId}")
    public String delUser(@PathVariable("userId") Long userId) {
        System.out.println(userId);
        userService.delUserRelation(userId);
        userService.delUser(userId);
        return "redirect:/user/list";
    }

    @RequestMapping("/login")
    public String login(User user, HttpSession session) {
        System.out.println("what");
        //1.查询数据是否存在该用户
        Boolean flag=userService.login(user);
        System.out.println(flag);
        if(flag){
            session.setAttribute("user", user);
            System.out.println("跳转");
            return "redirect:/index.jsp";
        }
        return "redirect:/login.jsp";
    }
}
