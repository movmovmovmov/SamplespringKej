package sample.spring.kej;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//����XML�� �����Ű�� DAO Ŭ���� �����ϱ�
@Repository
public class BookDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//å �Է� ������ �����ϴ� DAO �޼ҵ� �����
	public int insert(Map<String,Object>map) {
		return this.sqlSessionTemplate.insert("book.insert", map);
	}
	//selectOne �޼ҵ�� ���� ��� �� ���� 0�̸� null ��ȯ, ���� ������ TooManyResultException ���ܷ� ����
	public Map<String, Object> selectDetail(Map<String, Object> map) {
	    return this.sqlSessionTemplate.selectOne("book.select_detail", map);
	}
	
	public int update(Map<String, Object> map) {
		return this.sqlSessionTemplate.update("book.update", map);
	}
	//ù��° �Ķ���ʹ� ����id, �ι�° �Ķ���ʹ� ���� �Ķ�����̸� ��ȯ���� ������� �� �� 
	public int delete(Map<String, Object> map) {
		return this.sqlSessionTemplate.delete("book.delete", map);
	}
	public List<Map<String, Object>> selectList(Map<String, Object> map) {
		return this.sqlSessionTemplate.select
	}
}
