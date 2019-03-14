package com.edu.zucc.ygg.movie.dao;

import com.edu.zucc.ygg.movie.domain.UpgradePro;
import com.edu.zucc.ygg.movie.dto.UpgradeSearchDto;
import com.edu.zucc.ygg.movie.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpgradeProMapper extends MyMapper<UpgradePro> {

    public int updateState(@Param("id")int id,@Param("state")int state);

    public List<UpgradePro> searchUpgrade(UpgradeSearchDto searchDto);

    @Select("select count(0) from  upgrade_pro where state = 0 and user_id = #{id}")
    public int exist(@Param("id")int id);
}