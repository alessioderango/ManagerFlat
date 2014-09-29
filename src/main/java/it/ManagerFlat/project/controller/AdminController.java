package it.ManagerFlat.project.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import it.ManagerFlat.project.dao.AdminDAO;
import it.ManagerFlat.project.daoimpl.AdminDAOImpl;
import it.ManagerFlat.project.domain.Administrator;
import it.ManagerFlat.project.domain.Lettura;
import it.ManagerFlat.project.domain.Proprietario;
import it.ManagerFlat.project.domain.Stanza;
import it.ManagerFlat.project.service.MailService;
import it.ManagerFlat.project.service.ManagerFlat;
import it.ManagerFlat.project.util.MD5Java;
import it.ManagerFlat.project.util.StanzaConsumi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

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



//TODO Inserisci Inquilino
//TODO Elimina Inquilino
//TODO Modifica Inquilino

//TODO check valori attuali maggiori dei valori precedenti
//TODO check presenti tutte le stanze

@Controller
@SessionAttributes({ "admin", "consumoStanza1", "consumoStanza2",
		"consumoStanza3", "consumoStanza4","dataLettura" })
public class AdminController {

	@Autowired
	ManagerFlat manager = new ManagerFlat();
	@Autowired
	MailService mailService = new MailService();

	
	@RequestMapping(value = "/adminLogIn", method = RequestMethod.GET)
	public String adminLogInValidation(
			@RequestParam(value = "User") String usr,
			@RequestParam(value = "Password") String pwd, Model model) {

		String hpwd = MD5Java.md5Java(pwd);
		System.out.println(hpwd);
		AdminDAO ad = new AdminDAOImpl();
		Administrator admin = ad.getAdmin(usr, hpwd);

		if (admin == null) {
			return "indexError";
		} else {
			model.addAttribute("admin", admin);
		}

		return "redirect:homeAdmin.html";
	}


	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String adminLogIn(Model model) {
		if (model.containsAttribute("admin"))
			return "redirect:homeAdmin.html";

		return "index";
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model,HttpSession session,SessionStatus status,ModelMap  map) {
		if (!model.containsAttribute("admin"))
			return "redirect:homeAdmin.html";
		map.remove("admin");
		map.remove("consumoStanza1");
		map.remove("consumoStanza2");
		map.remove("consumoStanza3");
		map.remove("consumoStanza4");
		status.setComplete();
		return "index";
	}

	@RequestMapping(value = "/inserisciLettura", method = RequestMethod.GET)
	public String inserisciLettura(
			@RequestParam(value = "Stanza") String stanza,
			@RequestParam(value = "Luce", defaultValue = "0") String luce,
			@RequestParam(value = "AcquaFredda", defaultValue = "0") String acquaf,
			@RequestParam(value = "AcquaCalda", defaultValue = "0") String acquac,
			@RequestParam(value = "Gas", defaultValue = "0") String gas,
			@RequestParam(value = "Data") String data, Model model,@ModelAttribute("admin") Administrator admin) {
		if (!model.containsAttribute("admin")) {
			return "redirect:index.html";
		}
		//controlla che data non sia prima dell'attuale
		String[] split=data.split("-");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy ");
		Date date = new Date();
		String dataAttuale =dateFormat.format(date);
		String[] split2=dataAttuale.split("-");
		c2.set(Integer.parseInt(split2[2].replace(" ", "")), Integer.parseInt(split2[1]),Integer.parseInt(split2[0]));
		c1.set(Integer.parseInt(split[2]), Integer.parseInt(split[1]),Integer.parseInt(split[0]));
		if (c1.before(c2)) {
			return "redirect:dataErrata.html?Stanza=" + stanza + "&Luce="
					+ luce + "&AcquaFredda=" + acquaf + "&AcquaCalda=" + acquac
					+ "&Gas=" + gas;
		}
		//mi prendo l'appartamento relativo all'amministratore
		Proprietario pro= manager.getProprietario(admin.getName());
		Stanza sta = null;
		sta = manager.getStanza(stanza,pro.getAppartamenti().get(0).getId());
		float lucefloat = new Float("-1");
		float acquaffloat = new Float("-1");
		float acquacfloat = new Float("-1");
		float gasfloat = new Float("-1");
		try {
			lucefloat = Float.parseFloat(luce);
		} catch (NumberFormatException nfe) {
			return "redirect:luceValueErrato.html?Stanza=" + stanza + "&Luce="
					+ luce + "&AcquaFredda=" + acquaf + "&AcquaCalda=" + acquac
					+ "&Gas=" + gas;
		}
		try {
			acquaffloat = Float.parseFloat(acquaf);
		} catch (NumberFormatException nfe) {
			return "redirect:acquaFreddaValueErrato.html?Stanza=" + stanza
					+ "&Luce=" + luce + "&AcquaFredda=" + acquaf
					+ "&AcquaCalda=" + acquac + "&Gas=" + gas;
		}
		try {
			acquacfloat = Float.parseFloat(acquac);
		} catch (NumberFormatException nfe) {
			return "redirect:acquaCaldaValueErrato.html?Stanza=" + stanza
					+ "&Luce=" + luce + "&AcquaFredda=" + acquaf
					+ "&AcquaCalda=" + acquac + "&Gas=" + gas;
		}
		try {
			if(gas == "")
				gas = "0";
			gasfloat = Float.parseFloat(gas);
		} catch (NumberFormatException nfe) {
			return "redirect:gasValueErrato.html?Stanza=" + stanza + "&Luce="
					+ luce + "&AcquaFredda=" + acquaf + "&AcquaCalda=" + acquac
					+ "&Gas=" + gas;
		}
		System.out.println("dataAttuale "+dataAttuale);
		//controllo se esiste una lettura per quella data e per quella stanza
		if(manager.getLetturaByDataEStanza(data, stanza)!=null)
			return "redirect:inserisciLetturaErrore.html";
		System.out.println(luce);
		System.out.println("data "+data);

		Long id = manager.insertLettura(sta, acquaffloat, acquacfloat,
				gasfloat, data, "0", lucefloat);
		if (id == null) {
			return "redirect:inserisciLetturaErrore.html";
		} else {
			return "redirect:inserisciLetturaCorretto.html";
		}

	}

