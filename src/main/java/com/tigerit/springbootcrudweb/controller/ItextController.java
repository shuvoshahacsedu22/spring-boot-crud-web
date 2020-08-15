package com.tigerit.springbootcrudweb.controller;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceCmyk;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.ColumnDocumentRenderer;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.layout.font.FontSet;
import com.itextpdf.layout.property.Property;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.licensekey.LicenseKey;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Node;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.MalformedURLException;
import java.util.StringTokenizer;

@RestController
@RequestMapping("/itext")
public class ItextController {
    private  final String DEST = "D:/itex/sample.pdf";
    private static final String FONT = "D:/itex/fonts/Bangla.ttf";
    private static final String IMG = "D:/itex/img.jpg";
    private static final String DATA = "D:/itex/united_states.csv";
    private final FontSet set = new FontSet();

    //single font pdf written
    @GetMapping("/home")
    public ModelAndView goHome() throws Exception
    {
        LicenseKey.loadLicenseFile("D:/itex/eula/itextkey1597310225949_0.xml");
        PdfWriter writer = new PdfWriter(DEST);
        PdfDocument pdfDoc = new PdfDocument(writer);
        pdfDoc.addNewPage();
        Document document = new Document(pdfDoc);

        String imFile = "D:/itex/img.jpg";
        ImageData data = ImageDataFactory.create(imFile);
        Image image = new Image(data);
        image.setFixedPosition(10,20);
        document.add(image);
        PdfFont f = PdfFontFactory.createFont(FONT, PdfEncodings.IDENTITY_H);
        String para1= "হ্যালো দেয়ার!";
        Paragraph paragraph =new Paragraph().add(new Text(para1).setFont(f));

        document.add(paragraph);
        document.close();
        return new ModelAndView("itext");
    }

    //multiple font pdf written
    @GetMapping("/homes")
    public ModelAndView go() throws  Exception{
        LicenseKey.loadLicenseFile("D:/itex/eula/itextkey1597310225949_0.xml");
        final String[] sources = {"english.xml","arabic.xml","bangla.xml"};
        final PdfWriter writer = new PdfWriter(DEST);
        final PdfDocument pdfDocument = new PdfDocument(writer);
        final Document document = new Document(pdfDocument);
        final FontSet set = new FontSet();
        set.addFont("D:/itex/fonts/NotoNaskhArabic-Regular.ttf");
        set.addFont("D:/itex/fonts/OpenSans-Regular.ttf");
        set.addFont("D:/itex/fonts/Bangla.ttf");
        document.setFontProvider(new FontProvider(set));
        document.setProperty(Property.FONT, new String[]{"MyFontFamilyName"});
        for (final String source : sources) {
        final File xmlFile = new File(source);
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final org.w3c.dom.Document doc = builder.parse(xmlFile);
        final Node element = doc.getElementsByTagName("text").item(0);
        final Paragraph paragraph = new Paragraph();
        final Node textDirectionElement = element.getAttributes().getNamedItem("direction");
        boolean rtl = textDirectionElement != null && textDirectionElement.getTextContent()
                .equalsIgnoreCase("rtl");
        if (rtl) {
            paragraph.setTextAlignment(TextAlignment.RIGHT);
        }
            System.out.println(element.getTextContent());
        paragraph.add(element.getTextContent());
        document.add(paragraph);
    }
        document.close();
        pdfDocument.close();
        writer.close();
        return new ModelAndView("itext");
}
    @RequestMapping(value = "/uploadGeneratedPdf", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generatePdfReport() throws IOException {
        ByteArrayInputStream bis =new ByteArrayInputStream(getItextChapter5PdfByteArrayOutputStream().toByteArray());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    private ByteArrayOutputStream getItextChapter1PdfByteArrayOutputStream(){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            LicenseKey.loadLicenseFile("D:/itex/eula/itextkey1597310225949_0.xml");
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            pdfDoc.addNewPage();
            Document document = new Document(pdfDoc);


            document.add(chapter1HelloWorld());
            document.add(chapter1Font());
            document.add(chapter1ListItem());
            document.add(chapter1Image());
            document.add(chapter1TableFromCSV());
            document.close();

        }
        catch (Exception e){
        }
        return baos;
    }
    private ByteArrayOutputStream getItextChapter2PdfByteArrayOutputStream(){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            LicenseKey.loadLicenseFile("D:/itex/eula/itextkey1597310225949_0.xml");
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            pdfDoc.addNewPage();
            Document document = new Document(pdfDoc);
            document.add(chapter1HelloWorld());



            document.close();

        }
        catch (Exception e){
        }
        return baos;
    }
    private ByteArrayOutputStream getItextChapter3PdfByteArrayOutputStream(){
        return chapter3EventHandlerWithHeaderFooter();
    }
    private ByteArrayOutputStream getItextChapter5PdfByteArrayOutputStream() throws IOException {
        return chapter5ManipulatingExistingPdf();
    }

