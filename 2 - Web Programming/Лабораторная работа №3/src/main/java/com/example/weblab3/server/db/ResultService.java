package com.example.weblab3.server.db;

import com.example.weblab3.server.Resulted;

import java.util.List;

public class ResultService {

    private ResultDAOImpl resultDAO = new ResultDAO();

    public ResultService() {
    }


    public void saveResult(Resulted result) {
        resultDAO.save(result);
    }

    public void deleteAllResult() {
        resultDAO.deleteAll();
    }

    public List<Resulted> getAllResult() {
        return resultDAO.getAll();
    }
}
