package com.dongbao.ums.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class MybatisPlusMetaObjectHandle implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("Insert Column gmt_create");
        this.strictInsertFill(metaObject, "gmtCreate", LocalDateTime.class, LocalDateTime.now());
        this.strictUpdateFill(metaObject, "gmtModified", LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("Update column gmt_modify");
        this.strictUpdateFill(metaObject, "gmtModified", LocalDateTime.class, LocalDateTime.now());
    }
}
