package com.carterbrother.techtool.webdevtool.code.service;

import com.carterbrother.techtool.webdevtool.code.model.DataBaseModel;
import com.carterbrother.techtool.webdevtool.code.model.TableModel;
import com.carterbrother.techtool.webdevtool.utils.DateUtils;
import com.carterbrother.techtool.webdevtool.utils.GenerateUtil;
import com.carterbrother.techtool.webdevtool.utils.SessionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by carter on 2017/5/23. Copyright © 2016 －2017 旦倍科技
 *
 * 生产bll代码
 *
 */
public class BllService {


    /**
     * 生产bll代码；
     * @param tableModel
     * @param request
     * @return
     */
    public static String generateBll(TableModel tableModel, HttpServletRequest request) {

        DataBaseModel dataBaseModel = SessionUtils.getDataBase(request);
        String tableEntityName = GenerateUtil.getTableEntityName(tableModel);

        StringBuffer  bllStringBuffer = new StringBuffer();


        //package
        bllStringBuffer.append("package "+dataBaseModel.getDbPackageName()+".bll;\n\n");

        //import
        bllStringBuffer.append("\nimport "+dataBaseModel.getDbPackageName()+".dao."+ tableEntityName +"Dao;");
        bllStringBuffer.append("\nimport com.danbay.dbaccess.DataTable;");
        bllStringBuffer.append("\nimport com.danbay.dbaccess.SplitPage;");

        //注释
        bllStringBuffer.append("\n\n/**");
        bllStringBuffer.append("\n * Created by "+dataBaseModel.getDatabaseCreator()+" on "+ DateUtils.Date2String(new Date())+". Copyright © 2016 －2017 旦倍科技");
        bllStringBuffer.append("\n * "+tableModel.getTableComment()+"操作的BLL层");
        bllStringBuffer.append("\n */");

        //class
        bllStringBuffer.append("\npublic class "+ tableEntityName +"Bll {\n");

        //field

        bllStringBuffer.append("\n\n\tprivate "+tableEntityName+"Dao  dao = new "+tableEntityName+"Dao();");

        //create
        bllStringBuffer.append("\n\n\t/**");
        bllStringBuffer.append("\n\t* 新增"+tableModel.getTableComment());

        tableModel.getFields().stream().filter(item-> !SqlService.add_Ignore_List.contains(item.getFieldName())).collect(Collectors.toList())
                .forEach(item->{
                    bllStringBuffer.append("\n\t* @param ").append(item.getFieldName()).append("\t").append(item.getFieldComment());
                });
        bllStringBuffer.append("\n\t* @return   新增的记录id");

        bllStringBuffer.append("\n\t*/");

        //create method
        bllStringBuffer.append("\n\n\tpublic  Long  create(");

        Optional.ofNullable(tableModel.getFields().stream().filter(item-> !SqlService.add_Ignore_List.contains(item.getFieldName())).collect(Collectors.toList())).ifPresent(
                op->{
                     op.forEach(item->{
                        bllStringBuffer.append(GenerateUtil.typeMap.get(item.getFieldType())+" ").append(item.getFieldName()).append(",");
                    });

                     if(op.size()>0)
                     {
                         bllStringBuffer.deleteCharAt(bllStringBuffer.length()-1);
                     }
                }
        );



        bllStringBuffer.append(")");
        bllStringBuffer.append("\n\t{");
        bllStringBuffer.append("\n\t\treturn  dao.create(");

        Optional.ofNullable(tableModel.getFields().stream().filter(item-> !SqlService.add_Ignore_List.contains(item.getFieldName())).collect(Collectors.toList())).ifPresent(op->{
            op.forEach(item->{
                bllStringBuffer.append(item.getFieldName()).append(",");
            });
            if(op.size()>0)
            bllStringBuffer.deleteCharAt(bllStringBuffer.length()-1);
        });

        bllStringBuffer.append(");");
        bllStringBuffer.append("\n\t}");

        //deleteById
        bllStringBuffer.append("\n\n\t/**");
        bllStringBuffer.append("\n\t* 按照id删除"+tableModel.getTableComment());
        bllStringBuffer.append("\n\t* @param id "+tableModel.getTableComment()+"的id");
        bllStringBuffer.append("\n\t* @return  删除结果");
        bllStringBuffer.append("\n\t*/");

        bllStringBuffer.append("\n\n\tpublic  boolean  deleteById(Long id)");
        bllStringBuffer.append("\n\t{");
        bllStringBuffer.append("\n\t\treturn  dao.deleteById(id,false)>0;");
        bllStringBuffer.append("\n\t}");

        //update
        bllStringBuffer.append("\n\n\t/**");
        bllStringBuffer.append("\n\t* 更新"+tableModel.getTableComment());

        tableModel.getFields().stream().filter(item-> !SqlService.update_Ignore_List.contains(item.getFieldName())).collect(Collectors.toList())
                .forEach(item->{
                    bllStringBuffer.append("\n\t* @param ").append(item.getFieldName()).append("\t").append(item.getFieldComment());
                });
        bllStringBuffer.append("\n\t* @return   更新结果");
        bllStringBuffer.append("\n\t*/");
        bllStringBuffer.append("\n\n\tpublic  boolean  update(");
        tableModel.getFields().stream().filter(item-> !SqlService.update_Ignore_List.contains(item.getFieldName())).collect(Collectors.toList())
                .forEach(item->{
                    bllStringBuffer.append(GenerateUtil.typeMap.get(item.getFieldType())+" ").append(item.getFieldName()).append(",");
                });
        bllStringBuffer.deleteCharAt(bllStringBuffer.length()-1);
        bllStringBuffer.append(")");
        bllStringBuffer.append("\n\t{");
        bllStringBuffer.append("\n\t\treturn  dao.update(");

        tableModel.getFields().stream().filter(item-> !SqlService.update_Ignore_List.contains(item.getFieldName())).collect(Collectors.toList())
                .forEach(item->{
                    bllStringBuffer.append(item.getFieldName()).append(" ,");
                });
        bllStringBuffer.deleteCharAt(bllStringBuffer.length()-1);
        bllStringBuffer.append(")>0;");
        bllStringBuffer.append("\n\t}");

        //findById
        bllStringBuffer.append("\n\n\t/**");
        bllStringBuffer.append("\n\t* 通过id查找"+tableModel.getTableComment());
        bllStringBuffer.append("\n\t* @param id "+tableModel.getTableComment()+"id");
        bllStringBuffer.append("\n\t* @return   "+tableModel.getTableComment()+"信息组成的table");
        bllStringBuffer.append("\n\t*/");

        bllStringBuffer.append("\n\tpublic DataTable findById(Long id)");
        bllStringBuffer.append("\n\t{");
        bllStringBuffer.append("\n\t\t return  dao.findById(id);");
        bllStringBuffer.append("\n\t}");

        //findAll
        bllStringBuffer.append("\n\n\t/**");
        bllStringBuffer.append("\n\t* 查询所有没有删除的"+tableModel.getTableComment());
        bllStringBuffer.append("\n\t*/");

        bllStringBuffer.append("\n\tpublic DataTable findAll()");
        bllStringBuffer.append("\n\t{");
        bllStringBuffer.append("\n\t\t return  dao.findAll(false);");
        bllStringBuffer.append("\n\t}");


        //findByPage
        bllStringBuffer.append("\n\n\t/**");
        bllStringBuffer.append("\n\t* 分页查询所有没有删除的"+tableModel.getTableComment());
        bllStringBuffer.append("\n\t* @param pageIndex  第几页");
        bllStringBuffer.append("\n\t* @param pageSize   每一页记录条数");
        bllStringBuffer.append("\n\t*/");

        bllStringBuffer.append("\n\tpublic DataTable findByPage(int pageIndex, int pageSize)");
        bllStringBuffer.append("\n\t{");
        bllStringBuffer.append("\n\t\treturn  dao.findByPage(pageIndex,pageSize);");
        bllStringBuffer.append("\n\t}");

        //findByPageWithCounts
        bllStringBuffer.append("\n\n\t/**");
        bllStringBuffer.append("\n\t* 分页查询所有没有删除的"+tableModel.getTableComment()+"，并返回总记录数；");
        bllStringBuffer.append("\n\t* @param pageIndex  第几页");
        bllStringBuffer.append("\n\t* @param pageSize   每一页记录条数");
        bllStringBuffer.append("\n\t*/");

        bllStringBuffer.append("\n\tpublic SplitPage findByPageWithCounts(int pageIndex, int pageSize)");
        bllStringBuffer.append("\n\t{");
        bllStringBuffer.append("\n\t\treturn  dao.findByPageWithCounts(pageIndex,pageSize);");
        bllStringBuffer.append("\n\t}");

        //结束

        bllStringBuffer.append("\n\n\n\n}");



        return bllStringBuffer.toString();
    }


}
