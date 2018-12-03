/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.spring.controller;

import ci.spring.domain.Beneficiaire;
import ci.spring.service.IBeneficiaireService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author sglo
 */
@Controller
@RequestMapping(value = "/benef")
public class BenefController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BenefController.class);

    @Autowired
    IBeneficiaireService beneficiaireService;

    @RequestMapping(value = "/ajoutform")
    public String pageAjouter(Model model) {

        model.addAttribute("beneficiaire", new Beneficiaire());
        return "ajoutform";
    }

    @PostMapping(value = "/ajouter")
    public String saveBenef(Beneficiaire beneficiaire) {
        this.logger.info("acces à la methode 'saveBenef' ");

        this.beneficiaireService.add(beneficiaire);

        this.logger.info("*** show benef id: " + beneficiaire.getCodeB());
        return "redirect:/benef/afficher/" + beneficiaire.getCodeB();
    }

//    @PreAuthorize("hasRole('ADMIN') ")
    @PostMapping(value = "/update")
    public String updateBenef(Beneficiaire b, Model model) {
        model.addAttribute("beneficiaire", this.beneficiaireService.getById(b.getCodeB()));
        return "ajoutform";
    }

//    @PreAuthorize("hasAnyRole('USER','ADMIN') ")
    @GetMapping(value = "/afficher/{id}")
    public String afficherUnBenef(@PathVariable Integer id, Model model) {
        model.addAttribute("benefAffiche", this.beneficiaireService.getById(id));
        return "afficher-un";
    }

    @GetMapping(value = "/editer/{id}")
    public String editer(@PathVariable Integer id, Model model) {
        model.addAttribute("beneficiaire", this.beneficiaireService.getById(id));
        return "ajoutform";
    }

    @GetMapping(value = "/lister")
    public String list(Model model) {
        model.addAttribute("listBeneficiaire", this.beneficiaireService.getAll());
        return "liste-benef";
    }

    @GetMapping(value = "/liste-non-a-jour")
    public String listNonAJour(Model model) {
        model.addAttribute("listBeneficiaire", this.beneficiaireService.getByPay(80000));
        return "index";
    }

}
