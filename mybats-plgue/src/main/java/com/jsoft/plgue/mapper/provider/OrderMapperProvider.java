package com.jsoft.plgue.mapper.provider;

import com.jsoft.plgue.domain.resp.NumPO;

public class OrderMapperProvider {


    public String findMemberById(Long meetingId) {

        StringBuffer sql = new StringBuffer();
        sql.append(" select * from common_member where is_deleted = 0 ");
        sql.append(" and id = ").append(meetingId);
        return sql.toString();
    }

    public String queryListById(int i) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select * from `order` o left JOIN `order_info` b on o.id = b.id ");
        sql.append(" where o.id = 1 ");
        return sql.toString();
    }


    public String updateById(int i, String name) {
        StringBuffer sql = new StringBuffer();
        sql.append(" update `order` set name = ").append(name);
        sql.append("  where id = 2 ");
        return sql.toString();
    }


    public String getComStaMaxSerialNumber(String key) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select * from t_num where `key` = '").append(key).append("'");
        sql.append(" order by num desc limit 1 ");
        return sql.toString();
    }
    public String getComStaMaxSerialNumber2(NumPO numPO) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select * from t_num where `key` = '").append(numPO.getKey()).append("'");
        sql.append(" order by num desc limit 1 ");
        return sql.toString();
    }


}
