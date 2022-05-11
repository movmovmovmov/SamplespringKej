package sample.spring.kej;
import java.util.Map;
import java.util.List;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

//BookService �������̽� �����ϱ�
//BookDao�� insert �޼ҵ带 �����Ű�� ���� �޼ҵ� �ۼ��� ��
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
	
	//1���� ���� ����� ���� �޾Ҵ��� �˻��ϱ�
	@Override
	public boolean edit(Map<String, Object> map) {
		int affectRowCount = this.bookDao.update(map);
		return affectRowCount == 1;
	}
	//1���� ���� ����� ���� �޾Ҵ��� �˻��ϱ�
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
	

