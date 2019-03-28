package com.lucky.share.service;

import com.lucky.share.constant.ErrorCode;
import com.lucky.share.convert.AjaxResult;
import com.lucky.share.domain.Bonus;
import com.lucky.share.domain.BonusDetail;
import com.lucky.share.dto.TranDto;
import com.lucky.share.mapper.BonusDetailMapper;
import com.lucky.share.mapper.BonusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kevin.Chen
 * @date 2019/03/24
 */
@Service
public class BonusService {

    @Autowired
    private BonusDetailMapper bonusDetailMapper;

    @Autowired
    private BonusMapper bonusMapper;

    /**
     * 创建账号
     * @param uid
     * @return
     */
    public AjaxResult<Object> create(Integer uid) {
        Bonus bonus = new Bonus();
        bonus.setUid(uid);
        bonus.setBalance(0);
        // 这里不管存不存在，都返回SUCCESS
        bonusMapper.insert(bonus);
        return new AjaxResult<Object>().create(ErrorCode.SUCCESS, null);
    }

    /**
     * 获取详情
     * @param uid
     * @param page
     * @param size
     * @return
     */
    public AjaxResult<List> getDetail(Integer uid, Integer page, Integer size) {
        return new AjaxResult<List>().create(ErrorCode.SUCCESS, bonusDetailMapper.getDetail(uid, page * size, size));
    }

    /**
     * 账号交易
     * @param uid
     * @param tran
     * @return
     */
    public AjaxResult<Object> tran(Integer uid, TranDto tran) {
        // 查询余额
        Integer balance = bonusMapper.getBalance(uid);
        if (balance == null) {
            return new AjaxResult<>().create(ErrorCode.BONUS_NOTEXIST, null);
        }

        // 预判断余额是否充值
        // volume 为负的时候，则为扣款
        if (tran.getVolume() < 0 && (balance + tran.getVolume() <= 0)) {
            // 余额不足
            return new AjaxResult<>().create(ErrorCode.BONUS_NOTENOUGH, null);
        }

        // 实现扣款，sql中还要再次判断余额是否足够
        Integer result = bonusMapper.updateBalance(uid, tran.getVolume());
        if (result <= 0) {
            // 更新失败 ,余额不足
            return new AjaxResult<>().create(ErrorCode.BONUS_NOTENOUGH, null);
        }

        // 写详情表
        BonusDetail detail = new BonusDetail();
        detail.setBalance(balance + tran.getVolume());
        detail.setRemark(tran.getRemark());
        detail.setUid(uid);
        detail.setVolume(tran.getVolume());
        bonusDetailMapper.insert(detail);
        return new AjaxResult<>().create(ErrorCode.SUCCESS, detail.getBalance());
    }
}
