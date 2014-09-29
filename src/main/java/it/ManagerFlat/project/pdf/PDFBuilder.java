package it.ManagerFlat.project.pdf;

import it.ManagerFlat.project.service.ManagerFlat;
import it.ManagerFlat.project.util.StanzaConsumi;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * This view class generates a PDF document 'on the fly' based on the data
 * contained in the model.
 * 
 * @author www.codejava.net
 * 
 */
public class PDFBuilder extends AbstractITextPdfView {
	
	ManagerFlat manager = new ManagerFlat();

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc,
			PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy ");
		Date date = new Date();
		// OutputStream file = new FileOutputStream(new
		// File("C:\\prova\\PDF_Java4s.pdf"));
		// PdfWriter.getInstance(doc, file);
		// get data model which is passed by the Spring container
		StanzaConsumi consumi = (StanzaConsumi) model
				.get("consumoStanza");

		Paragraph dateLetture = new Paragraph("Consumi dal " + consumi.getDataInizio() + " al "+ consumi.getDataFine()); 
		dateLetture.setAlignment(Element.ALIGN_CENTER);
		dateLetture.setSpacingAfter(25);
		doc.add(dateLetture);

		Paragraph preface = new Paragraph("Stanza "+consumi.getNumero()); 
		preface.setAlignment(Element.ALIGN_CENTER);
		preface.setSpacingAfter(25);
		doc.add(preface);
		doc.add(new Paragraph("Acqua"));
		
//		Paragraph total = new Paragraph("TOTALE EURO________________________________________________________"+String.valueOf((float)Math.round((consumi.getConsumoAcquaStanza()+consumi.getConsumoAcquaStanzaComune()+consumi.getConsumoEnergiaStanza()+consumi.getConsumoEnergiaStanzaComune()+consumi.getConsumoGasStanza()+consumi.getConsumoGasStanzaComune())*100)/100); 

		Paragraph total = new Paragraph("TOTALE EURO________________________________________________________"+(consumi.getConsumoAcquaStanza()+consumi.getConsumoAcquaStanzaComune()+consumi.getConsumoEnergiaStanza()+consumi.getConsumoEnergiaStanzaComune()+consumi.getConsumoGasStanza()+consumi.getConsumoGasStanzaComune())); 
		
		
		
		PdfPTable tableAcqua = createTable(consumi, "Acqua");

		PdfPTable tableEnergia = createTable(consumi, "Energia");

		PdfPTable tableGas = createTable(consumi, "Gas");

		try {

			doc.add(tableAcqua);
			doc.add(new Paragraph("Energia"));
			doc.add(tableEnergia);
			doc.add(new Paragraph("Gas"));
			doc.add(tableGas);
			doc.add(total);
		} catch (DocumentException s) {
			// TODO Auto-generated catch block
			
		}
		// file.close();
	}

	
	private PdfPTable createTable(StanzaConsumi consumi, String string) {
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100.0f);
		try {
			table.setWidths(new float[] { 2.0f, 2.0f, 2.0f, 2.0f, 1.0f });
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.setSpacingBefore(50);
		table.setSpacingAfter(50);

		// define font for table header row
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(BaseColor.WHITE);

		// define table header cell
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);

		// write table header
		cell.setPhrase(new Phrase("", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Unita di misura", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Prezzi unitari", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Quantita", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Totale Euro", font));
		table.addCell(cell);

		// write table row data

		table.addCell("Stanza");

		
		// Acqua
		if (string.equals("Acqua")) {
			table.addCell("euro/metro cubo");
			table.addCell(Float.toString(manager.getParametro(new Long(3)).getValore()));
			table.addCell(Float.toString((float)Math.round(consumi.getQuantitaAcquaStanza()*100)/100));
			table.addCell(Float.toString((float)Math.round(consumi.getConsumoAcquaStanza()*100)/100));
		}
		if (string.equals("Energia")) {
			table.addCell("euro/kWh");
			table.addCell(Float.toString(manager.getParametro(new Long(1)).getValore()));
			table.addCell(Float.toString((float)Math.round(consumi.getQuantitaEnergiaStanza()*100)/100));
			table.addCell(Float.toString((float)Math.round(consumi.getConsumoEnergiaStanza()*100)/100));
		}
		if (string.equals("Gas")) {
			table.addCell("euro/litri");
			table.addCell(Float.toString(manager.getParametro(new Long(2)).getValore()));
			table.addCell(Float.toString((float)Math.round(consumi.getQuantitaGasStanza()*100)/100));
			table.addCell(Float.toString((float)Math.round(consumi.getConsumoGasStanza()*100)/100));
		}
		table.addCell("Stanza Comune");
		if (string.equals("Acqua")) {
			table.addCell("euro/metro cubo");
			table.addCell(Float.toString(manager.getParametro(new Long(3)).getValore()));
			table.addCell(Float.toString((float)Math.round(consumi.getQuantitaAcquaStanzaComune()*100)/100));
			table.addCell(String.valueOf((float)Math.round(consumi.getConsumoAcquaStanzaComune()*100)/100));
		}
		if (string.equals("Energia")) {
			table.addCell("euro/kWh");
			table.addCell(Float.toString(manager.getParametro(new Long(1)).getValore()));
			table.addCell(Float.toString((float)Math.round(consumi
					.getQuantitaEnergiaStanzaComune()*100)/100));
			table.addCell(String.valueOf((float)Math.round(consumi
					.getConsumoEnergiaStanzaComune()*100)/100));
		}
		if (string.equals("Gas")) {
			table.addCell("euro/litri");
			table.addCell(Float.toString(manager.getParametro(new Long(2)).getValore()));
			table.addCell(Float.toString((float)Math.round(consumi.getQuantitaGasStanzaComune()*100)/100));
			table.addCell(Float.toString((float)Math.round(consumi.getConsumoGasStanzaComune()*100)/100));
		}
		
		
		
		return table;
	}
}