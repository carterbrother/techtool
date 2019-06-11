package com.danbay.tool.service;

import com.danbay.tool.model.FieldModel;
import com.danbay.tool.model.TableModel;

/**
 * Created by yangyanchen on 2017/2/8.
 */
public final class JspService {

    public static String generateListJsp(TableModel tableModel) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<%@ page language=\"java\" contentType=\"text/html;charset=UTF-8\"%>\n")
                .append("<%@include file=\"/inc/header.jsp\" %>\n")
                .append("<div class=\"pad-10\">\n")
                .append("<fieldset>\n")
                .append("<legend>")
                .append(tableModel.getTableComment() + "</legend>\n")
                .append("<div class=\"table-list\">\n")
                .append("<table width=\"100%\" cellspacing=\"0\">\n")
                .append("<thead>\n")
                .append("<tr align=\"center\">\n");
        for (FieldModel fieldModel:tableModel.getFields()) {
            buffer.append("<th>").append(fieldModel.getFieldComment()).append("</th>\n");
        }
         buffer.append("</tr>\n"+"</thead>\n")
               .append("\n")
               .append("<c:forEach items=\"${dataList}\" var=\"e\">\n")
               .append("<tr align=\"center\">\n");
        for (FieldModel fieldModel:tableModel.getFields()) {
            buffer.append("<td>${e.getColumnValue(\"")
                    .append(fieldModel.getFieldName().toLowerCase())
                    .append("\") } </td>\n");
        }
        buffer.append("<td><a>编辑</a>| <a>删除</a></td>\n")
                .append("</tr>\n")
                .append("</c:forEach>\n")
                .append("</table>\n")
                .append("<div class=\"pkg\">${pageInfo}</div>\n")
                .append("</div>\n")
                .append("</fieldset>\n")
                .append("</div>\n")
                .append("\n")
                .append("<%@include file=\"/inc/footer.jsp\"  %>\n");
        return buffer.toString();
    }

}
