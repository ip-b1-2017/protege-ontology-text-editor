
package Validation.pos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for parseSentence_TXT complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="parseSentence_TXT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rawSentenceInput" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parseSentence_TXT", propOrder = {
    "rawSentenceInput"
})
public class ParseSentenceTXT {

    protected String rawSentenceInput;

    /**
     * Gets the value of the rawSentenceInput property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRawSentenceInput() {
        return rawSentenceInput;
    }

    /**
     * Sets the value of the rawSentenceInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRawSentenceInput(String value) {
        this.rawSentenceInput = value;
    }

}
