package com.kotlin.aop.aspect

import lombok.extern.slf4j.Slf4j
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionStatus
import org.springframework.transaction.support.DefaultTransactionDefinition
import java.sql.SQLException

@Slf4j
@Aspect
@Component
class MyTransactionalAspect(
    private val transactionalManger: PlatformTransactionManager
) {

    private val logger = LoggerFactory.getLogger(MyTransactionalAspect::class.java)


    @Around("@annotation(com.kotlin.aop.annotation.MyTransactional)")
    fun aroundMyTransactional(joinPoint: ProceedingJoinPoint): Any? {
        val transactionalDefinition = DefaultTransactionDefinition()
        val methodSignature = joinPoint.signature as MethodSignature

        println("Aspect triggered for method: ${methodSignature.method.name}")

        val params = joinPoint.args.mapIndexed { index, arg -> "arg$index=$arg" }
        logger.info("Starting transaction for method ${methodSignature.method.name} with args: $params")

        val transactionStatus = transactionalManger.getTransaction(transactionalDefinition)

        return try {
            val result = joinPoint.proceed()
            transactionalManger.commit(transactionStatus)
            result
        } catch (e: SQLException) {
            logger.error("Exception in transaction, rolling back. Error code: ${e.errorCode}", e)
            transactionalManger.rollback(transactionStatus)
            throw e
        }
    }


}