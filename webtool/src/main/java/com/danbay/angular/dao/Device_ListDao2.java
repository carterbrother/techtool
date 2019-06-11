//package com.danbay.angular.dao;
//
//import com.danbay.dbaccess.*;
//
//import java.util.Optional;
//
///**
// * Created by carter on 2017/5/23. Copyright © 2016 －2017 旦倍科技
// * 设备dao操作代码
// */
//public class Device_ListDao2 {
//
//    private  static final String  database_connectString = "test";
//    private Mysql  _db =  new Mysql();
//
//    public Long create(String device_no, String sn_id) {
//        _db.createConnection(database_connectString);
//        _db.createCommand("cp_device_list_create", DBCommandType.StoreProcedure);
//
//        //开始整理参数
//        _db.addParameter("device_noValue",device_no);
//        _db.addParameter("sn_idValue",sn_id);
//        _db.addParameter("SPReturnValue", ParameterDirection.Output);
//
//        //开始执行操作
//        _db.executeNonQuery();
//
//        return Optional.ofNullable(Long.valueOf( _db.getParamValue("SPReturnValue"))).orElse(0L);
//
//    }
//
//    public Long deleteById(Long id, boolean realDelete) {
//        _db.createConnection(database_connectString);
//        _db.createCommand("cp_device_list_deleteById", DBCommandType.StoreProcedure);
//
//        //开始整理参数
//        _db.addParameter("idValue",id);
//        _db.addParameter("RealDelete",realDelete?1:0);
//        _db.addParameter("SPReturnValue", ParameterDirection.Output);
//
//        //开始执行操作
//        _db.executeNonQuery();
//
//        return Optional.ofNullable(Long.valueOf( _db.getParamValue("SPReturnValue"))).orElse(0L);
//    }
//
//    public Long update(Long id, String device_no, String sn_id) {
//        _db.createConnection(database_connectString);
//        _db.createCommand("cp_device_list_update", DBCommandType.StoreProcedure);
//
//        //开始整理参数
//        _db.addParameter("idValue",id);
//        _db.addParameter("device_noValue",device_no);
//        _db.addParameter("sn_idValue",sn_id);
//        _db.addParameter("SPReturnValue", ParameterDirection.Output);
//
//        //开始执行操作
//        _db.executeNonQuery();
//
//        return Optional.ofNullable(Long.valueOf( _db.getParamValue("SPReturnValue"))).orElse(0L);
//    }
//
//    public DataTable findById(Long id) {
//        _db.createConnection(database_connectString);
//        _db.createCommand("cp_device_list_findById",DBCommandType.StoreProcedure);
//        _db.addParameter("idValue", id);
//
//        return  Optional.ofNullable(_db.executeQuery()).orElse(new DataTable());
//    }
//
//    public DataTable findAll(boolean IsGetAll) {
//        _db.createConnection(database_connectString);
//        _db.createCommand("cp_device_list_findAll",DBCommandType.StoreProcedure);
//        _db.addParameter("IsGetAll", IsGetAll?1:0);
//
//        return  Optional.ofNullable(_db.executeQuery()).orElse(new DataTable());
//    }
//
//    public DataTable findByPage(int pageIndex, int pageSize) {
//        _db.createConnection(database_connectString);
//        _db.createCommand("cp_device_list_findByPage",DBCommandType.StoreProcedure);
//        _db.addParameter("pageIndex", pageIndex);
//        _db.addParameter("pageSize", pageSize);
//
//        return  Optional.ofNullable(_db.executeQuery()).orElse(new DataTable());
//    }
//
//    public SplitPage findByPageWithCounts(int pageIndex, int pageSize) {
//        _db.createConnection(database_connectString);
//        _db.createCommand("cp_device_list_findByPageWithCounts",DBCommandType.StoreProcedure);
//        _db.addParameter("pageIndex", pageIndex);
//        _db.addParameter("pageSize", pageSize);
//
//        return new SplitPage(pageIndex,pageSize,Optional.ofNullable(Integer.valueOf( _db.getParamValue("SPReturnValue"))).orElse(0),Optional.ofNullable(_db.executeQuery()).orElse(new DataTable())) ;
//    }
//}