	@RequestMapping(value = "/inserisciLetturaCorretto", method = RequestMethod.GET)
	public String inserisciLetturaCorretto(Model model) {
		if (!model.containsAttribute("admin")) {
			return "redirect:index.html";
		}
		model.addAttribute("resultInserimento",
				"inserimento andato a buon fine");
		return "accountAdmin";
	}

	@RequestMapping(value = "/inserisciLetturaErrore", method = RequestMethod.GET)
	public String inserisciLetturaErrore(Model model) {
		if (!model.containsAttribute("admin")) {
			return "redirect:index.html";
		}
		model.addAttribute("resultInserimento", "errore nell'inserimento o lettura gia presente");
		return "accountAdmin";
	}

	@RequestMapping(value = "/luceValueErrato", method = RequestMethod.GET)
	public String luceValueErrato(
			@RequestParam(value = "Stanza") String stanza,
			@RequestParam(value = "Luce") String luce,
			@RequestParam(value = "AcquaFredda") String acquaf,
			@RequestParam(value = "AcquaCalda") String acquac,
			@RequestParam(value = "Gas") String gas, Model model) {
		if (!model.containsAttribute("admin")) {
			return "redirect:index.html";
		}
		model.addAttribute("stanza", stanza);
		model.addAttribute("luce", luce);
		model.addAttribute("acquaFredda", acquaf);
		model.addAttribute("acquaCalda", acquac);
		model.addAttribute("gas", gas);
		model.addAttribute("luceValueErrato", "inserire un valore numerico");
		return "accountAdmin";
	}
	@RequestMapping(value = "/dataErrata", method = RequestMethod.GET)
	public String dataErrata(
			@RequestParam(value = "Stanza") String stanza,
			@RequestParam(value = "Luce") String luce,
			@RequestParam(value = "AcquaFredda") String acquaf,
			@RequestParam(value = "AcquaCalda") String acquac,
			@RequestParam(value = "Gas") String gas, Model model) {
		if (!model.containsAttribute("admin")) {
			return "redirect:index.html";
		}
		model.addAttribute("stanza", stanza);
		model.addAttribute("luce", luce);
		model.addAttribute("acquaFredda", acquaf);
		model.addAttribute("acquaCalda", acquac);
		model.addAttribute("gas", gas);
		model.addAttribute("dataErrata", "data errata");
		return "accountAdmin";
	}

	@RequestMapping(value = "/acquaFreddaValueErrato", method = RequestMethod.GET)
	public String acquaFreddaValueErrato(
			@RequestParam(value = "Stanza") String stanza,
			@RequestParam(value = "Luce") String luce,
			@RequestParam(value = "AcquaFredda") String acquaf,
			@RequestParam(value = "AcquaCalda") String acquac,
			@RequestParam(value = "Gas") String gas, Model model) {
		if (!model.containsAttribute("admin")) {
			return "redirect:index.html";
		}
		model.addAttribute("stanza", stanza);
		model.addAttribute("luce", luce);
		model.addAttribute("acquaFredda", acquaf);
		model.addAttribute("acquaCalda", acquac);
		model.addAttribute("gas", gas);
		model.addAttribute("acquaFreddaValueErrato",
				"inserire un valore numerico");
		return "accountAdmin";
	}

	@RequestMapping(value = "/acquaCaldaValueErrato", method = RequestMethod.GET)
	public String acquaCaldaValueErrato(
			@RequestParam(value = "Stanza") String stanza,
			@RequestParam(value = "Luce") String luce,
			@RequestParam(value = "AcquaFredda") String acquaf,
			@RequestParam(value = "AcquaCalda") String acquac,
			@RequestParam(value = "Gas") String gas, Model model) {
		if (!model.containsAttribute("admin")) {
			return "redirect:index.html";
		}
		model.addAttribute("stanza", stanza);
		model.addAttribute("luce", luce);
		model.addAttribute("acquaFredda", acquaf);
		model.addAttribute("acquaCalda", acquac);
		model.addAttribute("gas", gas);
		model.addAttribute("acquaCaldaValueErrato",
				"inserire un valore numerico");
		return "accountAdmin";
	}

	@RequestMapping(value = "/gasValueErrato", method = RequestMethod.GET)
	public String gasValueErrato(@RequestParam(value = "Stanza") String stanza,
			@RequestParam(value = "Luce") String luce,
			@RequestParam(value = "AcquaFredda") String acquaf,
			@RequestParam(value = "AcquaCalda") String acquac,
			@RequestParam(value = "Gas") String gas, Model model) {
		if (!model.containsAttribute("admin")) {
			return "redirect:index.html";
		}
		model.addAttribute("stanza", stanza);
		model.addAttribute("luce", luce);
		model.addAttribute("acquaFredda", acquaf);
		model.addAttribute("acquaCalda", acquac);
		model.addAttribute("gas", gas);
		model.addAttribute("gasValueErrato", "inserire un valore numerico");
		
		return "accountAdmin";
	}

