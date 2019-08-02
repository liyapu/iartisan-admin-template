package org.iartisan.admin.template.service.management;

import org.iartisan.admin.template.dao.mapper.BizStaffMapper;
import org.iartisan.admin.template.dao.model.BizStaffDO;
import org.iartisan.admin.template.service.entity.StaffEntity;
import org.iartisan.runtime.constants.FlagType;
import org.iartisan.runtime.support.BaseManagementServiceSupport;
import org.iartisan.runtime.utils.UUIDUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author King  2019-7-30
 */
@Service
public class StaffManagementService extends BaseManagementServiceSupport<BizStaffMapper, StaffEntity> {

    @Override
    public void saveData(StaffEntity staffEntity) {
        BizStaffDO dbInsert = new BizStaffDO();
        dbInsert.setStaffId(UUIDUtil.timeBaseId(32));
        dbInsert.setStaffName(staffEntity.getStaffName());
        dbInsert.setStaffDept(staffEntity.getStaffDept());
        dbInsert.setStaffStatus(FlagType.Y.name());
        dbInsert.setCreateTime(new Date());
        this.baseMapper.insert(dbInsert);
    }
}
