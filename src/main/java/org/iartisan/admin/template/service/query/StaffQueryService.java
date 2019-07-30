package org.iartisan.admin.template.service.query;

import org.iartisan.admin.template.dao.mapper.BizStaffMapper;
import org.iartisan.admin.template.service.entity.StaffEntity;
import org.iartisan.runtime.bean.Page;
import org.iartisan.runtime.bean.PageWrapper;
import org.iartisan.runtime.support.BaseQueryServiceSupport;
import org.springframework.stereotype.Service;

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
    public PageWrapper<StaffEntity> getPageData(Page page, StaffEntity entity) {
        return getPageDataByWrapper(page, null);
    }


}
