package sample.spring.kej;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
/*Ŭ���̾�Ʈ(client)�� ��û(request)�� �޾Ƽ� 
 ����(response)�� ������ ��Ʈ�ѷ�(controller) Ŭ����*/
@Controller
public class BookController {
	//������ �ּҰ� /create�� �� GET ������� �ԷµǾ��� �� /book/create ����� �並 �����ش�.
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView create() {
		return new ModelAndView("book/create");
	}

}
