package com.fibo.ddp.analysis.controller;



import com.fibo.ddp.common.model.analyse.vo.AnalyseData;
import com.fibo.ddp.common.model.analyse.vo.AnalyseEngineSummaryVo;
import com.fibo.ddp.common.model.analyse.vo.AnalyseRequestParam;
import com.fibo.ddp.common.model.common.ResponseEntityBuilder;
import com.fibo.ddp.common.model.common.ResponseEntityDto;
import com.fibo.ddp.common.service.analyse.AnalyseEngineSummaryService;
import com.fibo.ddp.common.service.analyse.AnalyseService;
import com.fibo.ddp.common.service.analyse.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("v3/analyse")
public class AnalyseController {

    @Autowired
    private AnalyseService analyseService;
    @Resource
    @Qualifier("analyseChartStatisticServiceImpl")
    private StatisticsService statisticsService;
    @Autowired
    private AnalyseEngineSummaryService analyseEngineSummaryService;

    /**
     * 分析中心-下方图表数据
     * @param param
     * @return
     */
    @PostMapping("/getData")
    public ResponseEntityDto getAnalyseData(@RequestBody AnalyseRequestParam param){
        AnalyseData analyseData = analyseService.getAnalyseData(param);
        return ResponseEntityBuilder.buildNormalResponse(analyseData);
        return "danglegedang";
    }

    /**
     * 分析中心-引擎概况数据
     * @param param
     * @return
     */
    @PostMapping("/getEngineSummary")
    public ResponseEntityDto getEngineSummary(@RequestBody AnalyseRequestParam param){
        Map<String, AnalyseEngineSummaryVo> analyseData = analyseEngineSummaryService.getAnalyseData(param);
        return ResponseEntityBuilder.buildNormalResponse(analyseData);
    }

    /**
     * 分析中心- 监控数据统计分析后入库
     * @return
     */
    @PostMapping("/decision")
    public ResponseEntityDto decision(){
        statisticsService.statisticData();
        return ResponseEntityBuilder.buildNormalResponse();
    }
}
