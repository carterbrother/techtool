//package com.danbay.angular.bll;
//
//import com.danbay.angular.dao.Device_ListDao2;
//import com.danbay.dbaccess.DataTable;
//import com.danbay.dbaccess.SplitPage;
//
///**
// * Created by carter on 2017/5/23. Copyright © 2016 －2017 旦倍科技
// * 设备操作的BLL层
// */
//public class Device_ListBll2 {
//
//    private Device_ListDao2 dao = new Device_ListDao2();
//
//
//    /**
//     * 新增设备
//     * @param device_no  设备编号
//     * @param sn_id      设备型号
//     * @return   新增的记录id
//     */
//    public  Long  create(String device_no, String sn_id)
//    {
//        return  dao.create(device_no,sn_id);
//    }
//
//
//    /**
//     * 按照id删除设备
//     * @param id 设备的id
//     * @return  删除结果
//     */
//    public  boolean  deleteById(Long id)
//    {
//        return  dao.deleteById(id,false)>0;
//    }
//
//    /**
//     * 更新设备
//     * @param id   设备id
//     * @param device_no     设备编号
//     * @param sn_id         设备型号
//     * @return  更新结果
//     */
//    public  boolean  update(Long id,String device_no, String sn_id)
//    {
//        return  dao.update(id,device_no,sn_id)>0;
//    }
//
//    /**
//     * 通过id查找设备
//     * @param id 设备id
//     * @return 设备信息组成的table
//     */
//    public DataTable findById(Long id)
//    {
//        return  dao.findById(id);
//    }
//
//    /**
//     * 查询所有没有删除的设备
//     */
//    public DataTable findAll()
//    {
//        return  dao.findAll(false);
//    }
//
//    /**
//     * 分页查询所有没有的设备；
//     * @param pageIndex  第几页
//     * @param pageSize   每一页记录条数
//     */
//    public DataTable findByPage(int pageIndex, int pageSize)
//    {
//        return  dao.findByPage(pageIndex,pageSize);
//    }
//
//    /**
//     * 分页查询所有没有删除的设备，并返回总记录数；
//     * @param pageIndex   第几页
//     * @param pageSize    每一页记录条数
//     * @return
//     */
//    public SplitPage findByPageWithCounts(int pageIndex, int pageSize)
//    {
//        return  dao.findByPageWithCounts(pageIndex,pageSize);
//    }
//
//
//
//
//}
