package com.wipro.bartenders.users.api.common.audit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AuditBodyUtil.class)
public class AuditRequestWrapperTest {

    @Test
    public void getBody_servletRequestWithBody_wrapperHasBody() throws Exception {
        new TestSpec()
                .given_servletRequest_body()
                .when_built_request_wrapper()
                .then_wrapper_has_body();
    }

    @Test
    public void getHeader_servletRequestWithHeaders_wrapperHasHeaders() throws Exception {
        new TestSpec()
                .given_servletRequest_headers()
                .when_built_request_wrapper()
                .then_getHeader_return_header();
    }

    @Test
    public void getHeaders_servletRequestWithHeaders_wrapperHasHeaders() throws Exception {
        new TestSpec()
                .given_servletRequest_headers()
                .when_built_request_wrapper()
                .then_getHeaders_return_header();
    }

    @Test
    public void setAuditRequestHeaders_givenHeaders_headersAreSet() throws Exception {
        new TestSpec()
                .given_wrapper()
                .given_auditRequestHeaders()
                .when_called_setAuditRequestHeaders()
                .then_getAuditRequestHeaders_return_headers()
                .then_headerMap_contains_audit_headers();
    }


    class TestSpec {


        AuditRequestWrapper request;

        @Mock
        HttpServletRequest httpServletRequest;


        String requestBody;
        AuditRequestHeaders headers;
        Enumeration<String> uaEnum;
        Enumeration<String> contentTypeEnum;


        TestSpec() {
            MockitoAnnotations.initMocks(this);
            PowerMockito.mockStatic(AuditBodyUtil.class);
        }

        TestSpec given_servletRequest() throws Exception {
            this.given_servletRequest_body()
                    .given_servletRequest_headers();
            return this;
        }

        TestSpec given_servletRequest_body() throws IOException {
            requestBody = "{}";
            given(AuditBodyUtil.readBody(any())).willReturn(requestBody);
            given(httpServletRequest.getHeaderNames()).willReturn(Collections.emptyEnumeration());
            return this;
        }

        TestSpec given_servletRequest_headers() throws Exception {

            List<String> headerNames = Arrays.asList("Content-Type", "User-Agent");
            given(httpServletRequest.getHeaderNames()).willReturn(Collections.enumeration(headerNames));

            String content = "Content-Type";
            List<String> contentTypeList = Arrays.asList("application/json");
            contentTypeEnum = Collections.enumeration(contentTypeList);
            given(httpServletRequest.getHeaders(content)).willReturn(contentTypeEnum);
            given(AuditBodyUtil.joinHeaders(contentTypeEnum)).willReturn(contentTypeList.get(0));


            String ua = "User-Agent";
            List<String> uaList = Arrays.asList("wget", "curl");
            uaEnum = Collections.enumeration(uaList);
            given(httpServletRequest.getHeaders(ua)).willReturn(uaEnum);
            given(AuditBodyUtil.joinHeaders(uaEnum)).willReturn("wget,curl");

            return this;
        }

        TestSpec when_built_request_wrapper() throws IOException {
            request = new AuditRequestWrapper(httpServletRequest);
            return this;
        }

        TestSpec then_wrapper_has_body() {
            assertThat(this.request.getBody()).isEqualTo(this.requestBody);
            return this;
        }

        TestSpec given_wrapper() throws Exception {
            this.given_servletRequest_body()
                    .given_servletRequest_headers()
                    .when_built_request_wrapper();
            return this;
        }

        TestSpec given_auditRequestHeaders() {
            headers = AuditRequestHeaders.builder()
                    .correlationId("correlationId")
                    .requestId("requestId")
                    .saveAuditData("saveAuditData")
                    .simulated("simulated")
                    .userId("userId")
                    .build();
            return this;
        }

        TestSpec when_called_setAuditRequestHeaders() {
            this.request.setAuditRequestHeaders(this.headers);
            return this;
        }

        TestSpec then_getAuditRequestHeaders_return_headers() {
            assertThat(this.request.getAuditRequestHeaders()).isEqualTo(this.headers);
            return this;
        }

        TestSpec then_headerMap_contains_audit_headers() {
            assertThat(this.request.getHeader(AuditRequestHeadersConstants.HEADER_SIMULATE)).isEqualTo(this.headers.getSimulated());
            assertThat(this.request.getHeader(AuditRequestHeadersConstants.HEADER_USER_ID)).isEqualTo(this.headers.getUserId());
            assertThat(this.request.getHeader(AuditRequestHeadersConstants.HEADER_SAVE_AUDIT_DATA)).isEqualTo(this.headers.getSaveAuditData());
            assertThat(this.request.getHeader(AuditRequestHeadersConstants.HEADER_RQST_ID)).isEqualTo(this.headers.getRequestId());
            assertThat(this.request.getHeader(AuditRequestHeadersConstants.HEADER_CORRELATION_ID)).isEqualTo(this.headers.getCorrelationId());
            return this;
        }

        TestSpec then_wrapper_has_headers() {
            assertThat(this.request.getHeader("Content-Type")).isEqualTo("application/json");
            assertThat(this.request.getHeader("User-Agent")).isEqualTo("wget,curl");
            return this;
        }

        TestSpec then_getHeader_return_header() {
            assertThat(this.request.getHeader("User-Agent"))
                    .isEqualTo("wget,curl");
            assertThat(this.request.getHeader("Content-Type"))
                    .isEqualTo("application/json");
            return this;
        }

        TestSpec then_getHeaders_return_header() {
            assertThat(enumerationToList(this.request.getHeaders("User-Agent")))
                    .isEqualTo(Arrays.asList("wget,curl"));
            assertThat(enumerationToList(this.request.getHeaders("Content-Type")))
                    .isEqualTo(Arrays.asList("application/json"));
            return this;
        }

        <T> List<T> enumerationToList(Enumeration<T> enumeration){
            List<T> list = new ArrayList<>();
            while (enumeration.hasMoreElements()) {
                list.add(enumeration.nextElement());
            }
            return list;
        }

    }
}
