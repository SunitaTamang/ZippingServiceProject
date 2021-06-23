package com.zip.api;

import com.zip.pojo.ZippingData;
import com.zip.service.ZippingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ZippingController {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ZippingService zippingService;

    /**
     * API which take zip code data and calculate the range of zipcode data.
     * @param zippingDataList zippingDataList.
     * @return  ResponseEntity<List<ZippingData>>.
     */
    @PostMapping
    public ResponseEntity<List<ZippingData>> getShippingCode(@RequestBody List<ZippingData> zippingDataList) {
        LOGGER.info("API to process Zipping =>"+ zippingDataList.toString());
        return new ResponseEntity(zippingService.performZipping(zippingDataList), HttpStatus.OK);
    }
}
