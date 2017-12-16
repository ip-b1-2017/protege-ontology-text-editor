
package Validation.pos;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "PosTaggerRoWS", targetNamespace = "http://webPosRo.uaic/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PosTaggerRoWS {


    /**
     * 
     * @param rawSentenceInput
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "parseSentence_XML")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "parseSentence_XML", targetNamespace = "http://webPosRo.uaic/", className = "Validation.pos.ParseSentenceXML")
    @ResponseWrapper(localName = "parseSentence_XMLResponse", targetNamespace = "http://webPosRo.uaic/", className = "Validation.pos.ParseSentenceXMLResponse")
    @Action(input = "http://webPosRo.uaic/PosTaggerRoWS/parseSentence_XMLRequest", output = "http://webPosRo.uaic/PosTaggerRoWS/parseSentence_XMLResponse")
    public String parseSentenceXML(
        @WebParam(name = "rawSentenceInput", targetNamespace = "")
        String rawSentenceInput);

    /**
     * 
     * @param rawTextInput
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "parseText_XML")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "parseText_XML", targetNamespace = "http://webPosRo.uaic/", className = "Validation.pos.ParseTextXML")
    @ResponseWrapper(localName = "parseText_XMLResponse", targetNamespace = "http://webPosRo.uaic/", className = "Validation.pos.ParseTextXMLResponse")
    @Action(input = "http://webPosRo.uaic/PosTaggerRoWS/parseText_XMLRequest", output = "http://webPosRo.uaic/PosTaggerRoWS/parseText_XMLResponse")
    public String parseTextXML(
        @WebParam(name = "rawTextInput", targetNamespace = "")
        String rawTextInput);

    /**
     * 
     * @param rawSentenceInput
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "parseSentence_TXT")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "parseSentence_TXT", targetNamespace = "http://webPosRo.uaic/", className = "Validation.pos.ParseSentenceTXT")
    @ResponseWrapper(localName = "parseSentence_TXTResponse", targetNamespace = "http://webPosRo.uaic/", className = "Validation.pos.ParseSentenceTXTResponse")
    @Action(input = "http://webPosRo.uaic/PosTaggerRoWS/parseSentence_TXTRequest", output = "http://webPosRo.uaic/PosTaggerRoWS/parseSentence_TXTResponse")
    public String parseSentenceTXT(
        @WebParam(name = "rawSentenceInput", targetNamespace = "")
        String rawSentenceInput);

    /**
     * 
     * @param rawTextInput
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "parseText_TXT")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "parseText_TXT", targetNamespace = "http://webPosRo.uaic/", className = "Validation.pos.ParseTextTXT")
    @ResponseWrapper(localName = "parseText_TXTResponse", targetNamespace = "http://webPosRo.uaic/", className = "Validation.pos.ParseTextTXTResponse")
    @Action(input = "http://webPosRo.uaic/PosTaggerRoWS/parseText_TXTRequest", output = "http://webPosRo.uaic/PosTaggerRoWS/parseText_TXTResponse")
    public String parseTextTXT(
        @WebParam(name = "rawTextInput", targetNamespace = "")
        String rawTextInput);

}
