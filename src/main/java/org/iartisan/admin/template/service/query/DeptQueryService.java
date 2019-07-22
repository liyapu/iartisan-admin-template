package org.iartisan.admin.template.service.query;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.collect.Lists;
import org.iartisan.admin.template.dao.mapper.BizDeptMapper;
import org.iartisan.admin.template.dao.model.BizDeptDO;
import org.iartisan.admin.template.service.entity.DeptEntity;
import org.iartisan.admin.template.service.entity.DeptTreeEntity;
import org.iartisan.runtime.support.BaseQueryServiceSupport;
import org.iartisan.runtime.utils.CollectionUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门管理
 *
 * @author King  2019-7-19
 */
@Service
public class DeptQueryService extends BaseQueryServiceSupport<BizDeptMapper, DeptEntity> {

    public List<DeptTreeEntity> getDeptList() {
        //获取root节点
        Wrapper<BizDeptDO> queryWrapper = new EntityWrapper<>();
        queryWrapper.isNull("DEPT_PATH");
        List<BizDeptDO> dbResult = this.baseMapper.selectList(queryWrapper);
        DeptTreeEntity root = new DeptTreeEntity();
        List<DeptTreeEntity> roots = Lists.newArrayList();
        if (CollectionUtil.isNotEmpty(dbResult)) {
            BizDeptDO rootDept = dbResult.get(0);
            root.setId(rootDept.getDeptId());
            root.setTitle(rootDept.getDeptName());
            //查询下级节点
            getChildrenTree(root);
        }
        roots.add(root);
        return roots;
    }

    private void getChildrenTree(DeptTreeEntity entity) {
        //查询下级节点
        List<DeptTreeEntity> childrenDept = getChildrenDept(entity.getId());
        entity.setChildren(childrenDept);
        if (CollectionUtil.isNotEmpty(childrenDept)) {
            for (DeptTreeEntity o : childrenDept) {
                List<DeptTreeEntity> _childrenDept = getChildrenDept(o.getId());
                o.setChildren(_childrenDept);
                //下直去获取
                getChildrenTree(o);
            }
        }
    }

    private List<DeptTreeEntity> getChildrenDept(String deptId) {
        BizDeptDO dbQuery = new BizDeptDO();
        dbQuery.setDeptParent(deptId);
        Wrapper<BizDeptDO> queryWrapper = new EntityWrapper<>(dbQuery);
        List<BizDeptDO> dbResult = this.baseMapper.selectList(queryWrapper);
        List<DeptTreeEntity> dataList = Lists.newArrayList();
        if (CollectionUtil.isNotEmpty(dbResult)) {
            dbResult.forEach(v -> {
                DeptTreeEntity entity = new DeptTreeEntity();
                entity.setTitle(v.getDeptName());
                entity.setId(v.getDeptId());
                dataList.add(entity);
            });
            return dataList;
        }
        return null;
    }


}
