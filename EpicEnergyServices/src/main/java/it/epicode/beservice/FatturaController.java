package it.epicode.beservice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.beservice.model.Fattura;
import it.epicode.beservice.model.StatoFattura;
import it.epicode.beservice.services.ClienteService;
import it.epicode.beservice.services.FatturaService;
import it.epicode.beservice.services.StatoFatturaService;

@RestController
@RequestMapping("/fatturactrl")
public class FatturaController {
	@Autowired
	FatturaService service;
	@Autowired
	ClienteService cs;
	@Autowired
	StatoFatturaService sfc;

	@PostMapping("/update/{id}")
	public void update(@PathVariable Long id, @RequestBody Fattura f) {
		service.update(id, f);
	}

	@PostMapping("/save")
	public void save(@RequestBody Fattura f) {
		service.save(f);
	}

	@GetMapping("/savepage")
	public void savepage(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data, String numero, Double importo,
			String stato, String cliente) {
		Fattura f = new Fattura();
		f.setCliente(cs.getByNome(cliente));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
		String anno = data.format(formatter);
		f.setAnno(anno);
		f.setData(data);
		f.setImporto(importo);
		f.setNumero(numero);
		if (!sfc.existsByNome(stato)) {
			StatoFattura sf = new StatoFattura(stato);
			sfc.save(sf);
		}
		f.setStato(sfc.getByNome(stato));
		service.save(f);
	}

	@GetMapping("/delete/{id}")
	public void remove(@PathVariable Long id) {
		service.remove(id);
	}

	@GetMapping("/getbycliente")
	public ResponseEntity<List<Fattura>> getfatturabycliente(@RequestParam String cliente,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "4") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		List<Fattura> list = service.getByCliente(cliente, pag);
		return new ResponseEntity<List<Fattura>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/getbystato")
	public ResponseEntity<List<Fattura>> getfatturabystato(@RequestParam String stato,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "4") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		List<Fattura> list = service.getByStato(stato, pag);
		return new ResponseEntity<List<Fattura>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/getbydata")
	public ResponseEntity<List<Fattura>> getfatturabydata(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "4") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		List<Fattura> list = service.getByData(data, pag);
		return new ResponseEntity<List<Fattura>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/getbyanno")
	public ResponseEntity<List<Fattura>> getfatturabydata(@RequestParam String anno,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "4") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		List<Fattura> list = service.getByAnno(anno, pag);
		return new ResponseEntity<List<Fattura>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/getbyrangeimporto")
	public ResponseEntity<List<Fattura>> getfatturabyrangeimporto(@RequestParam(defaultValue = "0") Double minimo,
			@RequestParam(defaultValue = "10000000") Double massimo, @RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		List<Fattura> list = service.getByRangeImporto(minimo, massimo, pag);
		return new ResponseEntity<List<Fattura>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/getbyclienteview")
	public ModelAndView getfatturabyclienteview(@RequestParam String cliente,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "50") Integer size) {
		ModelAndView myModel = new ModelAndView();
		Pageable pag = PageRequest.of(page, size);
		List<Fattura> list = service.getByCliente(cliente, pag);
		myModel.addObject("fatture", list);
		myModel.setViewName("fatture");
		return myModel;
	}

	@GetMapping("/getbystatoview")
	public ModelAndView getfatturabystatoview(@RequestParam String stato, @RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "50") Integer size) {
		ModelAndView myModel = new ModelAndView();
		Pageable pag = PageRequest.of(page, size);
		List<Fattura> list = service.getByStato(stato, pag);
		myModel.addObject("fatture", list);
		myModel.setViewName("fatture");
		return myModel;
	}
	@GetMapping("/getbydataview")
	public ModelAndView getfatturabydataview(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "50") Integer size) {
		ModelAndView myModel=new ModelAndView();
		Pageable pag = PageRequest.of(page, size);
		List<Fattura> list = service.getByData(data, pag);
		myModel.addObject("fatture", list);
		myModel.setViewName("fatture");
		return myModel;
	}

	@GetMapping("/getbyannoview")
	public ModelAndView getfatturabydataview(@RequestParam String anno, @RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "50") Integer size) {
		ModelAndView myModel=new ModelAndView();
		Pageable pag = PageRequest.of(page, size);
		List<Fattura> list = service.getByAnno(anno, pag);
		myModel.addObject("fatture", list);
		myModel.setViewName("fatture");
		return myModel;
	}
	
	@GetMapping("/getbyrangeimportoview")
	public ModelAndView getfatturabyrangeimportoview(@RequestParam(defaultValue = "0") Double minimo,
			@RequestParam(defaultValue = "10000000") Double massimo, @RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "50") Integer size) {
		ModelAndView myModel=new ModelAndView();
		Pageable pag = PageRequest.of(page, size);
		List<Fattura> list = service.getByRangeImporto(minimo, massimo, pag);
		myModel.addObject("fatture", list);
		myModel.setViewName("fatture");
		return myModel;
	}
	@GetMapping("/fatturadetails/{id}")
	public ModelAndView getclienteDetails(@PathVariable Long id) {
		ModelAndView myModel=new ModelAndView();
		Fattura f=service.getById(id);
		myModel.addObject("fattura", f);
		myModel.setViewName("fatturadetails");
		return myModel;
		
	}@GetMapping("/getbyclienteviewadmin")
	public ModelAndView getfatturabyclienteviewadmin(@RequestParam String cliente,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "50") Integer size) {
		ModelAndView myModel = new ModelAndView();
		Pageable pag = PageRequest.of(page, size);
		List<Fattura> list = service.getByCliente(cliente, pag);
		myModel.addObject("fatture", list);
		myModel.setViewName("fattureadmin");
		return myModel;
	}

	@GetMapping("/getbystatoviewadmin")
	public ModelAndView getfatturabystatoviewadmin(@RequestParam String stato, @RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "50") Integer size) {
		ModelAndView myModel = new ModelAndView();
		Pageable pag = PageRequest.of(page, size);
		List<Fattura> list = service.getByStato(stato, pag);
		myModel.addObject("fatture", list);
		myModel.setViewName("fattureadmin");
		return myModel;
	}
	@GetMapping("/getbydataviewadmin")
	public ModelAndView getfatturabydataviewadmin(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "50") Integer size) {
		ModelAndView myModel=new ModelAndView();
		Pageable pag = PageRequest.of(page, size);
		List<Fattura> list = service.getByData(data, pag);
		myModel.addObject("fatture", list);
		myModel.setViewName("fattureadmin");
		return myModel;
	}

	@GetMapping("/getbyannoviewadmin")
	public ModelAndView getfatturabydataviewadmin(@RequestParam String anno, @RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "50") Integer size) {
		ModelAndView myModel=new ModelAndView();
		Pageable pag = PageRequest.of(page, size);
		List<Fattura> list = service.getByAnno(anno, pag);
		myModel.addObject("fatture", list);
		myModel.setViewName("fattureadmin");
		return myModel;
	}
	
	@GetMapping("/getbyrangeimportoviewadmin")
	public ModelAndView getfatturabyrangeimportoviewadmin(@RequestParam(defaultValue = "0") Double minimo,
			@RequestParam(defaultValue = "10000000") Double massimo, @RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "50") Integer size) {
		ModelAndView myModel=new ModelAndView();
		Pageable pag = PageRequest.of(page, size);
		List<Fattura> list = service.getByRangeImporto(minimo, massimo, pag);
		myModel.addObject("fatture", list);
		myModel.setViewName("fattureadmin");
		return myModel;
	}
}
