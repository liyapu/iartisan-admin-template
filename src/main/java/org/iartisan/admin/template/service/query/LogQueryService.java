package org.iartisan.admin.template.service.query;

import org.iartisan.admin.template.dao.mapper.SystemLogMapper;
import org.iartisan.admin.template.service.entity.LogEntity;
import org.iartisan.runtime.support.BaseQueryServiceSupport;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志分页查询
 *
 * @author King
 * @since 2018/4/17
 */
@Service
public class LogQueryService extends BaseQueryServiceSupport<SystemLogMapper, LogEntity> {
}
