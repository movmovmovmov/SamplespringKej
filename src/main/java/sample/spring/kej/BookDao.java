package sample.spring.kej;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//매퍼XML을 실행시키는 DAO 클래스 생성하기
@Repository
public class BookDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//책 입력 쿼리를 실행하는 DAO 메소드 만들기
	public int insert(Map<String,Object>map) {
		return this.sqlSessionTemplate.insert("book.insert", map);
	}
	//selectOne 메소드는 쿼리 결과 행 수가 0이면 null 반환, 많이 나오면 TooManyResultException 예외로 빠짐
	public Map<String, Object> selectDetail(Map<String, Object> map) {
	    return this.sqlSessionTemplate.selectOne("book.select_detail", map);
	}
	
	public int update(Map<String, Object> map) {
		return this.sqlSessionTemplate.update("book.update", map);
	}
	//첫번째 파라미터는 쿼리id, 두번째 파라미터는 쿼리 파라미터이며 반환값은 영향받은 행 수 
	public int delete(Map<String, Object> map) {
		return this.sqlSessionTemplate.delete("book.delete", map);
	}
	public List<Map<String, Object>> selectList(Map<String, Object> map) {
		return this.sqlSessionTemplate.select
	}
}
