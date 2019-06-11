package com.carterbrother.techtool.webdevtool.utils;

import com.carterbrother.techtool.webdevtool.code.model.DataBaseModel;
import com.carterbrother.techtool.webdevtool.code.model.TableModel;
import com.carterbrother.techtool.webdevtool.code.service.BllService;
import com.carterbrother.techtool.webdevtool.code.service.DaoService;
import com.carterbrother.techtool.webdevtool.code.service.ModelService;
import com.carterbrother.techtool.webdevtool.code.service.SqlService;
import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Files;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by carter on 2017/5/23. Copyright © 2016 －2017 旦倍科技
 */
public class GenerateUtil {

    public static Map<String,String> typeMap = Maps.newHashMap();
    public static Map<String,String> defaultMap = Maps.newHashMap();

    static {

        typeMap.put("varchar","String");
        typeMap.put("bigint","Long");
        typeMap.put("unsigned bigint","Long");
        typeMap.put("datetime","java.time.LocalDateTime");
        typeMap.put("date","java.time.LocalDate");
        typeMap.put("tinyint","int");
        typeMap.put("char","String");
        typeMap.put("text","String");
        typeMap.put("bit","boolean");
        typeMap.put("decimal","java.math.BigDecimal");


        defaultMap.put("String","\"\"");
        defaultMap.put("Long","0L");
        defaultMap.put("java.time.LocalDateTime","java.time.LocalDateTime.now()");
        defaultMap.put("java.time.LocalDate","java.time.LocalDate.now()");
        defaultMap.put("int","0");
        defaultMap.put("boolean","false");
        defaultMap.put("java.math.BigDecimal","new java.math.BigDecimal(0)");
    }


    public static String getTableEntityName(TableModel tableModel) {

        String tableName =  tableModel.getTableName();

        StringBuffer entityStringBuffer = new StringBuffer();
        StringUtils.split2List(tableName,"_").stream().map(item->item.substring(0,1).toUpperCase()+item.substring(1,item.length())).collect(Collectors.toList()).forEach(item->entityStringBuffer.append(item).append("_"));

        return entityStringBuffer.deleteCharAt(entityStringBuffer.length()-1).toString();

    }


    public static void genereateSqlFile(DataBaseModel dataBaseModel, String codePath) throws IOException {
        //生成sql
        String fileName = codePath+ "db."+dataBaseModel.getDatabaseName()+"\\"+dataBaseModel.getDatabaseName()+".sql";
        File file = new File(fileName);
        file.mkdirs();
        if(file.exists())
        {
            file.delete();
        }
        file.createNewFile();


        StringBuffer stringBuffer = new StringBuffer();
        //循环的把表相关的sql追加到文件中；
        dataBaseModel.getTables().values().forEach(item->{
            stringBuffer.append("\n#########################################################################################\n");
            stringBuffer.append("#"+item.getTableComment()+"的建表脚本和相关存储过程");
            stringBuffer.append("\n#########################################################################################\n");
            stringBuffer.append(SqlService.generateSql(item,dataBaseModel.getDatabaseName())).append("\n\n\n");

        });

        Files.append(stringBuffer.toString(),file, Charsets.UTF_8);
    }

    public static void genereateCodeFile(DataBaseModel dataBaseModel, String codePath, HttpServletRequest request) {
        //生成sql
        String fileName = codePath+ "db."+dataBaseModel.getDatabaseName()+"\\src\\main\\java\\";
        String dbPackage = dataBaseModel.getDbPackageName();
        fileName+=getDir(dbPackage);


        String finalFileName = fileName;
        dataBaseModel.getTables().values().forEach(item->{

            Arrays.asList("Bll","Dao","Model").forEach(itemModel->{

                File file = new File(finalFileName +"\\"+itemModel.toLowerCase()+"\\"+getTableEntityName(item)+itemModel+".java");
                file.mkdirs();
                if(file.exists())
                {
                    file.delete();
                }
                try {
                    file.createNewFile();
                    String fileContent = "";
                    if("Bll".equalsIgnoreCase(itemModel))
                    {
                        fileContent = BllService.generateBll(item,request);
                    }else if("Dao".equalsIgnoreCase(itemModel))
                    {
                        fileContent = DaoService.generateDao(item,request);
                    }else if("Model".equalsIgnoreCase(itemModel))
                    {
                        fileContent= ModelService.generateModel(item,request);
                    }

                    Files.append(fileContent,file,Charsets.UTF_8);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });


        });


    }

    private static String getDir(String dbPackage) {
//        String s = dbPackage.replaceAll(".", "\\\\");
        StringBuffer stringBuffer = new StringBuffer();
        StringUtils.split2List(dbPackage,".").forEach(item->{
            stringBuffer.append(item).append("\\\\");
        });

        return stringBuffer.toString();
    }

    public static void generatePom(DataBaseModel dataBaseModel, String codePath) {
        String fileName = codePath+ "db."+dataBaseModel.getDatabaseName()+"\\pom.xml";
        File file = new File(fileName);
        file.mkdirs();
        if(file.exists())
        {
            file.delete();
        }
        try {
            file.createNewFile();

            StringBuffer pomStringBuffer = new StringBuffer();
            pomStringBuffer.append("<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                    "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">");
            pomStringBuffer.append("\n\t<parent>\n" +
                    "        <artifactId>sln_lib</artifactId>\n" +
                    "        <groupId>com.danbay</groupId>\n" +
                    "        <version>1.0-SNAPSHOT</version>\n" +
                    "    </parent>\n" +
                    "    <modelVersion>4.0.0</modelVersion>");

            pomStringBuffer.append("\n\n\t<artifactId>db."+dataBaseModel.getDatabaseName()+"</artifactId>");
            pomStringBuffer.append("\n\t<packaging>jar</packaging>\n");


            pomStringBuffer.append("\n\n\t<name>db."+dataBaseModel.getDatabaseName()+"</name>");
            pomStringBuffer.append("\n\t<url>http://www.danbay.cn</url>\n");


            pomStringBuffer.append("\n\n\t<properties>\n\t\t<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>\n" +
                    "    </properties>");

            pomStringBuffer.append("\n\t<dependencies>");

            pomStringBuffer.append(" <dependency>\n" +
                    "            <groupId>com.danbay</groupId>\n" +
                    "            <artifactId>danbay.dbaccess</artifactId>\n" +
                    "            <version>1.0-SNAPSHOT</version>\n" +
                    "        </dependency>\n" +
                    "        <dependency>\n" +
                    "            <groupId>com.danbay</groupId>\n" +
                    "            <artifactId>danbay.constant</artifactId>\n" +
                    "            <version>1.0-SNAPSHOT</version>\n" +
                    "        </dependency>");


            pomStringBuffer.append("\n\t</dependencies>\n</project>");


            Files.append(pomStringBuffer.toString(),file,Charsets.UTF_8);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
