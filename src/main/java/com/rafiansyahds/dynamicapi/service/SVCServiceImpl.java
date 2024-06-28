package com.rafiansyahds.dynamicapi.service;

import java.sql.SQLException;
import java.util.List;

import com.rafiansyahds.dynamicapi.models.SVCData;
import com.rafiansyahds.dynamicapi.models.SVCRest;
import com.rafiansyahds.dynamicapi.repository.SVCRepo;
import com.rafiansyahds.dynamicapi.service.interfaces.SVCService;

public class SVCServiceImpl implements SVCService{
    private final SVCRepo repo;

    public SVCServiceImpl(){
        this.repo = new SVCRepo();
    }

    @Override
    public void createDynamicApi(SVCRest data) throws SQLException {
        String query = "INSERT INTO svc_rest(ID, NM, HTTP_METHOD, HTTP_URI, IS_ACTIVE, CREATED_DT, UPDATED_DT) VALUES((?), (?), (?), (?), (?), (?), (?))";
        repo.createApi(query, data);
    }

    @Override
    public List<SVCRest> getAllDynamicApi() {
        String query = "SELECT * FROM svc_rest";
        return repo.getApiPath(query);
    }

    @Override
    public List<SVCRest> getDynamicApiById(String id){
        String query = "SELECT * FROM svc_rest WHERE ID = \""+ id + "\"";
        return repo.getApiPath(query);
    }

    @Override
    public void updateDynamicApi(SVCRest data) throws SQLException{
        String query = "UPDATE svc_rest SET NM=(?), HTTP_METHOD=(?), HTTP_URI=(?), IS_ACTIVE=(?), CREATED_DT=(?), UPDATED_DT=(?) where ID=(?)";
        repo.updateApi(query, data);
    }

    @Override
    public void deleteDynamicApi(String id) throws SQLException {
        String query = "DELETE FROM svc_rest WHERE ID=(?)";
        repo.deleteApi(query, id);
    }

    @Override
    public List<SVCRest> getDynamicApi(String method, String uri) {
        String query = "SELECT * FROM svc_rest WHERE HTTP_URI=(?) AND HTTP_METHOD=(?)";
        return repo.getApiPath(query, uri, method);
    }

    @Override
    public void createApiData(SVCData data) throws SQLException {
       String query = "INSERT INTO svc_rest_data(ID, DATA) VALUES((?),(?))";
        repo.createApiData(query, data);
    }

    @Override
    public List<SVCData> getAllApiData() {
        String query = "SELECT * FROM svc_rest_data";
        return repo.getAPIdata(query);
    }

    @Override
    public void updateApiData(SVCData data) throws SQLException {
        String query = "UPDATE svc_rest_data SET DATA=(?) WHERE ID=(?)";
        repo.updateApiData(query, data);
    }

    @Override
    public void deleteApiData(String id) throws SQLException {
        String query = "DELETE FROM svc_rest_data WHERE ID=(?)";
        repo.deleteApiData(query, id);
    }

    @Override
    public List<SVCData> getApiData(String id) {
        String query = "SELECT * FROM svc_rest_data sd JOIN svc_rest s ON s.ID = sd.ID where sd.ID = \'" + id + "\'";
        return repo.getAPIdata(query);
    }
}
