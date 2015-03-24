package com.cloud.ocs.portal.core.infrastructure.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.ocs.portal.common.dto.OperateObjectDto;
import com.cloud.ocs.portal.core.infrastructure.dto.AddHostDto;
import com.cloud.ocs.portal.core.infrastructure.dto.ClusterDto;
import com.cloud.ocs.portal.core.infrastructure.dto.HostDto;
import com.cloud.ocs.portal.core.infrastructure.dto.NetworkOfferingDto;
import com.cloud.ocs.portal.core.infrastructure.dto.PodDto;
import com.cloud.ocs.portal.core.infrastructure.dto.PrimaryStorageDto;
import com.cloud.ocs.portal.core.infrastructure.dto.SecondaryStorageDto;
import com.cloud.ocs.portal.core.infrastructure.dto.ServiceOfferingDto;
import com.cloud.ocs.portal.core.infrastructure.dto.SystemVmDto;
import com.cloud.ocs.portal.core.infrastructure.dto.TemplateDto;
import com.cloud.ocs.portal.core.infrastructure.dto.ZoneDto;
import com.cloud.ocs.portal.core.infrastructure.service.InfrastructureService;

/**
 * 系统资源基础设施模块Controller
 * 
 * @author Wang Chao
 *
 * @date 2014-12-11 上午11:29:31
 *
 */
@Controller
@RequestMapping(value="/resource/infrastructure")
public class InfrastructureController {
	
	@Resource
	private InfrastructureService infrastructureService;

	@RequestMapping(value="/listZones", method=RequestMethod.GET)
	@ResponseBody
	public List<ZoneDto> listZones() {
		return infrastructureService.getZonesList();
	}
	
	@RequestMapping(value="/listPods", method=RequestMethod.GET)
	@ResponseBody
	public List<PodDto> listPods(@RequestParam("zoneId") String zoneId) {
		return infrastructureService.getPodsList(zoneId);
	}
	
	@RequestMapping(value="/listSecondaryStorage", method=RequestMethod.GET)
	@ResponseBody
	public List<SecondaryStorageDto> listSecondaryStorage(@RequestParam("zoneId") String zoneId) {
		return infrastructureService.getSecondaryStorageList(zoneId);
	}
	
	@RequestMapping(value="/listSystemVms", method=RequestMethod.GET)
	@ResponseBody
	public List<SystemVmDto> listSystemVms(@RequestParam("zoneId") String zoneId) {
		return infrastructureService.getSystemVmsList(zoneId);
	}
	
	@RequestMapping(value="/listClusters", method=RequestMethod.GET)
	@ResponseBody
	public List<ClusterDto> listClusters(@RequestParam("podId") String podId) {
		return infrastructureService.getClustersList(podId);
	}
	
	@RequestMapping(value="/listPrimaryStorage", method=RequestMethod.GET)
	@ResponseBody
	public List<PrimaryStorageDto> listPrimaryStorage(@RequestParam("clusterId") String clusterId) {
		return infrastructureService.getPrimaryStorageList(clusterId);
	}
	
	@RequestMapping(value="/listHosts", method=RequestMethod.GET)
	@ResponseBody
	public List<HostDto> listHosts(@RequestParam("clusterId") String clusterId) {
		return infrastructureService.getHostsList(clusterId);
	}
	
	@RequestMapping(value="/listIsolatedNetworkOfferingsWithSourceNatService", method=RequestMethod.GET)
	@ResponseBody
	public List<NetworkOfferingDto> listIsolatedNetworkOfferingsWithSourceNatService() {
		return infrastructureService.getIsolatedNetworkOfferingsWithSourceNatServiceList();
	}
	
	@RequestMapping(value="/listSelfExecutableTemplates", method=RequestMethod.GET)
	@ResponseBody
	public List<TemplateDto> listTemplates() {
		return infrastructureService.getSelfExecutableTemplates();
	}
	
	@RequestMapping(value="/listNonSystemServiceOffering", method=RequestMethod.GET)
	@ResponseBody
	public List<ServiceOfferingDto> listNonSystemServiceOffering() {
		return infrastructureService.getNonSystemServiceOfferingList();
	}
	
	@RequestMapping(value="/addHost", method=RequestMethod.POST)
	@ResponseBody
	public AddHostDto addHost(@RequestParam("zoneId") String zoneId,
			@RequestParam("podId") String podId,
			@RequestParam("clusterId") String clusterId,
			@RequestParam("ipAddress") String ipAddress,
			@RequestParam("hostAccount") String hostAccount,
			@RequestParam("hostPassword") String hostPassword) {
		return infrastructureService.addHost(zoneId, podId, clusterId, ipAddress, hostAccount, hostPassword);
	}
	
	public OperateObjectDto removeHost(String hostId) {
		return null;
	}
	
	public OperateObjectDto addPrimaryStorage(String a, String b, String c) {
		return null;
	}
	
	public OperateObjectDto removePrimaryStorage(String id) {
		return null;
	}
	
	public OperateObjectDto addSecondaryStorage(String a, String b, String c) {
		return null;
	}
	
	public OperateObjectDto removeSecondaryStorage(String id) {
		return null;
	}
}
