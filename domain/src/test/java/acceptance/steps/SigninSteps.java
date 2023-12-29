//package acceptance.steps;
//
//import com.ecommerce.model.auth.AuthRequest;
//import com.ecommerce.model.auth.AuthResponse;
//import com.ecommerce.port.adapters.repositories.CivilityRepositoryPort;
//import com.ecommerce.port.adapters.repositories.UserRepositoryPort;
//import com.ecommerce.security.JwtUtils;
//import com.ecommerce.security.UserDetailsImpl;
//import com.ecommerce.service.AuthenticationService;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Collections;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.util.AssertionErrors.assertNull;
//
//public class SigninSteps {
//
//    private AuthRequest authRequest;
//    private AuthResponse authResponse;
//    private AuthenticationService authenticationService;
//    @Mock
//    private UserRepositoryPort userRepository;
//    @Mock
//    private AuthenticationManager authenticationManager;
//    private Exception thrownException;
//
//    CivilityRepositoryPort civilityRepository;
//    @Mock
//    PasswordEncoder encoder;
//    @Mock
//    JwtUtils jwtUtils;
//
//    public SigninSteps() {
//        MockitoAnnotations.openMocks(this);
//        authenticationService = new AuthenticationService(authenticationManager, userRepository, civilityRepository, encoder, jwtUtils);
//    }
//
//    @Given("a user exists with an email {string}")
//    public void aUserExistsWithAnEmailAndAndRole(String email) {
//        when(userRepository.existsByEmail(email)).thenReturn(true);
//
//    }
//
//    @When("the user with role {string} attempts to log in with email {string} and password {string}")
//    public void theUserWithAttemptsToLogInWithEmailAndPassword(String role, String email, String password) {
//        try {
//            UserDetailsImpl user = new UserDetailsImpl(
//                    1L, email, password, Collections.singletonList(new SimpleGrantedAuthority(role)));
//            Authentication auth = new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
//
//            when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password))).thenReturn(auth);
//            when(jwtUtils.generateToken(email, role)).thenReturn("jwt-token");
//
//            authRequest = new AuthRequest(email, password);
//            authResponse = authenticationService.signIn(authRequest);
//
//            assertNotNull(authResponse, "authResponse should not be null");
//        } catch (Exception e) {
//            thrownException = e;
//            e.printStackTrace();
//        }
//    }
//
//    @Then("the user should be authenticated with success")
//    public void theUserShouldBeAuthenticatedWithSuccess() {
//        assertNull("No exception should be thrown", thrownException);
//    }
//
//    @And("the user should receive a token with userDetails")
//    public void theUserShouldReceiveAValidToken() {
//        assertEquals("jwt-token", authResponse.getToken());
//        assertNotNull(authResponse.getUser());
//    }
//
//
//
//
//}