   /*========================================================================
                       Chapter 1(Basic building blocks)
    ========================================================================*/
    private Paragraph chapter1HelloWorld(){

        return new Paragraph("Hello World");
    }
    private Paragraph chapter1Font() throws IOException {

        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        return new Paragraph("iText is:").setFont(font);
    }
    private List chapter1ListItem() throws IOException {

        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        List list = new List()
                .setSymbolIndent(12)
                .setListSymbol("\u2022")
                .setFont(font);
        list.add(new ListItem("Never gonna give you up"))
                .add(new ListItem("Never gonna let you down"))
                .add(new ListItem("Never gonna run around and desert you"))
                .add(new ListItem("Never gonna make you cry"))
                .add(new ListItem("Never gonna say goodbye"))
                .add(new ListItem("Never gonna tell a lie and hurt you"));
        return list;
    }
    private Paragraph chapter1Image() throws MalformedURLException {
        Image fox = new Image(ImageDataFactory.create(IMG));
        Image dog = new Image(ImageDataFactory.create(IMG));
        Paragraph p = new Paragraph("The quick brown ")
                .add(fox)
                .add(" jumps over the lazy ")
                .add(dog);
        return p;
    }
    private Table chapter1TableFromCSV() throws IOException {
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        Table table = new Table(new float[]{4, 1, 3, 4, 3, 3, 3, 3, 1});
        table.setWidth(UnitValue.createPercentValue(100));
        BufferedReader br = new BufferedReader(new FileReader(DATA));
        String line = br.readLine();
        process(table, line, bold, true);
        while ((line = br.readLine()) != null) {
            process(table, line, font, false);
        }
        return table;
    }
    public void process(Table table, String line, PdfFont font, boolean isHeader) {
        StringTokenizer tokenizer = new StringTokenizer(line, ";");
        while (tokenizer.hasMoreTokens()) {
            if (isHeader) {
                table.addHeaderCell(
                        new Cell().add(
                                new Paragraph(tokenizer.nextToken()).setFont(font)));
            } else {
                table.addCell(
                        new Cell().add(
                                new Paragraph(tokenizer.nextToken()).setFont(font)));
            }
        }
    }

