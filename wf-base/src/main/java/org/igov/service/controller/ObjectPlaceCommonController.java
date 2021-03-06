package org.igov.service.controller;

import org.igov.service.business.object.ObjectPlaceCommonService;
import org.igov.service.exception.CommonServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@Api(tags = { "ObjectPlaceController -- Обьекты мест (населенных пунктов и регионов) и стран" })
@RequestMapping(value = "/object/place/sub")
public class ObjectPlaceCommonController {
    private static final Logger LOG = LoggerFactory.getLogger(ObjectPlaceCommonController.class);

    private static final String JSON_TYPE = "Accept=application/json";
    
    @Autowired
    ObjectPlaceCommonService objectPlaceCommonService;

    @ApiOperation(value = "Получение списка улиц", notes = "Полученаем список улиц. " + "Пример:\n")
    @RequestMapping(value = "/PB/getSubPlaces_", method = RequestMethod.GET, headers = { JSON_TYPE })
    public @ResponseBody String getSubPlaces_(
	    @ApiParam(value = "sID_SubPlace_PB - код узла адреса)", required = true) @RequestParam(value = "sID_SubPlace_PB", required = true) String sID_SubPlace_PB,
	    @ApiParam(value = "sFind - строка поиска (ищет по вхождению текста в название улицы)", required = false) @RequestParam(value = "sFind", required = false) String sFind)
	    throws CommonServiceException {

	LOG.debug("sID_SubPlace_PB={}, sFind={}", sID_SubPlace_PB, sFind);

	return objectPlaceCommonService.getSubPlaces_(sID_SubPlace_PB, sFind);
    }

}
