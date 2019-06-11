package com.carterbrother.techtool.webdevtool.code.web;

import com.carterbrother.techtool.webdevtool.code.model.DataBaseModel;
import com.carterbrother.techtool.webdevtool.code.model.FieldModel;
import com.carterbrother.techtool.webdevtool.code.model.TableModel;
import com.carterbrother.techtool.webdevtool.code.service.*;
import com.carterbrother.techtool.webdevtool.utils.*;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by carter on 2017/2/7. Copyright © 2016 －2017 旦倍科技
 */
@Controller("CodeController")
@RequestMapping("/code")
public class CodeController {

    final String View_Path = "/code/";
    final String  filePath = "C:\\database\\";
    final String  codePath = "C:\\database\\code\\";


    @RequestMapping("/exit.do")
    public ModelAndView exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SessionUtils.exit(request);
        return index(request,response);
    }
    /**
     * 生成代码；
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/generateCode.do")
    public ModelAndView generateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        DataBaseModel dataBaseModel = SessionUtils.getDataBase(request);
        //生成sql
        GenerateUtil.genereateSqlFile(dataBaseModel, codePath);
        //生成dao 生成bll 生成model
        GenerateUtil.genereateCodeFile(dataBaseModel, codePath,request);
        //生成pom
        GenerateUtil.generatePom(dataBaseModel,codePath);





        ModelAndView mv = new ModelAndView(View_Path+"gene");
        mv.addObject("codePath",codePath+"db."+dataBaseModel.getDatabaseName());
        return  mv;
    }




    /**
     * 把数据库相关信息保存成json文件；
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/openJson.do")
    public ModelAndView openJson(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = new ModelAndView(View_Path + "open");

        File file = new File(filePath);


        List<String> files = Arrays.asList(file.listFiles()).stream().map((File fileitem)-> fileitem.getName()).collect(Collectors.toList());


        modelAndView.addObject("files",files);

        return     modelAndView;
    }


    @RequestMapping("/open.do")
    public ModelAndView open(HttpServletRequest request, HttpServletResponse response) {

        String  fileItem = CommonUtils.getStringValue(request.getParameter("fileItem"));
        if(StringUtils.isEmpty(fileItem))
        {
            return showMsg(response,false,"fileItem为空");
        }

        File file = new File(filePath+fileItem);

        try {
            String databaseJsonString = Files.toString(file, Charsets.UTF_8);

            DataBaseModel dataBaseModel = JsonUtils.createObjectFromJson(databaseJsonString,DataBaseModel.class);

            SessionUtils.saveDataBase(request,dataBaseModel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return showMsg(response,true,"加载成功");


    }





    /**
     * 把数据库相关信息保存成json文件；
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/saveJson.do")
    public ModelAndView saveJson(HttpServletRequest request, HttpServletResponse response)
    {

        ModelAndView modelAndView = new ModelAndView(View_Path+"save");

        DataBaseModel dataBaseModel =  SessionUtils.getDataBase(request);

        if(null == dataBaseModel)
        {
            modelAndView.addObject("saveResult","保存失败，对象为空;");
        }


        //保存成文件，放在系统的C盘 database目录下；



        File file =  new File(filePath);

        if(!file.exists())
        {
            file.mkdirs();
        }

        String fileName = dataBaseModel.getDatabaseName()+"_"+ DateUtils.Date2String(new Date(),"yyyyMMddHHmmss")+".danbayDatabase";

        byte[] bytes = JsonUtils.getJson(dataBaseModel).getBytes(Charsets.UTF_8);
        try {
            Files.write(bytes,new File(filePath+fileName));
            modelAndView.addObject("saveResult","保存成功，目录是："+fileName+"，可以下次再打开;");
        } catch (IOException e) {
            e.printStackTrace();
            modelAndView.addObject("saveResult","保存失败，原因是："+e.getMessage());
        }


        return  modelAndView;

    }


    @RequestMapping("/index.do")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView modelAndView = new ModelAndView(View_Path+"index");
        DataBaseModel dataBase = SessionUtils.getDataBase(request);
        modelAndView.addObject(SessionUtils.DANBAY_DATABASE, dataBase);
        modelAndView.addObject("title",null==dataBase?"创建":"编辑");

        return  modelAndView;
    }

    @RequestMapping("/tables.do")
    public ModelAndView tables(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView modelAndView = new ModelAndView(View_Path+"tables");
        DataBaseModel dataBaseModel =  SessionUtils.getDataBase(request);
        Map<String, String> tableMap = Maps.newHashMap();
        if(null != dataBaseModel)
        {
            tableMap = dataBaseModel.getTableMap();
        }

        modelAndView.addObject("tables", tableMap);
        return  modelAndView;
    }

    @RequestMapping("/database.do")
    public ModelAndView database(HttpServletRequest request, HttpServletResponse response)
    {
        String requestMethod = request.getMethod();
        if("get".equalsIgnoreCase(requestMethod))
        {
            ModelAndView mv = new ModelAndView(View_Path+"database");
            mv.addObject("currenturl", RequestUtils.getUrl(request));
            DataBaseModel dataBase = SessionUtils.getDataBase(request);
            mv.addObject("database", dataBase);
            mv.addObject("title",null==dataBase?"创建":"编辑");
            return  mv;
        }else
        {
            String databaseName = CommonUtils.getStringValue(request.getParameter("txt_databaseName"));
            String databaseComment = CommonUtils.getStringValue(request.getParameter("txt_databaseComment"));
            String databaseCreator = CommonUtils.getStringValue(request.getParameter("txt_databaseCreator"));
            String dbPackageName = CommonUtils.getStringValue(request.getParameter("txt_dbPackageName"));
            String appPackageName = CommonUtils.getStringValue(request.getParameter("txt_appPackageName"));

            if (Strings.isNullOrEmpty(databaseName))
            {
                return showMsg(response,false,"数据库名字不能为空");
            }

            if (Strings.isNullOrEmpty(databaseComment))
            {
                return showMsg(response,false,"数据库备注不能为空");
            }

            if (Strings.isNullOrEmpty(databaseCreator))
            {
                return showMsg(response,false,"数据库创建者不能为空");
            }

            DataBaseModel  dataBaseModel = new DataBaseModel();
            dataBaseModel.setDatabaseName(databaseName);
            dataBaseModel.setDatabaseComment(databaseComment);
            dataBaseModel.setDatabaseCreator(databaseCreator);
            dataBaseModel.setDbPackageName(dbPackageName);
            dataBaseModel.setAppPackageName(appPackageName);

            SessionUtils.saveDataBase(request,dataBaseModel);

            return showMsg(response,true,"保存成功");
        }

    }

    @RequestMapping("/table/{tableName}/delete.do")
    public ModelAndView deletetable(@PathVariable("tableName") String tableName, HttpServletRequest request, HttpServletResponse response)
    {
        SessionUtils.getDataBase(request).getTables().remove(tableName);
        SessionUtils.getDataBase(request).getTableMap().remove(tableName);

        return new ModelAndView(new RedirectView("/code/index.do"));
    }


    @RequestMapping("/table/{tableName}/save.do")
    public ModelAndView savetable(@PathVariable("tableName") String tableName, HttpServletRequest request, HttpServletResponse response)
    {
        String requestMethod = request.getMethod();
        if("get".equalsIgnoreCase(requestMethod))
        {
            ModelAndView mv = new ModelAndView(View_Path+"table");
            mv.addObject("currenturl", RequestUtils.getUrl(request));
            mv.addObject("table",SessionUtils.getTable(request,tableName));

            mv.addObject("title",tableName.equals("null")?"创建":"编辑");

            return  mv;
        }else
        {
            String tableName_pre = CommonUtils.getStringValue(request.getParameter("txt_tableName_pre"));
            String txt_tableName = CommonUtils.getStringValue(request.getParameter("txt_tableName"));
            String tableComment = CommonUtils.getStringValue(request.getParameter("txt_tableComment"));

            if (Strings.isNullOrEmpty(txt_tableName))
            {
                return showMsg(response,false,"表名字不能为空");
            }

            if (Strings.isNullOrEmpty(tableComment))
            {
                return showMsg(response,false,"表备注不能为空");
            }


            TableModel tableModel = new TableModel();
            tableModel.setTableName(txt_tableName);
            tableModel.setTableComment(tableComment);

            boolean addSuccess = SessionUtils.saveTable(request,tableModel,tableName_pre);
            if(addSuccess)
            {
                return showMsg(response,true,"保存成功");
            }else
            {
                return showMsg(response,false,"存在同名表"+tableName);
            }
        }

    }


    @RequestMapping("/table/{tableName}.do")
    public ModelAndView table(@PathVariable("tableName") String tableName, HttpServletRequest request, HttpServletResponse response)
    {
        String requestMethod = request.getMethod();
        ModelAndView mv = new ModelAndView(View_Path+"field");
        if("get".equalsIgnoreCase(requestMethod))
        {
            mv.addObject("currenturl", RequestUtils.getUrl(request));
            mv.addObject("tableName",tableName);
            mv.addObject("fieldList",SessionUtils.getTable(request,tableName).getFields());
            return  mv;
        }else
        {
            String fieldName_pre = CommonUtils.getStringValue(request.getParameter("txt_fieldName_pre"));
            String fieldName = CommonUtils.getStringValue(request.getParameter("txt_fieldName"));
            String fieldComment = CommonUtils.getStringValue(request.getParameter("txt_fieldComment"));
            String fieldType = CommonUtils.getStringValue(request.getParameter("sel_fieldType"));
            String fieldDefaultValue = CommonUtils.getStringValue(request.getParameter("txt_fieldDefaultValue"));
            int fieldLength = CommonUtils.getIntValue(request.getParameter("txt_fieldLength"),0);

            if (Strings.isNullOrEmpty(fieldName))
            {
                return showMsg(response,false,"字段名字不能为空");
            }

            if (Strings.isNullOrEmpty(fieldComment))
            {
                return showMsg(response,false,"字段备注不能为空");
            }

            if (Strings.isNullOrEmpty(fieldType))
            {
                return showMsg(response,false,"字段类型不能为空");
            }

            FieldModel fieldModel = new FieldModel();
           fieldModel.setFieldName(fieldName);
           fieldModel.setFieldComment(fieldComment);
           fieldModel.setFieldType(fieldType);
           fieldModel.setFieldLength(fieldLength);
           fieldModel.setFieldDefaultValue(fieldDefaultValue);


            boolean saveFieldSuccess = SessionUtils.saveField(request,tableName,fieldModel,fieldName_pre);
            if(saveFieldSuccess)
            {
                mv.setView(new RedirectView("/code//table/"+tableName+".do"));
                return mv;
            }else
            {
                return showMsg(response,false,"保存失败");
            }
        }
    }

    @RequestMapping("/field/{tableName}/{fieldName}/save.do")
    public ModelAndView editfield(@PathVariable("tableName") String tableName,@PathVariable("fieldName") String fieldName, HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mv = new ModelAndView(View_Path+"field");


        if(Arrays.asList("id","create_time","update_time","delete_state").contains(fieldName))
        {
            return showMsg(response,false,fieldName+"不可以编辑",RequestUtils.getUrl(request));
        }

        FieldModel fieldModel = SessionUtils.getTableField(request,tableName,fieldName);

            if(null!= fieldModel)
            {
                mv.addObject("selectfield",fieldModel);
                mv.addObject("tableName",tableName);
                mv.addObject("fieldList",SessionUtils.getTable(request,tableName).getFields());
                return mv;
            }else
            {
                return showMsg(response,false,"加载表字段失败");
            }
    }


    @RequestMapping("/field/{tableName}/{fieldName}/delete.do")
    public ModelAndView deletefield(@PathVariable("tableName") String tableName,@PathVariable("fieldName") String fieldName, HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mv = new ModelAndView(View_Path+"field");

        if(Arrays.asList("id","create_time","update_time","delete_state").contains(fieldName))
        {
            return showMsg(response,false,fieldName+"不可以删除");
        }

        TableModel tableModel = SessionUtils.getTable(request,tableName);

        if(null == tableModel)
        {
            return showMsg(response,false,tableName+"不不存在");
        }




           boolean  deleteTableFieldSuccess  = SessionUtils.deleteTableField(request,tableName,fieldName);

        if(deleteTableFieldSuccess)
        {
            mv.addObject("tableName",tableName);
            mv.addObject("fieldList",SessionUtils.getTable(request,tableName).getFields());
            return mv;
        }else
        {
            return showMsg(response,false,"删除表字段失败");
        }
    }




    @RequestMapping("/generate/{tableName}/{type}.do")
    public ModelAndView generate(@PathVariable("tableName") String tableName,@PathVariable("type") String type, HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mv = new ModelAndView(View_Path+"code");
        String code = "生成失败";

        TableModel tableModel = SessionUtils.getTable(request,tableName);
       DataBaseModel dataBaseModel = SessionUtils.getDataBase(request);


        if("syc".equalsIgnoreCase(type))
        {
            code = SycService.generateSyc(tableModel,dataBaseModel.getDatabaseName());
        }

        if("sql".equalsIgnoreCase(type))
        {
            code = SqlService.generateSql(tableModel,dataBaseModel.getDatabaseName());
        }
        if("jsp".equalsIgnoreCase(type))
        {
            if(tableModel != null && tableModel.getFields().size() > 0)
            {
                 code = JspService.generateListJsp(tableModel);
            }
        }
        if("controller".equalsIgnoreCase(type))
        {
            if(tableModel != null && tableModel.getFields().size() > 0)
            {
                code = ControllerService.generateController(tableModel);
            }

        }
        if("model".equalsIgnoreCase(type))
        {
            if (tableModel != null && tableModel.getFields().size() > 0)
            {
                code = ModelService.generateModel(tableModel,request);
            }
        }
        if("dao".equalsIgnoreCase(type))
        {
            if (tableModel != null && tableModel.getFields().size() > 0)
            {
                code = DaoService.generateDao(tableModel,request);
            }
        }

        if("bll".equalsIgnoreCase(type))
        {
            if (tableModel != null && tableModel.getFields().size() > 0)
            {
                code = BllService.generateBll(tableModel,request);
            }
        }

        mv.addObject("codedata",code);
        return mv;

    }




    public ModelAndView showMsg(HttpServletResponse response,boolean isSuccess, String message)
    {
        return new ModelAndView(new RedirectView("/error/message.do?url=xx&result=" + isSuccess + "&message=" + EncodingUtils.urlEncode(message)));
    }
    public ModelAndView showMsg(HttpServletResponse response,boolean isSuccess, String message,String url)
    {
        return new ModelAndView(new RedirectView("/error/message.do?url="+url+"&result=" + isSuccess + "&message=" + EncodingUtils.urlEncode(message)));
    }


}
