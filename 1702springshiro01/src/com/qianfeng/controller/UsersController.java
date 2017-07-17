package com.qianfeng.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.qianfeng.bean.Users;
import com.qianfeng.dao.UsersDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class UsersController {
    @Resource
    private UsersDao udao;

    @RequestMapping("selectByUsers.do")
    @ResponseBody
    public Map<String,Object> showUsers(int page,int size) {
        int start=(page-1)*size;
        int count=udao.usersCount();
        List<Users> usersList= udao.selectByUsers(start,size);
        Map<String,Object> map= new HashMap<>();

       /* Date date = new Date();
        String dateStr = JSON.toJSONString(users.getDakatime(),SerializerFeature.WriteDateUseDateFormat);
        System.out.println(dateStr);*/
        //map.put("time",dateStr);
        map.put("total",count);
        map.put("usersList",usersList);
        return map;
    }
    @RequestMapping("selectById.do")
    @ResponseBody
    public Map<String,Object> showUsers(HttpSession session) {
       Users users= (Users) session.getAttribute("users");
        List<Users> usersList= udao.selectById(users.getUid());
        Map<String,Object> map= new HashMap<>();
        map.put("total",1);
        map.put("usersList",usersList);
        return map;
    }
    @RequestMapping("daKa.do")
    @ResponseBody
    public int daKa(int id){
        System.out.print("前台传的"+id);
        int x=1;
        Date date=new Date();
        SimpleDateFormat sfd= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dakatime = sfd.format(date);
        Date dt1= null;
        Calendar cal=Calendar.getInstance();
        int hour=cal.get(Calendar.HOUR_OF_DAY);
        int minute= cal.get(Calendar.MINUTE);
        String dakastate=null;
        if(hour>9&&hour<24){

            dakastate="迟到";
        }else if(hour==9&&minute>0) {

            dakastate="迟到";
        }else if (hour<9){
            dakastate="正常打卡";
        }
        else {
            dakastate="未打卡";
        }
        Map<String,Object> map=new HashMap<>();
        map.put("dakastate",dakastate);
        map.put("dakatime",dakatime);
        map.put("flag",1);
        map.put("uid",id);
      try {
          int counts= udao.dakaCount(date,id);
          if (counts>0){
              x=2;
          }else{
              udao.daKa(map);
          }

        } catch (Exception e) {
          e.printStackTrace();
           x=-1;
        }
        return x;
    }

    //多条数据删除
    @RequestMapping("removeDakaRecode.do")
    @ResponseBody
    public int removeUser(@RequestBody ArrayList<Integer> data) {
        int x=-1;
        try {
            System.out.print("前台传的数组"+data);
            udao.removeDakaRecode(data);
            x=1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }



}
