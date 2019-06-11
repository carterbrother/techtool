package com.carterbrother.techtool.webdevtool.code.service;

import com.carterbrother.techtool.webdevtool.code.model.FieldModel;
import com.carterbrother.techtool.webdevtool.code.model.TableModel;
import com.carterbrother.techtool.webdevtool.utils.CommonUtils;
import com.google.common.base.Strings;

import java.util.Arrays;
import java.util.List;

/**
 * Created by carter on 2017/2/8. Copyright © 2016 －2017 旦倍科技
 */
public final class SqlService {

    public static final String CREATE_TIME = "create_time";
    public static final String  UPDATE_TIME = "update_time";
    public static final String DELETE_STATE = "delete_state";
    public static final List<String> add_Ignore_List = Arrays.asList("id", "create_time", "update_time", "delete_state");
    public static final List<String> update_Ignore_List = Arrays.asList( "create_time", "update_time", "delete_state");

    public static String generateSql(TableModel tableModel, String databaseName)
    {
        StringBuffer sqlStringBuffer = new StringBuffer();

        //选定数据库
        sqlStringBuffer.append("USE "+databaseName+" ;\n");
        //创建表格

        sqlStringBuffer.append(" \n/*建表"+tableModel.getTableName()+"脚本*/\n");
        sqlStringBuffer.append("DROP TABLE IF EXISTS "+tableModel.getTableName()+" ;\n");

        sqlStringBuffer.append("CREATE TABLE");
        sqlStringBuffer.append("`").append(tableModel.getTableName()).append("` (\n");

        for(FieldModel fieldModel: tableModel.getFields())
        {
            if("id".equalsIgnoreCase(fieldModel.getFieldName()))
            {
                sqlStringBuffer.append("`id` bigint(20) NOT NULL AUTO_INCREMENT,\n");
            }else
            {
            sqlStringBuffer.append("`").append(fieldModel.getFieldName()).append("` ").append(fieldModel.getFieldType())
                    .append(getLengthString(fieldModel)) .append(" NOT NULL ").append(getDefaultString(fieldModel)).append("  COMMENT '").append(fieldModel.getFieldComment()).append("',\n");
            }
        }


        sqlStringBuffer.append("  PRIMARY KEY (`id`)\n" );
        sqlStringBuffer.append(") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='");
        sqlStringBuffer.append(tableModel.getTableComment()).append("';");

        //创建存储过程

        //1,查询单条
        sqlStringBuffer.append("\n\n/*按照id查询单条数据存储过程*/\n");
        sqlStringBuffer.append("DROP PROCEDURE IF EXISTS cp_"+tableModel.getTableName()+"_findById;\n");
        sqlStringBuffer.append("CREATE PROCEDURE cp_"+tableModel.getTableName()+"_findById(IN idValue BIGINT)\n");
        sqlStringBuffer.append("BEGIN\n").append(" SELECT * FROM "+tableModel.getTableName()+" WHERE id=idValue;\n").append("END;\n");
        //2,删除单条
        sqlStringBuffer.append("\n/*按照id删除单条数据存储过程*/\n");
        sqlStringBuffer.append("DROP PROCEDURE IF EXISTS cp_"+tableModel.getTableName()+"_deleteById;\n");
        sqlStringBuffer.append("CREATE PROCEDURE cp_"+tableModel.getTableName()+"_deleteById(IN idValue INT,IN RealDelete INT,OUT SPReturnValue INT)\n");
        sqlStringBuffer.append("BEGIN\n").append(" IF(RealDelete=1) THEN \n  DELETE FROM "+tableModel.getTableName()+" WHERE id=idValue;\n ELSE\n" +
                "  UPDATE "+tableModel.getTableName()+" SET delete_state=TRUE WHERE id=idValue;\n" +
                " END IF;\n  SET SPReturnValue=ROW_COUNT();").append("\nEND;\n");
        //3,分页查询
        sqlStringBuffer.append("\n/*分页查询数据存储过程*/\n");
        sqlStringBuffer.append("DROP PROCEDURE IF EXISTS cp_"+tableModel.getTableName()+"_findByPage;\n");
        sqlStringBuffer.append("CREATE PROCEDURE cp_"+tableModel.getTableName()+"_findByPage(IN pageIndex INT,IN pageSize INT)\n");
        sqlStringBuffer.append("BEGIN\n");
        sqlStringBuffer.append("  DECLARE _start INT;\n\n" +
                "  IF (pageIndex<1) THEN\n" +
                "  SET pageIndex=1;\n" +
                "  END IF;\n\n" +
                "  IF (pageSize<1) THEN\n" +
                "  SET pageSize=20;\n" +
                "  END IF;\n\n" +
                "  SET    _start= pageSize*(pageIndex-1);\n" +
                " \n  SELECT * FROM ").append(tableModel.getTableName()).append("  WHERE delete_state=0  ORDER BY id DESC LIMIT _start,pageSize;\nEND; \n");

        //4,分页查询带总条数
        sqlStringBuffer.append("\n/*分页查询带总数数据存储过程*/\n");
        sqlStringBuffer.append("DROP PROCEDURE IF EXISTS cp_"+tableModel.getTableName()+"_findByPageWithCounts;\n");
        sqlStringBuffer.append("CREATE PROCEDURE cp_"+tableModel.getTableName()+"_findByPageWithCounts(IN pageIndex INT,IN pageSize INT,OUT SPReturnValue INT)\n");
        sqlStringBuffer.append("BEGIN\n");
        sqlStringBuffer.append("  DECLARE _start INT;\n\n" +
                "  IF (pageIndex<1) THEN\n" +
                "  SET pageIndex=1;\n" +
                "  END IF;\n\n" +
                "  IF (pageSize<1) THEN\n" +
                "  SET pageSize=1;\n" +
                "  END IF;\n\n" +
                "  SET    _start= pageSize*(pageIndex-1);\n" +
                " \n  SELECT * FROM ").append(tableModel.getTableName()).append(" WHERE delete_state=0  ORDER BY id DESC LIMIT _start,pageSize;\n  SELECT COUNT(*) INTO SPReturnValue FROM "+tableModel.getTableName()+" WHERE delete_state=0 ;\nEND; \n");


        //5,查询所有
        sqlStringBuffer.append("\n/*查询所有有效数据存储过程*/\n");
        sqlStringBuffer.append("DROP PROCEDURE IF EXISTS cp_"+tableModel.getTableName()+"_findAll;\n");
        sqlStringBuffer.append("CREATE PROCEDURE cp_"+tableModel.getTableName()+"_findAll(IN IsGetAllValue TINYINT)\n");
        sqlStringBuffer.append("BEGIN\n").append("  IF(IsGetAllValue=1) THEN\n   SELECT * FROM "+tableModel.getTableName()+" ;\n  ELSE\n" +
                "    SELECT * FROM ").append(tableModel.getTableName()).append(" WHERE delete_state=0 ;\n  END IF;\nEND;\n");
        //6,增加
        sqlStringBuffer.append("\n/*添加数据存储过程*/\n");
        sqlStringBuffer.append("DROP PROCEDURE IF EXISTS cp_"+tableModel.getTableName()+"_create;\n");
        sqlStringBuffer.append("CREATE PROCEDURE cp_"+tableModel.getTableName()+"_create("+getAddPara(tableModel)+"\nOUT SPReturnValue BIGINT\n)\n");
        sqlStringBuffer.append("BEGIN\n");
        sqlStringBuffer.append("  INSERT INTO ").append(tableModel.getTableName()).append(getInsertSql(tableModel));
        sqlStringBuffer.append(";\n  SET SPReturnValue=LAST_INSERT_ID();\nEND;");

        //7,修改
        sqlStringBuffer.append("\n/*修改数据存储过程*/\n");
        sqlStringBuffer.append("DROP PROCEDURE IF EXISTS cp_"+tableModel.getTableName()+"_update;\n");
        sqlStringBuffer.append("CREATE PROCEDURE cp_"+tableModel.getTableName()+"_update("+getProcedurePara(tableModel,false)+"\nOUT SPReturnValue BIGINT\n)\n");
        sqlStringBuffer.append("BEGIN\n");
        sqlStringBuffer.append("  UPDATE ").append(tableModel.getTableName()).append(" SET\n").append(getUpdateSql(tableModel));
        sqlStringBuffer.append("  WHERE id=idValue;\n  SET SPReturnValue=ROW_COUNT();\nEND;");



        return sqlStringBuffer.toString();
    }

