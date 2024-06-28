package com.rafiansyahds.dynamicapi.service.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.rafiansyahds.dynamicapi.models.SVCData;
import com.rafiansyahds.dynamicapi.models.SVCRest;

public interface SVCService {
    public void createDynamicApi(SVCRest data) throws SQLException;
    public List<SVCRest> getAllDynamicApi();
    public  List<SVCRest> getDynamicApiById(String id);
    public void updateDynamicApi(SVCRest data) throws SQLException;
    public void deleteDynamicApi(String id)throws SQLException;
    public List<SVCRest> getDynamicApi(String method, String uri);

    public void createApiData(SVCData data) throws SQLException;
    public List<SVCData> getAllApiData();
    public void updateApiData(SVCData data) throws SQLException;
    public void deleteApiData(String id)throws SQLException;
    public List<SVCData> getApiData(String id);
}
