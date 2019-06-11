package com.carterbrother.techtool.webdevtool.utils;

import com.carterbrother.techtool.webdevtool.code.model.DataBaseModel;
import com.carterbrother.techtool.webdevtool.code.model.FieldModel;
import com.carterbrother.techtool.webdevtool.code.model.TableModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Created by carter on 2017/2/7. Copyright © 2016 －2017 旦倍科技
 */
public final   class SessionUtils {

    public static final String DANBAY_DATABASE = "danbay_database";

    public static void exit(HttpServletRequest request)
    {
        request.getSession().setMaxInactiveInterval(60*60*24*30);
        request.getSession().removeAttribute(DANBAY_DATABASE);
    }


    public static void saveDataBase(HttpServletRequest request, DataBaseModel dataBaseModel)
    {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(60*60*24*30);

       DataBaseModel dataBaseModel1 =  getDataBase(request);

       if(null == dataBaseModel1)
       {

           session.setAttribute(DANBAY_DATABASE,dataBaseModel);
       }else
       {
           dataBaseModel1.setDatabaseComment(dataBaseModel.getDatabaseComment());
           dataBaseModel1.setDatabaseCreator(dataBaseModel.getDatabaseCreator());
           dataBaseModel1.setDbPackageName(dataBaseModel.getDbPackageName());
           dataBaseModel1.setAppPackageName(dataBaseModel.getAppPackageName());

           session.setAttribute(DANBAY_DATABASE,dataBaseModel1);
       }

    }

    public static DataBaseModel getDataBase(HttpServletRequest request)
    {

       try {
           return (DataBaseModel) request.getSession().getAttribute(DANBAY_DATABASE);
       }catch (Exception ex)
       {
           return null;
       }


    }


    public static boolean saveTable(HttpServletRequest request, TableModel tableModel, String tableName_pre) {
        DataBaseModel dataBaseModel = getDataBase(request);

        Optional<TableModel> optional = dataBaseModel.getTables().values().stream().filter(item -> item.getTableName().equals(tableName_pre)).findAny();

        optional.ifPresent(item->{
            tableModel.setFields(item.getFields());
            dataBaseModel.getTables().remove(item.getTableName());
            dataBaseModel.getTableMap().remove(item.getTableName());
        });


        return dataBaseModel.addTable(tableModel);
    }

    public static TableModel getTable(HttpServletRequest request ,String tableName) {
       return  Optional.ofNullable(getDataBase(request).getTableModel(tableName)).orElse(new TableModel());
    }

    public static boolean saveField(HttpServletRequest request, String tableName, FieldModel fieldModel, String fieldName_pre) {
        TableModel tableModel = getTable(request,tableName);

        Optional<FieldModel> first = tableModel.getFields().stream().filter(item -> item.getFieldName().equals(fieldName_pre)).findAny();

        if(first.isPresent())
        {//存在，则删除；
            tableModel.getFields().remove(first.get());

        }

        //在重新添加

        return tableModel.addField(fieldModel);
    }

    public static FieldModel getTableField(HttpServletRequest request, String tableName, String fieldName) {
        TableModel tableModel = getTable(request,tableName);

       return tableModel.getFields().stream().filter(item->fieldName.equals(item.getFieldName())).findFirst().orElse(null);


    }

    public static boolean deleteTableField(HttpServletRequest request, String tableName, String fieldName) {
        TableModel tableModel = getTable(request,tableName);

        FieldModel fieldModel = getTableField(request,tableName,fieldName);

        tableModel.getFields().remove(fieldModel);

       return true;
    }
}
