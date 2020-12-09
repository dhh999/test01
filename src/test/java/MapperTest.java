import com.bean.Customer;
import com.mapper.CustomerMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContext_*.xml")
public class MapperTest {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private SqlSession sqlSession;

    @Test
    public void test1(){
//        customerMapper.insertSelective(new Customer(null,"老王","男","12345-6789","天河学院"));

        //批量插入多个员工；批量，使用可以执行批量操作的sqlSession
        CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);
        for (int i = 0;i < 100;i++){
            String uid = UUID.randomUUID().toString().substring(0,5)+i;
            mapper.insertSelective(new Customer(null,uid,"男",uid+"-6789","天河学院"));
        }
        System.out.println("批量完成，加油！");
    }
}
