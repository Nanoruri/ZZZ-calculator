package me.jh.zenless.calc.filter;

import me.jh.zenless.calc.config.WebFilterConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class LoggingFilterTest {

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private LoggingFilter loggingFilter;


    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    public void setUp() {
         request = new MockHttpServletRequest();
         response = new MockHttpServletResponse();

    }

    @Test
    public void initLogsInitialization() throws ServletException {
        loggingFilter.init(mock(FilterConfig.class));
    }


    @Test
    public void doFilterLogsRequestAndResponse() throws IOException, ServletException {
        request.setRequestURI("/api/test");

        loggingFilter.doFilter(request, response, filterChain);

        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void doFilterSkipsImagesResources() throws IOException, ServletException {
        request.setRequestURI("/images/test");

        loggingFilter.doFilter(request, response, filterChain);

        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        verify(filterChain).doFilter(request, response);
        verifyNoMoreInteractions(filterChain);
    }

    @Test
    public void doFilterHandlesException() throws IOException, ServletException {
        request.setRequestURI("/api/test");
        doThrow(new RuntimeException("서버 에러")).when(filterChain).doFilter(request, response);

        loggingFilter.doFilter(request, response, filterChain);

        assertEquals(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response.getStatus());
        assertEquals(500, response.getStatus());
        assertThrows(RuntimeException.class, () -> filterChain.doFilter(request, response));
    }

    @Test
    public void destroyLogsDestruction() {
        loggingFilter.destroy();
    }
}