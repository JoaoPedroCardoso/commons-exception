package br.com.commons.exception.handler;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import br.com.commons.exception.response.ErrorMessageResponse;
import br.com.commons.exception.test.TestApp;
import br.com.commons.exception.test.TestController;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApp.class)
@AutoConfigureMockMvc
@EnableControllerExceptionHandling
public class ExceptionHandlingControllerAdviceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("should handle BusinessException")
    public void testBusinessExceptionHandling() throws Exception {
        ErrorMessageResponse errorMessageResponse = ErrorMessageResponse.of(TestController.BUSINESS_ERROR_CODE, "Bad things happen");

        mockMvc.perform(get("/exception/business"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(errorMessageResponse.getCode()))
                .andExpect(jsonPath("$.message").value(errorMessageResponse.getMessage()));
    }

    @Test
    @DisplayName("should handle IntegrationExceptions")
    public void testIntegrationExceptionHandling() throws Exception {
        mockMvc.perform(get("/exception/integration"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code").value(TestController.INTEGRATION_ERROR_CODE))
                .andExpect(jsonPath("$.message").value("Integration failure"));
    }

}
