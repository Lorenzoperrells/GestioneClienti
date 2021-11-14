package it.epicode.beservice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.beservice.model.Cliente;
import it.epicode.beservice.model.Indirizzo;
import it.epicode.beservice.model.Provincia;
import it.epicode.beservice.model.Regione;
import it.epicode.beservice.model.TipoCliente;
import it.epicode.beservice.services.ClienteService;
import it.epicode.beservice.services.ComuneService;
import it.epicode.beservice.services.IndirizzoService;
import it.epicode.beservice.services.ProvinciaService;
import it.epicode.beservice.services.RegioneService;

@RestController
@RequestMapping("/clientectrl")
public class ClienteController {
	@Autowired
	ClienteService service;
	@Autowired
	ComuneService cs;
	@Autowired
	IndirizzoService indirizzo;
	@Autowired
	RegioneService rs;
	@Autowired
	ProvinciaService provincia;
	
	@PostMapping("/update/{id}")
	public void update(@PathVariable Long id, @RequestBody Cliente c) {
		service.update(id, c);
	}

	@PostMapping("/save")
	public void save(@RequestBody Cliente c) {
		service.save(c);
	}

	@GetMapping("/savepage")
	public ModelAndView savepage(@RequestParam String ragioneSociale, String partitaIva, TipoCliente tipoCliente, String email, String pec,
			String telefono, String nomeContatto, String cognomeContatto, String telefonoContatto, String emailContatto,
			String cap, String via,String civico,String localita,String nomeComune,String capl, String vial,String civicol,String localital,String nomeComunel,
			@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataUltimoContatto, double fatturatoAnnuale) {
		Cliente c=new Cliente();
		if(!indirizzo.existByVia(via)) {
		Indirizzo i=new Indirizzo(via,civico,cap,localita,cs.getBynome(nomeComune));
		indirizzo.save(i);
		}
		if(!indirizzo.existByVia(vial)) {
		Indirizzo il=new Indirizzo(vial,civicol,capl,localital,cs.getBynome(nomeComunel));
		indirizzo.save(il);
		}
		c.setDataInserimento(LocalDate.now());
		c.setCognomeContatto(cognomeContatto);
		c.setDataUltimoContatto(dataUltimoContatto);
		c.setEmail(email);
		c.setEmailContatto(emailContatto);
		c.setFatturatoAnnuale(fatturatoAnnuale);
		c.setIndirizzoSedeLegale(indirizzo.getByVia(vial));
		c.setIndirizzoSedeOperativa(indirizzo.getByVia(via));
		c.setNomeContatto(nomeContatto);
		c.setPartitaIva(partitaIva);
		c.setPec(pec);
		c.setRagioneSociale(ragioneSociale);
		c.setTelefono(telefono);
		c.setTelefonoContatto(telefonoContatto);
		c.setTipoCliente(tipoCliente);
		service.save(c);
		ModelAndView myModel=new ModelAndView();
		myModel.addObject("cliente",c);
		myModel.setViewName("clientedetails");
		return myModel;
	}

	@GetMapping("/delete")
	public void remove(@RequestParam Long id) {
		service.remove(id);
	}

	@GetMapping(value = "/getorder", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cliente>> myGetAllUserPageSizeSort(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "2") Integer size, @RequestParam(defaultValue = "nomeContatto") String sort) {
		List<Cliente> list = service.myFindAllClientePageSizeSort(page, size, sort);
		return new ResponseEntity<List<Cliente>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/getbyfatturato")
	public ResponseEntity<List<Cliente>> mygetByfatturato(@RequestParam Double fatturato,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "5") Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		List<Cliente> list = service.getByFatturato(fatturato, pageable);
		return new ResponseEntity<List<Cliente>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/getbydataultimocontatto")
	public ResponseEntity<List<Cliente>> mygetByfatturato(
			@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataUltimoContatto,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "5") Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		List<Cliente> list = service.getByDataUltimoContatto(dataUltimoContatto, pageable);
		return new ResponseEntity<List<Cliente>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/getbydatainserimento")
	public ResponseEntity<List<Cliente>> getClientiByDataInserimento(
			@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInserimento,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "4") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		List<Cliente> list = service.getByDataInserimento(dataInserimento, pag);
		return new ResponseEntity<List<Cliente>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/getbypartedelnome")
	public ResponseEntity<List<Cliente>> getClientiByParteDelNome(@RequestParam String nome,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "4") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		List<Cliente> list = service.getByParteDelNome(nome, pag);
		return new ResponseEntity<List<Cliente>>(list, new HttpHeaders(), HttpStatus.OK);

	}
	@GetMapping(value = "/getorderview", produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView myGetAllUserPageSizeSortModel(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "50") Integer size, @RequestParam(defaultValue = "nomeContatto") String sort) {
		ModelAndView myModel=new ModelAndView();
		List<Cliente> list = service.myFindAllClientePageSizeSort(page, size, sort);
		myModel.addObject("clienti", list);
		myModel.setViewName("clienti");
		return myModel;
	}

