import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.lab3.HelloServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class HelloServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    private HelloServlet servlet;
    private StringWriter responseBody;

    @BeforeEach
    void setUp() throws IOException {
        servlet = new HelloServlet();
        responseBody = new StringWriter();
        // The response mock hands out a writer that we can inspect afterwards.
        lenient().when(response.getWriter()).thenReturn(new PrintWriter(responseBody, true));
    }

    @Test
    @DisplayName("doGet sets the content type to text/html")
    void doGet_setsContentType() throws IOException {
        servlet.init();

        servlet.doGet(request, response);

        verify(response).setContentType("text/html");
    }

    @Test
    @DisplayName("doGet writes the complete HTML page with the greeting")
    void doGet_writesHelloWorldPage() throws IOException {
        servlet.init();

        servlet.doGet(request, response);

        String body = responseBody.toString();
        assertTrue(body.contains("<html><body>"), "body should open with <html><body>");
        assertTrue(body.contains("<h1>Hello World!</h1>"), "body should contain the greeting");
        assertTrue(body.contains("</body></html>"), "body should close the page");
    }

    @Test
    @DisplayName("doGet writes the tags in the correct order")
    void doGet_writesTagsInOrder() throws IOException {
        servlet.init();

        servlet.doGet(request, response);

        String body = responseBody.toString();
        int open = body.indexOf("<html><body>");
        int heading = body.indexOf("<h1>Hello World!</h1>");
        int close = body.indexOf("</body></html>");
        assertTrue(open >= 0 && open < heading && heading < close);
    }

    @Test
    @DisplayName("doGet requests the writer exactly once")
    void doGet_requestsWriterOnce() throws IOException {
        servlet.init();

        servlet.doGet(request, response);

        verify(response, times(1)).getWriter();
    }

    @Test
    @DisplayName("doGet does not read anything from the request")
    void doGet_doesNotTouchRequest() throws IOException {
        servlet.init();

        servlet.doGet(request, response);

        verifyNoInteractions(request);
    }

    @Test
    @DisplayName("Without init() the message is not set, so the page shows \"null\"")
    void doGet_withoutInit_showsNull() throws IOException {
        servlet.doGet(request, response);

        assertTrue(responseBody.toString().contains("<h1>null</h1>"));
    }

    @Test
    @DisplayName("destroy() completes without errors")
    void destroy_doesNotThrow() {
        assertDoesNotThrow(() -> servlet.destroy());
    }
}