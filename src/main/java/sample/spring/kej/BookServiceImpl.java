package sample.spring.kej;
import java.util.Map;
import java.util.List;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

//BookService 인터페이스 구현하기
//BookDao의 insert 메소드를 실행시키는 서비스 메소드 작성할 것
@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookDao bookDao;
	@Override
	public String create(Map<String, Object> map) {
		int affectRowCount = this.bookDao.insert(map);
		if (affectRowCount == 1) {
			return map.get("book_id").toString();
			}
		return null;
		}
	@Override
	public Map<String, Object> detail(Map<String,Object> map){
		return this.bookDao.selectDetail(map);
	}
	
	//1개의 행이 제대로 영향 받았는지 검사하기
	@Override
	public boolean edit(Map<String, Object> map) {
		int affectRowCount = this.bookDao.update(map);
		return affectRowCount == 1;
	}
	//1개의 행이 제대로 영향 받았는지 검사하기
	@Override
	public boolean remove(Map<String, Object> map) {
		int affectRowCount = this.bookDao.delete(map);
		return affectRowCount == 1;
	}
	@Override
	public List<Map<String, Object>> list(Map<String, Object> map){
		return this.bookDao.selectList(map);
		
	}
	}
	

