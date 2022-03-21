package com.study.front.controller;

import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/20 21:07
 */
public class TestDemo {

    private static List<Dept> deptList;

    static {
        Dept dept1 = new Dept(1, 0, "中国",null);
        Dept dept2 = new Dept(2, 1, "北京",null);
        Dept dept3 = new Dept(3, 1, "上海",null);
        Dept dept4 = new Dept(4, 0, "广东",null);
        Dept dept5 = new Dept(5, 4, "广州",null);
        Dept dept6 = new Dept(6, 4, "深圳",null);
        Dept dept7 = new Dept(7, 5, "天河区",null);
        deptList = new ArrayList<Dept>();
        deptList.add(dept1);
        deptList.add(dept2);
        deptList.add(dept3);
        deptList.add(dept4);
        deptList.add(dept5);
        deptList.add(dept6);
        deptList.add(dept7);
    }

    private static List<Dept> buildTree(List<Dept> deptList,int pid){
        List<Dept> treeList = new ArrayList<Dept>();
        for (Dept dept : deptList) {
            if (dept.getParentId() == pid) {
                dept.setChild(buildTree(deptList, dept.getId()));
                treeList.add(dept);
            }
        }
        return treeList;
    }

    public static void main(String[] args) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", buildTree(deptList, 1));
        System.out.println(JSONUtil.toJsonStr(map));

    }
}
