/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.spring.repository;

import ci.spring.domain.Beneficiaire;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sglo
 */
@Repository
public interface BeneficiaireRepository extends JpaRepository<Beneficiaire, Integer>{
    
    public List<Beneficiaire> findBySoldeGreaterThanEqual( Integer pay);
}
