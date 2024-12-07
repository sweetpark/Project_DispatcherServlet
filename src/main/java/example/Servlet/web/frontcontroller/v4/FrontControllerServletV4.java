package example.Servlet.web.frontcontroller.v4;

import example.Servlet.web.frontcontroller.ModelView;
import example.Servlet.web.frontcontroller.MyView;
import example.Servlet.web.frontcontroller.v3.ControllerV3;
import example.Servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import example.Servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import example.Servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import example.Servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import example.Servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import example.Servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name="frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();
    private Map<String, Object> model = new HashMap<>();

    public FrontControllerServletV4(){
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();

        ControllerV4 controller = controllerMap.get(requestURI);
        if(controller == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(req);
        String viewName = controller.process(paramMap, model);



        MyView view = viewResolver(viewName);
        view.render(model, req, resp);
    }


    private Map<String, String> createParamMap(HttpServletRequest request){
        Map<String, String> paramMap = new HashMap<>();

        Enumeration<String> em = request.getParameterNames();
        while(em.hasMoreElements()){
            String paramName = em.nextElement();
            paramMap.put(paramName, request.getParameter(paramName));
        }
//       request.getParameterNames().asIterator().forEachRemaining(paramName-> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

    private MyView viewResolver(String viewName){
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
