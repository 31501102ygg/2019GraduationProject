package com.edu.zucc.ygg.movie.service.impl;

import com.edu.zucc.ygg.movie.dao.UpgradeProMapper;
import com.edu.zucc.ygg.movie.domain.UpgradePro;
import com.edu.zucc.ygg.movie.dto.UpgradeSearchDto;
import com.edu.zucc.ygg.movie.service.UpgradeProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpgradeProServiceImpl implements UpgradeProService {
    @Autowired
    UpgradeProMapper upgradeProMapper;

    @Override
    public int add(UpgradePro upgradePro) {
        return upgradeProMapper.insert(upgradePro);
    }

    @Override
    public int update(int id, int state) {
        return upgradeProMapper.updateState(id,state);
    }

    @Override
    public List<UpgradePro> search(UpgradeSearchDto searchDto) {
        return upgradeProMapper.searchUpgrade(searchDto);
    }

    @Override
    public boolean exist(int id) {
        if (upgradeProMapper.exist(id)>0)
            return true;
        return false;
    }

    @Override
    public int getUserId(int id) {
        UpgradePro upgradePro = upgradeProMapper.selectByPrimaryKey(id);
        return upgradePro.getUserId();
    }
}
