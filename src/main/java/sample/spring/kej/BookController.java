package sample.spring.kej;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
/*클라이언트(client)의 요청(request)을 받아서 
 응답(response)을 만들어내는 컨트롤러(controller) 클래스*/
@Controller
public class BookController {
	//브라우저 주소가 /create일 때 GET 방식으로 입력되었을 때 /book/create 경로의 뷰를 보여준다.
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
		}else { //상세페이지
			mav.setViewName("redirect:/detail?bookId=" + bookId);
		}
		
		return mav;
		}
	
	//책 상세 URL이 입력되면 실행되는 메소드를 작성한다. 
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
	//책 수정 화면의 첫화면을 위해, 기존에 있는 데이터베이스 불러오기
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam Map<String, Object> map) {
		Map<String, Object> detailMap = this.bookService.detail(map);
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", detailMap);
		mav.setViewName("/book/update");
		return mav;
	}
	//책 수정 화면에서 수정 기능으로 보내주는 파라미터 4개, 하나는 GET 파라미터로 전달되는 bookId
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView updatePost(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		boolean isUpdateSuccess = this.bookService.edit(map); 
		if(isUpdateSuccess) { //정상적으로 데이터가 갱신되면 확인을 위해 상세 페이지로 이동
			String bookId = map.get("bookId").toString();
			mav.setViewName("redirect:/detail?bookId=" + bookId);
		}else { //갱신이 안 될 경우, 갱신 화면을 다시 보여줌
			mav = this.update(map);
		}
		return mav;
	}
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView deletePost(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		boolean isDeleteSuccess = this.bookService.remove(map);
		if(isDeleteSuccess) { //삭제 성공, 목록으로 리다이렉트 
			mav.setViewName("redirect:/list");
		}else { //아니라면 다시 상세페이지로
			String bookId = map.get("bookId").toString();
			mav.setViewName("redirect:/detail?bookId=" + bookId);
		}
		return mav;
	}
}