	@RequestMapping(value = "/accountAdmin", method = RequestMethod.GET)
	public String accountAdmin(@RequestParam(value = "Stanza") String stanza,
			@RequestParam(value = "Luce") String luce,
			@RequestParam(value = "AcquaFredda") String acquaf,
			@RequestParam(value = "AcquaCalda") String acquac,
			@RequestParam(value = "Gas") String gas, Model model) {
		if (!model.containsAttribute("admin")) {
			return "redirect:index.html";
		}
		model.addAttribute("stanza", stanza);
		model.addAttribute("luce", luce);
		model.addAttribute("acquaFredda", acquaf);
		model.addAttribute("acquaCalda", acquac);
		model.addAttribute("gas", gas);

		return "accountAdmin";
	}

	@RequestMapping(value = "/homeAdmin", method = RequestMethod.GET)
	public String homeAdmin(Model model) {
		if (!model.containsAttribute("admin")) {
			return "redirect:index.html";
		}
		return "accountAdmin";
	}

	@RequestMapping(value = "/viewTableLetture", method = RequestMethod.GET)
	public @ResponseBody
	String viewTableLetture(Model model,@ModelAttribute("admin") Administrator admin) {
		if (!model.containsAttribute("admin")) {
			return "noAdmin";
		}
		Proprietario pro= manager.getProprietario(admin.getName());
		return createTable(false,pro.getAppartamenti().get(0).getId());
	}
	
	@RequestMapping(value = "/visualizzaTableEliminaModifica", method = RequestMethod.GET)
	public @ResponseBody
	String visualizzaTableEliminaModifica(Model model,@ModelAttribute("admin") Administrator admin) {
		if (!model.containsAttribute("admin")) {
			return "noAdmin";
		}
		System.out.println("nome admin "+admin.getName());
		Proprietario pro= manager.getProprietario(admin.getName());
		return createTableModifica(pro.getAppartamenti().get(0).getId());
	}

	@RequestMapping(value = "/viewTableLettureCheckbox", method = RequestMethod.GET)
	public @ResponseBody
	String viewTableLettureCheckbox(Model model,@ModelAttribute("admin") Administrator admin) {
		if (!model.containsAttribute("admin")) {
			return "noAdmin";
		}
		Proprietario pro= manager.getProprietario(admin.getName());
		return createTable(true,pro.getAppartamenti().get(0).getId());
		
	}

	@RequestMapping(value = "/creaPDFLetture", method = RequestMethod.POST)
	public @ResponseBody String creaPDFLetture(@RequestParam(value = "Data") String data,
			Model model) {
		if (!model.containsAttribute("admin")) {
			return "noAdmin";
		}
		System.out.println(data);
		String[] ingredients = data.split("#");
		String[] attuali = ingredients[0].split(";");
		String[] precedenti = ingredients[1].split(";");
		

		List<Long> lettureIdsAttuale = new ArrayList<Long>();
		System.out.println("creaPDFLetture");
		List<Long> lettureIdsPrecedenti = new ArrayList<Long>();
		for (String s : attuali) {
			String[] ingString = s.split(",");
			if (ingString.length == 2) {
				Long id = Long.parseLong(ingString[1]);
				if (ingString[0].equalsIgnoreCase("true")) {
					System.out.println("lettureIdsAttuale "+ingString[1]);
					lettureIdsAttuale.add(id);
				}					
			}

		}
		for (String s : precedenti) {
			String[] ingString = s.split(",");
			if (ingString.length == 2) {
				Long id = Long.parseLong(ingString[1]);
				if (ingString[0].equalsIgnoreCase("true")) {
					System.out.println("lettureIdsPrecedenti "+ingString[1]);
					lettureIdsPrecedenti.add(id);
				}					
			}
			
		}
		

		// lista di 5 lettture (1,2,3,4,Stanza) Attuale
		List<Lettura> lettureAttuale = new ArrayList<Lettura>();
		// lista di 5 lettture (1,2,3,4,Stanza) Precedente
		List<Lettura> letturePrecedente = new ArrayList<Lettura>();
		for (Long long1 : lettureIdsAttuale) {
			lettureAttuale.add(manager.getLettura(long1));

		}
		for (Long long1 : lettureIdsPrecedenti) {
			letturePrecedente.add(manager.getLettura(long1));
		}
		System.out.println(lettureAttuale.size());
		String error = controllaLetture(lettureAttuale);
		if(error.equals("error")){
			return "error";
		}
		
		List<StanzaConsumi> stanzeConsumi = calcolaLetture(lettureAttuale,
				letturePrecedente);
		creaPdf(stanzeConsumi);
		model.addAttribute("consumoStanza1", stanzeConsumi.get(0));
		model.addAttribute("consumoStanza2", stanzeConsumi.get(1));
		model.addAttribute("consumoStanza3", stanzeConsumi.get(2));
		model.addAttribute("consumoStanza4", stanzeConsumi.get(3));
		model.addAttribute("dataLettura", stanzeConsumi.get(0).getDataFine());

		// new ModelAndView("consumoStanza", "consumoStanza",
		// stanzeConsumi.get(0));

		// mailService.sendMail("alessio.derango@gmail.com",
		// "alessio.derango@gmail.com", "Testing123",
		// "Testing only \n\n Hello Spring Email Sender");
		//
		// mailService.sendAlertMail("Exception occurred");

		return "ok";
	}
	
