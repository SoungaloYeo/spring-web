/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.spring.service;

import ci.spring.domain.Beneficiaire;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author sglo
 */
public interface IBeneficiaireService {
    
    public Beneficiaire add(Beneficiaire b);
    
    public Optional<Beneficiaire> getById(Integer id);
    
    public Beneficiaire update(Beneficiaire b);
    
    public List<Beneficiaire> getByPay(Integer pay);
    
    public List<Beneficiaire> getAll();
    
}
