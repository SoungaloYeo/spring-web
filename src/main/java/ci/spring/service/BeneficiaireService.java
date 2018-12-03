/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.spring.service;

import ci.spring.domain.Beneficiaire;
import ci.spring.repository.BeneficiaireRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sglo
 */
@Service
public class BeneficiaireService implements IBeneficiaireService {
    
    private BeneficiaireRepository beneficiaireRepository;

    @Autowired
    public void setBeneficiaireRepository(BeneficiaireRepository beneficiaireRepository) {
        this.beneficiaireRepository = beneficiaireRepository;
    }

    @Override
    public Beneficiaire add(Beneficiaire b) {
            return this.beneficiaireRepository.save(b);
    }

    @Override
    public Optional<Beneficiaire> getById(Integer id) {
            return this.beneficiaireRepository.findById(id);
    }

    @Override
    public Beneficiaire update(Beneficiaire b) {
            return this.beneficiaireRepository.save(b);
    }

    @Override
    public List<Beneficiaire> getByPay(Integer pay) {
            return this.beneficiaireRepository.findBySoldeGreaterThanEqual(pay);
    }

    @Override
    public List<Beneficiaire> getAll() {
            return this.beneficiaireRepository.findAll();
    }
    
           
    
}
