package com.zip.validator;

import com.zip.pojo.ZippingData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Component
public class ZippingCodeValidator {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * Method to validate Zipping code Data.
     * @param zippingDataList zippingDataList
     * @return Boolean.
     */
   public boolean validateZipCode(List<ZippingData> zippingDataList) {
     LOGGER.info("Performing validation for zipping data");
     for(ZippingData zippingData: zippingDataList) {
         if(!validateZippingData(zippingData)){
             return false;
         }
     }
     return true;
   }

    private boolean validateZippingData(ZippingData zippingData) {
        LOGGER.info("Validating Zipping data =>" + zippingData.toString());
        if (ObjectUtils.isEmpty(zippingData) || ObjectUtils.isEmpty(zippingData.getLowerRangeData())
                || ObjectUtils.isEmpty(zippingData.getHigherRangeData())) {
            LOGGER.error("Zip code Data is not available");
            return false;
        }
        if (zippingData.getLowerRangeData() > zippingData.getHigherRangeData()) {
            LOGGER.error("Lower Range should be equal or less than higher range");
            return false;
        }
        return true;
    }
}
