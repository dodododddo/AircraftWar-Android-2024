package com.example.aircraftwar2024.dao;

import com.example.aircraftwar2024.record.Record;

import java.util.List;


public interface RecordDAO {
    void insert(Record record);
    void delete(int idx);
    List<Record> getAll();
}
