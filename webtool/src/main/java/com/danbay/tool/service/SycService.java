package com.danbay.tool.service;

import com.danbay.tool.model.FieldModel;
import com.danbay.tool.model.TableModel;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by carter on 2017/6/6. Copyright © 2016 －2017 旦倍科技
 */
public class SycService {
    public static String generateSyc(TableModel tableModel, String databaseName) {

        StringBuffer sycStringBuffer = new StringBuffer();

        //1,清理存储过程语句；
        sycStringBuffer.append("USE danbay_task;\n");
        sycStringBuffer.append("DROP PROCEDURE IF EXISTS syc_"+tableModel.getTableName()+";\n");
        
        //2,创建表
        //创建表格

        sycStringBuffer.append(" \n/*建表"+tableModel.getTableName()+"脚本*/\n");
        sycStringBuffer.append("DROP TABLE IF EXISTS "+tableModel.getTableName()+" ;\n");

        sycStringBuffer.append("CREATE TABLE");
        sycStringBuffer.append("`").append(tableModel.getTableName()).append("` (\n");

        for(FieldModel fieldModel: tableModel.getFields().stream().filter(item-> !Arrays.asList("create_time","update_time","delete_state").contains(item.getFieldName())).collect(Collectors.toList()))
        {
            if("id".equalsIgnoreCase(fieldModel.getFieldName()))
            {
                sycStringBuffer.append("`id` bigint(20) NOT NULL ,\n");
            }else
            {
                sycStringBuffer.append("`").append(fieldModel.getFieldName()).append("` ").append(fieldModel.getFieldType())
                        .append(SqlService.getLengthString(fieldModel)) /*.append(" NOT NULL ")*/.append(SqlService.getDefaultString(fieldModel)).append("  COMMENT '").append(fieldModel.getFieldComment()).append("',\n");
            }
        }

        sycStringBuffer.delete(sycStringBuffer.length()-2,sycStringBuffer.length()-1);

        sycStringBuffer.append(") ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='");
        sycStringBuffer.append(tableModel.getTableComment()).append("';\n\n");
        
        
        //2,创建存储过程
        sycStringBuffer.append("CREATE PROCEDURE syc_"+tableModel.getTableName()+"()\n");
        sycStringBuffer.append("\tCOMMENT '同步其他数据库中的数据到任务库中，方便进行查看，一般适合数据量比较少的表。'\n");
        sycStringBuffer.append("BEGIN");
        sycStringBuffer.append("\n\t#1，拿到来源表，这里做全量同步；");
        sycStringBuffer.append("\n\tSET     @dest_table ='"+tableModel.getTableName()+"';");
        sycStringBuffer.append("\n\tSELECT  @source_table:=task_list.from_table FROM task_list WHERE table_name=@dest_table;");
        sycStringBuffer.append("\n\t#2,更新值不等的数据;");
        sycStringBuffer.append("\n\tSELECT concat('UPDATE ',@dest_table,'  T_dest,',@source_table,' T_source  SET') INTO @sql;");
        sycStringBuffer.append("\n\tSELECT concat(@sql,'\n" );


                //字段循环；
                tableModel.getFields().stream().filter(item->!Arrays.asList("id","create_time","update_time","delete_state").contains(item.getFieldName())).forEach(item->{
                    sycStringBuffer.append("T_dest."+item.getFieldName()+"=T_source."+item.getFieldName()+",");
                });
         sycStringBuffer.deleteCharAt(sycStringBuffer.length()-1);


                sycStringBuffer.append("  WHERE T_dest.id=T_source.id\n" +
                "  AND (\n" );
                        tableModel.getFields().stream().filter(item->!Arrays.asList("id","create_time","update_time","delete_state").contains(item.getFieldName())).forEach(item->{
                            sycStringBuffer.append("T_dest."+item.getFieldName()+" <> T_source."+item.getFieldName()+" OR ");
                        });
        sycStringBuffer.delete(sycStringBuffer.length()-3,sycStringBuffer.length()-1);

                sycStringBuffer.append("        )\\; ') INTO @sql;");
        sycStringBuffer.append("\n\t##执行拼接的sql；");
        sycStringBuffer.append("\n\tPREPARE stmt2 FROM @sql;");
        sycStringBuffer.append("\n\tEXECUTE stmt2 ;");
        sycStringBuffer.append("\n\tDEALLOCATE PREPARE stmt2;");

        sycStringBuffer.append("\n\t#3，插入目标表中不存在的数据；");
        sycStringBuffer.append("\n\tSELECT concat(' INSERT INTO ',@dest_table,'(\n");

                tableModel.getFields().stream().filter(item->!Arrays.asList("create_time","update_time","delete_state").contains(item.getFieldName())).forEach(item->{
                    sycStringBuffer.append(item.getFieldName()+",");
                });
        sycStringBuffer.deleteCharAt(sycStringBuffer.length()-1);
        sycStringBuffer.append("  ) SELECT ");
        tableModel.getFields().stream().filter(item->!Arrays.asList("create_time","update_time","delete_state").contains(item.getFieldName())).forEach(item->{
            sycStringBuffer.append(item.getFieldName()+",");
        });
        sycStringBuffer.deleteCharAt(sycStringBuffer.length()-1);

                sycStringBuffer.append("  FROM\n" +
                "  ',@source_table,' WHERE id not IN ( SELECT id FROM ',@dest_table,');') INTO @sql;");
        sycStringBuffer.append("\n\t##执行拼接的sql；");
        sycStringBuffer.append("\n\tPREPARE stmt2 FROM @sql;");
        sycStringBuffer.append("\n\tEXECUTE stmt2 ;");
        sycStringBuffer.append("\n\tDEALLOCATE PREPARE stmt2;");


        sycStringBuffer.append("\n\t #4，删除原表中不存在的数据；");
        sycStringBuffer.append("\n\tSELECT concat(' DELETE FROM ',@dest_table,' WHERE id not IN (\n" +
                "  SELECT id FROM  ',@source_table,')\\;') INTO  @sql;");
        sycStringBuffer.append("\n\t##执行拼接的sql；");
        sycStringBuffer.append("\n\tPREPARE stmt2 FROM @sql;");
        sycStringBuffer.append("\n\tEXECUTE stmt2 ;");
        sycStringBuffer.append("\n\tDEALLOCATE PREPARE stmt2;");

        sycStringBuffer.append("\n\t#5，更新任务执行信息；");
        sycStringBuffer.append("\n\tUPDATE task_list SET update_time=now() ,last_time=now() WHERE table_name=@dest_table;");
        sycStringBuffer.append("\nEND");

        return sycStringBuffer.toString();
    }
}
