package com.danbay.tool.model;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by carter on 2017/2/7. Copyright © 2016 －2017 旦倍科技
 */
public class DataBaseModel {
    private String databaseName = "";
    private String databaseComment = "";
    private String databaseCreator = "";
    private String dbPackageName = "";//生成代码的包名
    private String appPackageName = "";//生成程序代码的包名

    private Map<String,String> tableMap = Maps.newHashMap();
    private Map<String,TableModel> tables = Maps.newHashMap();

    public DataBaseModel() {
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDatabaseComment() {
        return databaseComment;
    }

    public void setDatabaseComment(String databaseComment) {
        this.databaseComment = databaseComment;
    }

    public String getDatabaseCreator() {
        return databaseCreator;
    }

    public void setDatabaseCreator(String databaseCreator) {
        this.databaseCreator = databaseCreator;
    }

    public Map<String, String> getTableMap() {
        return tableMap;
    }

    public TableModel getTableModel(String tableName) {
        return tables.get(tableName);
    }

    public boolean addTable(TableModel tableModel) {
        if(tableMap.containsKey(tableModel.getTableName()))
        {
            return false;
        }
        else
        {
            tableMap.put(tableModel.getTableName(),tableModel.getTableComment());
            tables.put(tableModel.getTableName(),tableModel);
            return  true;
        }
    }

    public String getDbPackageName() {
        return dbPackageName;
    }

    public void setDbPackageName(String dbPackageName) {
        this.dbPackageName = dbPackageName;
    }

    public String getAppPackageName() {
        return appPackageName;
    }

    public void setAppPackageName(String appPackageName) {
        this.appPackageName = appPackageName;
    }

    public Map<String, TableModel> getTables() {
        return tables;
    }
}
