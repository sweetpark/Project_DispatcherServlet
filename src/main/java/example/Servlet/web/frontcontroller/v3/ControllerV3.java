package example.Servlet.web.frontcontroller.v3;

import example.Servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String, String>paramMap);
}
