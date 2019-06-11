package com.carterbrother.techtool.webdevtool.code.service;

import com.carterbrother.techtool.webdevtool.code.model.DataBaseModel;
import com.carterbrother.techtool.webdevtool.code.model.FieldModel;
import com.carterbrother.techtool.webdevtool.code.model.TableModel;
import com.carterbrother.techtool.webdevtool.utils.DateUtils;
import com.carterbrother.techtool.webdevtool.utils.Constants;
import com.carterbrother.techtool.webdevtool.utils.GenerateUtil;
import com.carterbrother.techtool.webdevtool.utils.SessionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by yangyanchen on 2017/5/22
 * Copyright © 2016 －2017 旦倍科技
 */
public class DaoService {

    public static String generateDao(TableModel tableModel, HttpServletRequest request){
        DataBaseModel dataBaseModel = SessionUtils.getDataBase(request);
        String tableEntityName = GenerateUtil.getTableEntityName(tableModel);

        StringBuffer daoStringBuffer = new StringBuffer();



        //package
        daoStringBuffer.append("package "+dataBaseModel.getDbPackageName()+".dao;");

        //import
        daoStringBuffer.append("\n\nimport com.danbay.dbaccess.*;");
        daoStringBuffer.append("\nimport java.util.Optional;");

        //注释
        daoStringBuffer.append("\n\n/**");
        daoStringBuffer.append("\n * Created by "+dataBaseModel.getDatabaseCreator()+" on "+ DateUtils.Date2String(new Date())+". Copyright © 2016 －2017 旦倍科技");
        daoStringBuffer.append("\n * "+tableModel.getTableComment()+"dao操作代码");
        daoStringBuffer.append("\n */");
        //class
        daoStringBuffer.append("\npublic class "+ tableEntityName +"Dao {");

        //成员变量
        daoStringBuffer.append("\n\n\tprivate  static final String  database_connectString = \""+dataBaseModel.getDatabaseName()+"_connection\";");
        daoStringBuffer.append("\n\tprivate Mysql  _db =  new Mysql();");

        //create
        daoStringBuffer.append("\n\n\tpublic  Long  create(");

        Optional.of(tableModel.getFields().stream().filter(item-> !SqlService.add_Ignore_List.contains(item.getFieldName())).collect(Collectors.toList())).ifPresent(
                op->{
                      op.forEach(item->{
                        daoStringBuffer.append(GenerateUtil.typeMap.get(item.getFieldType())+" ").append(item.getFieldName()).append(",");
                    });
                    if(op.size()>0)
                    {
                        daoStringBuffer.deleteCharAt(daoStringBuffer.length()-1);
                    }
                }

        );

        daoStringBuffer.append("){");

        daoStringBuffer.append("\n\t\t_db.createConnection(database_connectString);");
        daoStringBuffer.append("\n\t\t_db.createCommand(\"cp_"+tableModel.getTableName()+"_create\", DBCommandType.StoreProcedure);");
        daoStringBuffer.append("\n\n\t\t//开始整理参数");

        tableModel.getFields().stream().filter(item-> !SqlService.add_Ignore_List.contains(item.getFieldName())).collect(Collectors.toList())
                .forEach(item->{
                    daoStringBuffer.append("\n\t\t _db.addParameter(\""+item.getFieldName()+"Value\","+item.getFieldName()+");");
                });

        daoStringBuffer.append("\n\t\t _db.addParameter(\"SPReturnValue\", ParameterDirection.Output);");
        daoStringBuffer.append("\n\n\t\t//开始执行操作");
        daoStringBuffer.append("\n\t\t_db.executeNonQuery();");
        daoStringBuffer.append("\n\t\t return Optional.ofNullable(Long.valueOf( _db.getParamValue(\"SPReturnValue\"))).orElse(0L);");

        daoStringBuffer.append("\n\t}");
        //deleteById
        daoStringBuffer.append("\n\n\n\tpublic  Long  deleteById(Long id, boolean realDelete){");
        daoStringBuffer.append("\n\n\t\t_db.createConnection(database_connectString);");
        daoStringBuffer.append("\n\t\t_db.createCommand(\"cp_"+tableModel.getTableName()+"_deleteById\", DBCommandType.StoreProcedure);");
        daoStringBuffer.append("\n\n\t\t//开始整理参数");
        daoStringBuffer.append("\n\t\t_db.addParameter(\"idValue\",id);");
        daoStringBuffer.append("\n\t\t_db.addParameter(\"RealDelete\",realDelete?1:0);");
        daoStringBuffer.append("\n\t\t_db.addParameter(\"SPReturnValue\", ParameterDirection.Output);");

        daoStringBuffer.append("\n\n\t\t//开始执行操作");
        daoStringBuffer.append("\n\t\t_db.executeNonQuery();");
        daoStringBuffer.append("\n\t\t return Optional.ofNullable(Long.valueOf( _db.getParamValue(\"SPReturnValue\"))).orElse(0L);");

        daoStringBuffer.append("\n\t}");

        //update
        daoStringBuffer.append("\n\n\n\tpublic  Long  update(");
        tableModel.getFields().stream().filter(item-> !SqlService.update_Ignore_List.contains(item.getFieldName())).collect(Collectors.toList())
                .forEach(item->{
                    daoStringBuffer.append(GenerateUtil.typeMap.get(item.getFieldType())+" ").append(item.getFieldName()).append(",");
                });
        daoStringBuffer.deleteCharAt(daoStringBuffer.length()-1);
        daoStringBuffer.append("){\n");
        daoStringBuffer.append("\n\t\t_db.createConnection(database_connectString);");
        daoStringBuffer.append("\n\t\t_db.createCommand(\"cp_"+tableModel.getTableName()+"_update\", DBCommandType.StoreProcedure);");
        daoStringBuffer.append("\n\n\t\t//开始整理参数");

        tableModel.getFields().stream().filter(item-> !SqlService.update_Ignore_List.contains(item.getFieldName())).collect(Collectors.toList())
                .forEach(item->{
                    daoStringBuffer.append("\n\t\t_db.addParameter(\""+item.getFieldName()+"Value\","+item.getFieldName()+");");
                });

        daoStringBuffer.append("\n\t\t _db.addParameter(\"SPReturnValue\", ParameterDirection.Output);");

        daoStringBuffer.append("\n\n\t\t//开始执行操作");
        daoStringBuffer.append("\n\t\t_db.executeNonQuery();");
        daoStringBuffer.append("\n\t\t return Optional.ofNullable(Long.valueOf( _db.getParamValue(\"SPReturnValue\"))).orElse(0L);");

        daoStringBuffer.append("\n\t}");
        //findById
        daoStringBuffer.append("\n\n\n\tpublic DataTable findById(Long id){\n");
        daoStringBuffer.append("\n\t\t_db.createConnection(database_connectString);");
        daoStringBuffer.append("\n\t\t_db.createCommand(\"cp_"+tableModel.getTableName()+"_findById\", DBCommandType.StoreProcedure);");
        daoStringBuffer.append("\n\t\t_db.addParameter(\"idValue\", id);");
        daoStringBuffer.append("\n\t\treturn  Optional.ofNullable(_db.executeQuery()).orElse(new DataTable());");
        daoStringBuffer.append("\n\t}");

        //findAll
        daoStringBuffer.append("\n\n\n\tpublic DataTable findAll(boolean IsGetAll) {\n");
        daoStringBuffer.append("\n\t\t_db.createConnection(database_connectString);");
        daoStringBuffer.append("\n\t\t_db.createCommand(\"cp_"+tableModel.getTableName()+"_findAll\", DBCommandType.StoreProcedure);");
        daoStringBuffer.append("\n\t\t_db.addParameter(\"IsGetAll\", IsGetAll?1:0);");
        daoStringBuffer.append("\n\t\treturn  Optional.ofNullable(_db.executeQuery()).orElse(new DataTable());");
        daoStringBuffer.append("\n\t}");
        //findByPage
        daoStringBuffer.append("\n\n\n\tpublic DataTable findByPage(int pageIndex, int pageSize) {\n");
        daoStringBuffer.append("\n\t\t_db.createConnection(database_connectString);");
        daoStringBuffer.append("\n\t\t_db.createCommand(\"cp_"+tableModel.getTableName()+"_findByPage\", DBCommandType.StoreProcedure);");
        daoStringBuffer.append("\n\n\t\t_db.addParameter(\"pageIndex\", pageIndex);");
        daoStringBuffer.append("\n\t\t_db.addParameter(\"pageSize\", pageSize);");
        daoStringBuffer.append("\n\n\t\treturn  Optional.ofNullable(_db.executeQuery()).orElse(new DataTable());");
        daoStringBuffer.append("\n\t}");
        //findByPageWithCounts
        daoStringBuffer.append("\n\n\n\tpublic SplitPage findByPageWithCounts(int pageIndex, int pageSize){\n");
        daoStringBuffer.append("\n\t\t_db.createConnection(database_connectString);");
        daoStringBuffer.append("\n\t\t_db.createCommand(\"cp_"+tableModel.getTableName()+"_findByPageWithCounts\", DBCommandType.StoreProcedure);");
        daoStringBuffer.append("\n\n\t\t_db.addParameter(\"pageIndex\", pageIndex);");
        daoStringBuffer.append("\n\t\t_db.addParameter(\"pageSize\", pageSize);");
        daoStringBuffer.append("\n\t\treturn new SplitPage(pageIndex,pageSize,Optional.ofNullable(Integer.valueOf( _db.getParamValue(\"SPReturnValue\"))).orElse(0),Optional.ofNullable(_db.executeQuery()).orElse(new DataTable())) ;");
        daoStringBuffer.append("\n\t}");
        //end

        daoStringBuffer.append("\n\n}");
        return daoStringBuffer.toString();
    }

