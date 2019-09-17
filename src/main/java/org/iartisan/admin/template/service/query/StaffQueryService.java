package org.iartisan.admin.template.service.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.iartisan.admin.template.dao.mapper.BizStaffMapper;
import org.iartisan.admin.template.service.entity.StaffEntity;
import org.iartisan.runtime.bean.PageWrapper;
import org.iartisan.runtime.bean.Pagination;
import org.iartisan.runtime.constants.DataStatus;
import org.iartisan.runtime.constants.FlagType;
import org.iartisan.runtime.support.BaseQueryServiceSupport;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门管理
 *
 * @author King  2019-7-19
 */
@Service
public class StaffQueryService extends BaseQueryServiceSupport<BizStaffMapper, StaffEntity> {

    /**
     * 分页查询员工数据
     *
     * @param page
     * @return
     */
    @Override
    public PageWrapper<StaffEntity> getPageData(Pagination page, StaffEntity entity) {
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("STAFF_DEPT", entity.getStaffDept());
        wrapper.eq("STATUS", DataStatus.E.name());
        return getPageDataByWrapper(page, wrapper);
    }

    public List<StaffEntity> getStaffListByDeptId(String deptId) {
        StaffEntity t = new StaffEntity();
        t.setStaffDept(deptId);
        t.setStaffStatus(FlagType.Y.name());
        return getListDataByObjs(t);
    }

}
