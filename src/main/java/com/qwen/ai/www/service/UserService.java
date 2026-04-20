package com.qwen.ai.www.service;
import com.qwen.ai.www.VO.ResultVO;
import com.qwen.ai.www.entities.models.User;
import java.util.List;

public interface UserService {
    /*查询数据库所有数据*/
    public abstract List<User> selectAll();

    /*根据用户id修改用户信息（修改数据成功几条就返回几）
    * 修改成功时，在前端返回：修改成功n条数据
    * */
    String updateById(User user);

    /*删除功能：根据id进行删除*/
    String deleteById(Integer userId);

    String deleteByIds(Integer userId);

    /*添加功能*/
    ResultVO<Integer> insertUser(User user);
}
