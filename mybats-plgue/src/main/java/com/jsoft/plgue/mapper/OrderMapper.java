//package com.jsoft.plgue.mapper;
//
//import com.jsoft.plgue.annotation.TableCreate;
//import com.jsoft.plgue.domain.resp.NumPO;
//import com.jsoft.plgue.domain.resp.OrderPO;
//import com.jsoft.plgue.enums.SubTypeEnums;
//import com.jsoft.plgue.mapper.provider.OrderMapperProvider;
//import org.apache.ibatis.annotations.*;
//
//import java.util.List;
//import java.util.Map;
//
//@Mapper
//@TableCreate(tableName = "t_num", strategyName = SubTypeEnums.SUFFIX)
//public interface OrderMapper {
////    @InterceptAnnotation
//    @SelectProvider(method = "queryListById", type = OrderMapperProvider.class)
//    List<OrderPO> queryListById(int i);
//
//    @UpdateProvider(method = "updateById", type = OrderMapperProvider.class)
//    void updateById(int i, String name);
//
//    @SelectProvider(method = "getComStaMaxSerialNumber", type = OrderMapperProvider.class)
//    NumPO getComStaMaxSerialNumber(String key);
//
//    @Insert({" insert into t_num (`key`, num, version, is_del) values (#{key}, #{num}, #{version}, #{is_del}) "})
//    void insert(NumPO copyCode);
////    @Insert({" insert into t_num (id, `key`, num, version, is_del) values (#{id} , #{key}, #{num}, #{version}, #{is_del}) "})
//    @Insert({ "<script>", "insert into t_num (id, `key`, num, version, is_del) values", "<foreach collection='id' item='' separator=','>", "(#{id} , #{key}, #{num}, #{version}, #{is_del})</foreach>", "</script>"})
//    void saveNum(@Param("id") List<NumPO> id);
//
//    @Select({" call getMaxId('t_num', 1, @maxId) "})
//    Integer getMaxId();
//
////    @Select({" select * from t_num where id in <foreach collection='ids' item='id' open='(' separator=',' close=','>#{id}</foreach> "})
//    @Select({ "<script>", "select", "*", "from", "t_num", "where id in", "<foreach collection='id' item='id' open='(' separator=',' close=')'>#{id}</foreach>", "</script>" })
//    List<NumPO> getNumList(@Param("id") List<String> id);
//
//    @Select({" select * from t_num where id = #{map.id} "})
//    List<NumPO> getNumList2(Map<String, String> map);
//}
