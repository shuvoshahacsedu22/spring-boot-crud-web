package com.tigerit.springbootcrudweb.controller;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.layout.font.FontSet;
import com.itextpdf.layout.property.Property;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.licensekey.LicenseKey;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Node;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("/itext")
public class ItextController extends HttpServlet {
    private  final String DEST = "D:/itex/sample.pdf";
    private static final String FONT = "D:/itex/fonts/Bangla.ttf";
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


//frist way
    @GetMapping(value = "/uploadGeneratedPdf")
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            LicenseKey.loadLicenseFile("D:/itex/eula/itextkey1597310225949_0.xml");
            PdfWriter writer = new PdfWriter(baos);
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

        }
        catch (Exception e){
        }
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control",
                "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setContentType("application/pdf");
        response.setContentLength(baos.size());
        OutputStream os = response.getOutputStream();
        baos.writeTo(os);
        baos.toByteArray();
        os.flush();
        os.close();
    }

}
