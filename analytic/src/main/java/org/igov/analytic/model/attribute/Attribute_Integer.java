/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.igov.analytic.model.attribute;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.igov.model.core.AbstractEntity;

/**
 *
 * @author olga
 */
@javax.persistence.Entity
public class Attribute_Integer extends AbstractEntity{
    
    @JsonProperty(value = "nValue")
    @Column
    private Integer nValue;
    
    @JsonIgnore
    @OneToOne
    @Cascade({CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "nID_Attribute")
    Attribute oAttribute;
    
    public Integer getnValue() {
        return nValue;
    }

    public void setnValue(Integer nValue) {
        this.nValue = nValue;
    }
    
    public Attribute getoAttribute() {
        return oAttribute;
    }

    public void setoAttribute(Attribute oAttribute) {
        this.oAttribute = oAttribute;
    }
    
}
