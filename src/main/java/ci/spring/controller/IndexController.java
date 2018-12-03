/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.spring.controller;

import ci.spring.service.IBeneficiaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author sglo
 */
@Controller
public class IndexController {

    @Autowired
    IBeneficiaireService beneficiaireService;

//    @PreAuthorize("hasAnyRole('USER','ADMIN') ")
    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("listBeneficiaire", this.beneficiaireService.getByPay(80000));
        return "/index";
    }
    
    @GetMapping(value = "/login")
    public String login(Model model) {
        model.addAttribute("listBeneficiaire", this.beneficiaireService.getByPay(80000));
        return "index";
    }

}
