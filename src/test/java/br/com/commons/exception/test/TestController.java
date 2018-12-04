package br.com.commons.exception.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.commons.exception.BusinessException;
import br.com.commons.exception.IntegrationException;

@RestController
public class TestController {

    public static final String BUSINESS_ERROR_CODE = "BUS-001";

    public static final String INTEGRATION_ERROR_CODE = "INT-001";

    public static final String BUSINESS_ERROR_PLACEHOLDER = "exception.business.bad-things";

    @GetMapping("/exception/business")
    public String throwBusinessException() {
        throw BusinessException.of(BUSINESS_ERROR_CODE, BUSINESS_ERROR_PLACEHOLDER);
    }

    @GetMapping("/exception/integration")
    public String throwIntegrationException() {
        throw IntegrationException.of(INTEGRATION_ERROR_CODE, "Some problems with legacy system");
    }

}
