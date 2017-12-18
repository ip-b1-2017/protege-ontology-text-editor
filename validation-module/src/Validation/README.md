**Validation**

Team members:
1. Bogatu Ion
2. Chmilevski Monica
3. Coroș Ștefan
4. Gălățanu Andrei
5. Grigore Marius
6. Ivașcu Gabriela
7. Mazilu Florin
8. Rîșcanu Andrei

[ASSIGNED TASKS](https://docs.google.com/spreadsheets/d/101vOgeR3xXB2KoanKcJ7FK3xiMBlPWI2iqYFdDP_xeU/edit?usp=sharing)

We are the first ones in the whole process. We will get a path to a local or remote supported document and we will process it so we will transmit to the other modules only the information that might be useful. From the specification we will have to process 3 document types: PDF, DOC(X) and HTML.

After we consulted with the team we brought it down to 5 steps:
1. Extraction of the text that might be useful from the document and clean it from the garbage from each type of document (images, tags, links, etc.).
2. A general clean - after the text is extracted in the step above it will be cleaned from other garbage that can appear.
3. Calling the WebPosRo tool (http://nlptools.info.uaic.ro/WebPosRo) with the text processed above that will return an XML file that will split the text by words and will give us information about each of it (if it in Romanian and is correctly written), including the word as it appears in dictionary (what we will use).
4. XML processing and creation of a list of tuples with to string elements: the word as it appears in text and the word as it appears in dictionary (returned by WebPosRo tool).
5. Creating the API methods that can be called by the other modules.

[WORK DONE ASIDE WRITING CODE]
- Florin Mazilu -> Research
  - PDF
    - https://stackoverflow.com/questions/18098400/how-to-get-raw-text-from-pdf-file-using-java
    - https://pdfbox.apache.org/
  - DOCX
    - https://www.tutorialspoint.com/apache_poi_word/apache_poi_word_text_extraction.htm
  - HTML
    - https://jsoup.org/
