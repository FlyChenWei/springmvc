package it.heima.controller;


import it.heima.domain.Role;
import it.heima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/hello")
    @ResponseBody
    public void test() {
        System.out.println("hello");
    }

    @RequestMapping("/list")
    public ModelAndView list(ModelAndView modelAndView) {
        System.out.println("com...");
        List<Role> roleList = roleService.list();
        //设置模型对象
        modelAndView.addObject("roleList", roleList);
        //设置视图
        modelAndView.setViewName("role-list");
        return modelAndView;
    }

    @RequestMapping("/save")
    public String save(Role role) {
        System.out.println("save方法");
        roleService.save(role);
        return "redirect:/role/list";
    }
}
