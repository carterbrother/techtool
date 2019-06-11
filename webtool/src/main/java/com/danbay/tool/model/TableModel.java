package com.danbay.tool.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Created by carter on 2017/2/7. Copyright © 2016 －2017 旦倍科技
 */
public class TableModel {
    private String tableName = "";
    private String tableComment="";
    private List<FieldModel> fields = Lists.newLinkedList();




    public TableModel() {
        FieldModel fieldModel = new FieldModel();
        fieldModel.setFieldName("id");
        fieldModel.setFieldComment("主键");
        fieldModel.setFieldLength(20);
        fieldModel.setFieldType("bigint");

        fields.add(fieldModel);

        FieldModel fieldModel2 = new FieldModel();
        fieldModel2.setFieldName("create_time");
        fieldModel2.setFieldComment("数据添加时间");
        fieldModel2.setFieldLength(0);
        fieldModel2.setFieldType("datetime");
        fields.add(fieldModel2);

        FieldModel fieldModel4 = new FieldModel();
        fieldModel4.setFieldName("update_time");
        fieldModel4.setFieldComment("数据修改时间");
        fieldModel4.setFieldLength(0);
        fieldModel4.setFieldType("datetime");
        fields.add(fieldModel4);

        FieldModel fieldModel3 = new FieldModel();
        fieldModel3.setFieldName("delete_state");
        fieldModel3.setFieldComment("1为删除，0为正常");
        fieldModel3.setFieldLength(1);
        fieldModel3.setFieldType("bit");
        fieldModel3.setFieldDefaultValue("0");
        fields.add(fieldModel3);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public List<FieldModel> getFields() {
        return fields;
    }

    public void setFields(List<FieldModel> fields) {
        this.fields = fields;
    }

    public boolean addField(FieldModel fieldModel) {
        //得到一个map，方便判断是否存在
        Map<String,FieldModel> fieldModelMap = Maps.newHashMap();
        for(FieldModel field: fields)
        {
            fieldModelMap.put(field.getFieldName(),field);
        }


        if(fieldModelMap.containsKey(fieldModel.getFieldName()))
        {
            return false;
        }else
        {//如果不含有该字段，则添加到字段列表中
            fields.add(fieldModel);
            return true;
        }
    }
}
