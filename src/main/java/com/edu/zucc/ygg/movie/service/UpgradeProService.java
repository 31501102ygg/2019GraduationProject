package com.edu.zucc.ygg.movie.service;

import com.edu.zucc.ygg.movie.domain.UpgradePro;
import com.edu.zucc.ygg.movie.dto.UpgradeSearchDto;

import java.util.List;

public interface UpgradeProService {

    public int add(UpgradePro upgradePro);

    public int update(int id,int state);

    public List<UpgradePro> search(UpgradeSearchDto searchDto);

    public boolean exist(int id);

    public int getUserId(int id);
}
