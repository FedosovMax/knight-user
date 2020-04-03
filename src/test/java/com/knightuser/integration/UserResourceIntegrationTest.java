package com.knightuser.integration;

import static com.knightuser.Constants.USERS_BASE_URL;
import static com.knightuser.Constants.buildDeleteUserByIdUrl;
import static com.knightuser.Constants.buildGetUserByIdBaseUrl;
import static com.knightuser.Constants.buildUpdateUserBaseUrl;
import static com.knightuser.TestConstants.buildIdJsonPath;
import static com.knightuser.TestConstants.buildJsonPathToLength;
import static com.knightuser.TestConstants.buildLoginJsonPath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knightuser.KnightUserApplication;
import com.knightuser.factories.UserFactory;
import com.knightuser.gateway.privatedb.repository.UserRepository;
import com.knightuser.gateway.privatedb.representation.User;
import com.knightuser.rest.request.UserRequestDto;
import javax.transaction.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KnightUserApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserResourceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void addUser_shouldAddUserAndReturnIt_whenRequestIsCorrect() throws Exception {
        UserRequestDto userRequestDto = UserFactory.createUserRequestDtoInstance();

        mockMvc.perform(post(USERS_BASE_URL)
            .content(objectMapper.writeValueAsString(userRequestDto))
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isCreated())
            .andExpect(jsonPath(buildLoginJsonPath()).exists());

        assertThat(userRepository.count()).isEqualTo(1);
    }

    @Test
    public void addUser_shouldRespondWithBadRequestStatus_whenLoginIsNull() throws Exception {
        UserRequestDto requestDto = UserFactory.createUserRequestDtoWithoutLoginInstance();

        mockMvc.perform(post(USERS_BASE_URL)
            .content(objectMapper.writeValueAsString(requestDto))
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest());

        assertThat(userRepository.count()).isEqualTo(0);
    }

    @Test
    public void addUser_shouldRespondWithBadRequestStatus_whenLoginConsistsOfSpaces() throws Exception {
        UserRequestDto requestDto = UserFactory.createUserRequestDtoWithEmptyLoginInstance();

        mockMvc.perform(post(USERS_BASE_URL)
            .content(objectMapper.writeValueAsString(requestDto))
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest());

        assertThat(userRepository.count()).isEqualTo(0);
    }

    @Test
    public void getAllUsers_shouldReturnAllUsers() throws Exception {
        userRepository.save(UserFactory.createUserInstance());
        userRepository.save(UserFactory.createUserInstance());

        mockMvc.perform(get(USERS_BASE_URL))
            .andExpect(status().isFound())
            .andExpect(jsonPath(buildJsonPathToLength()).value(2));
    }

    @Test
    public void getUserById_shouldReturnExistingUser_whenIdIsCorrect() throws Exception {
        User user = userRepository.save(UserFactory.createUserInstance());

        mockMvc.perform(get(buildGetUserByIdBaseUrl(user.getId())))
            .andExpect(status().isFound())
            .andExpect(jsonPath(buildIdJsonPath()).value(user.getId()))
            .andExpect(jsonPath(buildLoginJsonPath()).value(user.getLogin()));
    }

    @Test
    public void updateUser_shouldUpdateUserAndReturnIt_whenRequestIsCorrect() throws Exception {
        User user = userRepository.save(UserFactory.createUserInstance());
        UserRequestDto userRequestDto = UserFactory.updateUserRequestDtoInstance();

        mockMvc.perform(put(buildUpdateUserBaseUrl(user.getId()))
            .content(objectMapper.writeValueAsString(userRequestDto))
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath(buildLoginJsonPath()).value(userRequestDto.getLogin()));

        assertThat(userRepository.findById(user.getId()).get().getLogin())
            .isEqualTo(userRequestDto.getLogin());
    }

    @Test
    public void updateUser_shouldRespondWithBadRequestStatus_whenLoginIsNull() throws Exception {
        User user = userRepository.save(UserFactory.createUserInstance());
        UserRequestDto userRequestDto = UserFactory.updateUserRequestDtoWithoutLoginInstance();

        mockMvc.perform(put(buildUpdateUserBaseUrl(user.getId()))
            .content(objectMapper.writeValueAsString(userRequestDto))
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void updateUser_shouldRespondWithBadRequestStatus_whenLoginConsistsOfSpaces() throws Exception {
        User user = userRepository.save(UserFactory.createUserInstance());
        UserRequestDto userRequestDto = UserFactory.updateUserRequestDtoWithLoginConsistingSpacesInstance();

        mockMvc.perform(put(buildUpdateUserBaseUrl(user.getId()))
            .content(objectMapper.writeValueAsString(userRequestDto))
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void updateUser_shouldRespondWithBadRequestStatus_whenPasswordIsNull() throws Exception {
        User user = userRepository.save(UserFactory.createUserInstance());
        UserRequestDto userRequestDto = UserFactory.updateUserRequestDtoWithoutPasswordInstance();

        mockMvc.perform(put(buildUpdateUserBaseUrl(user.getId()))
            .content(objectMapper.writeValueAsString(userRequestDto))
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void deleteUser_shouldDeleteUser_whenIdIsCorrect() throws Exception {
        User user = userRepository.save(UserFactory.createUserInstance());

        mockMvc.perform(delete(buildDeleteUserByIdUrl(user.getId())))
            .andExpect(status().isOk());

        assertThat(userRepository.findById(user.getId())).isEmpty();
    }
}
