package com.cloud.ocs.portal.core.monitor.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.ocs.portal.core.monitor.dto.MessageProcessTimeDto;
import com.cloud.ocs.portal.core.monitor.dto.MessageThroughputDto;
import com.cloud.ocs.portal.core.monitor.dto.RxbpsTxbpsDto;
import com.cloud.ocs.portal.core.monitor.dto.OcsVmDetail;
import com.cloud.ocs.portal.core.monitor.service.OcsVmMonitorService;

/**
 * 用户VM监控模块Controller
 * 
 * @author Wang Chao
 *
 * @date 2014-12-27 下午5:31:22
 *
 */
@Controller
@RequestMapping(value="/monitor/vm")
public class OcsVmMonitorController {
	
	@Resource
	private OcsVmMonitorService vmMonitorService;
	
	@RequestMapping(value="/listVmDetail", method=RequestMethod.GET)
	@ResponseBody
	public List<OcsVmDetail> listVmDetail(@RequestParam("hostId") String hostId) {
		return vmMonitorService.getVmDetailList(hostId);
	}
	
	@RequestMapping(value="/getCurVmCpuUsagePercentage", method=RequestMethod.GET)
	@ResponseBody
	public double getCurVmCpuUsagePercentage(@RequestParam("vmId") String vmId) {
		return vmMonitorService.getCurVmCpuUsagePercentage(vmId);
	}
	
	@RequestMapping(value="/getCurVmMemoryUsagePercentage", method=RequestMethod.GET)
	@ResponseBody
	public double getCurVmMemoryUsagePercentage(@RequestParam("vmId") String vmId) {
		return vmMonitorService.getCurMemoryUsagePercentage(vmId);
	}
	
	@RequestMapping(value="/getRxbpsTxbps", method=RequestMethod.GET)
	@ResponseBody
	public RxbpsTxbpsDto getRxbpsTxbps(@RequestParam("cityVmId") String cityVmId,
			@RequestParam("interfaceName") String interfaceName) {
		return vmMonitorService.getVmRxbpsTxbps(cityVmId, interfaceName);
	}
	
	@RequestMapping(value="/getConcurrencyRequestNum", method=RequestMethod.GET)
	@ResponseBody
	public Long getConcurrencyRequestNum(@RequestParam("cityVmId") String cityVmId) {
		return vmMonitorService.getVmConcurrencyRequestNum(cityVmId);
	}
	
	@RequestMapping(value="/getCityVmMessageThroughput", method=RequestMethod.GET)
	@ResponseBody
	public MessageThroughputDto getCityVmMessageThroughput(@RequestParam("cityVmId") String cityVmId) {
		return vmMonitorService.getMessageThroughput(cityVmId);
	}
	
	@RequestMapping(value="/getCityVmMessageProcessTime", method=RequestMethod.GET)
	@ResponseBody
	public MessageProcessTimeDto getCityVmMessageProcessTime(@RequestParam("cityVmId") String cityVmId) {
		return vmMonitorService.getCityVmMessageProcessTime(cityVmId);
	}

}
