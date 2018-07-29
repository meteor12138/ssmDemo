package com.met.service.impl;

import com.met.dto.Exposer;
import com.met.dto.SeckillExecution;
import com.met.entity.Seckill;
import com.met.exception.RepeatKillException;
import com.met.exception.SeckillCloseException;
import com.met.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceImplTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() {
        List<Seckill> seckillList = seckillService.getSeckillList();
        logger.info("list={}", seckillList);
    }

    @Test
    public void getById() {
        long id = 1000L;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}", seckill);
    }

    /*@Test
    public void exportsSeckillUrl() {
        long id = 1004L;
        Exposer exposer = seckillService.exportsSeckillUrl(id);
        logger.info("exposer{}",exposer);
    }

    @Test
    public void executeSeckill() {
        long id = 1004L;
        long userPhone = 15666668888L;
        String md5 = "fdc3cfe2541b3ce04f208fd030959211";
        SeckillExecution seckillExecution = seckillService.executeSeckill(id,userPhone,md5);
        logger.info("result{}",seckillExecution);
    }*/

    @Test
    public void testSeckillLogic() {
        long id = 1004;
        Exposer exposer = seckillService.exportsSeckillUrl(id);
        if (exposer.isExposed()) {
            logger.info("exposer={}", exposer);
            long userPhone = 14866665555L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(id, userPhone, md5);
                logger.info("result={}", seckillExecution);
            } catch (RepeatKillException e) {
                logger.error(e.getMessage());
            } catch (SeckillCloseException e) {
                logger.error(e.getMessage());
            }
        } else {
            // 秒杀未开启
            logger.warn("exposer={}", exposer);
        }
    }
}