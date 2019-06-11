package com.carterbrother.techtool.webdevtool.code.service;

import com.carterbrother.techtool.webdevtool.code.model.FieldModel;
import com.carterbrother.techtool.webdevtool.code.model.TableModel;

/**
 * Created by yangyanchen on 2017/2/8.
 */
public final class ControllerService {
    /**
     * 提供工程名，requestmapping
     * 备注:没有提供requestMapping与VIEWPATH 后续自己进行添加
     * @return
     */
    public static String generateController(TableModel tableModel){
        StringBuffer buffer = new StringBuffer();
        Class cls = TableModel.class;
        buffer.append("@Controller(\"").append(cls.getSimpleName()).append("Controller\")\n");
        buffer.append("@RequestMapping(\"\")\n");
        buffer.append("public class "+cls.getSimpleName()+"Controller"+" extends BaseController{\n");
        buffer.append("private String VIEW_PATH = \"\";\n");
        buffer.append("@RequestMapping(\"/list.do\")\n");
        buffer.append(" public ModelAndView getRecordListPage(HttpServletRequest request, HttpServletResponse response){\n");
        buffer.append("//检测登录，并判断权限,ADMIN_USER_ADD\n");
        buffer.append("BasePage page  = this.checkLogin(request,response);\n");
        buffer.append("if(page.userID == 0)\n");
        buffer.append("{\n");
        buffer.append("return this.login(request);\n");
        buffer.append("}\n");
        buffer.append("if(!this.isUserHavePower(page.userID, ProjectsAdmin.Device_AmMeter_List))\n");//备注：待修改
        buffer.append("{\n");
        buffer.append("return ShowMessage.show(response,false,\"您不具有当前操作权限\");\n");
        buffer.append("}\n");
        buffer.append("int pageIndex = CommonUtils.getIntValue(request.getParameter(\"page\"),1);\n");
        buffer.append("SplitPage t = new"+ cls.getSimpleName()+"Bll"+"().getRecordListPage(pageIndex,30);\n");
        buffer.append("//设置返回值\n");
        buffer.append("ModelAndView modelAndView = new ModelAndView(VIEW_PATH + \"list\");\n");
        buffer.append("modelAndView.addObject(\"dataList\",t.getPageDate().rows);\n");
        buffer.append("modelAndView.addObject(\"pageInfo\",t.returnSimplePage(request));\n");
        buffer.append("return modelAndView ;\n");
        buffer.append("}\n");
        buffer.append("\n");
        buffer.append("@RequestMapping(\"/{id}/save.do\")\n");
        buffer.append("public ModelAndView save(@PathVariable(\"id\") int id,HttpServletRequest request, HttpServletResponse response)\n");
        buffer.append("{\n");
        buffer.append("//检测登录，并判断权限,ADMIN_USER_ADD\n");
        buffer.append("BasePage page  = this.checkLogin(request,response);\n");
        buffer.append("if(page.userID == 0)\n");
        buffer.append("{\n");
        buffer.append("return this.login(request);\n");
        buffer.append("}\n");
        buffer.append("if(!this.isUserHavePower(page.userID, ProjectsAdmin.Device_AmMeter_Save))\n");//备注：待修改
        buffer.append("{\n");
        buffer.append("return ShowMessage.show(response,false,\"您不具有当前操作权限\");\n");
        buffer.append("}\n");
        buffer.append("//设置返回值\n");
        buffer.append("ModelAndView mv = new ModelAndView(VIEW_PATH + \"info\");\n");
        buffer.append("mv.addObject(\"saveURL\", RequestUtils.getUrl(request));\n");
        buffer.append("boolean isAdd = (id==0);\n");
        buffer.append("mv.addObject(\"isAdd\",isAdd);\n");
        buffer.append(cls.getSimpleName()+" object = new "+cls.getSimpleName()+"();\n");
        buffer.append("object.setID(id)\n");
        buffer.append("String requestMethod = request.getMethod();\n");
        buffer.append("if(\"get\".equalsIgnoreCase(requestMethod))\n");
        buffer.append("{//查询保存\n");
        buffer.append("if(isAdd)\n");
        buffer.append("{\n");
        buffer.append("object = new "+cls.getSimpleName()+"();\n");
        buffer.append("}else\n");
        buffer.append("{\n");
        buffer.append("object = new"+cls.getSimpleName()+"Bll().getRecordInfoByID(id);");
        buffer.append("mv.addObject(\"data\",object);");
        buffer.append("}else if (\"post\".equalsIgnoreCase(requestMethod))\n");
        buffer.append("{\n");
        buffer.append("//获取参数，校验\n");
        //备注：校验参数只有一个参考模版，后续进行添加
        //获取参数，校验
        buffer.append("String AmMeterName = CommonUtils.getStringValue(request.getParameter(\"txt_AmMeterName\"));\n");
        //备注：参数校验的返回信息只生成一个信息返回模版，后续进行添加
        buffer.append("if(Strings.isNullOrEmpty(AmMeterName))\n");
        buffer.append(" {\n");
        buffer.append("return  ShowMessage.show(response,false,\"返回信息\");\n");
        buffer.append("}\n");
        //备注：设置参数的实体属性会生成所有的set方法，后续进行删除
        for (FieldModel fieldModel:tableModel.getFields()) {
            String newString = fieldModel.getFieldName().substring(0,1).toUpperCase()+fieldModel.getFieldName().substring(1);
            buffer.append("object.set"+newString+"();\n");
        }
        buffer.append("if(isAdd)\n");
        buffer.append("{\n");
        buffer.append("new "+cls.getSimpleName()+"Bll().addRecord(object);\n");
        buffer.append("}\n");
        buffer.append("else{\n");
        buffer.append("new "+cls.getSimpleName()+"Bll().updateRecord(object);\n");
        buffer.append("}\n");
        buffer.append("mv.setView(new RedirectView(\"/list.do\"\n");
        buffer.append("}\n");
        buffer.append("return mv ;\n");
        buffer.append("}\n");
        buffer.append("\n");
        buffer.append("@RequestMapping(\"/{id}/delete.do\")\n");
        buffer.append("public ModelAndView delete(@PathVariable(\"id\") int id, HttpServletRequest request, HttpServletResponse response)\n");
        buffer.append("{\n");
        buffer.append("//检测登录，并判断权限,ADMIN_USER_ADD\n");
        buffer.append("BasePage page  = this.checkLogin(request,response);\n");
        buffer.append("if(page.userID == 0)\n");
        buffer.append("{\n");
        buffer.append("return this.login(request);\n");
        buffer.append("}\n");
        //备注：权限常量值后续将进行修改
        buffer.append("if(!this.isUserHavePower(page.userID, ProjectsAdmin.Device_AmMeter_Delete))\n");
        buffer.append("{\n");
        buffer.append("return ShowMessage.show(response,false,\"您不具有当前操作权限\");\n");
        buffer.append("}\n");
        buffer.append("new "+cls.getSimpleName()+"Bll().deleteRecord(id);\n");
        buffer.append("return getRecordListPage(request,response);\n");
        buffer.append("}\n");
        return buffer.toString();
    }
}