	@GetMapping("/getbyfatturatoview")
	public ModelAndView mygetByfatturatov(@RequestParam Double fatturato,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "50") Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		ModelAndView myModel=new ModelAndView();
		List<Cliente> list = service.getByFatturato(fatturato, pageable);
		myModel.addObject("clienti", list);
		myModel.setViewName("clienti");
		return myModel;
	}

	@GetMapping("/getbydataultimocontattoview")
	public ModelAndView mygetBydataContatto(
			@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataUltimoContatto,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "50") Integer size) {
		ModelAndView myModel=new ModelAndView();
		Pageable pageable = PageRequest.of(page, size);
		List<Cliente> list = service.getByDataUltimoContatto(dataUltimoContatto, pageable);
		myModel.addObject("clienti", list);
		myModel.setViewName("clienti");
		return myModel;
	}

	@GetMapping("/getbydatainserimentoview")
	public ModelAndView getClientiByDataInserimentov(
			@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInserimento,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "50") Integer size) {
		ModelAndView myModel=new ModelAndView();
		Pageable pag = PageRequest.of(page, size);
		List<Cliente> list = service.getByDataInserimento(dataInserimento, pag);
		myModel.addObject("clienti", list);
		myModel.setViewName("clienti");
		return myModel;
	}

	@GetMapping("/getbypartedelnomeview")
	public ModelAndView getClientiByParteDelNomev(@RequestParam String nome,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "50") Integer size) {
		ModelAndView myModel=new ModelAndView();
		Pageable pag = PageRequest.of(page, size);
		List<Cliente> list = service.getByParteDelNome(nome, pag);
		myModel.addObject("clienti", list);
		myModel.setViewName("clienti");
		return myModel;
		}
	@GetMapping("/clientedetails/{id}")
	public ModelAndView getclienteDetails(@PathVariable Long id) {
		ModelAndView myModel=new ModelAndView();
		Cliente c=service.getById(id);
		myModel.addObject("cliente", c);
		myModel.setViewName("clientedetails");
		return myModel;
		}
	@GetMapping(value = "/getorderviewadmin", produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView myGetAllUserPageSizeSortModela(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "50") Integer size, @RequestParam(defaultValue = "nomeContatto") String sort) {
		ModelAndView myModel=new ModelAndView();
		List<Cliente> list = service.myFindAllClientePageSizeSort(page, size, sort);
		myModel.addObject("clienti", list);
		myModel.setViewName("clientiadmin");
		return myModel;
	}

	@GetMapping("/getbyfatturatoviewadmin")
	public ModelAndView mygetByfatturatova(@RequestParam Double fatturato,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "50") Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		ModelAndView myModel=new ModelAndView();
		List<Cliente> list = service.getByFatturato(fatturato, pageable);
		myModel.addObject("clienti", list);
		myModel.setViewName("clientiadmin");
		return myModel;
	}

	@GetMapping("/getbydataultimocontattoviewadmin")
	public ModelAndView mygetBydataContattoa(
			@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataUltimoContatto,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "50") Integer size) {
		ModelAndView myModel=new ModelAndView();
		Pageable pageable = PageRequest.of(page, size);
		List<Cliente> list = service.getByDataUltimoContatto(dataUltimoContatto, pageable);
		myModel.addObject("clienti", list);
		myModel.setViewName("clientiadmin");
		return myModel;
	}

	@GetMapping("/getbydatainserimentoviewadmin")
	public ModelAndView getClientiByDataInserimentova(
			@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInserimento,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "50") Integer size) {
		ModelAndView myModel=new ModelAndView();
		Pageable pag = PageRequest.of(page, size);
		List<Cliente> list = service.getByDataInserimento(dataInserimento, pag);
		myModel.addObject("clienti", list);
		myModel.setViewName("clientiadmin");
		return myModel;
	}

	@GetMapping("/getbypartedelnomeviewadmin")
	public ModelAndView getClientiByParteDelNomeva(@RequestParam String nome,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "50") Integer size) {
		ModelAndView myModel=new ModelAndView();
		Pageable pag = PageRequest.of(page, size);
		List<Cliente> list = service.getByParteDelNome(nome, pag);
		myModel.addObject("clienti", list);
		myModel.setViewName("clientiadmin");
		return myModel;
		}
	@GetMapping("/dashboard")
	public ModelAndView a(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "50") Integer size) {
		ModelAndView myModel = new ModelAndView();
		Pageable pag = PageRequest.of(page, size);
		List<Cliente> rangeBasso=service.getByRangeFatturatoAnnualeOrderByFatturatoAnnuale(0.0, 200000.0, pag);
		List<Cliente> rangeMedio=service.getByRangeFatturatoAnnualeOrderByFatturatoAnnuale(200000.1, 400000.0, pag);
		List<Cliente> rangeAlto=service.getByRangeFatturatoAnnualeOrderByFatturatoAnnuale(400000.1, 1000000000.0, pag);
		Map<String,List<String>>reg=new HashMap<String,List<String>>();
		List<Regione> regioni=rs.findAll();
		for (Regione r : regioni) {
			List<Cliente> c = service.getClienteByRegione(r.getNome());
			Integer i = c.size();
			List<Provincia> p = provincia.getByRegione(r.getNome());
			List<String> prov =new ArrayList<String>();
			for (Provincia x : p) {
				
				Integer n = service.getClienteByProvincia(x.getNome()).size();
				prov.add(x.getNome() + ": " + n);

			}
			String s = r.getNome() + ": " + i;
			reg.put(s, prov);
		}
		
		myModel.addObject("regioni",reg);
		myModel.addObject("rangebasso", rangeBasso);
		myModel.addObject("rangemedio", rangeMedio);
		myModel.addObject("rangealto", rangeAlto);
		myModel.setViewName("dashboard");
		return myModel;
	}
	@GetMapping("/selectprovincia/{nome}")
    public ModelAndView selectProvincia(@PathVariable String nome) {
        ModelAndView model = new ModelAndView();
        String[] lstCartStr = nome.split(":");
        List<Cliente> clientiByProvincia = service.getClienteByProvincia(lstCartStr[0]);
        model.addObject("clienti", clientiByProvincia);
        model.setViewName("clientiadmin");
        return model;
    }


}