	@RequestMapping(value = "/eliminaLetture", method = RequestMethod.GET)
	public @ResponseBody String eliminaModificaLetture(@RequestParam(value = "Data") String data,
			Model model) {
		if (!model.containsAttribute("admin")) {
			return "noAdmin";
		}
		String[] attuali = data.split(";");
		for (String s : attuali) {
			String[] ingString = s.split(",");
			if (ingString.length == 2) {
				Long id = Long.parseLong(ingString[1]);
				if (ingString[0].equalsIgnoreCase("true")) {
					System.out.println(ingString[1]);
					boolean state= manager.removeLettura(id);
					if(!state){
						return "error";
					}
				}					
			}

		}
		return "ok";
	}
	@RequestMapping(value = "/impostaParametri", method = RequestMethod.GET)
	public @ResponseBody String impostaParametri(
			Model model) {
		if (!model.containsAttribute("admin")) {
			return "noAdmin";
		}
		String toSend="";
		
		toSend+="<form class='form-horizontal' role='form'>";
		toSend+="<div class='form-group '>";
		toSend+="<label class='col-sm-3 control-label'>Costo KW</label>";
		toSend+="<div class='col-sm-9'>";
		toSend+="	<input class='form-control' id='inputCostoKW' placeholder='Costo KW' value='"+manager.getParametro(new Long(1)).getValore()+"' >";
		toSend+="</div>";
		toSend+="</div>";
		toSend+="<br>";
		toSend+="<div class='form-group'>";
		toSend+="<label class='col-sm-3 control-label'> Costo Gas/Litro</label>";
		toSend+="<div class='col-sm-9'>";
		toSend+="<input class='form-control' id='inputCostoGasLitro' placeholder='Costo Gas/Litro' value='"+manager.getParametro(new Long(2)).getValore()+"' >"	+"</div>";
		toSend+="</div>";
		toSend+="<br>";
		toSend+="<div class='form-group'>";
		toSend+="<label class='col-sm-3 control-label'>Costo Acqua/metro cubo</label>";
		toSend+="<div class='col-sm-9'>";
		toSend+="<input class='form-control' id='inputCostoAcquametrocubo' placeholder='Costo Acqua/metro cubo' value='"+manager.getParametro(new Long(3)).getValore()+"' >";
		toSend+="</div>";
		toSend+="</div>";
		toSend+="<div class='form-group'>";

		toSend+="<div class=' col-sm-offset-1 col-sm-10 '>";
		toSend+="<button type='submit' class='btn btn-default' id='buttonImpostaParametri'>Inserisci</button>";
		toSend+="</div>";
		toSend+="</div>";
		toSend+="</form>";
		
		
		
		return toSend;
	}
	@RequestMapping(value = "/modificaLetture", method = RequestMethod.GET)
	public @ResponseBody String modificaLetture(@RequestParam(value = "Data") String data,
			Model model) {
		String[] attuali = data.split(";");
		float gas = 0;
		float acquac = 0;
		float acquaf = 0;
		float luce = 0;
		Long id=(long) 0;
		for (String s : attuali) {
			String[] ingString = s.split("=");
			if (ingString.length == 2) {
				if (ingString[0].equals("acquac")) {
					try{
						acquac=Float.parseFloat(ingString[1]);
					}catch(NumberFormatException e){
						return "error";
					}
				}					
				if (ingString[0].equals("id")) {
					try{
						id=Long.parseLong(ingString[1]);
					}catch(NumberFormatException e){
						return "error";
					}
				}					
				if (ingString[0].equals("gas")) {
					try{
						gas=Float.parseFloat(ingString[1]);
					}catch(NumberFormatException e){
						return "error";
					}
				}					
				if (ingString[0].equals("acquaf")) {
					try{
						acquaf=Float.parseFloat(ingString[1]);
					}catch(NumberFormatException e){
						return "error";
					}
				}					
				if (ingString[0].equals("luce")) {
					try{
						luce=Float.parseFloat(ingString[1]);
					}catch(NumberFormatException e){
						return "error";
					}
				}					
			}
			
		}
		
		if(manager.aggiornaLettura(id, acquaf, acquac, gas, luce))
			return "ok";
		else
			return "error";
	}
	
	@RequestMapping(value = "/modificaParametri", method = RequestMethod.GET)
	public @ResponseBody String modificaParametri(@RequestParam(value = "Data") String data,
			Model model) {
		String[] split = data.split(";");
		float costoKW=0;
		float costoGas=0;
		float costoAcqua=0;
		//1 enrgia
		//2 Gas
		//3 Acqua
			try{
				costoKW=Float.parseFloat(split[0]);
				costoGas=Float.parseFloat(split[1]);
				costoAcqua=Float.parseFloat(split[2]);
			}catch(NumberFormatException e){
				return "error";
			}
		System.out.println(costoKW);
		System.out.println(costoGas);
		System.out.println(costoAcqua);
		boolean kw = manager.setParametro(new Long(1), costoKW);
		if(!kw){
			return "error";
		}
		boolean gas = manager.setParametro(new Long(2), costoGas);
		if(!gas){
			return "error";
		}
		boolean acqua = manager.setParametro(new Long(3),costoAcqua);
		if(!acqua){
			return "error";
		}
		System.out.println("ok");
		
		return "ok";
		
	}

	private String controllaLetture(List<Lettura> lettureAttuale) {
		//controllo che tutte le letture abbiamo la stessa data
		if(lettureAttuale.size()!=5)
			return "error";
		String data = lettureAttuale.get(0).getData();
		for (Lettura lettura : lettureAttuale) {
			if(!lettura.getData().equals(data)){
				//ERRORE DATE DIVERSE QUINDI LETTURE NON SONO 5
				return "error";
			}
		}
		return "ok";
	}