    public static String generateDao_dapeng(TableModel table, HttpServletRequest request)
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("package ")
                .append(SessionUtils.getDataBase(request).getDbPackageName())
                .append(".dao;\n");
        buffer.append("public class ")
                .append(ModelService.removeUnderLine(table.getTableName(),true))
                .append("Dao {\n");
        buffer.append("public static final String _connectstring  = \"")
                .append(SessionUtils.getDataBase(request).getDatabaseName())
                .append("_connection\";\n");
        buffer.append("private Mysql _db = new Mysql();\n");
        //添加方法
        buffer.append("public boolean addRecord(")
                .append(ModelService.removeUnderLine(table.getTableName(),true))
                .append("Model model)\n");
        buffer.append("{\n");
        buffer.append(generateStmt(Constants.addMethod,table.getTableName()));
        buffer.append(generateParameter(table.getFields(),Constants.addMethod));
        buffer.append(generateOutPutStmt());
        buffer.append("}\n");
        //删除方法
        buffer.append("public boolean deleteRecord(int id,int realDelete)\n")
                .append("{\n")
                .append(generateStmt(Constants.deleteMethod,table.getTableName()));
        buffer.append("_db.addParameter(\"id_value\",id);\n");
        buffer.append("_db.addParameter(\"real_delete\",realDelete);\n");
        buffer.append(generateOutPutStmt());
        buffer.append("}\n");
        //修改方法
        buffer.append("public boolean updateRecord(")
                .append(ModelService.removeUnderLine(table.getTableName(),true))
                .append("Model model)\n");
        buffer.append("{\n");
        buffer.append(generateStmt(Constants.updateMethod,table.getTableName()));
        buffer.append(generateParameter(table.getFields(),Constants.updateMethod));
        buffer.append(generateOutPutStmt());
        buffer.append("}\n");
        //单个实体查询
        buffer.append("public ")
                .append(ModelService.removeUnderLine(table.getTableName(),true))
                .append("Model ")
                .append("getRecord(int id)\n");
        buffer.append("{\n");
        buffer.append(generateStmt(Constants.getByIdMethod,table.getTableName()));
        buffer.append((ModelService.removeUnderLine(table.getTableName(),true)))
                        .append("Model model = new ")
                .append((ModelService.removeUnderLine(table.getTableName(),true)))
                        .append("Model();\n");
        buffer.append("_db.addParameter(\"id_value\",id);\n");
        buffer.append("DataTable table = _db.executeQuery();\n");
        buffer.append("if(table.rows.length>0){\n");
        buffer.append(generateResult(table.getFields()));
        buffer.append("}\n");
        buffer.append("return  model;\n");
        buffer.append("}\n");
        //查询所有
        buffer.append("public SplitPage getRecords(int pageIndex,int pageSize)\n");
        buffer.append("{\n");
        buffer.append(generateStmt(Constants.getRecordsMethod,table.getTableName()));
        buffer.append("_db.addParameter(\"page_index\", pageIndex);\n");
        buffer.append("_db.addParameter(\"page_size\", pageSize);\n");
        buffer.append("_db.addParameter(\"SPReturnValue\", ParameterDirection.Output);\n");
        buffer.append("DataTable dataTable = _db.executeQuery();\n");
        buffer.append("int rows = Integer.parseInt(_db.getParamValue(\"SPReturnValue\"));\n");
        buffer.append("return  new SplitPage(pageIndex,pageSize,rows,dataTable);\n");
        buffer.append("}\n");
        buffer.append("}\n");
        return buffer.toString();
    }

    private static String generateStmt(int stmtType,String tableName)
    {
        //备注：1:添加,2:删除,3:修改,4:单个条件查询,5:分页查询(带总数)
        StringBuffer buffer = new StringBuffer("_db.createConnection(_connectstring);\n");
        switch (stmtType)
        {
            case 1:
                buffer.append("_db.createCommand(\"cp_" +
                         tableName+
                        "_addRecord\", DBCommandType.StoreProcedure);\n");
                break;
            case 2:
                buffer.append("_db.createCommand(\"cp_" +
                        tableName+
                        "_deleteRecord\", DBCommandType.StoreProcedure);\n");
                break;
            case 3:
                buffer.append("_db.createCommand(\"cp_" +
                        tableName+
                        "_updateRecord\", DBCommandType.StoreProcedure);\n");
                break;
            case 4:
                buffer.append("_db.createCommand(\"cp_" +
                        tableName+
                        "_getRecordById\", DBCommandType.StoreProcedure);\n");
                break;
            case 5:
                buffer.append("_db.createCommand(\"cp_" +
                        tableName+
                        "_getRecords\", DBCommandType.StoreProcedure);\n");
                break;
        }
        return buffer.toString();
    }

    private static String generateParameter(List<FieldModel> list, int stmtType)
    {
        StringBuffer buffer = new StringBuffer();
        Iterator iterator = list.iterator();
        while (iterator.hasNext())
        {
            FieldModel fieldModel = (FieldModel) iterator.next();
            if("id".equalsIgnoreCase(fieldModel.getFieldName()) && stmtType == 1)
            {
                continue;
            }
            if("create_time".equalsIgnoreCase(fieldModel.getFieldName()))
            {
                continue;
            }
            if("delete_state".equalsIgnoreCase(fieldModel.getFieldName()))
            {
                continue;
            }
            if("update_time".equalsIgnoreCase(fieldModel.getFieldName()))
            {
                continue;
            }
            buffer.append("_db.addParameter(\"")
                    .append(fieldModel.getFieldName())
                    .append("_value\",")
                    .append("model.get")
                    .append(upperCaseField(fieldModel.getFieldName()))
                    .append("());\n");
        }
        return buffer.toString();
    }

    private static String upperCaseField(String fieldName)
    {
        if(fieldName.equalsIgnoreCase("id"))
        {
            return "Id";
        }
        fieldName = fieldName.replace(fieldName.substring(0, 1), fieldName.substring(0, 1).toUpperCase());
        String[] temp = fieldName.split("_");
        String result = "";
        for (int i = 0; i < temp.length; i++) {
            temp[i] = temp[i].replace(temp[i].substring(0, 1), temp[i].substring(0, 1).toUpperCase());
            result += temp[i];
        }
        return result;
    }

    private static String generateOutPutStmt()
    {
        StringBuffer buffer = new StringBuffer();
         buffer.append("_db.addParameter(\"SPReturnValue\", ParameterDirection.Output);\n");
         buffer.append("_db.executeNonQuery();\n");
         buffer.append("return CommonUtils.getIntValue(_db.getParamValue(\"SPReturnValue\"))>0;\n");
         return buffer.toString();
    }

    private static String generateResult(List<FieldModel> list)
    {
        StringBuffer buffer = new StringBuffer();
        Iterator iterator = list.iterator();
        while (iterator.hasNext())
        {
            FieldModel model = (FieldModel)iterator.next();
            buffer.append("model.set")
                    .append(upperCaseField(model.getFieldName()))
                    .append("(")
                    .append(inferType(model.getFieldType(),model.getFieldName()))
                    .append("table.rows[0].getColumnValue(")
                    .append("\""+model.getFieldName()+"\"")
                    .append(")));\n");
        }
        return buffer.toString();
    }

    private static String inferType(String fieldType,String fieldName)
    {
        String result = "";
        if("unsigned bigint".equalsIgnoreCase(fieldType))
        {
           result =  "CommonUtils.getIntValue(";
        }
        if("varchar".equalsIgnoreCase(fieldType))
        {
            result = "CommonUtils.getStringValue(";
        }
        if("datetime".equalsIgnoreCase(fieldType))
        {
            if("update_time".equalsIgnoreCase(fieldName))
            {
                return result;
            }
            result = "CommonUtils.getDateTime2Value(";
        }
        if("tinyint".equalsIgnoreCase(fieldType))
        {
            result =  "CommonUtils.getIntValue(";
        }
        if("bit".equalsIgnoreCase(fieldType))
        {
            result =  "CommonUtils.getBooleanValue(";
        }
        if("decimal".equalsIgnoreCase(fieldType))
        {
            result =  "CommonUtils.getDoubleValue(";
        }
        return result;
    }
}
