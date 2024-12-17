import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.taskmanagementsystems.api.dto.request.RegistrationUserRequestDto;
import com.example.taskmanagementsystems.api.dto.response.RegistrationUserResponseDto;
import com.example.taskmanagementsystems.impl.controller.UserControllerImpl;
import com.example.taskmanagementsystems.impl.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class UserControllerTest {

//  private static final Logger log = LoggerFactory.getLogger(UserControllerTest.class);
//
//  private MockMvc mockMvc;
//
//  private ObjectMapper objectMapper;
//
//  @Mock
//  private UserService userService;
//
//  @InjectMocks
//  private UserControllerImpl userController;
//
//  @BeforeEach
//  public void setUp() {
//    MockitoAnnotations.openMocks(this);
//    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
//    objectMapper = new ObjectMapper();
//    objectMapper.registerModule(new JavaTimeModule());
//  }
//
//  @Test
//  public void testRegisterNewUser() throws Exception {
//
//    UUID userId = UUID.randomUUID();
//    LocalDateTime dateTime = LocalDateTime.now();
//
//    RegistrationUserRequestDto requestDto = new RegistrationUserRequestDto(
//        "Test", "Test",
//        "Test", dateTime);
//
//    RegistrationUserResponseDto responseDto = new RegistrationUserResponseDto(
//        userId, "Created", dateTime);
//
//    when(userService.registerNewUser(any(RegistrationUserRequestDto.class))).thenReturn(
//        responseDto);
//
//    var response = mockMvc.perform(
//            MockMvcRequestBuilders.post("/api/user-service/registration/user")
//                .content(objectMapper.writeValueAsString(requestDto))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//        .andExpect(status().isCreated())
//        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//        .andExpect(MockMvcResultMatchers.jsonPath("$.user_id").value(userId.toString()))
//        .andReturn().getResponse();
//
//    Mockito.verify(userService, Mockito.times(1))
//        .registerNewUser(any(RegistrationUserRequestDto.class));
//
//    log.info("Response Content Type: {}", response.getContentType());
//    log.info("Response Content: {} ", response.getContentAsString());

//    ResponseEntity<RegistrationUserResponseDto> response = userController.registerNewUser(
//        requestDto);
//
//    assertEquals(HttpStatus.CREATED, response.getStatusCode());
//    assertEquals(responseDto, response.getBody());
  }
//
//  @Test
//  public void testGetUserInformation() {
//    UUID userId = UUID.randomUUID();
//    UserProfileResponseDto responseDto = new UserProfileResponseDto();
//    // Set properties on responseDto as needed
//
//    when(userService.getUserInformation(userId)).thenReturn(responseDto);
//
//    ResponseEntity<UserProfileResponseDto> response = userController.getUserInformation(userId);
//
//    assertEquals(HttpStatus.OK, response.getStatusCode());
//    assertEquals(responseDto, response.getBody());
//  }
//
//  @Test
//  public void testGetAllUsersPageable() {
//    List<UserProfileResponseDto> userList = Collections.singletonList(new UserProfileResponseDto());
//
//    when(userService.getAllUsersPageable(0, 10)).thenReturn(userList);
//
//    ResponseEntity<List<UserProfileResponseDto>> response = userController.getAllUsersPageable(0, 10);
//
//    assertEquals(HttpStatus.OK, response.getStatusCode());
//    assertEquals(userList, response.getBody());
//  }
//
//  @Test
//  public void testDeleteUser() {
//    UUID userId = UUID.randomUUID();
//    DeleteUserResponseDto deleteResponse = new DeleteUserResponseDto();
//    // Set properties on deleteResponse as needed
//
//    when(userService.deleteUser(userId)).thenReturn(deleteResponse);
//
//    ResponseEntity<DeleteUserResponseDto> response = userController.deleteUser(userId);
//
//    assertEquals(HttpStatus.OK, response.getStatusCode());
//    assertEquals(deleteResponse, response.getBody());
//  }
//}
