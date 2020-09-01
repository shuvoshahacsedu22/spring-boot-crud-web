package com.tigerit.springbootcrudweb.controller;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceCmyk;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.colors.DeviceRgb;
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
import com.itextpdf.kernel.pdf.colorspace.PdfColorSpace;
import com.itextpdf.kernel.pdf.colorspace.PdfDeviceCs;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.ColumnDocumentRenderer;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.layout.font.FontSet;
import com.itextpdf.layout.property.*;
import com.itextpdf.licensekey.LicenseKey;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.tigerit.springbootcrudweb.model.Address;
import com.tigerit.springbootcrudweb.model.Employee;
import com.tigerit.springbootcrudweb.model.Voter;
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
import java.util.ArrayList;
import java.util.StringTokenizer;

@RestController
@RequestMapping("/voter")
public class VoterController {
    private  final String DEST = "D:/itex/sample.pdf";
    private static final String FONT = "D:/itex/fonts/kalpurush.ttf";
    private static final String IMG = "D:/itex/img.jpg";
    private static final String DATA = "D:/itex/united_states.csv";
    private final FontSet set = new FontSet();

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generateVoterListReport() throws IOException {
        ByteArrayInputStream bis =new ByteArrayInputStream(showListOfVoters().toByteArray());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=report.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
    private ByteArrayOutputStream showListOfVoters(){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfFont font=null;
        PdfFont bold = null;
        try {
            font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
            LicenseKey.loadLicenseFile("D:/itex/eula/itextkey1597310225949_0.xml");
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, new MyEventHandlerForVoterList());
            pdfDoc.addNewPage();
            Document document = new Document(pdfDoc);

            final FontSet set = new FontSet();
            set.addFont("D:/itex/fonts/OpenSans-Regular.ttf");
            set.addFont("D:/itex/fonts/kalpurush.ttf");
            document.setFontProvider(new FontProvider(set));
            document.setProperty(Property.FONT, new String[]{"MyFontFamilyName"});
            Paragraph p = new Paragraph("বাংলাদেশ নির্বাচন কমিশন \n ঢাকা, বাংলাদেশ।");
            p.setTextAlignment(TextAlignment.CENTER);
            document.add(p);
            Table table = new Table(new float[]{4, 4, 4, 4});
            table.setWidth(UnitValue.createPercentValue(100));
            java.util.List<Voter> voterList= new ArrayList<>();
            addHeaderToTable(table,bold);
            int i=0;
            while(i<100) {
                i++;
                voterList.add(new Voter("ভোটার ১", "voter 1"+i, "1", "101"));
            }
            i=0;
            while(i<100) {
                processCellOfVoter(table, voterList.get(i), font);
                i++;
            }
            document.add(table);
            document.close();

        }
        catch (Exception e){
        }
        return baos;
    }
    private void addHeaderToTable(Table table,PdfFont font){
        table.addHeaderCell(
                new Cell().add(
                        new Paragraph("Name(Bangla)")));
        table.addHeaderCell(
                new Cell().add(
                        new Paragraph("Name(English)")));
        table.addHeaderCell(
                new Cell().add(
                        new Paragraph("PIN")));
        table.addHeaderCell(
                new Cell().add(
                        new Paragraph("National ID")));
    }
    private void processCellOfVoter(Table table, Voter voter, PdfFont font) {

            table.addCell(
                    new Cell().add(
                            new Paragraph(voter.getNameBangla())));
            table.addCell(
                    new Cell().add(
                            new Paragraph(voter.getNameEnglish())));
            table.addCell(
                    new Cell().add(
                            new Paragraph(voter.getPin())));
            table.addCell(
                    new Cell().add(
                            new Paragraph(voter.getNationalId())));

    }

