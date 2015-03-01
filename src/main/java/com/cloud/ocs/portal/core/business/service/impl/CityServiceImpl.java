package com.cloud.ocs.portal.core.business.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.ocs.portal.common.bean.City;
import com.cloud.ocs.portal.common.dao.CityDao;
import com.cloud.ocs.portal.core.business.constant.CityState;
import com.cloud.ocs.portal.core.business.dto.AddCityDto;
import com.cloud.ocs.portal.core.business.service.CityService;

/**
 * 城市Service实现类
 * 
 * @author Wang Chao
 * 
 * @date 2014-12-29 下午7:28:49
 * 
 */
@Transactional(value="portal_em")
@Service
public class CityServiceImpl implements CityService {

	@Resource
	private CityDao cityDao;

	@Override
	public AddCityDto addCity(City city) {
		AddCityDto result = new AddCityDto();

		Date created = new Date();
		city.setCreated(new Timestamp(created.getTime()));
		city.setState(CityState.NO_SERVICE);
		cityDao.persist(city);

		if (city.getId() != null) {
			result.setCode(AddCityDto.ADD_CITY_CODE_SUCCESS);
			result.setMessage("Add City Success.");
			result.setCity(city);
			result.setIndex(cityDao.findAll().size());
		} else {
			result.setCode(AddCityDto.ADD_CITY_CODE_ERROR);
			result.setMessage("Add City Failed by DB Operation.");
		}

		return result;
	}

	@Override
	public List<City> getCitiesList() {
		return cityDao.findAll();
	}

}
