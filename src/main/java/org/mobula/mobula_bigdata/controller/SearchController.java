/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package org.mobula.mobula_bigdata.controller;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sql.DataSource;

import org.mobula.mobula_bigdata.config.EngineConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
public class SearchController {
    private static final Logger logger = LoggerFactory.getLogger(HiveDataSourceController.class);


    @Autowired
    @Qualifier("mysqlJdbcDataSource")
    DataSource mysqlDataSource;


    @Autowired
    @Qualifier("hiveJdbcDataSource")
    DataSource druidDataSource;
    /**
     * 测试结果
     */
    @RequestMapping("/test")
    public String Test(){
        return "tables";
    }

    @RequestMapping(value = "/getdata", method = RequestMethod.POST)
    @ResponseBody
    public String GetData(@RequestParam(value = "sql", required = true) String sql,
                          @RequestParam(value = "engine", required = true) String engine){
        DataSource currentDataource;

        engine = engine.trim();
        switch (engine) {
            case EngineConfig.HIVE:
                currentDataource = druidDataSource;
                break;
            case EngineConfig.MYSQL:
                currentDataource = mysqlDataSource;
                break;
            default:
                currentDataource = druidDataSource;
        }
        logger.info(sql);
        JSONObject resJson = new JSONObject();
        String tab="";
        try {

            Statement statement = currentDataource.getConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);
            ResultSetMetaData rsmd = res.getMetaData();
            int colcount = rsmd.getColumnCount();
            ArrayList headArr = new ArrayList();
            for (int i=1;i<=colcount;i++) {
                System.out.println(rsmd.getColumnName(i));
                headArr.add(rsmd.getColumnName(i));
            }
            System.out.println(colcount);
            String name = rsmd.getColumnName(1);
            System.out.println(name);
            HashMap data = new HashMap();
            int len = 0;

            while (res.next()) {
                ArrayList bodyArr = new ArrayList();
                for (int i=1;i<=colcount;i++) {
                    bodyArr.add(res.getString(i));
                }
                data.put("a"+len,bodyArr);
                len++;
            }


            for(int i=0;i<len;i++)
            {
                tab += "<tr>";
                //循环列
                ArrayList list = (ArrayList) data.get("a"+i);
                for(int j=0;j<colcount;j++)
                {
                     tab+="<td>" +  list.get(j) + "</td>";
                }
                tab+="</tr>";
            }
            System.out.println(tab);

            String headtab = "";
            headtab += "<tr>";
            for(int j=0;j<colcount;j++)
            {
                headtab+="<td>" +  headArr.get(j) + "</td>";
            }
            headtab+="</tr>";
//            return tab;
            resJson.put("head",headtab);
            resJson.put("body",tab);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            logger.error("sql error",e);
            resJson.put("body",e.getMessage());
        }
        return resJson.toString();
    }

}