    private static String getUpdateSql(TableModel tableModel) {
        StringBuffer paraStringBuffer = new StringBuffer("");
        for(FieldModel fieldModel: tableModel.getFields())
        {
            if("id".equalsIgnoreCase(fieldModel.getFieldName())|| CREATE_TIME.equalsIgnoreCase(fieldModel.getFieldName())|| DELETE_STATE.equalsIgnoreCase(fieldModel.getFieldName()))
            {
                continue;
            }

            if(UPDATE_TIME.equalsIgnoreCase(fieldModel.getFieldName()))
            {
                paraStringBuffer.append("  "+fieldModel.getFieldName()).append("=NOW(),\n");
            }else{
                paraStringBuffer.append("  "+fieldModel.getFieldName()).append("="+fieldModel.getFieldName()+"Value,\n");
            }


        }

        paraStringBuffer.deleteCharAt(paraStringBuffer.length()-2);

        return paraStringBuffer.toString();
    }

    private static String getInsertSql(TableModel tableModel) {
        StringBuffer paraStringBuffer = new StringBuffer("(");
        StringBuffer paraValueStringBuffer = new StringBuffer(" VALUES(");
        for(FieldModel fieldModel: tableModel.getFields())
        {
           if(Arrays.asList("id").contains(fieldModel.getFieldName()))
           {
               continue;
           }
            paraStringBuffer.append("\n\t"+fieldModel.getFieldName()).append(",");

            if(DELETE_STATE.equalsIgnoreCase(fieldModel.getFieldName()))
            {
                paraValueStringBuffer.append("0,");
            }else if(CREATE_TIME.equalsIgnoreCase(fieldModel.getFieldName())||UPDATE_TIME.equalsIgnoreCase(fieldModel.getFieldName()))
            {
                paraValueStringBuffer.append("NOW(),");
            }else
            {
                paraValueStringBuffer.append("\n\t"+fieldModel.getFieldName()).append("Value,");
            }

        }

        paraStringBuffer.deleteCharAt(paraStringBuffer.length()-1).append(")");
        paraValueStringBuffer.deleteCharAt(paraValueStringBuffer.length()-1).append(")");

        return paraStringBuffer.append(paraValueStringBuffer).toString();
    }

