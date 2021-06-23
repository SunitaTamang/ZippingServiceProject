package com.zip.service;

import com.zip.pojo.ZippingData;
import com.zip.validator.ZippingCodeValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Service class to process Zipping code.
 */
@Service
public class ZippingService {

    private Logger LOGGER = LoggerFactory.getLogger(ZippingService.class);

    @Autowired
    private ZippingCodeValidator zippingCodeValidator;

    /**
     * Main Method which perform zipping.
     * @param zippingDataList zippingDataList
     * @return List.
     */
    public List<ZippingData> performZipping(List<ZippingData> zippingDataList) {
        boolean isValidData = zippingCodeValidator.validateZipCode(zippingDataList);
        if (!isValidData) {
            new RuntimeException("Zipping Data List can not be empty and Lower range should be equals or less that Higher range");
        }
        LOGGER.info("Zipping Data List is Valid and started Processing");
        List<ZippingData> calculatedZipDataList = new CopyOnWriteArrayList<>();
        zippingDataList.forEach(zippingData -> {
            LOGGER.info("Processing Zip Data =>" + zippingData.toString());
            if (calculatedZipDataList.isEmpty()) {
                calculatedZipDataList.add(zippingData);
            } else {
                calculateZippingAddressRange(calculatedZipDataList, zippingData);
            }
        });
        LOGGER.info("Processed Successfully.");
        return calculatedZipDataList;
    }

    /**
     * Calculate Merging of Zip code
     * @param resultZippingData resultZippingData
     * @param zippingData zippingData.
     */
    private void calculateZippingAddressRange(List<ZippingData> resultZippingData, ZippingData zippingData) {
        Iterator it = resultZippingData.iterator();
        boolean isMerged = false;
        while (it.hasNext()) {
            ZippingData savedData = (ZippingData) it.next();
            if (withinRange(savedData, zippingData)) {
                resultZippingData.remove(savedData);
                isMerged = true;
                resultZippingData.add(processZip(savedData, zippingData));
            }
        }
        if (!isMerged) {
            resultZippingData.add(zippingData);
        }
    }

    /**
     * Merging zip code.
     * @param savedData savedData
     * @param zippingData zippingData
     * @return ZippingData.
     */
    private ZippingData processZip(ZippingData savedData, ZippingData zippingData) {
        Integer lowerRange = savedData.getLowerRangeData() < zippingData.getLowerRangeData() ? savedData.getLowerRangeData() : zippingData.getLowerRangeData();
        Integer higherRange = savedData.getHigherRangeData() > zippingData.getHigherRangeData() ? savedData.getHigherRangeData() : zippingData.getHigherRangeData();
        ZippingData newZippingData = new ZippingData();
        newZippingData.setLowerRangeData(lowerRange);
        newZippingData.setHigherRangeData(higherRange);
        return newZippingData;
    }

    private boolean withinRange(ZippingData savedData, ZippingData zippingData) {
        if(savedData.getLowerRangeData() <= zippingData.getLowerRangeData() && zippingData.getLowerRangeData() <= savedData.getHigherRangeData()) {
            return true;
        }
        if(savedData.getLowerRangeData() <= zippingData.getHigherRangeData() && zippingData.getHigherRangeData()  <= savedData.getHigherRangeData()) {
            return true;
        }
        if( zippingData.getLowerRangeData() <= savedData.getLowerRangeData() && zippingData.getHigherRangeData() >= savedData.getHigherRangeData()) {
            return true;
        }
        return false;
    }
}
