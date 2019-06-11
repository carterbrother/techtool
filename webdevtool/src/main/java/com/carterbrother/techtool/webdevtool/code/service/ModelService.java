package com.carterbrother.techtool.webdevtool.code.service;

import com.carterbrother.techtool.webdevtool.code.model.DataBaseModel;
import com.carterbrother.techtool.webdevtool.code.model.FieldModel;
import com.carterbrother.techtool.webdevtool.code.model.TableModel;
import com.carterbrother.techtool.webdevtool.utils.DateUtils;
import com.carterbrother.techtool.webdevtool.utils.GenerateUtil;
import com.carterbrother.techtool.webdevtool.utils.SessionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yangyanchen on 2017/5/22
 * Copyright © 2016 －2017 旦倍科技
 */
public class ModelService {

    public static String  generateModel(TableModel tableModel, HttpServletRequest request)
    {

        DataBaseModel dataBaseModel = SessionUtils.getDataBase(request);
        String tableEntityName = GenerateUtil.getTableEntityName(tableModel);

        StringBuffer modelStringBuffer = new StringBuffer();

        //package
        modelStringBuffer.append("package "+dataBaseModel.getDbPackageName()+".model;");

        //import
        modelStringBuffer.append("\n\nimport java.io.Serializable;");
        //注释
        modelStringBuffer.append("\n\n/**");
        modelStringBuffer.append("\n * Created by "+dataBaseModel.getDatabaseCreator()+" on "+ DateUtils.Date2String(new Date())+". Copyright © 2016 －2017 旦倍科技");
        modelStringBuffer.append("\n * "+tableModel.getTableComment()+"实体类");
        modelStringBuffer.append("\n */");

        //class
        modelStringBuffer.append("\npublic class "+ tableEntityName +"Model  implements Serializable {\n");
        //字段

        tableModel.getFields().forEach(item->{
            String typeString = GenerateUtil.typeMap.get(item.getFieldType());
            modelStringBuffer.append("\n\tprivate\t").append(typeString).append("\t").append(item.getFieldName()).append("\t=\t").append(GenerateUtil.defaultMap.get(typeString)).append(";");
        });


        //构造方法

        modelStringBuffer.append("\n\n\tpublic "+tableEntityName+"Model(){}");


        //set 方法 get方法；
        tableModel.getFields().forEach(item->{
            String typeString = GenerateUtil.typeMap.get(item.getFieldType());
            String fieldName = item.getFieldName();
            String methodField = fieldName.substring(0,1).toUpperCase()+ fieldName.substring(1,fieldName.length());
            //set方法；
            modelStringBuffer.append("\n\n\tpublic void set"+methodField+"("+typeString+"\t"+fieldName+"){");
            modelStringBuffer.append("\n\t\tthis."+fieldName+" = "+fieldName+";\n\t}");

            //get方法
            modelStringBuffer.append("\n\n\tpublic "+typeString+" get"+methodField+"(){");
            modelStringBuffer.append("\n\t\treturn "+fieldName+";\n\t}");
        });

        //toString方法
        modelStringBuffer.append("\n\n\t@Override\n\tpublic String toString() {\n\t\treturn \""+tableEntityName+"Model{\"+");
        tableModel.getFields().forEach(item->{
            String typeString = GenerateUtil.typeMap.get(item.getFieldType());

            modelStringBuffer.append("\n\t\t\t\" ,"+item.getFieldName()+"=\" +").append(item.getFieldName()).append(" +");
        });

        modelStringBuffer.append("\n\t\t\t\"};\";\n\t}\n\n}");


        //结束





        return modelStringBuffer.toString();
    }


    public static String  generateModel_dapeng(TableModel tableModel, HttpServletRequest request)
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("package ")
                .append(SessionUtils.getDataBase(request).getDbPackageName())
                .append(".model;\n");
        buffer.append(getImportStmt(tableModel.getFields()));
        buffer.append("public class ")
                .append(removeUnderLine(tableModel.getTableName(),true))
                .append("Model {\n");
        for (FieldModel fieldModel:tableModel.getFields()) {
            buffer.append(getFieldDeclare(fieldModel.getFieldType(),fieldModel.getFieldName()));
        }
        buffer.append("}\n");
        return buffer.toString();
    }

    public static String removeUnderLine(String targetStr,boolean isTable)
    {
        if(targetStr.equalsIgnoreCase("id"))
        {
            return targetStr;
        }
        if(isTable)
        {
            targetStr = targetStr.replace(targetStr.substring(0, 1), targetStr.substring(0, 1).toUpperCase());
        }
        String[] temp = targetStr.split("_");
        String result = "";
        for (int i = 0; i < temp.length; i++) {
            if(i>0)
            {
                temp[i] = temp[i].replace(temp[i].substring(0, 1), temp[i].substring(0, 1).toUpperCase());
            }
            result += temp[i];
        }
        return result;
    }

    private static String getFieldDeclare(String fieldType,String fieldName)
    {
        String result = "";
        if("id".equalsIgnoreCase(fieldName))
        {
            result = "private  int id = 0;\n";
            return result;
        }
        if("varchar".equalsIgnoreCase(fieldType))
        {
            result = "private String "+removeUnderLine(fieldName,false)+" = \"\";\n";
        }
        if("unsigned bigint".equalsIgnoreCase(fieldType))
        {
            result = "private int "+removeUnderLine(fieldName,false)+" = 0;\n";
        }
        if("datetime".equalsIgnoreCase(fieldType))
        {
            result = "private Date "+removeUnderLine(fieldName,false)+" = new Date(System.currentTimeMillis());\n";
        }
        if("tinyint".equalsIgnoreCase(fieldType))
        {
            result = "private int "+removeUnderLine(fieldName,false)+" = 0;\n";
        }
        if("bit".equalsIgnoreCase(fieldType))
        {
            result = "private boolean "+removeUnderLine(fieldName,false)+" = false;\n";
        }
        if("decimal".equalsIgnoreCase(fieldType))
        {
            result = "private double "+removeUnderLine(fieldName,false)+" = 0l;\n";
        }
        return result;
    }

    private static String getImportStmt(List<FieldModel> list)
    {
        String result = "";
        Iterator iterator = list.iterator();
        while (iterator.hasNext())
        {
            FieldModel fieldModel = (FieldModel)iterator.next();
            if("datetime".equalsIgnoreCase(fieldModel.getFieldType()))
            {
                result = "import java.util.Date;\n";
            }
        }
        return result;
    }
}
