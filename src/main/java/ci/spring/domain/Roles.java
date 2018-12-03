/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.spring.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author sglo
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roles {
    
    @Id
    @Column(name = "role", unique = true)
    private String role;
    
}