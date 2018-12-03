/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.spring.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author sglo
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Beneficiaire")
@Table(name = "BENEFICIAIRE")
public class Beneficiaire implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codeB;
    
    @Size(min = 3, message = "ce champs doit avoir plus de 3 caractères")
    @NotNull(message = "ce champ ne peux pas etre vide")
    private String nom;
    
    @NotNull
    private String prenom;
    
    private Integer solde;

    public Beneficiaire(String nom, String prenom, Integer solde)   {
        this.nom = nom;
        this.prenom = prenom;
        this.solde = solde;
    }

}