    @RequestMapping(value = "/details", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generateVoterDetailsReport() throws IOException {
        ByteArrayInputStream bis =new ByteArrayInputStream(showVoterDetails().toByteArray());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=details.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    private ByteArrayOutputStream showVoterDetails(){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfFont font=null;
        PdfFont bold = null;
        try {
            font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
            LicenseKey.loadLicenseFile("D:/itex/eula/itextkey1597310225949_0.xml");
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, new MyEventHandlerForVoterList());
            pdfDoc.addNewPage();
            Document document = new Document(pdfDoc);

            initiate:
            {
                Voter voter = new Voter();
                voter.setNationalId("2351482589");
                voter.setPin("19882924707154161");
                voter.setStatus("printed");
                voter.setAfisStatus("NO_MATCH");
                voter.setLockFlag("N");
                voter.setDeath("");
                voter.setVoterNo("261327624456");
                voter.setFormNo("154161");
                voter.setSlNo("3289");
                voter.setTag("old");
                voter.setNameBangla("এস এম, জুবােয়র");
                voter.setNameEnglish("S.M. Jubayer");
                voter.setDateOfBirth("1988-08-05");
                voter.setFatherName("মোঃ জহির উদ্দিন");
                voter.setMotherName("লুৎফুন্নাহার");
                voter.setGender("male");
                voter.setMarital("unmarried");
                voter.setOccupation("ছাত্র/ছাত্রী");
                voter.setPresentAddress(new Address());
                voter.getPresentAddress().setDivision("ঢাকা");
                voter.getPresentAddress().setDistrict("ঢাকা");
                voter.getPresentAddress().setRmo("9");
                voter.getPresentAddress().setCityCorporationOrMunicipility("ঢাকা দক্ষিণ সিটি কর্পোরেশন");
                voter.getPresentAddress().setUpozilla("ধানমন্ডি");
                voter.getPresentAddress().setAdditionalMouzaMoholla("প.আলীপুর");
                voter.getPresentAddress().setAdditionalVillageRoad("ইউসুফ সড়ক");
                voter.getPresentAddress().setHomeOrHoldingNo("বাং");
                voter.getPresentAddress().setPostOffice("সারুলিয়া");
                voter.getPresentAddress().setPostalCode("১৩৬১");
                voter.getPresentAddress().setRegion("ঢাকা");
                voter.setPermanentAddress(new Address());
                voter.getPermanentAddress().setDivision("ঢাকা");
                voter.getPermanentAddress().setDistrict("ফরিদপুর");
                voter.getPermanentAddress().setRmo("2");
                voter.getPermanentAddress().setUpozilla("ফরিদপুর সদর");
                voter.getPermanentAddress().setAdditionalMouzaMoholla("দ.আলীপুর");
                voter.getPermanentAddress().setAdditionalVillageRoad("ইউসুফ সড়ক");
                voter.getPermanentAddress().setHomeOrHoldingNo("৫৮");
                voter.getPermanentAddress().setPostOffice("দেলদুয়ার");
                voter.getPermanentAddress().setPostalCode("১২৩৪");
                voter.getPermanentAddress().setRegion("ফরিদপুর");
                voter.setPhone("০১৭১৯৬৪৪৭৬৭");
                voter.setReligion("muslim");
                voter.setNoFinger("0");
                voter.setNoFingerPrint("1");
                voter.setVoterArea("শংকর(261327)");
                voter.setVoterAt("present");
            }

            final FontSet set = new FontSet();
            set.addFont("D:/itex/fonts/OpenSans-Regular.ttf");
            set.addFont("D:/itex/fonts/kalpurush.ttf");
            document.setFontProvider(new FontProvider(set));
            document.setProperty(Property.FONT, new String[]{"MyFontFamilyName"});
            Paragraph p = new Paragraph("বাংলাদেশ নির্বাচন কমিশন \n ঢাকা, বাংলাদেশ।");
            p.setTextAlignment(TextAlignment.CENTER);
            document.add(p);

            //Table table = new Table(5);
            Table table = new Table(new float[]{4,3,3,3,3});
            table.setTextAlignment(TextAlignment.CENTER);
            table.setFont(font).setFontSize(10);
            table.setWidth(400);

            table.addCell(createCell("Examination", 1, 1, 1,TextAlignment.CENTER).setWidth(100));
            table.addCell(createCell("Board", 1, 1, 4,TextAlignment.CENTER).setWidth(75));
            table.addCell(createCell("Month and Year of Passing", 1, 1, 1,TextAlignment.CENTER).setWidth(75));
            table.addCell(createCell("", 1, 1, 4,TextAlignment.CENTER).setWidth(75));
            table.addCell(createCell("Marks", 1, 1, 1,TextAlignment.CENTER).setWidth(75));
            table.addCell(createCell("Percentage", 1, 1, 4,TextAlignment.CENTER));
            table.addCell(createCell("Class / Grade", 1, 1, 1,TextAlignment.CENTER));
            table.addCell(createCell("", 1, 1, 4,TextAlignment.CENTER));
            table.addCell(createCell("Obtained", 1, 1, 1,TextAlignment.CENTER));
            table.addCell(createCell("Out of", 1, 1, 4,TextAlignment.CENTER));
            table.addCell(createCell("12th / I.B. Diploma", 1, 1, 1,TextAlignment.CENTER));
            table.addCell(createCell("*", 1, 1, 4,TextAlignment.CENTER));
            table.addCell(createCell("g", 1, 1, 1,TextAlignment.CENTER));
            table.addCell(createCell("Aggregate (all subjects)", 1, 1, 4,TextAlignment.CENTER));
            table.addCell(createCell("*", 1, 1, 1,TextAlignment.CENTER));
            table.addCell(createCell("g", 1, 1, 4,TextAlignment.CENTER));
            table.addCell(createCell("g", 1, 1, 1,TextAlignment.CENTER));
            table.addCell(createCell("g", 1, 1, 4,TextAlignment.CENTER));

            table.addCell(createCell("g", 1, 4, 1,TextAlignment.CENTER));

            table.addCell(createCell("g", 1, 1, 1,TextAlignment.CENTER));
            table.addCell(createCell("g", 1, 1, 1,TextAlignment.CENTER));
            table.addCell(createCell("g", 1, 1, 1,TextAlignment.CENTER));
            table.addCell(createCell("g", 1, 1, 1,TextAlignment.CENTER));

            table.addCell(createCell("g", 1, 1, 1,TextAlignment.CENTER));
            table.addCell(createCell("g", 1, 1, 1,TextAlignment.CENTER));
            table.addCell(createCell("g", 1, 1, 1,TextAlignment.CENTER));
            table.addCell(createCell("g", 1, 1, 1,TextAlignment.CENTER));

            table.addCell(createCell("g", 1, 1, 1,TextAlignment.CENTER));
            table.addCell(createCell("g", 1, 1, 1,TextAlignment.CENTER));
            table.addCell(createCell("g", 1, 1, 1,TextAlignment.CENTER));
            table.addCell(createCell("g", 1, 1, 1,TextAlignment.CENTER));

            document.add(table);
            document.close();

        }
        catch (Exception e){
        }
        return baos;
    }

    protected class MyEventHandlerForVoterList implements IEventHandler{
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
            /*Color limeColor = new DeviceCmyk(0.208f, 0, 0.584f, 0);
            Color blueColor = new DeviceCmyk(0.445f, 0.0546f, 0, 0.0667f);
            pdfCanvas.saveState()
                    .setFillColor(pageNumber % 2 == 1 ? limeColor : blueColor)
                    .rectangle(pageSize.getLeft(), pageSize.getBottom(),
                            pageSize.getWidth(), pageSize.getHeight())
                    .fill().restoreState();*/

            //Add header and footer
            pdfCanvas.beginText()
                    .setFontAndSize(font, 9)
                    .moveText(pageSize.getWidth() / 2 - 60, pageSize.getTop() - 20)
                    .showText("")//header text
                    .moveText(60, -pageSize.getTop() + 30)
                    .showText(String.valueOf(pageNumber))//footer text
                    .endText();

            pdfCanvas.release();
        }
    }

    public Cell createCell(String content, float borderWidth,int rowspan, int colspan, TextAlignment alignment) {
        Cell cell = new Cell(rowspan, colspan).add(new Paragraph(content))
                .setTextAlignment(alignment);
        return cell;
    }

}
