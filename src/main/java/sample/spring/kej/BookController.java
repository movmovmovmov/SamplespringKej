package sample.spring.kej;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
/*클라이언트(client)의 요청(request)을 받아서 
 응답(response)을 만들어내는 컨트롤러(controller) 클래스*/
@Controller
public class BookController {
	//브라우저 주소가 /create일 때 GET 방식으로 입력되었을 때 /book/create 경로의 뷰를 보여준다.
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView create() {
		return new ModelAndView("book/create");
	}

}