    private static String getAddPara(TableModel tableModel) {

       return  getProcedurePara(tableModel,true);
    }

    private static String getProcedurePara(TableModel tableModel,boolean isAdd) {

        StringBuffer paraStringBuffer = new StringBuffer();
        for(FieldModel fieldModel: tableModel.getFields())
        {
            if(isAdd)
            {
                if(add_Ignore_List.contains(fieldModel.getFieldName())) continue;
            }else{
                if(Arrays.asList("create_time","update_time","delete_state").contains(fieldModel.getFieldName())) continue;
            }
            paraStringBuffer.append("\nIN ").append(fieldModel.getFieldName()).append("Value ").append(fieldModel.getFieldType().toUpperCase()).append(getLengthString(fieldModel)).append(", ");
        }

        return paraStringBuffer.toString();
    }

    public static String getDefaultString(FieldModel fieldModel) {
        if(Strings.isNullOrEmpty(fieldModel.getFieldDefaultValue()))
        {
            return "";
        }

        if("int".equalsIgnoreCase(fieldModel.getFieldType()) || "bit".equalsIgnoreCase(fieldModel.getFieldType()))
        {
            return  " default "+ CommonUtils.getIntValue(fieldModel.getFieldDefaultValue(),0);
        }else if("char".equalsIgnoreCase(fieldModel.getFieldType())||"varchar".equalsIgnoreCase(fieldModel.getFieldType()))
        {
            return  " default '"+fieldModel.getFieldDefaultValue()+"'";
        }
        else {
            return "";
        }
    }

    public static String getLengthString(FieldModel fieldModel) {
        if("int".equalsIgnoreCase(fieldModel.getFieldType()))
        {
            return "(11)";
        }else if("char".equalsIgnoreCase(fieldModel.getFieldType())||"varchar".equalsIgnoreCase(fieldModel.getFieldType()))
        {
            return "("+fieldModel.getFieldLength()+")";
        }
        else {
            return "";
        }

    }


}
