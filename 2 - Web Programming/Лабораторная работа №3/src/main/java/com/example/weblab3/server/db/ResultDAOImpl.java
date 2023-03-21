package com.example.weblab3.server.db;

import com.example.weblab3.server.Resulted;

import java.util.List;

public interface ResultDAOImpl {

    void save(Resulted result);

    void deleteAll();

    List<Resulted> getAll();
}
