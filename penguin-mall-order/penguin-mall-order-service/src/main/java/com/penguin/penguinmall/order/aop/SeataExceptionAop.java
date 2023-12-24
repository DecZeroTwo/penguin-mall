package com.penguin.penguinmall.order.aop;

import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransactionContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Aspect
@Component
@Slf4j
public class SeataExceptionAop {
    @Pointcut("@annotation(io.seata.spring.annotation.GlobalTransactional)")
    public void transactionPointCut(){}
    @AfterThrowing(throwing = "e",pointcut = "transactionPointCut()")
    public void  globalTransactionalExceiton(Throwable e) throws TransactionException {
        log.debug("分布式事务异常:{}", e.getMessage());
        String xid = RootContext.getXID();
        if(StringUtils.hasText(xid)){
            log.debug("XID:{}执行回滚操作", xid);
            GlobalTransactionContext.reload(xid).rollback();
            log.debug("事务:{}回滚完成", xid);
            throw new TransactionException("事务处理失败，回滚完成........");
        }
    }
}