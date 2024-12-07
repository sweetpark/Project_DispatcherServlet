package example.Servlet.web.frontcontroller.v4;

import example.Servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV4 {
    String process(Map<String, String>paramMap, Map<String, Object> model);
}