	@RequestMapping(value = "/downloadPDFStanza1", method = {
			RequestMethod.POST, RequestMethod.GET })
	public ModelAndView downloadPDFStanza1(
			@ModelAttribute("consumoStanza1") StanzaConsumi consumo) {
		// return a view which will be resolved by an excel view resolver
		return new ModelAndView("pdfView", "consumoStanza", consumo);
	}

	@RequestMapping(value = "/downloadPDFStanza2", method = {
			RequestMethod.POST, RequestMethod.GET })
	public ModelAndView downloadPDFStanza2(
			@ModelAttribute("consumoStanza2") StanzaConsumi consumo) {
		// return a view which will be resolved by an excel view resolver
		return new ModelAndView("pdfView", "consumoStanza", consumo);
	}

	@RequestMapping(value = "/downloadPDFStanza3", method = {
			RequestMethod.POST, RequestMethod.GET })
	public ModelAndView downloadPDFStanza3(
			@ModelAttribute("consumoStanza3") StanzaConsumi consumo) {
		// return a view which will be resolved by an excel view resolver
		return new ModelAndView("pdfView", "consumoStanza", consumo);
	}

	@RequestMapping(value = "/downloadPDFStanza4", method = {
			RequestMethod.POST, RequestMethod.GET })
	public ModelAndView downloadPDFStanza4(
			@ModelAttribute("consumoStanza4") StanzaConsumi consumo) {
		// return a view which will be resolved by an excel view resolver
		return new ModelAndView("pdfView", "consumoStanza", consumo);
	}

