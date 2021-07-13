package gov.va.pie.cdw.model;

import org.springframework.data.annotation.Id;


public class PieProperty {

    @Id
    public String id;

    public String propertyName;
    public String propertyValue;
    
   /*
    * Intentionally blank
    */ 
    public PieProperty() {}

    public PieProperty(String propertyName, String propertyValue) {
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }

    @Override
    public String toString() {
        return String.format(
                "PieProperty[id=%s, firstName='%s', lastName='%s']",
                id, propertyValue, propertyValue);
    }
}
