package com.release;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yancheng
 * @since 2023/4/6
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Springboot06Application.class)
public class MongoTest {
    /**
     * 1、注入MongoTemplate对象
     * 2、调用对象方法完成数据的CRUD
     */
    @Resource
    private MongoTemplate mongoTemplate;


    //保存
    @Test
    public void testSave() {
        Person person = new Person();
        person.setName("张三");
        person.setAge(18);
        person.setAddress("济南高新区");
        mongoTemplate.save(person);
    }

    /**
     * 查询所有
     */
    @Test
    public void testFindAll() {
        List<Person> list = mongoTemplate.findAll(Person.class);
        for (Person person : list) {
            System.out.println(person);
        }
    }

    /**
     * 条件查询
     */
    @Test
    public void testFind() {
        //1、创建Criteria对象，并设置查询条件
        Criteria criteria = Criteria.where("myname").is("张三")
                .and("age").is(18);//is 相当于sql语句中的=
        //2、根据Criteria创建Query
        Query query = new Query(criteria);
        //3、查询
        List<Person> list = mongoTemplate.find(query, Person.class);//Query对象，实体类对象字节码
        for (Person person : list) {
            System.out.println(person);
        }
    }

    /**
     * 分页查询
     */
    @Test
    public void testPage() {
        int page = 1;
        int size = 2;
        //1、创建Criteria对象，并设置查询条件
        Criteria criteria = Criteria.where("age").lt(50);
        //2、根据Criteria创建Query
        Query queryLimit = new Query(criteria)
                .skip((page - 1) * size) //从第几条开始查询
                .limit(size) //每页查询条数
                .with(Sort.by(Sort.Order.desc("age")));
        //3、查询
        List<Person> list = mongoTemplate.find(queryLimit, Person.class);
        for (Person person : list) {
            System.out.println(person);
        }
    }


    /**
     * 更新
     */
    @Test
    public void testUpdate() {
        //1、构建Query对象
        //is 相当于sql语句中的=
        Query query = Query.query(Criteria.where("id").is("642e98d063bd7e6e61fc8c17"));
        //2、设置需要更新的数据内容
        Update update = new Update();
        update.set("age", 10);
        update.set("myname", "lisi");
        //3、调用方法
        mongoTemplate.updateFirst(query, update, Person.class);
    }

    //删除
    @Test
    public void testDelete() {
        //1、构建Query对象
        Query query = Query.query(Criteria.where("id").is("642e98d063bd7e6e61fc8c17"));
        mongoTemplate.remove(query, Person.class);
    }

}
