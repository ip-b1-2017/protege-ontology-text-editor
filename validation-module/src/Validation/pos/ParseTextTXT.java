
package Validation.pos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for parseText_TXT complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="parseText_TXT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rawTextInput" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parseText_TXT", propOrder = {
    "rawTextInput"
})
public class ParseTextTXT {

    protected String rawTextInput;

    /**
     * Gets the value of the rawTextInput property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRawTextInput() {
        return rawTextInput;
    }

    /**
     * Sets the value of the rawTextInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRawTextInput(String value) {
        this.rawTextInput = value;
    }

}
