package it.epicode.beservice;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.epicode.beservice.model.Cliente;
import it.epicode.beservice.model.Fattura;
import it.epicode.beservice.model.Indirizzo;
import it.epicode.beservice.model.Role;
import it.epicode.beservice.model.RoleType;
import it.epicode.beservice.model.StatoFattura;
import it.epicode.beservice.model.TipoCliente;
import it.epicode.beservice.repository.RoleRepository;
import it.epicode.beservice.security.SignupRequest;
import it.epicode.beservice.services.IndirizzoService;
import it.epicode.beservice.services.UserService;

@Component
public class GestioneClientiCRUD implements CommandLineRunner {

	@Autowired
	RoleRepository roleRepo;
	@Autowired
	ProvinciaController pc;
	@Autowired
	RegioneController rc;
	@Autowired
	ComuneController cc;
	@Autowired
	UserService us;
	@Autowired
	IndirizzoService is;
	@Autowired
	ClienteController clc;
	@Autowired
	AuthController ac;
	@Autowired
	StatoFatturaController sfc;
	@Autowired
	FatturaController fc;

	@Override
	public void run(String... args) throws Exception {
		Role r = new Role(RoleType.ROLE_ADMIN);
		Role r1 = new Role(RoleType.ROLE_USER);
		roleRepo.save(r);
		roleRepo.save(r1);
		Set<String> roles = new HashSet<String>();
		roles.add("ROLE_ADMIN");
		ac.registerUser(new SignupRequest("Admin", "admin@gmail.com", roles, "Admin", "Admin", "User"));
		ac.registerUser(new SignupRequest("User", "user@gmail.com", "User", "User", "User"));
		rc.popola();
		pc.popola();
		cc.popola();
		Indirizzo i = new Indirizzo("Via Carcaricola", "29", "00133", "Periferia", cc.getByNome("Roma"));
		Indirizzo i1 = new Indirizzo("Via Lombardi", "121", "24058", "Periferia", cc.getByNome("Romano Canavese"));
		Indirizzo i2 = new Indirizzo("Via Nazario Sauro", "110", "20050", "Periferia", cc.getByNome("Correzzana"));
		Indirizzo i3 = new Indirizzo("Corso Alcide De Gasperi", "28", "70034", "Periferia", cc.getByNome("Viterbo"));
		Indirizzo i4 = new Indirizzo("Via Carlo Cattaneo", "65", "07040", "Periferia",
				cc.getByNome("Monguelfo-Tesido"));
		Indirizzo i5 = new Indirizzo("Via Callicratide", "84", "52010", "Periferia", cc.getByNome("Bibbiena"));
		Indirizzo i6 = new Indirizzo("Piazza Concordia", "5", "43059", "Tornolo", cc.getByNome("Tornolo"));
		Indirizzo i7 = new Indirizzo("Via Lago Febo", "113", "25049", "Iseo", cc.getByNome("Iseo"));
		Indirizzo i8 = new Indirizzo("Via Firense", "174", "86074", "Filignano", cc.getByNome("Filignano"));
		Indirizzo i9 = new Indirizzo("Piazza Guglielmo Pepe", "96", "62011", "San Vittore",
				cc.getByNome("San Vittore Olona"));
		Indirizzo i10 = new Indirizzo("Via Castelfidardo", "151", "87030", "Carolei", cc.getByNome("Carolei"));
		Indirizzo i11 = new Indirizzo("Via Giacinto Gigante", "41", "41026", "Periferia", cc.getByNome("Carenno"));
		Indirizzo i12 = new Indirizzo("Via Spalato", "78", "30012", "Periferia", cc.getByNome("Colmurano"));
		Indirizzo i13 = new Indirizzo("Via Giulio Camuzzoni", "165", "89121", "Periferia",
				cc.getByNome("Santa Caterina Albanese"));
		Indirizzo i14 = new Indirizzo("Via Giovanni Amendola", " 84", "26010", "Periferia",
				cc.getByNome("Ripalta Guerina"));
		Indirizzo i15 = new Indirizzo("Via Santa Maria di Costantinopoli", "135", "46040", "marittima",
				cc.getByNome("Rodigo"));
		Indirizzo i16 = new Indirizzo("Via Francesco Del Giudice", "161", "50030", "montagna",
				cc.getByNome("Pancarana"));
		Indirizzo i17 = new Indirizzo("Via Duomo", "139", "57020", "centro", cc.getByNome("Pelago"));
		Indirizzo i18 = new Indirizzo("Via Antonio da Legnago", "37", "89010", "marittima", cc.getByNome("Cannara"));
		Indirizzo i19 = new Indirizzo("Via Nazario Sauro", "156", "20012", "centro", cc.getByNome("Cornaredo"));
		Indirizzo i20 = new Indirizzo("Via Croce Rossa", "176", "09070", "centro", cc.getByNome("Narbolia"));
		is.save(i);
		is.save(i1);
		is.save(i2);
		is.save(i3);
		is.save(i4);
		is.save(i5);
		is.save(i6);
		is.save(i7);
		is.save(i8);
		is.save(i9);
		is.save(i10);
		is.save(i11);
		is.save(i12);
		is.save(i13);
		is.save(i14);
		is.save(i15);
		is.save(i16);
		is.save(i17);
		is.save(i18);
		is.save(i19);
		is.save(i20);
		Cliente c = new Cliente("Edimedia Grafica e Comunicazione", "198731", TipoCliente.SPA, "edimedia@gmail.com",
				"edimedia@pec.com", "06296423", "Davide", "Arnaldo", "03835924188", "davide@gmail.com", i, i,
				LocalDate.of(2021, 11, 13), 120000.0);
		Cliente c1 = new Cliente("Ympronta", "273138212", TipoCliente.SAS, "ympronta@gmail.com", "ympronta@pec.com",
				"0623217432", "Demetrio", "Lettiere", "3188555015", "demetrio@gmail.com", i1, i1,
				LocalDate.of(2021, 11, 14), 300000.0);
		Cliente c2 = new Cliente("3M", "263197469", TipoCliente.PA, "3M@gmail.com", "3M@pec.com", "06125873", "Imelda",
				"Rossi", "3537516110", "imelda@gmail.com", i2, i2, LocalDate.of(2021, 11, 14), 220000.0);
		Cliente c3 = new Cliente("Adidas", "198731", TipoCliente.SRL, "adidas@gmail.com", "adidas@pec.com",
				"0678923492", "Venera", "Folliero", "3835924188", "venera@gmail.com", i3, i3,
				LocalDate.of(2021, 11, 15), 240000.0);
		Cliente c4 = new Cliente("Blaupunkt", "198731", TipoCliente.SAS, "blaupunkt@gmail.com", "blaupunkt@pec.com",
				"06452131", "Manfredo", "Baresi", "03835924188", "manfredo@gmail.com", i4, i4,
				LocalDate.of(2021, 11, 13), 167000.0);
		Cliente c5 = new Cliente("Carrefour", "46213889", TipoCliente.SPA, "carrefour@gmail.com", "carrefour@pec.com",
				"06351282", "Virginio", "Longo", "3763194918", "virginio@gmail.com", i5, i5, LocalDate.of(2021, 11, 16),
				320000.0);
		Cliente c6 = new Cliente("Alea Spa", "04338490404", TipoCliente.SPA, "info@alea.it", "alea@PEC.it", "054103425", "Elena", "Rossi", "3402587649","elenarossi@alea.it", i6, i6, LocalDate.of(2021, 05, 15), 150574.00);
		Cliente c7 = new Cliente("Edison Spa", "78526395412", TipoCliente.SPA, "info@edison.it", "edison@PEC.it", "087094536", "Elia", "Viesi", "3408956421","eliaviesi@edison.it", i7, i7,LocalDate.of(2020, 10, 28), 552634.00);
		Cliente c8 = new Cliente("ABB Spa", "04330404849", TipoCliente.SPA, "info@abb.it", "abb@PEC.it", "0463258713", "Marta", "Verdi", "3474425613","martaverdi@abb.it", i8, i8, LocalDate.of(2021, 07, 14), 495263.00);
		Cliente c9 = new Cliente("TaoSystem Srl", "04334045826", TipoCliente.SRL, "info@taosystem.it", "taosystem@PEC.it", "0562034598", "Alessia", "Lorenzi", "3485206392","alessialorenzi@taosystem.it", i9 ,i9, LocalDate.of(2021, 01, 15), 347404.00);
		Cliente c10 = new Cliente("Pam Srl", "84908485942", TipoCliente.SPA, "info@pam.it", "pam@PEC.it", "0899254166", "Marco", "Molinari", "3201148695","marcomolinari@pam.it", i10, i10, LocalDate.of(2020, 05, 06), 571504.00);
		Cliente c11 = new Cliente("Kodak", "1263127", TipoCliente.SPA, "kodak@gmail.com",
				"kodak@pec.com", "06321689", "Donatella", "Monaldo", "3971694873", "donatella@gmail.com", i11, i11,
				LocalDate.of(2021, 11, 22), 260000.0);
		Cliente c12 = new Cliente("Google", "6421921", TipoCliente.SPA, "google@gmail.com",
				"google@pec.com", "06123231", "Cristina", "Milani", "3442823927", "cristina@gmail.com", i12, i12,
				LocalDate.of(2021, 11, 13), 726000.0);
		Cliente c13 = new Cliente("eBay", "4692343", TipoCliente.SRL, "ebay@gmail.com",
				"ebay@pec.com", "06624938", "Filomena", "Dellucci", "3772269390", "filomena@gmail.com", i13, i13,
				LocalDate.of(2021, 11, 24), 320000.0);
		Cliente c14 = new Cliente("Daewoo", "2762583", TipoCliente.SPA, "daewoo@gmail.com",
				"daewoo@pec.com", "0636934", "Emma", "Pisano", "3712578531", "emma@gmail.com", i14, i14,
				LocalDate.of(2021, 11, 27), 450000.0);
		Cliente c15 = new Cliente("Sgarbi Digital Marketing", "2645668546", TipoCliente.SAS, "sdm@email.com",
				"sdm@pec.com", "87635411", "Galileo", "Napolitani", "3427375", "galileo@email.com", i15, i15,
				LocalDate.of(2020, 6, 30), 234526.00);
		Cliente c16 = new Cliente("Lpe Production", "5445416512", TipoCliente.SPA, "lpe@email.com", "lpe@pec.com",
				"3457833", "Ottavio", "Lettiere", "21343757", "ottavio@email.com", i16, i16, LocalDate.of(2021, 5, 5),
				125336.00);
		Cliente c17 = new Cliente("Geeksolution", "6516541656", TipoCliente.PA, "geeksolution@email.com",
				"geeksolution@pec.com", "717853", "Alice", "Pantaleone", "42375378573", "alice@email.com", i17, i17,
				LocalDate.of(2018, 7, 7), 322514.00);
		Cliente c18 = new Cliente("Weber Gabriele", "654165462", TipoCliente.SPA, "wg@email.com", "wg@pec.com", "124352",
				"Espedito", "Calabresi", "537537", "calabresi@email.com", i18, i18, LocalDate.of(2020, 2, 15),
				124563.00);
		Cliente c19 = new Cliente("Italiaonline Web Agency", "5864454646", TipoCliente.SRL, "iwa@email.com",
				"iwa@pec.com", "15052100", "Napoleone", "Udinese", "7357537", "napoleons@email.com", i19, i19,
				LocalDate.of(2021, 10, 3), 123458.00);
		Cliente c20 = new Cliente("Dunder Mifflin", "1234569", TipoCliente.SAS, "ddm@email.com", "ddm@pec.com",
				"56165656", "Micheal", "Scott", "2646465", "m.scott@email.com", i20, i20, LocalDate.of(2020, 10, 3),
				200000.05);
		clc.save(c);
		clc.save(c1);
		clc.save(c2);
		clc.save(c3);
		clc.save(c4);
		clc.save(c5);
		clc.save(c6);
		clc.save(c7);
		clc.save(c8);
		clc.save(c9);
		clc.save(c10);
		clc.save(c11);
		clc.save(c12);
		clc.save(c13);
		clc.save(c14);
		clc.save(c15);
		clc.save(c16);
		clc.save(c17);
		clc.save(c18);
		clc.save(c19);
		clc.save(c20);
		StatoFattura sf=new StatoFattura("SALDATA");
		StatoFattura sf1=new StatoFattura("DA SALDARE");
		sfc.save(sf);
		sfc.save(sf1);
		Fattura f1 = new Fattura(LocalDate.of(2021, 10, 15), "123456", 2000.00, sf, c1);
        Fattura f2 = new Fattura(LocalDate.of(2020, 2, 24), "123432", 300.00, sf1, c1);
        Fattura f3 = new Fattura(LocalDate.of(2021, 7, 15), "9655", 970.12, sf, c2);
        Fattura f4 = new Fattura(LocalDate.of(2021, 3, 2), "6548", 10450.00, sf1, c2);
        Fattura f5 = new Fattura(LocalDate.of(2018, 1, 16), "123456", 12345.00, sf, c3);
        Fattura f6 = new Fattura(LocalDate.of(2021, 7, 30), "54654", 3651.00, sf1, c3);
        Fattura f7 = new Fattura(LocalDate.of(2012, 7, 4), "566516", 326.00, sf, c4);
        Fattura f8 = new Fattura(LocalDate.of(2014, 11, 9), "21561", 7532.00, sf1, c4);
        Fattura f9 = new Fattura(LocalDate.of(2021, 3, 16), "31516", 1245.00, sf1, c5);
        Fattura f10 = new Fattura(LocalDate.of(2018, 10, 18), "1564", 5465.00, sf, c5);
        Fattura f11 = new Fattura(LocalDate.of(2020, 7, 15), "23565", 3565.00, sf1, c6);
        Fattura f12 = new Fattura(LocalDate.of(2021, 3, 20), "94565", 102.00, sf, c6);
        Fattura f13 = new Fattura(LocalDate.of(2021, 11, 25), "1256", 320.00, sf, c7);
        Fattura f14 = new Fattura(LocalDate.of(2021, 8, 5), "12345", 420.00, sf, c7);
        Fattura f15 = new Fattura(LocalDate.of(2020, 4, 24), "24235", 7546.00, sf, c8);
        Fattura f16 = new Fattura(LocalDate.of(2018, 12, 31), "24557", 12125.00, sf, c8);
        Fattura f17 = new Fattura(LocalDate.of(2021, 4, 7), "45783", 2230.00, sf1, c9);
        Fattura f18 = new Fattura(LocalDate.of(2021, 6, 3), "47537", 547.00, sf1, c9);
        Fattura f19 = new Fattura(LocalDate.of(2019, 3, 12), "354165", 22000.00, sf, c10);
        Fattura f20 = new Fattura(LocalDate.of(2020, 7, 15), "15166", 1245.00, sf1, c10);
        Fattura f21 = new Fattura(LocalDate.of(2021, 3, 4), "24276", 422.00, sf1, c11);
        Fattura f22 = new Fattura(LocalDate.of(2021, 4, 21), "2738", 412583.00, sf, c11);
        Fattura f23 = new Fattura(LocalDate.of(2021, 12, 15), "154556", 3400.00, sf, c12);
        Fattura f24 = new Fattura(LocalDate.of(2021, 10, 24), "36556", 2200.00, sf, c12);
        Fattura f25 = new Fattura(LocalDate.of(2018, 7, 24), "7377", 75564.00, sf, c13);
        Fattura f26 = new Fattura(LocalDate.of(2019, 9, 12), "12357", 42.00, sf1, c13);
        Fattura f27 = new Fattura(LocalDate.of(2021, 7, 15), "123456", 2000.00, sf, c14);
        Fattura f28 = new Fattura(LocalDate.of(2020, 8, 20), "123456", 2000.00, sf, c14);
        Fattura f29 = new Fattura(LocalDate.of(2021, 9, 4), "456", 31235.00, sf1, c15);
        Fattura f30 = new Fattura(LocalDate.of(2021, 10, 6), "56", 3225.00, sf, c15);
        Fattura f31 = new Fattura(LocalDate.of(2028, 3, 21), "121", 545.00, sf, c16);
        Fattura f32 = new Fattura(LocalDate.of(2021, 5, 23), "856", 5.00, sf, c16);
        Fattura f33 = new Fattura(LocalDate.of(2022, 9, 9), "48946", 6584.00, sf, c17);
        Fattura f34 = new Fattura(LocalDate.of(2024, 3, 4), "1", 1235.00, sf, c17);
        Fattura f35 = new Fattura(LocalDate.of(2021, 10, 30), "5166", 230.00, sf, c18);
        Fattura f36 = new Fattura(LocalDate.of(2021, 3, 9), "10", 36.00, sf, c18);
        Fattura f37 = new Fattura(LocalDate.of(2021, 6, 3), "13", 256.00, sf, c19);
        Fattura f38 = new Fattura(LocalDate.of(2021, 11, 15), "163", 986.00, sf, c19);
        Fattura f39 = new Fattura(LocalDate.of(2021, 12, 25), "123456", 2000.00, sf, c20);
        Fattura f40 = new Fattura(LocalDate.of(2021, 12, 31), "123456", 2000.00, sf, c20);
        fc.save(f1);
        fc.save(f2);
        fc.save(f3);
        fc.save(f4);
        fc.save(f5);
        fc.save(f6);
        fc.save(f7);
        fc.save(f8);
        fc.save(f9);
        fc.save(f10);
        fc.save(f11);
        fc.save(f12);
        fc.save(f13);
        fc.save(f14);
        fc.save(f15);
        fc.save(f16);
        fc.save(f17);
        fc.save(f18);
        fc.save(f19);
        fc.save(f20);
        fc.save(f21);
        fc.save(f22);
        fc.save(f23);
        fc.save(f24);
        fc.save(f25);
        fc.save(f26);
        fc.save(f27);
        fc.save(f28);
        fc.save(f29);
        fc.save(f30);
        fc.save(f31);
        fc.save(f32);
        fc.save(f33);
        fc.save(f34);
        fc.save(f35);
        fc.save(f36);
        fc.save(f37);
        fc.save(f38);
        fc.save(f39);
        fc.save(f40);
        
        
	}

}