	@RequestMapping(value = "/inviaLetture", method = RequestMethod.GET)
	public @ResponseBody
	String inviaLetture(Model model,@ModelAttribute("dataLettura") String data) {
		// calcolaLetture(lettureIds);
		// creaPdf(listBooks);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
		Date date = new Date();

		try {
			mailService.sendMail("alessio.derango@gmail.com",
					"alessio.derango@gmail.com",
					"Consumi " + data,
					"In Allegato i consumi",data);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		try {
			mailService.sendMail("alessio.derango@gmail.com",
					"derango@unical.it", "Consumi " + data,
					"In Allegato i consumi",data);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		try {
			mailService.sendMail("alessio.derango@gmail.com",
					"stefaniachimenti1@gmail.com",
					"Consumi " + data,
					"In Allegato i consumi",data);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		try {
			mailService.sendMail("alessio.derango@gmail.com",
					"salvatorederango@gmail.com",
					"Consumi " + data,
					"In Allegato i consumi",data);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		//
		// mailService.sendAlertMail("Exception occurred");
		return "ok";
	}

	private void creaPdf(List<StanzaConsumi> stanzeConsumi) {
		int count = 1;
		for (StanzaConsumi consumi : stanzeConsumi) {
			Document doc = new Document();
			OutputStream file = null;
			try {
				file = new FileOutputStream(new File("C:\\prova\\ConsumiStanza"
						+ count + "_" + consumi.getDataFine() + ".pdf"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				PdfWriter.getInstance(doc, file);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			// get data model which is passed by the Spring container

			Paragraph total = new Paragraph(
					"TOTALE EURO________________________________________________________"
							+ (consumi.getConsumoAcquaStanza()
									+ consumi.getConsumoAcquaStanzaComune()
									+ consumi.getConsumoEnergiaStanza()
									+ consumi.getConsumoEnergiaStanzaComune()
									+ consumi.getConsumoGasStanza() + consumi
										.getConsumoGasStanzaComune()));

			try {
				doc.open();
				Paragraph dateLetture = new Paragraph("Consumi dal " + consumi.getDataInizio() + " al "+ consumi.getDataFine()); 
				dateLetture.setAlignment(Element.ALIGN_CENTER);
				dateLetture.setSpacingAfter(25);
				doc.add(dateLetture);

				Paragraph preface = new Paragraph("Stanza "
						+ consumi.getNumero());
				preface.setAlignment(Element.ALIGN_CENTER);
				preface.setSpacingAfter(25);
				doc.add(preface);

				doc.add(new Paragraph("Acqua"));
			} catch (DocumentException e) {
				e.printStackTrace();
			}

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
				doc.close();
				file.close();
			} catch (DocumentException | IOException e) {
				e.printStackTrace();
			}
			// file.close();
			count++;
		}

	}

	private PdfPTable createTable(StanzaConsumi consumi, String string) {
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100.0f);
		try {
			table.setWidths(new float[] { 2.0f, 2.0f, 2.0f, 2.0f, 1.0f });
		} catch (DocumentException e) {
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

	private List<StanzaConsumi> calcolaLetture(List<Lettura> lettureAttuale,
			List<Lettura> letturePrecedente) {

	

		float costKW = (manager.getParametro(new Long(1))).getValore();
		float costGas = (manager.getParametro(new Long(2))).getValore();
		float costMetroCubo = (manager.getParametro(new Long(3))).getValore();

		Lettura stanza1Attuale = lettureAttuale.get(0);
		Lettura stanza1Precedente = letturePrecedente.get(0);
		Lettura stanza2Attuale = lettureAttuale.get(1);
		Lettura stanza2Precedente = letturePrecedente.get(1);
		Lettura stanza3Attuale = lettureAttuale.get(2);
		Lettura stanza3Precedente = letturePrecedente.get(2);
		Lettura stanza4Attuale = lettureAttuale.get(3);
		Lettura stanza4Precedente = letturePrecedente.get(3);
		Lettura comuneAttuale = lettureAttuale.get(4);
		Lettura comunePrecedente = letturePrecedente.get(4);

		float stanza1AcquaFredda = (stanza1Attuale.getAcquaf() - stanza1Precedente
				.getAcquaf());
		float stanza2AcquaFredda = (stanza2Attuale.getAcquaf() - stanza2Precedente
				.getAcquaf());
		float stanza3AcquaFredda = (stanza3Attuale.getAcquaf() - stanza3Precedente
				.getAcquaf());
		float stanza4AcquaFredda = (stanza4Attuale.getAcquaf() - stanza4Precedente
				.getAcquaf());

		// Calcolo GAS
		float stanza1AcquaCalda = (stanza1Attuale.getAcquac() - stanza1Precedente
				.getAcquac());
		float stanza2AcquaCalda = (stanza2Attuale.getAcquac() - stanza2Precedente
				.getAcquac());
		float stanza3AcquaCalda = (stanza3Attuale.getAcquac() - stanza3Precedente
				.getAcquac());
		float stanza4AcquaCalda = (stanza4Attuale.getAcquac() - stanza4Precedente
				.getAcquac());
		float stanzaComuneAcquaCalda = (comuneAttuale.getAcquac() - comunePrecedente
				.getAcquac());
		float totaleAcquaCalda = stanza1AcquaCalda + stanza2AcquaCalda
				+ stanza3AcquaCalda + stanza4AcquaCalda
				+ stanzaComuneAcquaCalda;

		float unitaConversioneMetro3Litri = (float) 4.166;
		float gasTotale = (comuneAttuale.getGas() - comunePrecedente.getGas())
				* unitaConversioneMetro3Litri;

		// Proporzione
		// consumoAcquaCaldaStanza : totale AcquaCalda = x : gasTotale
		float quantitaStanza1Gas = ((stanza1AcquaCalda * gasTotale) / totaleAcquaCalda);
		float quantitaStanza2Gas = ((stanza2AcquaCalda * gasTotale) / totaleAcquaCalda);
		float quantitaStanza3Gas = ((stanza3AcquaCalda * gasTotale) / totaleAcquaCalda);
		float quantitaStanza4Gas = ((stanza4AcquaCalda * gasTotale) / totaleAcquaCalda);

		float consumoStanza1Gas = quantitaStanza1Gas * costGas;
		float consumoStanza2Gas = quantitaStanza2Gas * costGas;
		float consumoStanza3Gas = quantitaStanza3Gas * costGas;
		float consumoStanza4Gas = quantitaStanza4Gas * costGas;

		float gasStanzaComune = ((stanzaComuneAcquaCalda * gasTotale) / totaleAcquaCalda);
		float percentualePerOgniStanzaGas = gasStanzaComune / 4;
		float consumoGasPerOgniStanza = percentualePerOgniStanzaGas * costGas;
		// finito calcolo Gas

		// Calcolo Acqua

		float quantitaStanza1Acqua = (stanza1AcquaCalda + stanza1AcquaFredda);
		float quantitaStanza2Acqua = (stanza2AcquaCalda + stanza2AcquaFredda);
		float quantitaStanza3Acqua = (stanza3AcquaCalda + stanza3AcquaFredda);
		float quantitaStanza4Acqua = (stanza4AcquaCalda + stanza4AcquaFredda);

		float consumoStanza1Acqua = quantitaStanza1Acqua * costMetroCubo;
		float consumoStanza2Acqua = quantitaStanza2Acqua * costMetroCubo;
		float consumoStanza3Acqua = quantitaStanza3Acqua * costMetroCubo;
		float consumoStanza4Acqua = quantitaStanza4Acqua * costMetroCubo;

		float acquaStanzaComune = (comuneAttuale.getAcquac() - comunePrecedente
				.getAcquac())
				+ (comuneAttuale.getAcquaf() - comunePrecedente.getAcquaf());
		float percentualePerOgniStanzaAqcua = acquaStanzaComune / 4;
		float consumoAcquaComunePerOgniStanza = percentualePerOgniStanzaAqcua
				* costMetroCubo;

		// Finito Calcolo Acqua

		// Calcolo Energia
		float quantitaStanza1Energia = (stanza1Attuale.getLuce() - stanza1Precedente
				.getLuce());
		float quantitaStanza2Energia = (stanza2Attuale.getLuce() - stanza2Precedente
				.getLuce());
		float quantitaStanza3Energia = (stanza3Attuale.getLuce() - stanza3Precedente
				.getLuce());
		float quantitaStanza4Energia = (stanza4Attuale.getLuce() - stanza4Precedente
				.getLuce());

		float consumoStanza1Energia = quantitaStanza1Energia * costKW;
		float consumoStanza2Energia = quantitaStanza2Energia * costKW;
		float consumoStanza3Energia = quantitaStanza3Energia * costKW;
		float consumoStanza4Energia = quantitaStanza4Energia * costKW;

		float stanzaComuneEnergia = (comuneAttuale.getLuce() - comunePrecedente
				.getLuce());
		float percentualePerogniStanzaEnergia = (stanzaComuneEnergia) / 4;
		float consumoEnergiaPerOgniStanza = percentualePerogniStanzaEnergia
				* costKW;

		// Finito Calcolo Energia

		StanzaConsumi stanza1 = new StanzaConsumi();
		StanzaConsumi stanza2 = new StanzaConsumi();
		StanzaConsumi stanza3 = new StanzaConsumi();
		StanzaConsumi stanza4 = new StanzaConsumi();

		stanza1.setNumero(1);
		stanza2.setNumero(2);
		stanza3.setNumero(3);
		stanza4.setNumero(4);
		
		stanza1.setDataInizio(letturePrecedente.get(0).getData());
		stanza2.setDataInizio(letturePrecedente.get(0).getData());
		stanza3.setDataInizio(letturePrecedente.get(0).getData());
		stanza4.setDataInizio(letturePrecedente.get(0).getData());
		
		stanza1.setDataFine(lettureAttuale.get(0).getData());
		stanza2.setDataFine(lettureAttuale.get(0).getData());
		stanza3.setDataFine(lettureAttuale.get(0).getData());
		stanza4.setDataFine(lettureAttuale.get(0).getData());

		// Consumi Acqua
		stanza1.setConsumoAcquaStanza(consumoStanza1Acqua);
		stanza2.setConsumoAcquaStanza(consumoStanza2Acqua);
		stanza3.setConsumoAcquaStanza(consumoStanza3Acqua);
		stanza4.setConsumoAcquaStanza(consumoStanza4Acqua);
		// Consumi Energia
		stanza1.setConsumoEnergiaStanza(consumoStanza1Energia);
		stanza2.setConsumoEnergiaStanza(consumoStanza2Energia);
		stanza3.setConsumoEnergiaStanza(consumoStanza3Energia);
		stanza4.setConsumoEnergiaStanza(consumoStanza4Energia);
		// Consumi Gas
		stanza1.setConsumoGasStanza(consumoStanza1Gas);
		stanza2.setConsumoGasStanza(consumoStanza2Gas);
		stanza3.setConsumoGasStanza(consumoStanza3Gas);
		stanza4.setConsumoGasStanza(consumoStanza4Gas);

		// Comune Acqua
		stanza1.setConsumoAcquaStanzaComune(consumoAcquaComunePerOgniStanza);
		stanza2.setConsumoAcquaStanzaComune(consumoAcquaComunePerOgniStanza);
		stanza3.setConsumoAcquaStanzaComune(consumoAcquaComunePerOgniStanza);
		stanza4.setConsumoAcquaStanzaComune(consumoAcquaComunePerOgniStanza);
		// Comune Energia
		stanza1.setConsumoEnergiaStanzaComune(consumoEnergiaPerOgniStanza);
		stanza2.setConsumoEnergiaStanzaComune(consumoEnergiaPerOgniStanza);
		stanza3.setConsumoEnergiaStanzaComune(consumoEnergiaPerOgniStanza);
		stanza4.setConsumoEnergiaStanzaComune(consumoEnergiaPerOgniStanza);
		// Comune Gas
		stanza1.setConsumoGasStanzaComune(consumoGasPerOgniStanza);
		stanza2.setConsumoGasStanzaComune(consumoGasPerOgniStanza);
		stanza3.setConsumoGasStanzaComune(consumoGasPerOgniStanza);
		stanza4.setConsumoGasStanzaComune(consumoGasPerOgniStanza);

		// Quantita Acqua Stanza
		stanza1.setQuantitaAcquaStanza(quantitaStanza1Acqua);
		stanza2.setQuantitaAcquaStanza(quantitaStanza2Acqua);
		stanza3.setQuantitaAcquaStanza(quantitaStanza3Acqua);
		stanza4.setQuantitaAcquaStanza(quantitaStanza4Acqua);
		// Quantita Energia Stanza
		stanza1.setQuantitaEnergiaStanza(quantitaStanza1Energia);
		stanza2.setQuantitaEnergiaStanza(quantitaStanza2Energia);
		stanza3.setQuantitaEnergiaStanza(quantitaStanza3Energia);
		stanza4.setQuantitaEnergiaStanza(quantitaStanza4Energia);
		// Quantita Gas Stanza
		stanza1.setQuantitaGasStanza(quantitaStanza1Gas);
		stanza2.setQuantitaGasStanza(quantitaStanza2Gas);
		stanza3.setQuantitaGasStanza(quantitaStanza3Gas);
		stanza4.setQuantitaGasStanza(quantitaStanza4Gas);

		// Quantita Acqua Stanza Comune
		stanza1.setQuantitaAcquaStanzaComune(percentualePerOgniStanzaAqcua);
		stanza2.setQuantitaAcquaStanzaComune(percentualePerOgniStanzaAqcua);
		stanza3.setQuantitaAcquaStanzaComune(percentualePerOgniStanzaAqcua);
		stanza4.setQuantitaAcquaStanzaComune(percentualePerOgniStanzaAqcua);
		// Quantita Energia Stanza Comune
		stanza1.setQuantitaEnergiaStanzaComune(percentualePerogniStanzaEnergia);
		stanza2.setQuantitaEnergiaStanzaComune(percentualePerogniStanzaEnergia);
		stanza3.setQuantitaEnergiaStanzaComune(percentualePerogniStanzaEnergia);
		stanza4.setQuantitaEnergiaStanzaComune(percentualePerogniStanzaEnergia);
		// Quantita Gas Stanza Comune
		stanza1.setQuantitaGasStanzaComune(percentualePerOgniStanzaGas);
		stanza2.setQuantitaGasStanzaComune(percentualePerOgniStanzaGas);
		stanza3.setQuantitaGasStanzaComune(percentualePerOgniStanzaGas);
		stanza4.setQuantitaGasStanzaComune(percentualePerOgniStanzaGas);

		List<StanzaConsumi> consumi = new ArrayList<StanzaConsumi>();
		consumi.add(stanza1);
		consumi.add(stanza2);
		consumi.add(stanza3);
		consumi.add(stanza4);

		return consumi;
	}

	private String createTable(boolean insertCheckbox,Long idApp) {

		String lettureTable = "<div style='overflow: auto;height: 700px !important;'>";

		if (insertCheckbox) {
			lettureTable += "<table class='table table-striped tablesorter' id='lettureTableCheckbox'>";
		} else
			lettureTable += "<table class='table table-striped tablesorter' id='lettureTable'>";

		if (insertCheckbox) {
			lettureTable += "<thead><tr><th>sel</th><th>Data</th><th>Stanza</th><th>Acqua Calda</th><th>Acqua Fredda</th><th>Luce</th></tr></thead>";
		} else
			lettureTable += "<thead><tr><th>Data</th><th>Stanza</th><th>Acqua Calda</th><th>Acqua Fredda</th><th>Luce</th></tr></thead>";

		lettureTable += "<tbody>";

		if (insertCheckbox) {
			List<List<Lettura>> lettureByData = manager.getAllLettureByData(idApp);
			for (List<Lettura> list : lettureByData) {
				// lettureTable += "<td>a</td>";
				// primaRiga
				boolean a = true;
				for (Lettura lettura : list) {
					lettureTable += "<tr>";
					if (insertCheckbox && a) {
						lettureTable += "<td ><input class='form-control' type='checkbox' style='-webkit-transform: scale(0.6,0.6); -moz-transform: scale(0.6,0.6); -o-transform: scale(0.6,0.6);'/></td>";
						lettureTable += "<td >" + list.get(0).getData()
								+ "</td>";
					}
					if (!a) {
						lettureTable += "<td ><div class='invisible' style='display:none'><input class='form-control' type='checkbox' style='-webkit-transform: scale(0.6,0.6); -moz-transform: scale(0.6,0.6); -o-transform: scale(0.6,0.6);'/></div></td>"
								+ "<td ><div style='display:none'>"
								+ list.get(0).getData() + "</div></td>";
					}
					lettureTable += "<td id='"+ lettura.getId()+"'>";
					if(lettura.getStanza().getId()==5)
						lettureTable+="comune";
					else
						lettureTable+= lettura.getStanza().getId();
					lettureTable+= "</td>"
							+ "<td>" + lettura.getAcquac() + "</td>" + "<td>"
							+ lettura.getAcquaf() + "</td>" + "<td>"
							+ lettura.getLuce() + "</td>";
					lettureTable += "</tr>";
					a = false;
				}

			}
		} else {
			
			for (Lettura p : manager.getAllLetture(idApp)) {
				lettureTable += "<tr>";

				lettureTable += "<td >" + p.getData() + "</td>"  + "<td >" + p.getStanza().getId()
						+ "</td>" + "<td>" + p.getAcquac() + "</td>" + "<td>"
						+ p.getAcquaf() + "</td>" + "<td>" + p.getLuce()
						+ "</td>" + "</tr>";
			}
		}
		lettureTable += "</tbody></table></div>";

		System.out.println(lettureTable);
		// if (insertCheckbox) {
		// lettureTable +=
		// "<div class='row'><div class='col-sm-offset-1 col-sm-10'><button id='inserisciLettura' type='submit'	class='btn btn-default'>Crea PDF</button></div></div>";
		// }

		return lettureTable;

	}
	
	public String createTableModifica(Long idApp){
		
		String lettureTable = "<div style='overflow: auto;height: 700px !important;'>";
		lettureTable += "<table class='table table-striped tablesorter' id='modificaSalvaTable'>";
		lettureTable += "<thead><tr><th>Data</th><th>Stanza</th><th>Acqua Calda</th><th>Acqua Fredda</th><th>Luce</th><th>Gas</th></tr></thead>";
		lettureTable += "<tbody>";
		for (Lettura p : manager.getAllLetture(idApp)) {
			lettureTable += "<tr>";
				

			lettureTable += "<td >" + p.getData() +"</td>" 
					+ "<td id='"+ p.getId()+"'> " +  p.getStanza().getId()  +"</td>"  
					+ "<td> <input type='text' value='" +  p.getAcquac() + "' style='width:60px;'>" +"</td>" 
					+ "<td> <input type='text' value='" +  p.getAcquaf() + "'style='width:60px;'>" +"</td>" 
					+ "<td> <input type='text' value='" +  p.getLuce() + "'style='width:60px;'>" +"</td>";
			if(!p.getStanza().getNome().equals("comune"))
				lettureTable +=  "<td> <input type='text' value='" +  p.getGas() + "'style='display:none; width:60px;' readonly>" +"</td>";
			else
				lettureTable += "<td><input type='text' value='" +  p.getGas() + "'style='width:60px;'>" +"</td>";
			lettureTable += "<td><input class='form-control' type='checkbox' style='-webkit-transform: scale(1.5,1.5); -moz-transform: scale(1.5,1.5); -o-transform: scale(1.5,1.5);'></td>";
					lettureTable+="<td> <button type='submit' class='btn btn-default salvaModifica' id='"+ p.getId()+"' >Salva Modifica</button></td>"	+ "</tr>";
		}
		lettureTable += "</tbody></table>  </div><div class='row'><div class='col-sm-offset-1 col-sm-10'><button id='eliminaLettura' type='submit' class='btn btn-default'>Elimina</button></div></div>";
		return lettureTable;
	}

}
