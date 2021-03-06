package com.example.demo.map;
import com.example.demo.domain.Class;
import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author Duan
 * @date 2020/3/25 - 11:14
 */

@Repository
public interface UserMap {
    //登录
    List<User> logSelect(User user);

    //添加数据
    int insert(User user);

    //查找所有 @Select("select * from user")
    List<User> selectAll();

    //通过id查找
    User selectId(int id);

    //分页查找
    List<User> selectPage(int page,int num);

    //通过id修改信息
    int updateId(User user);

    //修改状态
    int updateStatus(User user);

    //查询名字是否存在
    int selectName(User user);

    //添加销售单此次消费总金额
    int addCashierMoney(double cashierMoney,Integer userId);

    //通过用户名查找user对象
    User findByName(String name);
}
