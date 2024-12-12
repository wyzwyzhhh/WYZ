import cn.ssm.model.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductTest {

    private static InputStream inputStream = null;
    private static SqlSessionFactory sqlSessionFactory = null;
    static{
        // 根据路径加载mybatis配置文件
        try {
            inputStream = Resources.getResourceAsStream("./mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void save(Product product){
        // 通过工厂创建连接对象
        SqlSession session =  sqlSessionFactory.openSession();
        try{
            session.insert("aa.bb.cc.save",product);
//            Integer.parseInt("xyz");
            session.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
            session.rollback();
        }finally {
            session.close();
        }
    }

    public static void update(Product product){
        // 通过工厂创建连接对象
        SqlSession session =  sqlSessionFactory.openSession();
        try{
            session.update("aa.bb.cc.update",product);
//            Integer.parseInt("xyz");
            session.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
            session.rollback();
        }finally {
            session.close();
        }
    }

    public static List<Product> query(String keyword, int current_page, int size){
        Map<String,Object> hashMap = new HashMap<String,Object>();
        hashMap.put("start",(current_page - 1) * size);
        hashMap.put("size",size);
        hashMap.put("keyword","%%");
        SqlSession session =  sqlSessionFactory.openSession();
        return session.selectList("aa.bb.cc.query",hashMap);
    }

    public static void delete(int id){
        // 通过工厂创建连接对象
        SqlSession session =  sqlSessionFactory.openSession();
        try{
            session.delete("aa.bb.cc.delete",id);
            session.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
            session.rollback();
        }finally {
            session.close();
        }
    }
    // mybatis中:连接数据库的对象 SqlSession, SqlSessionFactory就是用来创建SqlSession工厂
    // 类似之前JdbcUtils

    public static void main(String[] args) throws Exception {
//        Product product = new Product();
//        product.setId(22);
//        product.setName("微软鼠标2");
//        product.setPrice(new BigDecimal(299.00));
//        product.setRemark("mybatis测试数据!!!");
//        ProductTest.update(product);
//          ProductTest.delete(22);
//        List<Product> proList = ProductTest.query("",2,7);
//        for(Product product:proList){
//            System.out.println(product);
//        }

    }
}