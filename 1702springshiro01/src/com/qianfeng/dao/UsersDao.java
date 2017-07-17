package com.qianfeng.dao;


import com.qianfeng.bean.Users;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UsersDao {
    Users selectUersByAccount(String account);
    public List<Users> selectByUsers(@Param(value ="start") int start,@Param(value ="size") int size);
    List<Users> selectById(int uid);
    //public int deleteById(int id);
    public void removeDakaRecode(ArrayList<Integer> list);
    int usersCount();
    void daKa(Map<String,Object> map);
    int dakaCount(@Param(value ="dakatime") Date dakatime, @Param(value ="uid") int uid);
}
