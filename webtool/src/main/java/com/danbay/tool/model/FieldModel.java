package com.danbay.tool.model;

/**
 * Created by carter on 2017/2/7. Copyright © 2016 －2017 旦倍科技
 */
public class FieldModel {

    private String fieldName ="";
    private String fieldComment ="";
    private String fieldType ="";
    private int fieldLength =0;
    private String fieldDefaultValue ="";
    private String formHtmlType ="";//添加或者编辑页面对应的标签类型

    public FieldModel() {
    }

    public FieldModel(String fieldName, String fieldComment, String fieldType, int fieldLength) {
        this.fieldName = fieldName;
        this.fieldComment = fieldComment;
        this.fieldType = fieldType;
        this.fieldLength = fieldLength;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldComment() {
        return fieldComment;
    }

    public void setFieldComment(String fieldComment) {
        this.fieldComment = fieldComment;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public int getFieldLength() {
        return fieldLength;
    }

    public void setFieldLength(int fieldLength) {
        this.fieldLength = fieldLength;
    }

    public String getFieldDefaultValue() {
        return fieldDefaultValue;
    }

    public void setFieldDefaultValue(String fieldDefaultValue) {
        this.fieldDefaultValue = fieldDefaultValue;
    }

    public String getFormHtmlType() {
        return formHtmlType;
    }

    public void setFormHtmlType(String formHtmlType) {
        this.formHtmlType = formHtmlType;
    }
}
