
package Validation.pos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the Validation.pos package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ParseSentenceXML_QNAME = new QName("http://webPosRo.uaic/", "parseSentence_XML");
    private final static QName _ParseTextXML_QNAME = new QName("http://webPosRo.uaic/", "parseText_XML");
    private final static QName _ParseSentenceXMLResponse_QNAME = new QName("http://webPosRo.uaic/", "parseSentence_XMLResponse");
    private final static QName _ParseTextXMLResponse_QNAME = new QName("http://webPosRo.uaic/", "parseText_XMLResponse");
    private final static QName _ParseTextTXTResponse_QNAME = new QName("http://webPosRo.uaic/", "parseText_TXTResponse");
    private final static QName _ParseSentenceTXT_QNAME = new QName("http://webPosRo.uaic/", "parseSentence_TXT");
    private final static QName _ParseTextTXT_QNAME = new QName("http://webPosRo.uaic/", "parseText_TXT");
    private final static QName _ParseSentenceTXTResponse_QNAME = new QName("http://webPosRo.uaic/", "parseSentence_TXTResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: Validation.pos
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ParseSentenceTXTResponse }
     * 
     */
    public ParseSentenceTXTResponse createParseSentenceTXTResponse() {
        return new ParseSentenceTXTResponse();
    }

    /**
     * Create an instance of {@link ParseSentenceTXT }
     * 
     */
    public ParseSentenceTXT createParseSentenceTXT() {
        return new ParseSentenceTXT();
    }

    /**
     * Create an instance of {@link ParseTextTXT }
     * 
     */
    public ParseTextTXT createParseTextTXT() {
        return new ParseTextTXT();
    }

    /**
     * Create an instance of {@link ParseTextXMLResponse }
     * 
     */
    public ParseTextXMLResponse createParseTextXMLResponse() {
        return new ParseTextXMLResponse();
    }

    /**
     * Create an instance of {@link ParseTextTXTResponse }
     * 
     */
    public ParseTextTXTResponse createParseTextTXTResponse() {
        return new ParseTextTXTResponse();
    }

    /**
     * Create an instance of {@link ParseSentenceXML }
     * 
     */
    public ParseSentenceXML createParseSentenceXML() {
        return new ParseSentenceXML();
    }

    /**
     * Create an instance of {@link ParseTextXML }
     * 
     */
    public ParseTextXML createParseTextXML() {
        return new ParseTextXML();
    }

    /**
     * Create an instance of {@link ParseSentenceXMLResponse }
     * 
     */
    public ParseSentenceXMLResponse createParseSentenceXMLResponse() {
        return new ParseSentenceXMLResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseSentenceXML }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webPosRo.uaic/", name = "parseSentence_XML")
    public JAXBElement<ParseSentenceXML> createParseSentenceXML(ParseSentenceXML value) {
        return new JAXBElement<ParseSentenceXML>(_ParseSentenceXML_QNAME, ParseSentenceXML.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseTextXML }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webPosRo.uaic/", name = "parseText_XML")
    public JAXBElement<ParseTextXML> createParseTextXML(ParseTextXML value) {
        return new JAXBElement<ParseTextXML>(_ParseTextXML_QNAME, ParseTextXML.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseSentenceXMLResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webPosRo.uaic/", name = "parseSentence_XMLResponse")
    public JAXBElement<ParseSentenceXMLResponse> createParseSentenceXMLResponse(ParseSentenceXMLResponse value) {
        return new JAXBElement<ParseSentenceXMLResponse>(_ParseSentenceXMLResponse_QNAME, ParseSentenceXMLResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseTextXMLResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webPosRo.uaic/", name = "parseText_XMLResponse")
    public JAXBElement<ParseTextXMLResponse> createParseTextXMLResponse(ParseTextXMLResponse value) {
        return new JAXBElement<ParseTextXMLResponse>(_ParseTextXMLResponse_QNAME, ParseTextXMLResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseTextTXTResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webPosRo.uaic/", name = "parseText_TXTResponse")
    public JAXBElement<ParseTextTXTResponse> createParseTextTXTResponse(ParseTextTXTResponse value) {
        return new JAXBElement<ParseTextTXTResponse>(_ParseTextTXTResponse_QNAME, ParseTextTXTResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseSentenceTXT }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webPosRo.uaic/", name = "parseSentence_TXT")
    public JAXBElement<ParseSentenceTXT> createParseSentenceTXT(ParseSentenceTXT value) {
        return new JAXBElement<ParseSentenceTXT>(_ParseSentenceTXT_QNAME, ParseSentenceTXT.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseTextTXT }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webPosRo.uaic/", name = "parseText_TXT")
    public JAXBElement<ParseTextTXT> createParseTextTXT(ParseTextTXT value) {
        return new JAXBElement<ParseTextTXT>(_ParseTextTXT_QNAME, ParseTextTXT.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseSentenceTXTResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webPosRo.uaic/", name = "parseSentence_TXTResponse")
    public JAXBElement<ParseSentenceTXTResponse> createParseSentenceTXTResponse(ParseSentenceTXTResponse value) {
        return new JAXBElement<ParseSentenceTXTResponse>(_ParseSentenceTXTResponse_QNAME, ParseSentenceTXTResponse.class, null, value);
    }

}
