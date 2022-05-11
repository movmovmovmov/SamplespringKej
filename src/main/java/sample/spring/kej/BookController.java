package sample.spring.kej;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
/*Ŭ���̾�Ʈ(client)�� ��û(request)�� �޾Ƽ� 
 ����(response)�� ������ ��Ʈ�ѷ�(controller) Ŭ����*/
@Controller
public class BookController {
	//������ �ּҰ� /create�� �� GET ������� �ԷµǾ��� �� /book/create ����� �並 �����ش�.
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		return new ModelAndView("book/create");
	}
	
	@Autowired
	BookService bookService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createPost(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		String bookId = this.bookService.create(map);
		if(bookId == null) {
			mav.setViewName("redirect:/create");
		}else { //��������
			mav.setViewName("redirect:/detail?bookId=" + bookId);
		}
		
		return mav;
		}
	
	//å �� URL�� �ԷµǸ� ����Ǵ� �޼ҵ带 �ۼ��Ѵ�. 
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam Map<String, Object> map) {
		Map<String, Object> detailMap = this.bookService.detail(map);
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", detailMap);
		String bookId = map.get("bookId").toString();
		mav.addObject("bookId", bookId);
		mav.setViewName("/book/detail");
		return mav;
	}
	//å ���� ȭ���� ùȭ���� ����, ������ �ִ� �����ͺ��̽� �ҷ�����
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam Map<String, Object> map) {
		Map<String, Object> detailMap = this.bookService.detail(map);
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", detailMap);
		mav.setViewName("/book/update");
		return mav;
	}
	//å ���� ȭ�鿡�� ���� ������� �����ִ� �Ķ���� 4��, �ϳ��� GET �Ķ���ͷ� ���޵Ǵ� bookId
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView updatePost(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		boolean isUpdateSuccess = this.bookService.edit(map); 
		if(isUpdateSuccess) { //���������� �����Ͱ� ���ŵǸ� Ȯ���� ���� �� �������� �̵�
			String bookId = map.get("bookId").toString();
			mav.setViewName("redirect:/detail?bookId=" + bookId);
		}else { //������ �� �� ���, ���� ȭ���� �ٽ� ������
			mav = this.update(map);
		}
		return mav;
	}
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView deletePost(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		boolean isDeleteSuccess = this.bookService.remove(map);
		if(isDeleteSuccess) { //���� ����, ������� �����̷�Ʈ 
			mav.setViewName("redirect:/list");
		}else { //�ƴ϶�� �ٽ� ����������
			String bookId = map.get("bookId").toString();
			mav.setViewName("redirect:/detail?bookId=" + bookId);
		}
		return mav;
	}
}