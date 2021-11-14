package it.epicode.beservice;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.beservice.model.Role;
import it.epicode.beservice.model.RoleType;
import it.epicode.beservice.model.User;
import it.epicode.beservice.model.UserDetailsImpl;
import it.epicode.beservice.repository.RoleRepository;
import it.epicode.beservice.repository.UserRepository;
import it.epicode.beservice.security.JwtUtils;
import it.epicode.beservice.security.LoginRequest;
import it.epicode.beservice.security.LoginResponse;
import it.epicode.beservice.security.SignupRequest;
import it.epicode.beservice.security.SignupResponse;
import it.epicode.beservice.security.WebSecurityConfig;



@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	PasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	WebSecurityConfig security;
	
	
	@PostMapping("/loginpage")
	public ModelAndView authenticateUserPage(LoginRequest loginRequest) {
		ModelAndView model = new ModelAndView();
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		authentication.getAuthorities();
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		if(roles.contains("ROLE_ADMIN")) {
			model.setViewName("scelteAdmin");
		}
		else{
			model.setViewName("scelte");
		}
		model.addObject(roles);
        return model;
	}
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser( @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		authentication.getAuthorities();
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(
				new LoginResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles, userDetails.getExpirationTime()));
	}
	
	@PostMapping("/signupform")
    public ModelAndView registerUserForm(SignupRequest signUpRequest) {
        // Verifica l'esistenza di Username e Email già registrate
		ModelAndView model = new ModelAndView();
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
        	 model.setViewName("username");
        	    return model;
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
        	model.setViewName("email");
            return model;
        }
        // Crea un nuovo user codificando la password
        User user = new User(signUpRequest.getUsername(),signUpRequest.getNome(),signUpRequest.getEmail(),encoder.encode(signUpRequest.getPassword()),signUpRequest.getCognome());
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
     // Verifica l'esistenza dei Role
        if (strRoles == null) {
            Role userRole = roleRepository.findByRoletype(RoleType.ROLE_USER).orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByRoletype(RoleType.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
                    roles.add(adminRole);
                    break;
                default:
                    Role userRole = roleRepository.findByRoletype(RoleType.ROLE_USER).orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
                    roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        authenticateUserPage(new LoginRequest(signUpRequest.getUsername(),signUpRequest.getPassword()));
        model.setViewName("scelte");
        return model;
    }
	@PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        // Verifica l'esistenza di Username e Email già registrate
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new SignupResponse("Errore: Username già in uso!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new SignupResponse("Errore: Email già in uso!"));
        }
        // Crea un nuovo user codificando la password
        User user = new User(signUpRequest.getUsername(),signUpRequest.getNome(),signUpRequest.getEmail(),encoder.encode(signUpRequest.getPassword()),signUpRequest.getCognome());
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
     // Verifica l'esistenza dei Role
        if (strRoles == null) {
            Role userRole = roleRepository.findByRoletype(RoleType.ROLE_USER).orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                case "ROLE_ADMIN":
                    Role adminRole = roleRepository.findByRoletype(RoleType.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
                    roles.add(adminRole);
                    break;
                default:
                    Role userRole = roleRepository.findByRoletype(RoleType.ROLE_USER).orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
                    roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new SignupResponse("User registrato con successo!"));
    }

}