     /*========================================================================
                       Chapter 2(Low level content)
      ========================================================================*/
      //complex not needed that much or can be work out later
     /*========================================================================
                       Chapter 3(Event handlers and page headers footers)
      ========================================================================*/
     public ByteArrayOutputStream chapter3EventHandlerWithHeaderFooter() {

         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         PdfFont font=null;
         PdfFont bold = null;
         try {
             font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
             bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
             LicenseKey.loadLicenseFile("D:/itex/eula/itextkey1597310225949_0.xml");
             PdfWriter writer = new PdfWriter(baos);
             PdfDocument pdfDoc = new PdfDocument(writer);
             pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, new MyEventHandler());
             pdfDoc.addNewPage();
             Document document = new Document(pdfDoc);
             document.add(chapter1TableFromCSV());
             document.close();

         }
         catch (Exception e){
         }
         return baos;
     }
     public ByteArrayOutputStream  chapter3MultipleColumn(){
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         try {
             LicenseKey.loadLicenseFile("D:/itex/eula/itextkey1597310225949_0.xml");
             PdfWriter writer = new PdfWriter(baos);
             PdfDocument pdfDoc = new PdfDocument(writer);
             PageSize ps = PageSize.A5;
             Document document = new Document(pdfDoc,ps);

             //Set column parameters
             float offSet = 36;
             float columnWidth = (ps.getWidth() - offSet * 2 + 10) / 3;
             float columnHeight = ps.getHeight() - offSet * 2;

             //Define column areas
             Rectangle[] columns = {
                     new Rectangle(offSet - 5, offSet, columnWidth, columnHeight),
                     new Rectangle(offSet + columnWidth, offSet, columnWidth, columnHeight),
                     new Rectangle(offSet + columnWidth * 2 + 5, offSet, columnWidth, columnHeight)
             };
             document.setRenderer(new ColumnDocumentRenderer(document, columns));


             Image inst = new Image(ImageDataFactory.create(IMG)).setWidth(columnWidth);
             String articleInstagram = "this is not the case";

             addArticleInColumn(document,
                     "Instagram May Change Your Feed, Personalizing It With an Algorithm",
                     "By MIKE ISAAC MARCH 15, 2016", inst, articleInstagram);

             document.add(new Paragraph("Sample text here is there"));
             document.close();

         }
         catch (Exception e){
         }
         return baos;
     }
     public static void addArticleInColumn(Document doc, String title, String author, Image img, String text) throws IOException {
            Paragraph p1 = new Paragraph().add(title);
            doc.add(p1);
            doc.add(img);
            Paragraph p2 = new Paragraph()
                    .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN))
                    .add(author);

            doc.add(p2);
            Paragraph p3 = new Paragraph().add(text);
            doc.add(p3);
        }
     protected class MyEventHandler implements IEventHandler{
        public void handleEvent(Event event) {
            PdfFont font = null;
            PdfFont bold = null;
            try {
                bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
                font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            } catch (IOException e) {
                e.printStackTrace();
            }

            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfDocument pdfDoc = docEvent.getDocument();
            PdfPage page = docEvent.getPage();
            int pageNumber = pdfDoc.getPageNumber(page);
            Rectangle pageSize = page.getPageSize();
            PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);

            //Set background
            Color limeColor = new DeviceCmyk(0.208f, 0, 0.584f, 0);
            Color blueColor = new DeviceCmyk(0.445f, 0.0546f, 0, 0.0667f);
            pdfCanvas.saveState()
                    .setFillColor(pageNumber % 2 == 1 ? limeColor : blueColor)
                    .rectangle(pageSize.getLeft(), pageSize.getBottom(),
                            pageSize.getWidth(), pageSize.getHeight())
                    .fill().restoreState();

            //Add header and footer
            pdfCanvas.beginText()
                    .setFontAndSize(font, 9)
                    .moveText(pageSize.getWidth() / 2 - 60, pageSize.getTop() - 20)
                    .showText("THE TRUTH IS OUT THERE")
                    .moveText(60, -pageSize.getTop() + 30)
                    .showText(String.valueOf(pageNumber))
                    .endText();

            pdfCanvas.release();
        }
    }

    /*========================================================================
                       Chapter 5(Manipulating existing pdf templates)
      ========================================================================*/
    public ByteArrayOutputStream chapter5ManipulatingExistingPdf() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String src=DEST;
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(baos));
        Document document = new Document(pdfDoc);
        document.add(new Paragraph("this is nicer than i thought of"));
        pdfDoc.close();
        return baos;
    }


}
